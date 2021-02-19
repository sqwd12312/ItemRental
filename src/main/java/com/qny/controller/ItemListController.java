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
import com.qny.dao.ItemMapper;
import com.qny.pojo.Item;
import com.qny.pojo.QueryItem;
import com.qny.pojo.User;
import com.qny.service.ItemService;

@Controller

public class ItemListController {
	@Autowired
	private ItemService itemService;

	/**
	* @author:  qny
	* @methodsName: getAllItems
	* @description: user查看所有可供租赁的物品信息
	* @param:  page: 展示第几页的数据；pageSize: 每页展示多少条数据
	* @return: String: 前往user模块下展示所有可供租赁的物品信息的jsp页面
	* @throws: 
	*/
	@RequestMapping("/item")
	public String getAllItems(Model model, 
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "8") Integer pageSize) {		
		PageHelper.startPage(page, pageSize);
		List<Item> item = itemService.selectAll();
		PageInfo<Item> p = new PageInfo<Item>(item);
		model.addAttribute("p", p);
		model.addAttribute("itemList", item);
		return "user/itemList";
	}

	
	/**
	* @author:  qny
	* @methodsName: itemList
	* @description: 查看可供租赁的所有物品
	* @param:  model;page:第几页;pageSize:每页展示的数据条数
	* @return: String:返回前端admin模块下的的物品列表页面
	* @throws: 
	*/
	@RequestMapping("/itemList")
	public String itemList(Model model, 
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "8") Integer pageSize) {

		//设置分页形式
		PageHelper.startPage(page, pageSize);
		//得到所有可供租赁物品的信息
		List<Item> item = itemService.selectAll();
		//进行分页
		PageInfo<Item> p = new PageInfo<Item>(item);
		model.addAttribute("p", p);
		model.addAttribute("item", item);
//		model.addAttribute("mainPage", "itemList.jsp");
//		return "admin/main1";
		return "admin/itemList";
		
	}
	
	
	/**
	* @description: admin组合查询可供租赁的物品
	*/
	@RequestMapping("/getItemByCompositeQuery")
	public String getItemByCQ(
			Model model,
			String itemId,
			String itemName,
			HttpSession httpSession,
			@RequestParam(defaultValue = "0.0") double minPrice,
			@RequestParam(defaultValue = "0.0") double maxPrice,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "8") Integer pageSize) {

		QueryItem qItem = new QueryItem();
		qItem.setItemId(itemId);
		qItem.setItemName(itemName);
		qItem.setMaxPrice(maxPrice);
		qItem.setMinPrice(minPrice);
		//设置分页形式
		PageHelper.startPage(page, pageSize);
		//得到所有可供租赁物品的信息
		List<Item> item = itemService.getItemByCompositeQuery(qItem);
		//进行分页
		PageInfo<Item> p = new PageInfo<Item>(item);
		User user = (User)httpSession.getAttribute("user");
		if (user.getType()=="admin") {
			model.addAttribute("p", p);
			model.addAttribute("item", item);
			model.addAttribute("qItem",qItem);
			return "admin/itemList";
		}else{
			model.addAttribute("p", p);
			model.addAttribute("itemList", item);
			model.addAttribute("qItem",qItem);
			return "user/itemList";
		}
		
		
	}
	
	
	
	
	
	//qny
	//租客查看自己的出租物品
	@RequestMapping("/itemListByZuke")
	public String itemListByZuke(Model model,HttpSession httpSession,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "8") Integer pageSize){
		
		User zuke = (User)httpSession.getAttribute("user");
		PageHelper.startPage(page,pageSize);
		List<Item> itemList = itemService.getItemListByMasterId(zuke.getId());
		PageInfo<Item> p = new PageInfo<>(itemList);
		model.addAttribute("itemList",itemList);
		model.addAttribute("p",p);
		return "user/myitemlist";
	}

	/**
	* @author:  qny
	* @methodsName: toAddItem
	* @description: 前往添加一个物品的添加界面
	* @param:  
	* @return: String:前往admin模块下的添加物品的jsp界面
	* @throws: 
	*/
	@RequestMapping("/toAddItem")
	public String toAddItem(Model model) {
		return "admin/addItem";
	}

	/**
	* @author:  qny
	* @methodsName: addItem
	* @description: 添加一个物品
	* @param:  item: 封装了要添加的物品信息
	* @return: String: 回到admin模块下的添加物品的jsp界面
	* @throws: 
	*/
	@RequestMapping("/addItem")
	public String addItem(Model model, Item item) {
		//得到物品编号
		String itemNumber = item.getItemNumber();
		//根据物品编号查询物品
		Item item2 = itemService.getItemByItemNumber(itemNumber);
		if (item2 != null) {
			model.addAttribute("info", "物品编号已存在");
			model.addAttribute("item", item);
			return "admin/addItem";
		} else {
			model.addAttribute("info", "添加成功");
			itemService.insertItem(item);
			model.addAttribute("item", item);
			return "admin/addItem";
		}
	}
	
	//qny
	//租客添加物品时跳转到物品添加页面
	@RequestMapping("/toAddItemByZuke")
	public String toAddItemByZuke(Model model) {
		return "user/myadditem";
	}
	
	//qny
	//租客添加物品
	@RequestMapping("/addItemByZuke")
	public String addItemByZuke(Model model,HttpSession httpSession,Item item){
			User zuke = (User) httpSession.getAttribute("user");
			item.setMasterId(zuke.getId());
			//查询物品编号是否已存在
			Item item2 = itemService.getItemByItemNumber(item.getItemNumber());
			if (item2!=null) {
				model.addAttribute("error", "此编号已存在");
				model.addAttribute("item", item);
				return "user/myadditem";	
			}else{
				itemService.insertItem(item);
				model.addAttribute("error", "添加成功");
				model.addAttribute("item", item);
				return "user/myadditem";
			}
			
	}
	

	/**
	* @author:  qny
	* @methodsName: deleteItem
	* @description: 删除一个物品的信息
	* @param:  id: 要删除物品的id
	* @return: String:重定向到itemList.action,从而展示删除该物品后的所有可供租赁物品信息
	* @throws: 
	*/
	@RequestMapping("/deleteItem")
	public String deleteItem(Integer id) {
		itemService.deleteItem(id);
		return "redirect:itemList.action";
	}
	
	//qny
	//租客删除自己出租的商品
	@RequestMapping("/zukeDeleteItem")
	public String zukeDeleteItem(Integer itemId){
		itemService.deleteItem(itemId);
		return "redirect:itemListByZuke.action";
	}

	@RequestMapping("/toahouselist")
	public String toahouselist() {

		return "ahouselist.action";
	}

	
	/**
	* @author:  qny
	* @methodsName: findid
	* @description: 查找一个物品的详细信息
	* @param:  itemId：物品id
	* @return: String 前往admin模块下更新物品的jsp页面
	* @throws: 
	*/
	@RequestMapping("/findId")
	public String findid(String itemId, Model model) {
		//根据物品id唯一查找到该物品
		Item item = itemService.findItemId(itemId);
		model.addAttribute("item", item);
		return "admin/changeItem";		
	}

	/**
	* @author:  qny
	* @methodsName: findItemIdUpdate
	* @description: 更新一个物品的信息
	* @param:  item：要更新的物品信息
	* @return: String 前往admin模块下更新物品的jsp页面
	* @throws: 
	*/
	@RequestMapping("/findItemIdUpdate")
	public String findItemIdUpdate(Item item, Model model) {
			itemService.updateItem(item);
			model.addAttribute("item", item);
			model.addAttribute("error", "更新成功");
			return "admin/changeItem";
		
	}
	
	//qny
	//租客修改自己出租的物品
	@RequestMapping("/zukeToUpdateItem")
	public String zukeToUpdateItem(Model model,Integer itemId){
		Item item = itemService.findItemId(itemId.toString());
		model.addAttribute("item",item);
		return "user/zukeupdateitem";
	}
	
	//qny
	//租客更新自己出租的商品
	@RequestMapping("/zukeUpdateItem")
	public String zukeUpdateItem(Item item,Model model,HttpSession httpSession){
		User zuke = (User)httpSession.getAttribute("user");
		item.setMasterId(zuke.getId());
		itemService.updateItem(item);
		model.addAttribute("item",item);
		model.addAttribute("error", "更新成功");
		return "user/zukeupdateitem";
	}

}
