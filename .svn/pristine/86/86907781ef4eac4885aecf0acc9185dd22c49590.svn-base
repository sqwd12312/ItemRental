package com.qst.controller;

import java.text.SimpleDateFormat;
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
import com.qst.pojo.Apply;
import com.qst.pojo.Item;
import com.qst.pojo.Paid;
import com.qst.pojo.User;
import com.qst.pojo.Userlist;
import com.qst.service.ApplyService;
import com.qst.service.ItemService;
import com.qst.service.PaidService;
import com.qst.service.UserlistService;

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
	//qny
	//写入物品租赁申请
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
				model.addAttribute("mainPage","checkorder.jsp");
				return "user/main";
			}else{
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
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) throws Exception {
		PageHelper.startPage(page, pageSize);
		List<Userlist> applylist = applyService.findapplylist();
		PageInfo<Userlist> p = new PageInfo<Userlist>(applylist);
		model.addAttribute("applylist", applylist);
		model.addAttribute("p", p);
		model.addAttribute("mainPage", "applylist.jsp");
		return "admin/main1";
	}
	
	//qny
	//用户查看自己物品的被申请租赁列表
	@RequestMapping("/getMyApply")
	public String getMyApply(HttpSession httpSession,Model model, 
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) throws Exception {
		PageHelper.startPage(page, pageSize);

		User zuke = (User) httpSession.getAttribute("user");
		List<Userlist> applylist = applyService.getMyApply(zuke.getId());
		PageInfo<Userlist> p = new PageInfo<Userlist>(applylist);
		model.addAttribute("applylist", applylist);
		model.addAttribute("p", p);
		model.addAttribute("mainPage", "myapplylist.jsp");
		return "user/main";
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
			@RequestParam(required = false, defaultValue = "3") Integer pageSize) {
		User user1 = (User) httpSession.getAttribute("user");
		PageHelper.startPage(page, pageSize);
		//2.根据userListId查询租客所有申请
		List<Userlist> list = userlistService.getmyapply(user1.getId());
		PageInfo<Userlist> p = new PageInfo<Userlist>(list);
		model.addAttribute("userlist", list);
		model.addAttribute("p", p);
		model.addAttribute("mainPage", "myapply.jsp");
		return "user/main";
	}
	
	//qny
	//根据申请id删除申请记录
	@RequestMapping("deleteApplyByUser")
	public String deleteApplyById(Model model,Integer id){
		applyService.deleteApplyById(id);
		model.addAttribute("mainPage", "myapply.jsp");
		return "redirect:getmyapply.action";
	}
}
