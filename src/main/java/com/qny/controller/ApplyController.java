package com.qny.controller;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qny.pojo.Apply;
import com.qny.pojo.Item;
import com.qny.pojo.Paid;
import com.qny.pojo.User;
import com.qny.pojo.Userlist;
import com.qny.service.ApplyService;
import com.qny.service.ItemService;
import com.qny.service.PaidService;
import com.qny.service.UserlistService;

@Controller
public class ApplyController {
	@Autowired
	private UserlistService userlistService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ApplyService applyService;
	@Autowired
	private PaidService paidService;

	
	/**
	* @author:  qny
	* @methodsName: InsertItemApply
	* @description: 更改被租赁物品状态为“已被申请”,进行余额转移,插入一条租赁申请
	* @param:  apply: 封装了申请租赁一个物品需要的信息
	* @return: String: 重定向到user模块下展示该用户全部租赁申请的action
	* @throws: 
	*/
	@RequestMapping("/InsertItemApply")
	public String InsertItemApply(HttpSession httpSession, Model model, Apply apply) {
		User user1 = (User) httpSession.getAttribute("user");
		Integer user_id = user1.getId();
		Userlist list = userlistService.findhasuserlist(user_id);
		
		
		
		if (list == null) {
			model.addAttribute("info", "applycheck");
			return "redirect:item.action";
		} else {
			//1.查询租客的账户余额
			double balance = list.getBalance();
			double balance2 = apply.getTotalPrice();
			//2.判断余额是否足够支付租金
			if (balance-balance2<0) {
				model.addAttribute("info", "抱歉,您的余额为"+balance+",不足以支付订单");
				model.addAttribute("apply",apply);
				model.addAttribute("Chuzu",apply.getChuZu());
				model.addAttribute("Zuke",apply.getZuKe());
				return "user/checkorderback";
			}
			//计算相差天数
			int days = daysxiangcha(apply.getFromDate(),apply.getToDate())+1;
			
			double price = apply.getPrice()*days;
		    if (price!=apply.getTotalPrice()) {
				model.addAttribute("info", "提交失败！需交租金为"+price+",元");
				model.addAttribute("apply",apply);
				model.addAttribute("Chuzu",apply.getChuZu());
				model.addAttribute("Zuke",apply.getZuKe());
				return "user/checkorderback";
			}
			else{
				Item item = itemService.findId(Integer.parseInt(apply.getItemId()));
				//3.更新商品状态
				item.setStatus("已被申请");
				itemService.updateItemStatus(item);				
				//4.插入支付表,租客提交申请时默认支付租金
				Paid paid = new Paid();
				Date dt = new Date();
				SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
				String paydate = matter1.format(dt);
				paid.setPaydate(paydate);
				paid.setItemNumber(item.getItemNumber());
				paid.setItemName(item.getName());
				paid.setPrice(apply.getTotalPrice());
				paid.setStatus("已缴纳");
				paid.setUserId(user1.getId());
				paid.setUserName(user1.getUsername());
				int masterId = itemService.getMasterIdByItemId(item.getItemId());
				paid.setMasterId(masterId);
				//这里返回了主键id，也就是orderNumber
				paidService.insertpaid(paid);
				//5.减少租客的账户余额
				list.setBalance(balance-balance2);
				userlistService.updateuserlist(list);
				//6.增加出租人账户余额
				Userlist chuZu = userlistService.findhasuserlist(item.getMasterId());
				chuZu.setBalance(chuZu.getBalance()+balance2);
				userlistService.updateuserlist(chuZu);
				//填充申请信息
				apply.setStatus("申请中");
				//将返回的orderNumber写入apply
				apply.setOrderNumber(paid.getId());
				apply.setUserId(list.getUser_id());
				//7.插入申请表
				applyService.insertapply(apply);
				model.addAttribute("info", "applysuccess");
				return "redirect:getmyapply.action";
			}
			
		}
	}
	
	/**
	* @author:  qny
	* @methodsName: findApplyList
	* @description: admin查看所有租赁申请信息
	* @param:  page: 展示第几页的数据；pageSize: 每页展示多少条数据
	* @return: String: 前往admin模块下展示所有租赁申请信息的jsp页面
	* @throws: 
	*/
	@RequestMapping("/findapplylist")
	public String findApplyList(Model model, 
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "8") Integer pageSize) throws Exception {
		PageHelper.startPage(page, pageSize);
		List<Userlist> applylist = applyService.findapplylist();
		PageInfo<Userlist> p = new PageInfo<Userlist>(applylist);
		model.addAttribute("applylist", applylist);
		model.addAttribute("p", p);
		return "admin/applylist";
	}
	
	//qny
	//用户查看自己物品的被申请租赁列表
	@RequestMapping("/getMyApply")
	public String getMyApply(HttpSession httpSession,Model model, 
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "8") Integer pageSize) throws Exception {
		PageHelper.startPage(page, pageSize);

		User zuke = (User) httpSession.getAttribute("user");
		List<Userlist> applylist = applyService.getMyApply(zuke.getId());
		PageInfo<Userlist> p = new PageInfo<Userlist>(applylist);
		model.addAttribute("applylist", applylist);
		model.addAttribute("p", p);
		return "user/myapplylist";
	}	


	//qny
	//管理员拒绝租赁申请
	@RequestMapping("/refuseapply")
	public String refuseapply(Integer itemId,Integer id, Model model) {
		Item item = new Item();
		item.setItemId(itemId);
		applyService.refuseapply(item,id);
		return "redirect:findapplylist.action";
	}
	
	//qny
	//用户拒绝租赁申请
	@RequestMapping("/refuseApplyByZuke")
	public String refuseApplyByZuke(Integer itemId,Integer id, Model model) {
		Item item = new Item();
		item.setItemId(itemId);
		applyService.refuseapply(item,id);
		return "redirect:getMyApply.action";
	}
	
	

	//qny
	// 租客查看自己的租赁申请
	@RequestMapping("/getmyapply")
	public String getmyapply(Model model, HttpSession httpSession,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "8") Integer pageSize) {
		User user1 = (User) httpSession.getAttribute("user");
		PageHelper.startPage(page, pageSize);
		//2.根据userListId查询租客所有申请
		List<Userlist> list = userlistService.getmyapply(user1.getId());
		PageInfo<Userlist> p = new PageInfo<Userlist>(list);
		model.addAttribute("userlist", list);
		model.addAttribute("p", p);
		return "user/myapply";
	}
	
	//qny
	//根据申请id删除申请记录
	@RequestMapping("deleteApplyByUser")
	public String deleteApplyById(Model model,Integer id){
		applyService.deleteApplyById(id);
		model.addAttribute("mainPage", "myapply.jsp");
		return "redirect:getmyapply.action";
	}
	
	//计算时间差的方法
	public static int daysxiangcha(String dateStr1, String dateStr2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		try{
		dateStr1 = sdf.format(sdf2.parse(dateStr1));
		dateStr2 = sdf.format(sdf2.parse(dateStr2));
		} catch (ParseException e) {
		e.printStackTrace();
		}
		int year1 = Integer.parseInt(dateStr1.substring(0, 4));
		int month1 = Integer.parseInt(dateStr1.substring(4, 6));
		int day1 = Integer.parseInt(dateStr1.substring(6, 8));
		int year2 = Integer.parseInt(dateStr2.substring(0, 4));
		int month2 = Integer.parseInt(dateStr2.substring(4, 6));
		int day2 = Integer.parseInt(dateStr2.substring(6, 8));
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, year1);
		c1.set(Calendar.MONTH, month1 - 1);
		c1.set(Calendar.DAY_OF_MONTH, day1);
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, year2);
		c2.set(Calendar.MONTH, month2 - 1);
		c2.set(Calendar.DAY_OF_MONTH, day2);
		long mills = c1.getTimeInMillis() > c2.getTimeInMillis()
		? c1.getTimeInMillis() - c2.getTimeInMillis()
		: c2.getTimeInMillis() - c1.getTimeInMillis();
		return (int) (mills / 1000 / 3600 / 24);
	}
		
}
