package com.qst.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qst.pojo.Checkout;
@Repository
public interface CheckoutMapper {
	public void insertcheckout(Checkout checkout);

	public List<Checkout> getallcheckout();
	
	//qny
	//用户查看自己物品的已退租列表
	public List<Checkout> getAllCheckoutByUser(Integer userId);

	//qny
	//根据已退租数据表id删除已退租数据
	public void deletecheckout(Integer cId);
	
	//qny
	//根据已退租的信息编号更新评价状态为“已评价”
	public void updateEvaStatusById(Integer cId);
}
