package com.qst.service;

import java.util.List;

import com.qst.pojo.Apply;
import com.qst.pojo.Hetong;
import com.qst.pojo.Userlist;

public interface HetongService {

	public void inserthetong(Hetong hetong);

	public Hetong findhetong(String itemId);

	public void updatehetong(Hetong hetong);

	public void deletehetong(String house_id);
	
	//qny
	//根据商品id查询对应申请
	public Hetong getApplyByItemId(Integer itemId);
	
	//qny
	//根据租客的userId查询自己的在租列表
	public List<Hetong> getHetongByUserId(Integer userListId);
	
	//qny
	//查询所有合同
	public List<Hetong> getAllHetong();
	
	//qny
	//根据商品的itemId查询商品合同
	public Hetong getHetongByItemId(Integer itemId);
	
	//qny
	//租客查询所有的出租列表
	public List<Hetong> getAllHetongByzuke(Integer userId);	
}
