package com.qst.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qst.pojo.Item;
import com.qst.pojo.QueryVo;
@Repository
public interface ItemMapper {
	public List<Item> selectAll();

	public Integer findItemListByVoCount(QueryVo vo);

	public Item findItemId(String itemId);

	//qny
	//添加物品
	public void insertItem(Item item);

	public void deleteItem(int id);

	public Item findId(int id);

	public Item findItemIdUpdate(Item item);

	//qny
	//更新物品
	public void updateItem(Item item);

	public void updateItemStatus(Item item);

	//qny
	//根据商品Id删除商品
	public void deleteItemByItemId(String itemId);

	public void updateStatus(Item item);
	
	//qny
	//根据item更新物品状态
	public void updateStatusByItem(Item item);
	
	//qny
	//根据商品Id得到商品主人的Id
	public Integer getMasterIdByItemId(Integer itemId);
	
	//qny
	//根据masterId查询物品列表
	public List<Item> getItemListByMasterId(@Param("masterId") Integer masterId);
	
	//qny
	//根据物品编号查询物品
	public Item getItemByItemNumber(String itemNumber);
	
}
