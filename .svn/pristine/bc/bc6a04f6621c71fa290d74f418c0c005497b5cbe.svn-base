package com.qst.controller;

import java.text.ParseException;
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
import com.qst.pojo.Evaluation;
import com.qst.pojo.Item;
import com.qst.pojo.User;
import com.qst.pojo.Userlist;
import com.qst.service.CheckoutService;
import com.qst.service.EvaluationService;
import com.qst.service.ItemService;
import com.qst.service.UserlistService;

@Controller
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserlistService userlistService;
    @Autowired
    private CheckoutService checkoutService;
    
    //qny
    //前往插入一条评价
    @RequestMapping("/toInsertEvaluation")
    public String toInsertEvaluation(Model model,String itemNumber,Integer  cId){
    	//根据商品编号查询商品
    	Item item = itemService.getItemByItemNumber(itemNumber);
    	model.addAttribute("itemNumber",itemNumber);
    	model.addAttribute("itemName",item.getName());
    	model.addAttribute("cId",cId);
    	//根据商品的主人id查询商品主人信息
    	Userlist userlist = userlistService.findhasuserlist(item.getMasterId());
    	model.addAttribute("chuzuName",userlist.getName());
    	model.addAttribute("mainPage","evaluation.jsp");
		return "user/main";   	
    }

    
    //qny
    //插入一条评价
    @RequestMapping("insertEvaluation")
    public String insertEvaluation(Model model,Evaluation evaluation,Integer cId,
    		                       HttpSession httpSession) throws ParseException{
    	//根据商品编号查询商品
    	Item item = itemService.getItemByItemNumber(evaluation.getItemNumber());
    	evaluation.setChuzuId(item.getMasterId().toString());
    	//获取当前用户信息
    	User zuke = (User)httpSession.getAttribute("user");
    	evaluation.setZukeId(zuke.getId().toString());
    	//获取系统时间
    	Date date = new Date();
    	SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
    	String nowTime = sdf.format(date);
    	Date time = sdf.parse( nowTime );
    	evaluation.setTime(time);
    	//插入评价
        evaluationService.insertEvaluation(evaluation);
    	//根据已退租的信息编号更新评价状态为“已评价”
        checkoutService.updateEvaStatusById(cId);
        return "redirect:/checkout/getMyCheckout.action";
        
    }
 
	/**
	* @author:  qny
	* @methodsName: getEvalutionByItemNumber
	* @description: user查看某件物品的所有评价
	* @param:  page: 展示第几页的数据；pageSize: 每页展示多少条数据
	* @param:  itemNumber: 要查询物品的编号
	* @return: String: 前往user模块下展示某个物品全部评价的jsp页面
	* @throws: 
	*/
    @RequestMapping("getEvalutionByItemNumber")
    public String getEvalutionByItemNumber(Model model,String itemNumber,
    		@RequestParam(required = false, defaultValue = "1") Integer page,
    		@RequestParam(required = false, defaultValue = "2") Integer pageSize){
    	Item item = itemService.getItemByItemNumber(itemNumber);
    	model.addAttribute("itemName",item.getName());
    	model.addAttribute("itemNumber",itemNumber);
    	PageHelper.startPage(page, pageSize);
    	List<Evaluation> evaluationList = evaluationService.getEvalutionByItemNumber(itemNumber);
    	if (evaluationList.size() == 0) {    		
    		model.addAttribute("info","该物品暂无评价！");
        	model.addAttribute("mainPage","itemevaluation.jsp");
    		return "user/main";
		}
    	PageInfo<Evaluation> p = new PageInfo<>(evaluationList);
    	model.addAttribute("evaluationList",evaluationList);
    	model.addAttribute("p",p);
    	model.addAttribute("mainPage","itemevaluation.jsp");
		return "user/main";
    }
    
    
    //qny
    //用户查看自己曾经给出的全部评价
    @RequestMapping("myZulingEvaluation")
    public String getMyZulingEvaluation(Model model,HttpSession httpSession,
    		@RequestParam(required = false, defaultValue = "1") Integer page,
    		@RequestParam(required = false, defaultValue = "2") Integer pageSize){
    	User user = (User)httpSession.getAttribute("user");
    	PageHelper.startPage(page, pageSize);
    	List<Evaluation> evaluationList = evaluationService.getMyZulingEvaluation(user.getId().toString());
    	PageInfo<Evaluation> pageInfo = new PageInfo<>(evaluationList);
    	model.addAttribute("evaluationList",evaluationList);
    	model.addAttribute("p",pageInfo);
    	model.addAttribute("mainPage","itemsevaluation.jsp");
    	return "user/main";
    }
    
    //qny
    //前往用户更改评价页面
    @RequestMapping("userToUpdateEvaluation")
    public String toUpdateEvaluation(Model model,String itemNumber,Integer id){
    	//根据商品编号查询商品
    	Item item = itemService.getItemByItemNumber(itemNumber);
    	model.addAttribute("itemNumber",itemNumber);
    	model.addAttribute("itemName",item.getName());
    	model.addAttribute("id",id);
    	//根据商品的主人id查询商品主人信息
    	Userlist userlist = userlistService.findhasuserlist(item.getMasterId());
    	model.addAttribute("chuzuName",userlist.getName());
    	model.addAttribute("mainPage","updateevaluation.jsp");
		return "user/main";
    }
    
    //qny
    //用户更改评价
    @RequestMapping("userUpdateEvaluation")
    public String UpdateEvaluation(Model model,Evaluation evaluation,String itemNumber,
    		                       String itemName,String chuzuName) throws ParseException{
    	
    	//获取系统时间
    	Date date = new Date();
    	SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
    	String nowTime = sdf.format(date);
    	Date time = sdf.parse( nowTime );
    	evaluation.setTime(time);
    	evaluationService.UpdateEvaluation(evaluation);
    	model.addAttribute("itemNumber",itemNumber);
    	model.addAttribute("itemName",itemName);
    	model.addAttribute("chuzuName",chuzuName);
    	model.addAttribute("content",evaluation.getContent());
    	model.addAttribute("error","修改成功!");
    	model.addAttribute("id",evaluation.getId());
    	model.addAttribute("mainPage","updateevaluation.jsp");
		return "user/main";
    }
    
    //qny
    //用户删除一条自己给出的评价
    @RequestMapping("deleteEvalutionById")
    public String deleteEvalutionById(Integer id,Model model){
    	evaluationService.deleteEvalutionById(id);
    	model.addAttribute("mainPage","itemsevaluation.jsp");
		return "user/main";
    }
    
    //qny
    //用户查询自己出租物品受到的评价
    @RequestMapping("myChuzuEvaluation")
    public String myChuzuEvaluation(Model model,HttpSession httpSession,
    		@RequestParam(required = false, defaultValue = "1") Integer page,
    		@RequestParam(required = false, defaultValue = "2") Integer pageSize){
    	User user = (User)httpSession.getAttribute("user");
    	PageHelper.startPage(page, pageSize);
    	List<Evaluation> evaluationList = evaluationService.getAllEvaluationFromZuke(user.getId());
    	PageInfo<Evaluation> pageInfo = new PageInfo<>(evaluationList);
    	model.addAttribute("evaluationList",evaluationList);
    	model.addAttribute("p",pageInfo);
    	model.addAttribute("mainPage","evaluationsbyzuke.jsp");
		return "user/main";
    }
 
	/**
	* @author:  qny
	* @methodsName: getItemEvaluations
	* @description: admin查看所有物品的评价信息
	* @param:  page: 展示第几页的数据；pageSize: 每页展示多少条数据
	* @return: String: 前往admin模块下展示所有物品评价的jsp页面
	* @throws: 
	*/
    @RequestMapping("getItemEvaluationsByAdmin")
    public String getItemEvaluations(Model model,
    		@RequestParam(required = false, defaultValue = "1") Integer page,
    		@RequestParam(required = false, defaultValue = "2") Integer pageSize){
    	//设置分页形式
    	PageHelper.startPage(page, pageSize);
    	//得到所有物品的评价信息
    	List<Evaluation> evaluationList = evaluationService.getItemEvaluations();
    	//将信息进行分页
    	PageInfo<Evaluation> pageInfo = new PageInfo<>(evaluationList);
    	model.addAttribute("evaluationList",evaluationList);
    	model.addAttribute("p",pageInfo);
    	model.addAttribute("mainPage","itemsevaluation.jsp");
    	return "admin/main1";
    }

	/**
	* @author:  qny
	* @methodsName: deleteEvalutionByAdmin
	* @description: admin删除一条物品的评价信息
	* @param:  id: 要删除评价的id
	* @return: String: 重定向到admin模块下展示所有物品评价的action
	* @throws: 
	*/    
    @RequestMapping("deleteEvalutionByAdmin")
    public String deleteEvalutionByAdmin(Integer id,Model model){
    	evaluationService.deleteEvalutionById(id);
    	return "redirect:/getItemEvaluationsByAdmin.action";
    }
       
}
