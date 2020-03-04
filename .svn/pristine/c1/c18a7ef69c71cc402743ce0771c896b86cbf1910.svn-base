package com.qst.service;

import java.util.List;

import com.qst.pojo.Evaluation;

public interface EvaluationService {
	
	//qny
	//插入一条评价信息
	public void insertEvaluation(Evaluation evaluation);
	
	//qny
	//根据商品编号查询商品评价
	public List<Evaluation> getEvalutionByItemNumber(String itemNumber);
	
	//qny
	//用户查看自己曾经给出的全部评价
	public List<Evaluation> getMyZulingEvaluation(String zukeId);
	
	//qny
	//用户更改评价 
	public void UpdateEvaluation(Evaluation evaluation);
	
	//qny
	//根据评价表id删除一条评价
	public void deleteEvalutionById(Integer id);
	
	//qny
	//用户查询自己出租物品受到的评价
	public List<Evaluation> getAllEvaluationFromZuke(Integer userId);

	//qny
	//管理员查看所有评价
	public List<Evaluation> getItemEvaluations();
}
