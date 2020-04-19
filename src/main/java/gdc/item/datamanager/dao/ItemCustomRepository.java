/**
 * 
 */
package gdc.item.datamanager.dao;

import java.util.HashMap;

import gdc.item.datamanager.pojo.Item;

/**
 * @author suhada
 *
 */
public interface ItemCustomRepository extends CustomRepository<Item> {

	public HashMap<String, Object> findItems(HashMap<String, Object> param);
	
	public Long findItemCount(HashMap<String,Object> param);
}
