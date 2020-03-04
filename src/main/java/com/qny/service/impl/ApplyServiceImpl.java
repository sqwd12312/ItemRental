package com.qny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qny.dao.ApplyMapper;
import com.qny.dao.ItemMapper;
import com.qny.dao.PaidMapper;
import com.qny.dao.UserlistMapper;
import com.qny.pojo.Apply;
import com.qny.pojo.Hetong;
import com.qny.pojo.Item;
import com.qny.pojo.Paid;
import com.qny.pojo.Userlist;
import com.qny.service.ApplyService;
import com.qny.service.HetongService;
import com.qny.service.PaidService;

@Service
@Transactional
public class ApplyServiceImpl implements ApplyService {

	@Autowired
	private ApplyMapper applyMapper;
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private UserlistMapper UserlistMapper;
	@Autowired
	private PaidMapper paidMapper;

	@Override
	public void insertapply(Apply apply) {
		applyMapper.insertapply(apply);

	}

	@Override
	public List<Userlist> findapplylist() throws Exception {
		List<Userlist> apply = applyMapper.findapplylist();
		return apply;
	}

	@Override
	public Apply findByItemId(String itemId) {
		Apply apply = applyMapper.findByItemId(itemId);
		return apply;
	}

	@Override
	public void deleteByItemId(String itemId) {
		applyMapper.deleteByItemId(itemId);

	}

	//qny
	//拒绝租赁申请
	@Override
	public void refuseapply(Item item,Integer orderNumber) {
	    item = itemMapper.findItemId(item.getItemId().toString());
		//1.退款给申请人
		Apply apply = applyMapper.findByItemId(item.getItemId().toString());
		int zuKeId = apply.getUserId();
		Userlist zuke = UserlistMapper.findhasuserlist(zuKeId);
		zuke.setBalance(zuke.getBalance()+apply.getTotalPrice());
		UserlistMapper.updateuserlist(zuke);
		//2.从出租人账户扣款
		Item item2 = itemMapper.findItemId(apply.getItemId());
		Userlist chuZu = UserlistMapper.findhasuserlist(item2.getMasterId());
		chuZu.setBalance(chuZu.getBalance()-apply.getTotalPrice());
		UserlistMapper.updateuserlist(chuZu);
		//3.将退款信息写入支付表
		Paid paid = paidMapper.getPaidByOrderNumber(orderNumber);
		paid.setStatus("租金退回");
		paidMapper.insertpaid(paid);
		//4.更新申请状态为“已拒绝”
		apply.setStatus("已拒绝");
		applyMapper.updateApplyStatus(apply);
		//5.为了支付表根据itemId查询订单的唯一性
		//这里更新商品Id，通过删除再插入的形式实现
		itemMapper.deleteItem(item.getItemId());
		item.setStatus("未租赁");
		item.setItemId(0);
		itemMapper.insertItem(item);
	}

	//qny
	//更改物品申请状态
	@Override
	public void updateApplyStatus(Apply apply) {
		applyMapper.updateApplyStatus(apply);
	}

	//qny
	//用户查看自己物品的被申请租赁列表
	@Override
	public List<Userlist> getMyApply(Integer userId) throws Exception {
		return applyMapper.getMyApply(userId);
	}

	
	//qny
	//根据申请id删除申请记录
	@Override
	public void deleteApplyById(Integer id) {
		applyMapper.deleteApplyById(id);
	}

}




