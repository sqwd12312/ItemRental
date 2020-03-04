package com.qst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qst.dao.HetongMapper;
import com.qst.pojo.Apply;
import com.qst.pojo.Hetong;
import com.qst.pojo.Userlist;
import com.qst.service.HetongService;

@Service
@Transactional
public class HetongServiceImpl implements HetongService {
	@Autowired
	private HetongMapper hetongMapper;

	@Override
	public void inserthetong(Hetong hetong) {
		hetongMapper.inserthetong(hetong);

	}

	@Override
	public Hetong findhetong(String itemId) {
		Hetong hetong = hetongMapper.findhetong(itemId);
		return hetong;
	}

	@Override
	public void updatehetong(Hetong hetong) {

		hetongMapper.updatehetong(hetong);
	}

	@Override
	public void deletehetong(String house_id) {
		// TODO Auto-generated method stub
		hetongMapper.deletehetong(house_id);

	}
	
	//qny
	//根据商品id查询对应申请
	@Override
	public Hetong getApplyByItemId(Integer itemId) {
		Hetong hetong = hetongMapper.getApplyByItemId(itemId);
		return hetong;
	}

	//qny
	//根据userId查询在租列表
	@Override
	public List<Hetong> getHetongByUserId(Integer userId) {
		return hetongMapper.getHetongByUserId(userId);
	}

	//qny
	//查询所有合同
	@Override
	public List<Hetong> getAllHetong() {
		return hetongMapper.getAllHetong();
	}

	//qny
	//根据商品的itemId查询商品合同
	@Override
	public Hetong getHetongByItemId(Integer itemId) {
		return hetongMapper.getHetongByItemId(itemId);
	}

	//qny
	//租客查询所有的出租列表
	@Override
	public List<Hetong> getAllHetongByzuke(Integer userId) {
		return hetongMapper.getAllHetongByzuke(userId);
	}

}
