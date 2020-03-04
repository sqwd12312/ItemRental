package com.qst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qst.dao.PaidMapper;
import com.qst.dao.TopaidMapper;
import com.qst.pojo.Paid;
import com.qst.pojo.QueryVo;
import com.qst.pojo.Topaid;
import com.qst.service.TopaidService;

@Service
@Transactional
public class TopaidServiceImpl implements TopaidService {
	@Autowired
	private TopaidMapper topaidMapper;
	@Autowired
	private PaidMapper paidMapper;

	@Override
	public void inserttopaid(Topaid topaid) {
		topaid.setStatus("租金未缴");
		topaidMapper.inserttopaid(topaid);
	}

	@Override
	public List<Topaid> findtopaid(QueryVo vo) {
		List<Topaid> list = topaidMapper.findtopaid(vo);
		return list;
	}

	@Override
	public Topaid findbyid(Integer id) {
		Topaid topaid = topaidMapper.findbyid(id);
		return topaid;
	}

	@Override
	public void gotopay(Integer id, Paid paid) {
		paidMapper.insertpaid(paid);
		topaidMapper.deletetopaid(id);

	}

}
