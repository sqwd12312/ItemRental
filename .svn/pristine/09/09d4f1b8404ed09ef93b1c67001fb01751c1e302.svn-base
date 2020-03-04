package com.qst.service;

import java.util.List;

import com.qst.pojo.Checkout;
import com.qst.pojo.Hetong;
import com.qst.pojo.Userlist;

public interface UserlistService {
	Userlist findhasuserlist(Integer user_id);

	Userlist checkuserlist(String idcard);

	void insertuserlist(Userlist userlist);

	void updateuserlist(Userlist userlist);

	Userlist finduserlistupdate(Userlist userlist);

	public List<Userlist> getUserzuList(Integer id);

	//qny
	//根据租客的userId查询租客的已退租商品
	public List<Checkout> getMyCheckout(Integer userId);

	public List<Userlist> getmyapply(Integer id);

	public List<Userlist> getmyapplyout(Integer id);

	public List<Userlist> findalluserlist();

	public void deleteuserlist(Integer id);

	

}
