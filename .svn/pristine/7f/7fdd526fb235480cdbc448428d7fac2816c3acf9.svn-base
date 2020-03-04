package com.qst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qst.dao.ItemMapper;
import com.qst.pojo.Item;
import com.qst.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;

	@Override
	public List<Item> selectAll() {
		List<Item> Item = itemMapper.selectAll();
		return Item;
	}

	@Override
	public Item findItemId(String itemId) {
		Item Item = itemMapper.findItemId(itemId);
		return Item;
	}

	//qny
	//添加物品
	@Override
	public void insertItem(Item item) {
		// TODO Auto-generated method stub
		itemMapper.insertItem(item);
	}

	@Override
	public void deleteItem(int id) {
		// TODO Auto-generated method stub
		itemMapper.deleteItem(id);
	}

	@Override
	public Item findItemIdUpdate(Item item) {
		Item list = itemMapper.findItemIdUpdate(item);
		return list;  
	}


	//qny
	//更新物品
	@Override
	public void updateItem(Item item) {
		itemMapper.updateItem(item);

	}

	@Override
	public Item findId(int id) {
		Item list = itemMapper.findId(id);
		return list;
	}

	@Override
	public void updateItemStatus(Item item) {
		// TODO Auto-generated method stub
		itemMapper.updateItemStatus(item);
	}

	@Override
	public void deleteItemByItemId(String itemId) {
		itemMapper.deleteItemByItemId(itemId);

	}

	//qny
	//根据item更新物品状态
	@Override
	public void updateStatusByItem(Item item) {
		itemMapper.updateStatusByItem(item);
		
	}

	//qny
	//根据商品Id得到商品主人的Id
	@Override
	public Integer getMasterIdByItemId(Integer itemId) {
		return itemMapper.getMasterIdByItemId(itemId);
	}
	
	//qny
	//根据masterId查询物品列表
	@Override
	public List<Item> getItemListByMasterId(Integer masterId) {	
		return itemMapper.getItemListByMasterId(masterId);
	}

	//qny
	//根据物品编号查询物品
	@Override
	public Item getItemByItemNumber(String itemNumber) {
		return itemMapper.getItemByItemNumber(itemNumber);
	}

}
