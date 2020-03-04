package com.qst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qst.dao.EvaluationMapper;
import com.qst.pojo.Evaluation;
import com.qst.service.EvaluationService;

@Service
@Transactional
public class EvaluationServiceImpl implements EvaluationService{
	
	@Autowired
	private EvaluationMapper evaluationMapper;

	//qny
	//插入一条评价信息
	@Override
	public void insertEvaluation(Evaluation evaluation) {
		
		evaluationMapper.insertEvaluation(evaluation);
		
	}

	//qny
	//根据商品编号查询商品评价
	@Override
	public List<Evaluation> getEvalutionByItemNumber(String itemNumber) {
		return evaluationMapper.getEvalutionByItemNumber(itemNumber);
	}

	//qny
	//用户查看自己曾经给出的全部评价
	@Override
	public List<Evaluation> getMyZulingEvaluation(String zukeId) {
		return evaluationMapper.getMyZulingEvaluation(zukeId);
	}
	
	//qny
	//用户更改评价
	@Override
	public void UpdateEvaluation(Evaluation evaluation) {
		evaluationMapper.UpdateEvaluation(evaluation);
		
	}

	//qny
	//根据评价表id删除一条评价
	@Override
	public void deleteEvalutionById(Integer id) {
		evaluationMapper.deleteEvalutionById(id);
		
	}

	//qny
	//用户查询自己出租物品受到的评价
	@Override
	public List<Evaluation> getAllEvaluationFromZuke(Integer userId) {
		return evaluationMapper.getAllEvaluationFromZuke(userId);
	}

	//qny
	//管理员查看所有评价
	@Override
	public List<Evaluation> getItemEvaluations() {
		return evaluationMapper.getItemEvaluations();
	}

}
