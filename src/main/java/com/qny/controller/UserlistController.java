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
import com.qny.pojo.User;
import com.qny.pojo.Userlist;
import com.qny.service.UserService;
import com.qny.service.UserlistService;

@Controller
public class UserlistController {

	@Autowired
	private UserlistService userlistService;
	@Autowired
	private UserService userService;

	
	/**
	* @author:  qny
	* @methodsName: findhasuserlist
	* @description: 得到用户个人信息并前往更新信息界面
	* @param:  
	* @return: String: 前往更新信息界面
	* @throws: 
	*/	
	@RequestMapping("/findhasuserlist")
	public String findhasuserlist(HttpSession httpSession, Model model) throws Exception {
		User user1 = (User) httpSession.getAttribute("user");
		Integer user_id = user1.getId();
		Userlist userlist = userlistService.findhasuserlist(user_id);
		model.addAttribute("userlist", userlist);
		return "user/updateuserlist";

	}
	
	/**
	* @author:  qny
	* @methodsName: updateAdminInfo
	* @description: 得到管理员个人信息并前往更新信息界面
	* @param:  
	* @return: String: 前往更新信息界面
	* @throws: 
	*/	
	@RequestMapping("/updateAdminInfo")
	public String updateAdminInfo(HttpSession httpSession, Model model) throws Exception {
		User user1 = (User) httpSession.getAttribute("user");
		Integer user_id = user1.getId();
		Userlist userlist = userlistService.findhasuserlist(user_id);
		model.addAttribute("userlist", userlist);
		return "admin/updateAdminInfo";

	}

	/**
	* @author:  qny
	* @methodsName: myDetails
	* @description: 用户查看个人信息
	* @param:  
	* @return: String: 前往展示用户个人信息的界面
	* @throws: 
	*/
	@RequestMapping("/myDetails")
	public String getUserDetails(HttpSession httpSession , Model model){
		User user = (User)httpSession.getAttribute("user");
		Integer userId = user.getId();
		Userlist userDetails = userlistService.findhasuserlist(userId);
		model.addAttribute("userDetails",userDetails);
		return"user/mydetails";
		
	}

	/**
	* @author:  qny
	* @methodsName: getAdminDetails
	* @description: 管理员查看个人信息
	* @param:  
	* @return: String: 前往展示管理员个人信息的界面
	* @throws: 
	*/
	@RequestMapping("/adminDetails")
	public String getAdminDetails(HttpSession httpSession , Model model){
		User user = (User)httpSession.getAttribute("user");
		Integer userId = user.getId();
		Userlist userDetails = userlistService.findhasuserlist(userId);
		model.addAttribute("userDetails",userDetails);
		return"admin/mydetails";
		
	}

	//qny
	//更新用户信息
	@RequestMapping("/checkuserlist")
	public String checkuserlist(Model model, Userlist userlist, HttpSession httpSession) throws Exception {

		    User user = (User)httpSession.getAttribute("user");
		    Integer userId = user.getId();
		    userlist.setUser_id(userId);
			Userlist list = userlistService.finduserlistupdate(userlist);
			if (list != null) {
				model.addAttribute("error", "该身份证号码已被绑定");
				model.addAttribute("userlist", userlist);
			} else {
				userlistService.updateuserlist(userlist);
				User user2 = new User();
				user2.setUsername(userlist.getName());
				user2.setId(userId);
				user2.setPhone(userlist.getPhone());
				userService.updateUser(user2);
				model.addAttribute("error", "更新成功");
				model.addAttribute("userlist", userlist);
			}

		
		return "user/updateuserlist";
	}

	/**
	* @author:  qny
	* @methodsName: findAllUserList
	* @description: admin查看所有用户的信息
	* @param:  page: 展示第几页的数据；pageSize: 每页展示多少条数据
	* @return: String: 前往admin模块下展示所有用户信息的action
	* @throws: 
	*/ 
	@RequestMapping("/findalluserlist")
	public String findAllUserList(Model model, 
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "8") Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Userlist> userlist = userlistService.findalluserlist();
		PageInfo<Userlist> p = new PageInfo<Userlist>(userlist);
		model.addAttribute("userlist", userlist);
		model.addAttribute("p", p);
		return "admin/userlist";

	}

	// 删除用户信息
	@RequestMapping("/deleteuserlist")
	public String deleteuserlist(Model model, Integer id) {
		userlistService.deleteuserlist(id);
		model.addAttribute("error", "deletesuccess");
		return "redirect:findalluserlist.action";
	}
}
