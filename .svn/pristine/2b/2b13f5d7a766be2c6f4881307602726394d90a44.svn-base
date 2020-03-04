package com.qst.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qst.pojo.Applyout;
@Repository
public interface ApplyoutMapper {
	public void insertapplyout(Applyout applyout);

	//qny
	//查询所有退租申请
	List<Applyout> getAllApplyOut();
	
	//qny
	//用户查看自己物品的所有退租申请
	public List<Applyout> getAllApplyOutByZuke(Integer userId);	
	
	//qny
	//根据物品itemId查询退租申请
	public Applyout getApplyOutByItemId(Integer itemId);

	public void updateapplyout(Applyout applyout);


	public Applyout findbyid(Integer id);

	//qny
	//根据退租申请表id删除已处理的退租申请
	public void deleteapplyout(Integer aoid);
	
	//qny
	//根据租客userId查询租客的退租申请
	public List<Applyout> getMyApplyOutByUserId(Integer userId);
	
	//qny
	//更新退租申请的状态
	public void updateApplyoutByItem(Applyout applyout);
	
	//qny
	//更新退租申请的状态
	public void updateApplyoutStatusByItem(Applyout applyout,@Param("itemId") String itemId);
}
