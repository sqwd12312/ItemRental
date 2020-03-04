package com.qst.service;

import java.util.List;

import com.qst.pojo.Apply;
import com.qst.pojo.Item;
import com.qst.pojo.Userlist;

public interface ApplyService {

	public void insertapply(Apply apply);

	public List<Userlist> findapplylist() throws Exception;

	Apply findByItemId(String itemId);

	public void deleteByItemId(String itemId);

	//拒绝租赁申请
	public void refuseapply(Item item,Integer orderNumber);
	
	//qny
	//更改物品申请状态
	public void updateApplyStatus(Apply apply);
	
	//qny
	//根据申请id删除申请记录
	public void deleteApplyById(Integer id);
	
	//qny
	//用户查看自己物品的被申请租赁列表
	public List<Userlist> getMyApply(Integer userId) throws Exception;	
}
