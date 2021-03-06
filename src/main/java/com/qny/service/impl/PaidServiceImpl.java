package com.qny.service.impl;

import java.util.List;

import javax.management.Query;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qny.dao.PaidMapper;
import com.qny.dao.ZulistMapper;
import com.qny.pojo.Paid;
import com.qny.pojo.QueryPaid;
import com.qny.pojo.QueryVo;
import com.qny.pojo.Zulist;
import com.qny.service.PaidService;

@Service
@Transactional
public class PaidServiceImpl implements PaidService {
	@Autowired
	private PaidMapper paidMapper;
	@Autowired
	private ZulistMapper zulistMapper;

	@Override
	public List<Paid> selectall(QueryVo vo) {
		List<Paid> list = paidMapper.selectall(vo);
		return list;
	}

	@Override
	public Double selectsum(QueryVo vo) {
		Double sum = paidMapper.selectsum(vo);
		return sum;
	}

	@Override
	public void deletepaid(Integer payId) {
		paidMapper.deletepaid(payId);

	}

	@Override
	public List<Zulist> findzuuserlist() throws Exception {
		List<Zulist> list = zulistMapper.findzuuserlist();
		return list;
	}

	@Override
	public Zulist findzukezulist(Integer id) {
		Zulist zulist = zulistMapper.findzukezulist(id);
		return zulist;
	}

	//qny
	//插入一条支付信息
	@Override
	public void insertpaid(Paid paid) {
		paidMapper.insertpaid(paid);		
	}

	//qny
	//根据租客Id查找已缴纳的租金
	@Override
	public List<QueryPaid> getPaidListByUserId(Integer userId) {
		return paidMapper.getPaidListByUserId(userId);
	}

	//qny
	//根据物品id唯一查到一条支付信息
	@Override
	public Paid getPaidByItemId(Integer itemId) {
		return paidMapper.getPaidByItemId(itemId);
	}

	//qny
	//根据前端的QueryVo中的起始到截止时间查询数据
	@Override
	public List<QueryPaid> getPaidListByTime(QueryVo vo) {
		return paidMapper.getPaidListByTime(vo);
	}

	//qny
	//根据QueryVo查询用户退回的租金
	@Override
	public List<QueryPaid> getPaidBackListByQueryVo(QueryVo vo) {
		return paidMapper.getPaidBackListByQueryVo(vo);
	}

	//qny
	//租客删除已退回的租金信息
	@Override
	public void deletePaidBackById(Integer id) {
		 paidMapper.deletePaidBackById(id);
	}

	//qny
	//根据用户Id和QueryVo查询共缴纳的租金金额
	@Override
	public Integer getAllPriceByUserId(QueryVo vo, Integer userId) {
		return paidMapper.getAllPriceByUserId(vo,userId);
	}

	//qny
	//根据用户Id和QueryVo查询总的退回租金金额	
	@Override
	public Integer getAllBackPriceByUserId(QueryVo vo, Integer userId) {
		return paidMapper.getAllBackPriceByUserId(vo,userId);
	}

	//qny
	//根据QueryVo和用户Id查询租金收入金额
	@Override
	public Integer getTotalIncome(@Param("vo") QueryVo vo,@Param("userId")Integer userId) {
		return paidMapper.getTotalIncome(vo,userId);
	}

	//qny
	//根据QueryVo查询租金收入信息
	@Override
	public List<QueryPaid> getIncomeByUserId(QueryVo vo) {
		return paidMapper.getIncomeByUserId(vo);
	}

	//qny
	//根据支付订单号唯一查到一条支付信息
	@Override
	public Paid getPaidByOrderNumber(Integer orderNumber) {
		return paidMapper.getPaidByOrderNumber(orderNumber);
	}

	//qny
	//查询所有已缴纳的租金信息
	@Override
	public List<QueryPaid> getAllPaid(QueryVo vo) {
		return paidMapper.getAllPaid(vo);
	}

	//qny
	//查询已缴纳的租金总金额
	@Override
	public Integer getAllPaidRent(QueryVo vo) {
		return paidMapper.getAllPaidRent(vo);
	}

	//qny
	//查询所有已退回的租金信息
	@Override
	public List<QueryPaid> getAllPaidBack(QueryVo vo) {
		return paidMapper.getAllPaidBack(vo);
	}

	//qny
	//查询已缴纳的租金总金额
	@Override
	public Integer getAllPaidBackRent(QueryVo vo) {
		return paidMapper.getAllPaidBackRent(vo);
	}

	//qny
	//删除已退回的租金信息
	@Override
	public void deletePayBack(Integer payId) {
		paidMapper.deletePayBack(payId);
	}


    

}
