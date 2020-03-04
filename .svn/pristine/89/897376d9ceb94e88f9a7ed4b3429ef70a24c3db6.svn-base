package com.qst.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qst.pojo.User;
import com.qst.pojo.Userlist;
import com.qst.service.UserService;
import com.qst.service.UserlistService;

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
}
