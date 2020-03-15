package com.qny.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qny.pojo.User;
import com.qny.pojo.Userlist;
import com.qny.service.UserService;
import com.qny.service.UserlistService;

@Controller

public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserlistService userListService;

	@RequestMapping("/login")
	public String userList() throws Exception {

		return "login";

	}
	@RequestMapping("/register")
	public String  register(User user,Userlist userlist,Model model) throws Exception {
		
		//1.将用户插入用户表
		//这里默认只能新增普通用户，不能新增管理员
		user.setType("user");
		//sql查询语句中设置了返回自增id，用来插入用户昵称表
		userService.insertUser(user);
		//2.将用户插入昵称表
		//默认新注册用户余额为0
		userlist.setBalance(0.00);		
		userlist.setName(user.getUsername());
		//设置userList表的用户id为刚注册的用户id
		userlist.setUser_id(user.getId());
		userListService.insertuserlist(userlist);
		model.addAttribute("error", "success");
		return "login";

	}


	//qny
	//用户登录验证
	@RequestMapping("/logincheck")
	public String login(User user, Model model, HttpSession httpSession) throws Exception {

		
		User user1 = userService.login(user);

		if (user1 != null) {
			
			httpSession.setAttribute("user", user1);
			if (user1.getType().equals("user")) {
				return "user/main";
			} else {
				return "admin/main1";
			}
		} else {
			String error = "error";
			model.addAttribute("error", error);
			return "login";
		}
	}

	@RequestMapping("/toindex")
	public String toindex(Model model) throws Exception {

		return "admin/index";
	}
	
	/**
	* @author:  qny
	* @methodsName: updatePwd
	* @description: 更新用户密码
	* @param:  
	* @return: String: 重定向到user模块下展示该用户全部租赁申请的action
	* @throws: 
	*/
	@RequestMapping("updatePwd")
	public String updatePwd(Model model,String OriginalPwd,String NewPwd,
			                String ConfirmPwd,HttpSession httpSession){
		//1.判断新密码与确认密码是否相同
		if (!NewPwd.equals(ConfirmPwd)) {
			model.addAttribute("info", "新密码与确认密码不一致");
			return "user/updatePwd";
		}
		//2.根据id查询密码后与原密码比较看是否相同
		User user = (User)httpSession.getAttribute("user");
		Integer id = user.getId();
		User user2 = userService.getUserById(id);
		if (!OriginalPwd.equals(user2.getPassword())) {
			model.addAttribute("info", "原密码不正确");
			return "user/updatePwd";
		}
		//3.更新密码
		user2.setPassword(NewPwd);
		userService.updateUser(user2);
		model.addAttribute("info", "密码更新成功");
		return "user/updatePwd";
	}
	
	/**
	* @author:  qny
	* @methodsName: gotoUpdatePwd
	* @description: 前往更新用户密码
	* @param:  
	* @return: String: 前往更新密码页面
	* @throws: 
	*/	
	@RequestMapping("gotoUpdatePwd")
	public String gotoUpdatePwd(Model model){
		return"user/updatePwd";
	}
	
	/**
	* @author:  qny
	* @methodsName: adminGotoUpdatePwd
	* @description: 前往更新管理员密码页面
	* @param:  
	* @return: String: 前往更新密码页面
	* @throws: 
	*/	
	@RequestMapping("adminGotoUpdatePwd")
	public String adminGotoUpdatePwd(Model model){
		return"admin/updatePwd";
	}
	
	
	
}
