package com.qny.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qny.pojo.QueryVo;
import com.qny.pojo.Solve;
@Repository
public interface SolveMapper {
	public List<Solve> selectall(QueryVo vo);

	public Integer selectcount(QueryVo vo);

	public void deletesolve(Integer id);

	public void insertsolve(Solve solve);
}
