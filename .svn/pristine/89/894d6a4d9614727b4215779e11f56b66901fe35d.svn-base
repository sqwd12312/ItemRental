package com.qst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qst.dao.UserlistMapper;
import com.qst.pojo.Checkout;
import com.qst.pojo.Hetong;
import com.qst.pojo.Userlist;
import com.qst.service.UserlistService;

@Service
@Transactional
public class UserlistServiceImpl implements UserlistService {

	@Autowired
	private UserlistMapper userlistMapper;

	// 根据用户Id查询用户详细信息  
	@Override
	public Userlist findhasuserlist(Integer user_id) {
		Userlist userlist = userlistMapper.findhasuserlist(user_id);
		return userlist;
	}

	@Override
	public Userlist checkuserlist(String idcard) {
		Userlist userlist = userlistMapper.checkuserlist(idcard);
		return userlist;
	}

	@Override
	public void insertuserlist(Userlist userlist) {
		userlistMapper.insertuserlist(userlist);

	}

	@Override
	public void updateuserlist(Userlist userlist) {
		userlistMapper.updateuserlist(userlist);

	}

	@Override
	public Userlist finduserlistupdate(Userlist userlist) {
		Userlist list = userlistMapper.finduserlistupdate(userlist);
		return list;
	}

	@Override
	public List<Userlist> getUserzuList(Integer id) {
		List<Userlist> userlist = userlistMapper.getUserzuList(id);
		for (Userlist list : userlist) {
			System.out.println(list);
		}
		return userlist;
	}

	//qny
	//根据租客的userId查询租客的已退租商品
	@Override
	public List<Checkout> getMyCheckout(Integer userId) {
		List<Checkout> list = userlistMapper.getMyCheckout(userId);
		return list;
	}

	@Override
	public List<Userlist> getmyapply(Integer id) {
		List<Userlist> list = userlistMapper.getmyapply(id);
		return list;
	}

	//根据userList中的id查询出对应用户的申请结果 
	@Override
	public List<Userlist> getmyapplyout(Integer id) {
		List<Userlist> list = userlistMapper.getmyapplyout(id);
		return list;
	}

	@Override
	public List<Userlist> findalluserlist() {
		List<Userlist> list = userlistMapper.findalluserlist();
		return list;
	}

	@Override
	public void deleteuserlist(Integer id) {
		userlistMapper.deleteuserlist(id);
		userlistMapper.deleteuser(id);
	}



}
