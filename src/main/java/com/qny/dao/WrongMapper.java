package com.qny.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qny.pojo.QueryVo;
import com.qny.pojo.Wrong;
@Repository
public interface WrongMapper {
	public List<Wrong> findwrong(QueryVo vo);

	public Wrong findbyid(Integer id);

	public void insertwrong(Wrong wrong);

	public void deletewrong(Integer id);
}
