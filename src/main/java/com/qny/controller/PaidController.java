
package com.qny.controller;


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
import com.qny.pojo.QueryPaid;
import com.qny.pojo.QueryVo;
import com.qny.pojo.User;
import com.qny.pojo.Userlist;
import com.qny.service.ItemService;
import com.qny.service.PaidService;
import com.qny.service.UserlistService;;

@Controller
@RequestMapping("/paid")
public class PaidController {
	@Autowired
	private PaidService paidService;
	@Autowired
	private UserlistService userlistService;
	@Autowired
	private ItemService itemService;

	// 管理员查找所有已缴租金列表
	@RequestMapping("/selectall")
	public String selectall(Model model, QueryVo vo, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Paid> list = paidService.selectall(vo);
		PageInfo<Paid> p = new PageInfo<Paid>(list);
		Double sum = paidService.selectsum(vo);
		model.addAttribute("paid", list);
		model.addAttribute("sum", sum);
		model.addAttribute("p", p);
		model.addAttribute("mainPage", "paid.jsp");
		model.addAttribute("vo", vo);
		return "admin/main1";
	}

	//qny
	// 租客查看自己已缴租金列表
	@RequestMapping("/findmypaid")
	public String findmypaid(HttpSession httpSession, Model model,QueryVo vo,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		User user1 = (User) httpSession.getAttribute("user");
		vo.setUserId(user1.getId());
		PageHelper.startPage(page, pageSize);
		List<QueryPaid> queryPaids = paidService.getPaidListByTime(vo);
		PageInfo<QueryPaid> p = new PageInfo<QueryPaid>(queryPaids);
		model.addAttribute("queryPaids", queryPaids);
		Integer sumPrice = paidService.getAllPriceByUserId(vo,user1.getId());
		model.addAttribute("sum", sumPrice);
		model.addAttribute("p", p);
		model.addAttribute("vo", vo);
		return "user/mypaid";
	}

	/**
	* @author:  qny
	* @methodsName: deletePaid
	* @description: admin删除一条已缴纳的租金信息
	* @param:  payId: 要删除的租金信息的id
	* @return: String: 重定向到admin模块下展示所有已缴纳租金信息的action
	* @throws: 
	*/
	@RequestMapping("/deletepaid")
	public String deletePaid(Integer payId) {
		paidService.deletepaid(payId);
		return "redirect:getAllPaidRent.action";
	}

	//qny
	//zuke删除已缴租金记录
	@RequestMapping("/zukedeletepaid")
	public String zukedeletepaid(Integer id) {
		paidService.deletepaid(id);
		return "redirect:findmypaid.action";
	}
	
	//qny
	//用户删除租金收入记录
	@RequestMapping("/deleteIncomeMesByUser")
	public String deleteIncomeMesByUser(Integer id) {
		paidService.deletepaid(id);
		return "redirect:incomeByUser.action";
	}	

	/**
	* @author:  qny
	* @methodsName: goToPay
	* @description: user申请租赁某件物品,系统查询数据库形成订单提供给user确认
	* @param:  itemId: 要租赁的物品id
	* @return: String: 前往user模块下确认订单的jsp页面
	* @throws: 
	*/
	@RequestMapping("/gotopay")
	public String goToPay(Integer itemId, Model model,HttpSession httpSession) {
		//得到用户信息
		User user1 = (User)httpSession.getAttribute("user");
		Item item = itemService.findId(itemId);
		Apply apply = new Apply();
		//得到物品主人信息
		Userlist userlist = userlistService.findhasuserlist(item.getMasterId());
		//填充申请信息
		apply.setItemId(item.getItemId().toString());
		apply.setItemName(item.getName());
		apply.setPrice(item.getPrice());
		apply.setStatus("已申请");
		apply.setUserId(user1.getId());
		apply.setItemNumber(item.getItemNumber());
		model.addAttribute("apply",apply);
		model.addAttribute("Chuzu",userlist.getName());
		model.addAttribute("Zuke",user1.getUsername());
		return "user/checkorder";
	}
	
	//qny
	//租客查看退回的租金
	@RequestMapping("/getMyPaidBack")
	public String getMyPaidBack(HttpSession httpSession,Model model,QueryVo vo,
			@RequestParam(required = false,defaultValue="1") Integer page,
			@RequestParam(required = false,defaultValue="2") Integer pageSize){
		User user = (User) httpSession.getAttribute("user");
		vo.setUserId(user.getId());
		PageHelper.startPage(page, pageSize);
		List<QueryPaid> paids = paidService.getPaidBackListByQueryVo(vo);
		PageInfo<QueryPaid> p = new PageInfo<>(paids);
		Integer sumBackPrice = paidService.getAllBackPriceByUserId(vo,user.getId());
		model.addAttribute("paids",paids);
		model.addAttribute("p",p);		
		model.addAttribute("sum",sumBackPrice);
		model.addAttribute("vo",vo);
		return "user/mypaidback";
	}
	
	//qny
	//租客删除退回的租金信息
	@RequestMapping("/deletePaidBack")
	public String deletePaidBackById(Integer id){
		paidService.deletePaidBackById(id);
		return"redirect:getMyPaidBack.action";
	}
	
	//qny
	//用户查看自己的租金收入
	@RequestMapping("/incomeByUser")
	public String incomeByUserId(QueryVo vo,Model model,HttpSession httpSession,
			@RequestParam(required = false,defaultValue = "1") Integer page,
			@RequestParam(required = false,defaultValue = "2") Integer pageSize){
		
		User user = (User)httpSession.getAttribute("user");
		vo.setUserId(user.getId());
		//1.查询用户的租金收入信息
		PageHelper.startPage(page, pageSize);
		List<QueryPaid> paids = paidService.getIncomeByUserId(vo);
		PageInfo<QueryPaid> p = new PageInfo<>(paids);
		//2.查询用户总的租金收入金额
		Integer totalIncome = paidService.getTotalIncome(vo,user.getId());
		model.addAttribute("totalIncome",totalIncome);
		model.addAttribute("paids",paids);
		model.addAttribute("p",p);
		model.addAttribute("vo",vo);
		return"user/income";
	}

	/**
	* @author:  qny
	* @methodsName: getAllPaidRent
	* @description: admin查看所有的已缴纳租金信息
	* @param:  page: 展示第几页的数据；pageSize: 每页展示多少条数据
	* @param:  vo: 用来封装前端传来的查询字段,以便进行条件查询
	* @return: String: 前往admin模块下展示所有已缴纳租金信息的jsp页面
	* @throws: 
	*/
	@RequestMapping("getAllPaidRent")
	public String getAllPaidRent(Model model,QueryVo vo,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize){
		//开启分页
		PageHelper.startPage(page, pageSize);
		//得到已缴纳的租金列表
		List<QueryPaid> queryPaids = paidService.getAllPaid(vo);
		PageInfo<QueryPaid> p = new PageInfo<QueryPaid>(queryPaids);
		//得到已缴纳的租金金额
		Integer sumPrice = paidService.getAllPaidRent(vo);
		model.addAttribute("queryPaids", queryPaids);
		model.addAttribute("sum", sumPrice);
		model.addAttribute("p", p);
		model.addAttribute("vo", vo);
		return"admin/paid";
	}

	/**
	* @author:  qny
	* @methodsName: getAllPaidBackRent
	* @description: admin查看所有退回的租金信息
	* @param:  page: 展示第几页的数据；pageSize: 每页展示多少条数据
	* @param:  vo: 用来封装前端传来的查询字段,以便进行条件查询
	* @return: String: 前往admin模块下展示所有已退回租金信息的jsp页面
	* @throws: 
	*/
	@RequestMapping("getAllPaidBackRent")
	public String getAllPaidBackRent(Model model,QueryVo vo,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize){
		//开启分页
		PageHelper.startPage(page, pageSize);
		//得到已退回的租金列表
		List<QueryPaid> queryPaids = paidService.getAllPaidBack(vo);
		PageInfo<QueryPaid> p = new PageInfo<QueryPaid>(queryPaids);
		//得到已退回的租金金额
		Integer sumPrice = paidService.getAllPaidBackRent(vo);
		model.addAttribute("queryPaids", queryPaids);
		model.addAttribute("sum", sumPrice);
		model.addAttribute("p", p);
		model.addAttribute("vo", vo);
		return"admin/payback";
	}

	/**
	* @author:  qny
	* @methodsName: deletePayBack
	* @description: admin删除一条退回的租金信息
	* @param:  payId: 要删除的退回租金信息的id
	* @return: String: 重定向到admin模块下展示所有已退回租金信息的action
	* @throws: 
	*/
	@RequestMapping("/deletePayBack")
	public String deletePayBack(Integer payId) {
		paidService.deletePayBack(payId);
		return "redirect:getAllPaidBackRent.action";
	}
}
