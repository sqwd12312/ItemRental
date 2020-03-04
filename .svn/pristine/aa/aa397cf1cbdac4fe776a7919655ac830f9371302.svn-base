package com.qst.service;

import java.util.List;

import com.qst.pojo.Item;

public interface ItemService {
	List<Item> selectAll();

	Item findItemId(String ItemId);

	//qny
	//添加物品
	void insertItem(Item Item);

	void deleteItem(int id);

	Item findId(int id);
	
	Item findItemIdUpdate(Item Item);

	//qny
	//更新物品
	void updateItem(Item Item);
	
	void updateItemStatus(Item Item);
	
	public void deleteItemByItemId(String itemId);
	
	//qny
	//根据item更新物品状态
	public void updateStatusByItem(Item item);
	
	//qny
	//根据商品Id得到商品主人的Id
	public Integer getMasterIdByItemId(Integer itemId);
	
	
	//qny
	//根据masterId查询物品列表
	public List<Item> getItemListByMasterId(Integer masterId);	
	
	//qny
	//根据物品编号查询物品
	public Item getItemByItemNumber(String itemNumber);
	
	
	
	
}
