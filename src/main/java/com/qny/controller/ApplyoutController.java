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
import com.qny.pojo.Applyout;
import com.qny.pojo.Hetong;
import com.qny.pojo.User;
import com.qny.service.ApplyoutService;
import com.qny.service.HetongService;

@Controller
@RequestMapping("/applyout")
public class ApplyoutController {
	@Autowired
	private ApplyoutService applyoutService;
	@Autowired
	private HetongService hetongService;

	//测试gitHub
	//qny
	//插入退租信息
	@RequestMapping("/insertapplyout")
	public String insertapplyout(String itemId, Model model) {
		//1.检查该物品是否在退租申请中，避免重复提交申请
		Applyout applyout = applyoutService.getApplyOutByItemId(Integer.parseInt(itemId));
		if (applyout!=null) {
			model.addAttribute("error", "chongFuTiJiao");
			return "redirect:/zulist/myzulist.action";
		}else{
			Hetong hetong = hetongService.getHetongByItemId(Integer.parseInt(itemId));
			applyoutService.insertapplyout(hetong);
			model.addAttribute("error", "applysuccess");
			return "redirect:/zulist/myzulist.action";
		}	
	}

	//管理员查看所有退租申请
	@RequestMapping("/findallapplyout")
	public String findallapplyout(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "8") Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Applyout> applyoutList = applyoutService.getAllApplyOut();
		PageInfo<Applyout> p = new PageInfo<Applyout>(applyoutList);
		model.addAttribute("applyoutList", applyoutList);
		model.addAttribute("p", p);
		return "admin/applyout";
	}
	
	//qny
	//用户查看自己物品的所有退租申请
	@RequestMapping("/findAllApplyoutByZuke")
	public String findAllApplyoutByZuke(HttpSession httpSession,Model model, 
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "8") Integer pageSize) {
		User user = (User) httpSession.getAttribute("user");
		PageHelper.startPage(page, pageSize);
		List<Applyout> applyoutList = applyoutService.getAllApplyOutByZuke(user.getId());
		PageInfo<Applyout> p = new PageInfo<Applyout>(applyoutList);
		model.addAttribute("applyoutList", applyoutList);
		model.addAttribute("p", p);
		return "user/applyoutbychuzu";
	}	

	/**
	* @author:  qny
	* @methodsName: refuseApplyOut
	* @description: admin拒绝一件物品的退租申请
	* @param:  itemId: 退租物品的id
	* @return: String: 重定向到admin查看所有退租申请的action
	* @throws: 
	*/
	@RequestMapping("/refuseapplyout")
	public String refuseApplyOut(Model model, String itemId) {
		Applyout applyout = new Applyout();
		applyout.setItemId(itemId);
		applyout.setStatus("已拒绝");
		applyoutService.updateapplyout(applyout);
		model.addAttribute("mainPage", "applyout.jsp");
		return "redirect:findallapplyout.action";
	}
	
	//qny
	//用户拒绝自己物品的退租申请
	@RequestMapping("/refuseApplyoutByUser")
	public String refuseApplyoutByUser(Model model, String itemId) {
		Applyout applyout = new Applyout();
		applyout.setItemId(itemId);
		applyout.setStatus("已拒绝");
		applyoutService.updateapplyout(applyout);
		return "redirect:findAllApplyoutByZuke.action";
	}

	/**
	* @author:  qny
	* @methodsName: agreeApplyOut
	* @description: 同意一件物品的退租申请
	* @param:  itemId: 退租物品的id
	* @return: String: 重定向到admin查看所有退租申请的action
	* @throws: 
	*/
	//qny
	// 管理员同意退租申请
	@RequestMapping("/agreeApplyOut")
	public String agreeApplyOut(Model model, Integer itemId) {
		applyoutService.agreeapplyout(itemId);
		model.addAttribute("error", "applyoutsucess");
		return "redirect:findallapplyout.action";
	}
	
	//qny
	//用户同意自己物品的退租申请
	@RequestMapping("/agreeApplyOutByUser")
	public String agreeApplyOutByUser(Model model, Integer itemId) {
		applyoutService.agreeapplyout(itemId);
		model.addAttribute("error", "applyoutsucess");
		return "redirect:findAllApplyoutByZuke.action";
	}	

	//qny
	//租客删除申请退租列表
	@RequestMapping("/deleteapplyout")
	public String deleteapplyout(Model model, Integer aoId) {
		applyoutService.deleteapplyout(aoId);
		model.addAttribute("error", "deletesucess");
		return "redirect:getmyapplyout.action";
	}
	
	/**
	* @author:  qny
	* @methodsName: adminDeleteApplyout
	* @description: admin删除一条已处理的退租信息
	* @param:  aoid: 已处理退租信息的id
	* @return: String: 重定向到admin查看所有退租申请的action
	* @throws: 
	*/
	@RequestMapping("/adminDeleteApplyout")
	public String adminDeleteApplyout(Model model, Integer aoid) {
		applyoutService.deleteapplyout(aoid);
		model.addAttribute("info", "deletesucess");
		return "redirect:findallapplyout.action";
	}
	
	//qny
	//用户删除已处理的退租申请列表
	@RequestMapping("/deleteApplyoutByUser")
	public String deleteApplyoutByUser(Model model, Integer aoId) {
		applyoutService.deleteapplyout(aoId);
		model.addAttribute("error", "deletesucess");
		return "redirect:findAllApplyoutByZuke.action";
	}

	//qny
	//租客查看自己的所有退租申请
	@RequestMapping("/getmyapplyout")
	public String getmyapplyout(Model model, HttpSession httpSession,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "8") Integer pageSize) {
		User user1 = (User) httpSession.getAttribute("user");
		PageHelper.startPage(page, pageSize);
		//根据userList中的id查询出对应用户的退租申请结果
		List<Applyout> ApplyoutList = applyoutService.getMyApplyOutByUserId(user1.getId());
		PageInfo<Applyout> p = new PageInfo<Applyout>(ApplyoutList);
		model.addAttribute("ApplyoutList", ApplyoutList);
		model.addAttribute("p", p);
		return "user/myapplyout";
	}
}
