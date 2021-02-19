/** 
 * projectName: ItemRental 
 * fileName: queryItem.java 
 * packageName: com.qny.pojo 
 * date: 2020年4月17日上午9:32:13 
 * copyright(c) 2020, qny
 */
package com.qny.pojo;

/**   
 * @title: queryItem.java 
 * @package com.qny.pojo 
 * @description: 物品组合查询所需的类
 * @author: qny
 * @date: 2020年4月17日 上午9:32:13 
 * @version: V1.0   
*/
public class QueryItem {
   
	private String itemId;
	
	private String itemName;
	
	private double minPrice;
	
	private double maxPrice;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}


	
	
	
}
