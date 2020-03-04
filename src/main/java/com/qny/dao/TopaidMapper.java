package com.qny.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qny.pojo.QueryVo;
import com.qny.pojo.Topaid;
@Repository
public interface TopaidMapper {
	public void inserttopaid(Topaid topaid);

	public List<Topaid> findtopaid(QueryVo vo);

	public Topaid findbyid(Integer id);

	public void deletetopaid(Integer id);
}
