/**
 * 
 */
package gdc.item.service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdc.item.common.form.ItemForm;
import gdc.item.common.form.validation.FormValidationUtil.FormKey;
import gdc.item.datamanager.access.ItemAccess;
import gdc.item.datamanager.pojo.Item;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Service
public class ItemService {

	private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
	
	@Autowired
	private ItemAccess itemAccess;
	
	public DataTransfer addItem(DataTransfer dataTrans) {
		logger.debug("------>> start addItem() <<------");
		try {
			ItemForm form = (ItemForm)dataTrans.getInput(Key.FORM);
			
			if(form==null) {
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.FAIL, "not found form details");
				return dataTrans;
			}
			
			if(!form.validate(FormKey.ITEMFORM, dataTrans)) {
				return dataTrans;
			}
			logger.debug("------>> after validation");
			
			Item item = this.itemAccess.getByCode(form.getCode());
			if(item != null) {
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.FAIL, "item code already exist");
				return dataTrans;
			}
			
			item = this.itemAccess.getByName(form.getName());
			if(item != null) {
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.FAIL, "item name already exist");
				return dataTrans;
			}
			
			item = this.setItemData(form, item);
			item.setItem_code(UUID.randomUUID().toString());
			boolean status = false;
			try {
				status = this.itemAccess.save(item);
			} catch (Exception e) {
				logger.error("Error in saving: ",e);
				dataTrans.setStatus(Status.ERROR);
				dataTrans.addOutput(Key.ERROR, "save failed (something went wrong)");
			}
			logger.debug("------>> status: "+status);
			if(status) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.POJO_ITEM, item);
			}else {
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.FAIL, "save failed");
			}
			
		} catch (Exception e) {
			logger.error("Error : ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		logger.debug("------>> end addItem() <<------");
		return dataTrans;
	}
	
	public DataTransfer updateItem(DataTransfer dataTrans) {
		logger.debug("------>> start updateItem() <<------");
		try {
			ItemForm form = (ItemForm)dataTrans.getInput(Key.FORM);
			
			if(form==null) {
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.FAIL, "not found form details");
				return dataTrans;
			}
			
			if(!form.validate(FormKey.ITEMFORM, dataTrans)) {
				return dataTrans;
			}
			logger.debug("------>> after validation");
			
			Item item = this.itemAccess.getById(form.getId());
			logger.debug("------>> item:"+item);
			if(item == null) {
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.FAIL, "not found item");
				return dataTrans;
			}
			
			Item updated_item = this.setItemData(form, item);
			boolean status = false;
			
			try {
				status = this.itemAccess.save(updated_item);
			} catch (Exception e) {
				logger.error("Error : ",e);
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.ERROR, " updating failed (something went wrong)");
			}
			
			logger.debug("------>> status: "+status);
			if(status) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.POJO_ITEM, updated_item);
			}else {
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.FAIL, "updating failed");
			}
			
		} catch (Exception e) {
			logger.error("Error : ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		logger.debug("------>> end updateItem() <<------");
		return dataTrans;
	}
	
	public DataTransfer getItems(DataTransfer dataTrans) {
		logger.debug("------>> start getItems() <<------");
		try {
			ItemForm form = (ItemForm)dataTrans.getInput(Key.FORM);
			
			if(form==null) {
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.FAIL, "not found form details");
				return dataTrans;
			}
			
			logger.debug("------>> count:" +form.getCount() + ", start:" + form.getStart() + ", order:" + form.getOrder() + ", page:"+ form.getPage() +"");
			
			HashMap<String, Object> param = new HashMap();
			param.put("count", form.getCount());
			param.put("page", form.getPage());
			param.put("name", form.getName());
			param.put("status", form.getStatus());
			param.put("code", form.getCode());
			param.put("barcode", form.getBarcode());
			
			try {
				param = this.itemAccess.getItems(param);
				dataTrans.setStatus(Status.SUCCESS);
			}catch(Exception e) {
				logger.error("------>> Error :",e);
				dataTrans.setStatus(Status.ERROR);
				dataTrans.addOutput(Key.ERROR, "items getting failed");
			}
			List<Item> list = (List<Item>)param.get("list");
			Long total_count = (Long)param.get("total_count");
			int page_count = (int)param.get("page_count");
			
			dataTrans.addOutput("item_list", list);
			dataTrans.addOutput("total_count", total_count);
			dataTrans.addOutput("page_count", page_count);
			
		} catch (Exception e) {
			logger.error("Error : ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		logger.debug("------>> end getItems() <<------");
		return dataTrans;
	}
	
	public DataTransfer getByCode(DataTransfer dataTrans) {
		logger.debug("------>> start getByCode() <<------");
		try {
			ItemForm form = (ItemForm)dataTrans.getInput(Key.FORM);
			
			if(form == null || form.getCode() == null || form.getCode().equals("")) {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found code");
				return dataTrans;
			}
			logger.debug("------>> code: "+form.getCode());
			Item item = this.itemAccess.getByCode(form.getCode());
			if(item != null) {				
				dataTrans.setStatus(Status.SUCCESS);
			}else {
				dataTrans.setStatus(Status.FAIL);
			}
			dataTrans.addOutput(Key.POJO_ITEM, item);
			
		} catch (Exception e) {
			logger.error("Error : ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		logger.debug("------>> end getByCode() <<------");
		return dataTrans;
	}
	
	public DataTransfer getByCodeLike(DataTransfer dataTrans) {
		logger.debug("------>> start getByCodeLike() <<------");
		try {
			ItemForm form = (ItemForm)dataTrans.getInput(Key.FORM);
			
			if(form == null || form.getCode() == null || form.getCode().equals("")) {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found code");
				return dataTrans;
			}
			logger.debug("------>> code: "+form.getCode());
			List<Item> list = this.itemAccess.getByCodeLike(form.getCode());
			if(list != null) {				
				dataTrans.setStatus(Status.SUCCESS);
			}else {
				dataTrans.setStatus(Status.FAIL);
			}
			dataTrans.addOutput("item_list", list);
			
		} catch (Exception e) {
			logger.error("Error : ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		logger.debug("------>> end getByCodeLike() <<------");
		return dataTrans;
	}
	
	public DataTransfer getByName(DataTransfer dataTrans) {
		logger.debug("------>> start getByName() <<------");
		try {
			ItemForm form = (ItemForm)dataTrans.getInput(Key.FORM);
			
			if(form == null || form.getName() == null || form.getName().equals("")) {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found name");
				return dataTrans;
			}
			logger.debug("------>> name: "+form.getName());
			Item item = this.itemAccess.getByName(form.getName());
			if(item != null) {				
				dataTrans.setStatus(Status.SUCCESS);
			}else {
				dataTrans.setStatus(Status.FAIL);
			}
			dataTrans.addOutput(Key.POJO_ITEM, item);
			
		} catch (Exception e) {
			logger.error("Error : ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		logger.debug("------>> end getByName() <<------");
		return dataTrans;
	}
	
	public DataTransfer getByNameLike(DataTransfer dataTrans) {
		logger.debug("------>> start getByNameLike() <<------");
		try {
			ItemForm form = (ItemForm)dataTrans.getInput(Key.FORM);
			
			if(form == null || form.getName() == null || form.getName().equals("")) {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found name");
				return dataTrans;
			}
			logger.debug("------>> name: "+form.getName());
			List<Item> list = this.itemAccess.getByNameLike(form.getName());
			if(list != null) {				
				dataTrans.setStatus(Status.SUCCESS);
			}else {
				dataTrans.setStatus(Status.FAIL);
			}
			dataTrans.addOutput("item_list", list);
			
		} catch (Exception e) {
			logger.error("Error : ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		logger.debug("------>> end getByNameLike() <<------");
		return dataTrans;
	}
	
	public DataTransfer itemEnableDisable(DataTransfer dataTrans) {
		logger.debug("------>> start itemEnableDisable() <<------");
		try {
			ItemForm form = (ItemForm)dataTrans.getInput(Key.FORM);
			
			if(form.getCode() == null || form.getCode().equals("") || form.getStatus() == null || form.getStatus().equals("")) {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found code or status");
				return dataTrans;
			}
			
			logger.debug("------>> code:"+form.getCode()+", status:"+form.getStatus());
			Item item = this.itemAccess.getByCode(form.getCode());
			if(form.getStatus().equalsIgnoreCase("Y")) {
				item.setStatus("Y");
			}else {
				item.setStatus("N");
			}
			
			boolean status = this.itemAccess.save(item);
			if(status) {
				dataTrans.setStatus(Status.SUCCESS);
			}else {
				dataTrans.setStatus(Status.FAIL);
			}
			dataTrans.addOutput(Key.POJO_ITEM, item);
		} catch (Exception e) {
			logger.error("Error : ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		logger.debug("------>> end itemEnableDisable() <<------");
		return dataTrans;
	}
	
	public DataTransfer getItemCount(DataTransfer dataTrans) {
		logger.debug("------>> start getItemCount() <<------");
		try {
			ItemForm form = (ItemForm)dataTrans.getInput(Key.FORM);
			
			if(form==null) {
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.FAIL, "not found form details");
				return dataTrans;
			}
			
			HashMap<String, Object> param = new HashMap();
			param.put("name", form.getName());
			param.put("status", form.getStatus());
			param.put("code", form.getCode());
			param.put("barcode", form.getBarcode());
			Long count=new Long(0);
			try {
				count = this.itemAccess.getItemCount(param);
			}catch(Exception e) {
				dataTrans.setStatus(Status.ERROR);
			}
			dataTrans.setStatus(Status.SUCCESS);
			dataTrans.addOutput("count", count);
			
		} catch (Exception e) {
			logger.error("Error : ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		logger.debug("------>> end getItemCount() <<------");
		return dataTrans;
	}
	
	private Item setItemData(ItemForm form, Item item) {
		if(item == null) {
			item = new Item();
		}
		if(item.getName()==null||item.getName().equals("")||!item.getName().equals(form.getName())) {
			item.setName(form.getName());
		}
		if(item.getCode()==null||item.getCode().equals("")||!item.getCode().equals(form.getCode())) {
			item.setCode(form.getCode());
		}
		if(item.getBarcode()==null||item.getBarcode().equals("")||!item.getBarcode().equals(form.getBarcode())) {
			item.setBarcode(form.getBarcode());
		}
		if(item.getStatus()==null||item.getStatus().equals("")||!item.getStatus().equals(form.getStatus())) {
			item.setStatus(form.getStatus());
		}
		if(item.getSys_add_date()==null && form.getSys_add_date() != null) {
			item.setSys_add_date(form.getSys_add_date());
		}
		return item;
	}

}
