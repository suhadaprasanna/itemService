/**
 * 
 */
package gdc.item.controllers;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdc.item.common.form.ItemForm;
import gdc.item.controllers.response.ResponseGeneratorFactory;
import gdc.item.controllers.response.ResponseGeneratorFactoryUtil.ResponseKey;
import gdc.item.service.ItemService;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@RestController
@RequestMapping("/item")
public class ItemController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ResponseGeneratorFactory resFactory;
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping(value="/add")
	public Object addItem(@ModelAttribute("itemForm") ItemForm form, HttpServletRequest req) {
		logger.debug("------>> start additem() <<------");
		DataTransfer dataTrans = new DataTransfer();
		try {
			dataTrans.addInput(Key.FORM, form);
			dataTrans = this.itemService.addItem(dataTrans);
		} catch (Exception e) {
			logger.error("------>> Error :",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.ADD_ITEM,req);
		logger.debug("------>> end additem() <<------");
		return res;
	}
	
	@PostMapping(value="/update")
	public Object updateItem(@ModelAttribute("itemForm") ItemForm form, HttpServletRequest req) {
		logger.debug("------>> start update() <<------");
		DataTransfer dataTrans = new DataTransfer();
		try {
			dataTrans.addInput(Key.FORM, form);
			dataTrans = this.itemService.updateItem(dataTrans);
		} catch (Exception e) {
			logger.error("------>> Error :",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.ADD_ITEM,req);
		logger.debug("------>> end update() <<------");
		return res;
	}
	
	@PostMapping(value="/get")
	public Object getItems(@ModelAttribute("itemForm") ItemForm form, HttpServletRequest req) {
		logger.debug("------>> start additem() <<------");
		DataTransfer dataTrans = new DataTransfer();
		try {
			String auth = req.getHeader("Authorization");
			logger.debug("---->> auth:"+auth);
			String generatedString = RandomStringUtils.randomAlphanumeric(10);
			logger.debug("---->> string:"+generatedString+new Date().getTime());
			logger.debug("---->> uuid:"+UUID.randomUUID().toString());
			dataTrans.addInput(Key.FORM, form);
			dataTrans = this.itemService.getItems(dataTrans);
		} catch (Exception e) {
			logger.error("------>> Error :",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.GET_ITEMS,req);
		logger.debug("------>> end additem() <<------");
		return res;
	}
	
	@PostMapping(value="/get/bycode")
	public Object getItemByCode(@ModelAttribute("itemForm") ItemForm form, HttpServletRequest req) {
		logger.debug("------>> start getItemByCode() <<------");
		DataTransfer dataTrans = new DataTransfer();
		try {
			dataTrans.addInput(Key.FORM, form);
			dataTrans = this.itemService.getByCode(dataTrans);
		} catch (Exception e) {
			logger.error("------>> Error :",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.GET_ITEM,req);
		logger.debug("------>> end getItemByCode() <<------");
		return res;
	}
	
	@PostMapping(value="/get/bycodelike")
	public Object getItemByCodeLike(@ModelAttribute("itemForm") ItemForm form, HttpServletRequest req) {
		logger.debug("------>> start getItemByCode() <<------");
		DataTransfer dataTrans = new DataTransfer();
		try {
			dataTrans.addInput(Key.FORM, form);
			dataTrans = this.itemService.getByCodeLike(dataTrans);
		} catch (Exception e) {
			logger.error("------>> Error :",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.GET_ITEMS,req);
		logger.debug("------>> end getItemByCode() <<------");
		return res;
	}
	
	@PostMapping(value="/get/byname")
	public Object getItemByName(@ModelAttribute("itemForm") ItemForm form, HttpServletRequest req) {
		logger.debug("------>> start getItemByName() <<------");
		DataTransfer dataTrans = new DataTransfer();
		try {
			dataTrans.addInput(Key.FORM, form);
			dataTrans = this.itemService.getByName(dataTrans);
		} catch (Exception e) {
			logger.error("------>> Error :",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.GET_ITEM,req);
		logger.debug("------>> end getItemByName() <<------");
		return res;
	}
	
	@PostMapping(value="/get/bynamelike")
	public Object getItemByNameLike(@ModelAttribute("itemForm") ItemForm form, HttpServletRequest req) {
		logger.debug("------>> start getItemByName() <<------");
		DataTransfer dataTrans = new DataTransfer();
		try {
			dataTrans.addInput(Key.FORM, form);
			dataTrans = this.itemService.getByNameLike(dataTrans);
		} catch (Exception e) {
			logger.error("------>> Error :",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.GET_ITEMS,req);
		logger.debug("------>> end getItemByName() <<------");
		return res;
	}
	
	@PostMapping(value="/enable_disable")
	public Object itemEnableDisable(@ModelAttribute("itemForm") ItemForm form, HttpServletRequest req) {
		logger.debug("------>> start itemEnableDisable() <<------");
		DataTransfer dataTrans = new DataTransfer();
		try {
			dataTrans.addInput(Key.FORM, form);
			dataTrans = this.itemService.itemEnableDisable(dataTrans);
		} catch (Exception e) {
			logger.error("------>> Error :",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.GET_ITEM,req);
		logger.debug("------>> end itemEnableDisable() <<------");
		return res;
	}

	@PostMapping(value="/get/count")
	public Object getItemCount(@ModelAttribute("itemForm") ItemForm form, HttpServletRequest req) {
		logger.debug("------>> start getItemCount() <<------");
		DataTransfer dataTrans = new DataTransfer();
		try {
			dataTrans.addInput(Key.FORM, form);
			dataTrans = this.itemService.getItemCount(dataTrans);
		} catch (Exception e) {
			logger.error("------>> Error :",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.GET_COUNT,req);
		logger.debug("------>> end getItemCount() <<------");
		return res;
	}
}
