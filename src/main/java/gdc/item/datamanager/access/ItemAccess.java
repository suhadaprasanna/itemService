/**
 * 
 */
package gdc.item.datamanager.access;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.item.datamanager.dao.ItemRepository;
import gdc.item.datamanager.pojo.Item;


/**
 * @author suhada
 *
 */
@Repository("itemAccess")
@Transactional
public class ItemAccess {
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ItemAccess.class);
	
	@Autowired
	private ItemRepository itemRepository;
	
	public boolean save(Item item) throws Exception {
		logger.debug("------>>start save()<<------");
		boolean status = false;
		this.itemRepository.save(item);
		if(item.getId()>0) {
			status = true;
		}
		logger.debug("------>>end save()<<------");
		return status;
	}
	
	public Item getById(Long id) {
		logger.debug("------>>start getById()<<------");
		Optional<Item> result = this.itemRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		logger.debug("------>>end getById()<<------");
		return null;
	}
	
	public List<Item> getByIds(List<Long> ids) {
		logger.debug("------>>start getById()<<------");
		List<Item> results = this.itemRepository.findAllById(ids);
		logger.debug("------>>end getById()<<------");
		return results;
	}

	/**
	 * @param param
	 */
	public HashMap getItems(HashMap<String, Object> param) {
		logger.debug("------>>start getItems()<<------");
		param = this.itemRepository.findItems(param);
		logger.debug("------>>end getItems()<<------");
		return param;
	}

	public Item getByCode(String code) {
		logger.debug("------>>start getByCode()<<------");
		Item item = null;
		item = this.itemRepository.findByItemCode(code);
		logger.debug("------>>end getByCode()<<------");
		return item;
	}
	
	public List<Item> getByCodeLike(String code) {
		logger.debug("------>>start getByCodeLike()<<------");
		List<Item> list = null;
		list = this.itemRepository.findByItemCodeLike(code);
		logger.debug("------>>end getByCodeLike()<<------");
		return list;
	}
	
	public Item getByName(String name) {
		logger.debug("------>>start getByName()<<------");
		Item item = null;
		item = this.itemRepository.findByItemName(name);
		logger.debug("------>>end getByName()<<------");
		return item;
	}
	
	public List<Item> getByNameLike(String name) {
		logger.debug("------>>start getByNameLike()<<------");
		List<Item> list = null;
		list = this.itemRepository.findByItemNameLike(name);
		logger.debug("------>>end getByNameLike()<<------");
		return list;
	}
	
	public List<Item> getByCodes(String[] codes){
		logger.debug("------>>start getByCodes()<<------");
		List<Item> list = null;
		list = this.itemRepository.findByItemCodes(codes);
		logger.debug("------>>end getByCodes()<<------");
		return list;
	}

	public Long getItemCount(HashMap<String, Object> param) {
		logger.debug("------>>start getItemCount()<<------");
		Long count = this.itemRepository.findItemCount(param);
		logger.debug("------>>end getItemCount()<<------");
		return count;
	}

}
