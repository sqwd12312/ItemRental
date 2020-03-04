package com.qst.service;

import java.util.List;

import com.qst.pojo.Applyout;
import com.qst.pojo.Hetong;

public interface ApplyoutService {
	public void insertapplyout(Hetong hetong);

	//qny
	//查询所有退租申请
	public List<Applyout> getAllApplyOut();
	
	//qny
	//用户查看自己物品的所有退租申请
	public List<Applyout> getAllApplyOutByZuke(Integer userId);	
	
	//qny
	//根据物品itemId查询退租申请
	public Applyout getApplyOutByItemId(Integer itemId);

	public void updateapplyout(Applyout applyout);

	public void agreeapplyout(Integer id);

	//qny
	//根据物品itemNumber删除已处理的退租申请
	public void deleteapplyout(Integer aoid);
	
	
	
	//qny
	//根据租客userId查询租客的退租申请
	public List<Applyout> getMyApplyOutByUserId(Integer userId);
	
	//qny
	//更新退租申请的状态
	public void updateApplyoutByItem(Applyout applyout);
	
	//qny
	//更新退租申请的状态
	public void updateApplyoutStatusByItem(Applyout applyout,String itemId);
}
