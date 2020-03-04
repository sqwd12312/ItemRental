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
import com.qst.pojo.Hetong;
import com.qst.pojo.User;
import com.qst.pojo.Userlist;
import com.qst.pojo.Zulist;
import com.qst.service.HetongService;
import com.qst.service.UserlistService;
import com.qst.service.ZulistService;

@Controller
@RequestMapping("/zulist")
public class ZulistController {
	@Autowired
	private HetongService hetongService;

	// 跳到增添合同的页面
	@RequestMapping("/toaddhetong")
	public String toaddhetong(Model model, String itemId) {
		Hetong hetong = new Hetong();
		hetong.setItemId(itemId);
		model.addAttribute("hetong", hetong);
		model.addAttribute("mainPage", "addhetong.jsp");
		return "admin/main1";
	}

	/**
	* @author:  qny
	* @methodsName: getUserInRent
	* @description: user查看自己的所有在租物品
	* @param:  page: 展示第几页的数据；pageSize: 每页展示多少条数据
	* @return: String: 前往user模块下展示用户所有在租物品的jsp页面
	* @throws: 
	*/
	@RequestMapping("/myzulist")
	public String getUserInRent(Model model, HttpSession httpSession,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) throws Exception {

		User user1 = (User) httpSession.getAttribute("user");
		//根据userListId查询自己的在租列表
		//注意：这下面段代码一定要写在查询语句上方，紧贴着，否则无法分页！
		PageHelper.startPage(page, pageSize);
		List<Hetong> hetongList = hetongService.getHetongByUserId(user1.getId());
		PageInfo<Hetong> p = new PageInfo<>(hetongList);
		model.addAttribute("hetongList", hetongList);		
		model.addAttribute("p", p);
		model.addAttribute("mainPage", "myzulist.jsp");
		return "user/main";
	}

}
