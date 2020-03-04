package com.qny.service;

import java.util.List;

import com.qny.pojo.Paid;
import com.qny.pojo.QueryVo;
import com.qny.pojo.Topaid;

public interface TopaidService {
	public void inserttopaid(Topaid topaid);

	public List<Topaid> findtopaid(QueryVo vo);

	public Topaid findbyid(Integer id);

	public void gotopay(Integer id, Paid paid);
}
