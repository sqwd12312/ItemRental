package com.qny.service;

import java.util.List;

import javax.management.Query;

import org.apache.ibatis.annotations.Param;

import com.qny.pojo.Paid;
import com.qny.pojo.QueryPaid;
import com.qny.pojo.QueryVo;
import com.qny.pojo.Zulist;

public interface PaidService {
	public List<Paid> selectall(QueryVo vo);

	public Double selectsum(QueryVo vo);
	//qny
	//租客删除已缴纳的租金信息
	public void deletepaid(Integer payId);

	public List<Zulist> findzuuserlist() throws Exception;

	public Zulist findzukezulist(Integer id);
	
	//qny
	//插入支付信息
	public void insertpaid(Paid paid);
	
	//qny
	//根据租客Id查找已缴纳的租金
	public List<QueryPaid> getPaidListByUserId(Integer userId);
	
	//qny
	//根据物品id唯一查到一条支付信息
	public Paid getPaidByItemId(Integer itemId);
	
	//qny
	//根据前端的QueryVo中的起始到截止时间查询数据
	public List<QueryPaid> getPaidListByTime(QueryVo vo);
	
	//qny
	//根据QueryVo查询用户退回的租金 
	public List<QueryPaid> getPaidBackListByQueryVo(QueryVo vo);
	
	//qny
	//租客删除已退回的租金信息
	public void deletePaidBackById(Integer id);
	
	//qny
	//根据QueryVo和用户id查询共缴纳的租金金额
    public Integer getAllPriceByUserId(QueryVo vo,Integer userId);
		
	//qny
	//根据QueryVo和用户id查询总的退回租金金额
	public Integer getAllBackPriceByUserId(QueryVo vo, Integer userId);
	
	//qny
	//根据QueryVo和用户Id查询租金收入金额
	public Integer getTotalIncome(@Param("vo") QueryVo vo,@Param("userId")Integer userId);
	
	//qny
	//根据QueryVo查询租金收入信息
	public List<QueryPaid> getIncomeByUserId(QueryVo vo);	
	
	//qny
	//根据支付订单号唯一查到一条支付信息
	public Paid getPaidByOrderNumber(Integer orderNumber);
	
	//qny
	//查询所有已缴纳的租金信息
	public List<QueryPaid> getAllPaid(QueryVo vo);
	
	//qny
	//查询已缴纳的租金总金额
	public Integer getAllPaidRent(QueryVo vo);
	
	//qny
	//查询所有已退回的租金信息
	public List<QueryPaid> getAllPaidBack(QueryVo vo);
	
	//qny
	//查询已缴纳的租金总金额
	public Integer getAllPaidBackRent(QueryVo vo);
	
	//qny
	//删除已退回的租金信息
	public void deletePayBack(Integer payId);

}
