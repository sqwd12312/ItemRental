package com.qny.service;

import java.util.List;

import com.qny.pojo.Checkout;
import com.qny.pojo.Hetong;
import com.qny.pojo.Userlist;

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
