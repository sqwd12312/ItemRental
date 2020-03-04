package com.qst.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.pojo.Checkout;
import com.qst.pojo.User;
import com.qst.pojo.Userlist;
import com.qst.service.CheckoutService;
import com.qst.service.UserlistService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
	@Autowired
	private CheckoutService checkoutService;
	@Autowired
	private UserlistService userlistService;


	/**
	* @author:  qny
	* @methodsName: getAllCheckOut
	* @description: admin查看所有的已退租信息
	* @param:  page: 展示第几页的数据；pageSize: 每页展示多少条数据
	* @return: String: 前往展示所有已退租信息的jsp页面
	* @throws: 
	*/
	@RequestMapping("/getallcheckout")
	public String getAllCheckOut(Model model, 
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		//查询所有已退租信息
		List<Checkout> checkoutList = checkoutService.getallcheckout();
		PageInfo<Checkout> p = new PageInfo<Checkout>(checkoutList);
		model.addAttribute("p", p);
		model.addAttribute("checkoutList", checkoutList);
		model.addAttribute("mainPage", "checkout.jsp");
		return "admin/main1";
	}

	//qny
	//用户查看自己物品的已退租列表
	@RequestMapping("/getAllCheckoutByUser")
	public String getAllCheckoutByUser(HttpSession httpSession,Model model, 
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		User user = (User) httpSession.getAttribute("user");
		PageHelper.startPage(page, pageSize);
		List<Checkout> checkoutList = checkoutService.getAllCheckoutByUser(user.getId());
		PageInfo<Checkout> p = new PageInfo<Checkout>(checkoutList);
		model.addAttribute("p", p);
		model.addAttribute("checkoutList", checkoutList);
		model.addAttribute("mainPage", "checkout.jsp");
		return "user/main";
	}

	// 租客删除自己已退租列表
	@RequestMapping("/deletecheckout")
	public String deletecheckout(Integer cId) {
		checkoutService.deletecheckout(cId);
		return "redirect:/checkout/getMyCheckout.action";
	}

	
	/**
	* @author:  qny
	* @methodsName: adminDeleteCheckOut
	* @description: admin删除一条已退租信息
	* @param:  cId: 已退租信息的id
	* @return: String: 重定向到admin查看所有退租信息的action
	* @throws: 
	*/
	@RequestMapping("/admindeletecheckout")
	public String adminDeleteCheckOut(Integer cId) {
		checkoutService.deletecheckout(cId);
		return "redirect:/checkout/getallcheckout.action";
	}
	
	//qny
	//用户根据已退租的信息编号删除已退租信息
	@RequestMapping("/deleteCheckoutByUser")
	public String deleteCheckoutByUser(Integer cId) {
		checkoutService.deletecheckout(cId);
		return "redirect:/checkout/getAllCheckoutByUser.action";
	}	

	//qny
	//租客查看已退租物品
	@RequestMapping("/getMyCheckout")
	public String getmycheckout(Model model, HttpSession httpSession,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		User user1 = (User) httpSession.getAttribute("user");
		PageHelper.startPage(page, pageSize);
		//根据租客的userListId查询租客的已退租商品
		List<Checkout> checkoutList = userlistService.getMyCheckout(user1.getId());
		PageInfo<Checkout> p = new PageInfo<Checkout>(checkoutList);
		model.addAttribute("zukeName",user1.getUsername());
		model.addAttribute("p", p);
		model.addAttribute("checkoutList", checkoutList);
		model.addAttribute("mainPage", "mycheckout.jsp");
		return "user/main";
	}
}
