package com.qny.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qny.pojo.Apply;
import com.qny.pojo.Applyout;
import com.qny.pojo.Userlist;

@Repository
public interface ApplyMapper {
	void insertapply(Apply apply);

	public List<Userlist> findapplylist() throws Exception;

	Apply findByItemId(String itemId);

	public void deleteByItemId(String itemId);

	public void updateapplyout(Applyout applyout);
	
	//qny
	//更改物品申请状态
	public void updateApplyStatus(Apply apply);
	
	//qny
	//用户查看自己物品的被申请租赁列表
	public List<Userlist> getMyApply(Integer userId) throws Exception;
	
	//qny
	//根据申请id删除申请记录
	public void deleteApplyById(Integer id);
}
