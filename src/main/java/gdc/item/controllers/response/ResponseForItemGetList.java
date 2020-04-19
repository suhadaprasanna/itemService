/**
 * 
 */
package gdc.item.controllers.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import gdc.item.controllers.response.ResponseGeneratorImpl;
import gdc.item.datamanager.pojo.Item;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Component
public class ResponseForItemGetList extends ResponseGeneratorImpl{

	private static final Logger logger = LoggerFactory.getLogger(ResponseForItemGetList.class);
	
	/* (non-Javadoc)
	 * @see gdc.item.controllers.response.ResponseGenerator#generate(gdc.utility.dataservice.DataTransfer, java.util.HashMap)
	 */
	@Override
	public HashMap<String, Object> generate(DataTransfer dataTrans, HashMap<String, Object> res) {
		logger.debug("------>>Start generate<<------");
		try {
			// create inputs
			HashMap<String, Object> inputs = new HashMap<String, Object>();
			this.processInputs(dataTrans, inputs);
			
			// create outputs
			HashMap<String, Object> outputs = new HashMap<String, Object>();
			this.processOutputs(dataTrans, outputs);
			
			res.put(Key.INPUTS, inputs);
			res.put(Key.OUTPUTS, outputs);
		}catch (Exception e) {
			logger.error("Error while generating response "+e);
		}
		logger.debug("------>>End generate<<------");
		return res;
	}

	/**
	 * @param dataTrans
	 * @param outputs
	 */
	private void processOutputs(DataTransfer dataTrans, HashMap<String, Object> outputs) {
		logger.debug("------>> start processOutputs<<------");
		try {
			List<Item> items = (List<Item>)dataTrans.getOutput("item_list");
			List _items = new ArrayList<>();
			if(items!=null && items.size()>0) {
				items.stream().forEach(item->{
					HashMap _item = new HashMap();
					_item.put("id", item.getId());
					_item.put("name", item.getName());
					_item.put("code", item.getCode());
					_item.put("barcode", item.getBarcode());
					_item.put("sys_add_date", item.getSys_add_date());
					_item.put("status", item.getStatus());
					_items.add(_item);
				});
			}
			outputs.put("list", _items);

			if(dataTrans.getOutput("total_count") != null) {
				Long total_count = (Long)dataTrans.getOutput("total_count");
				outputs.put("total_count", total_count);
			}
			if(dataTrans.getOutput("page_count") != null) {
				int page_count = (int)dataTrans.getOutput("page_count");
				outputs.put("page_count", page_count);
			}
		}catch(Exception e) {
			logger.error("------>> Error in finding Shop Object",e);
		}
		logger.debug("------>> end processOutputs<<------");
	}

	/**
	 * @param dataTrans
	 * @param inputs
	 */
	private void processInputs(DataTransfer dataTrans, HashMap<String, Object> inputs) {
		logger.debug("------>> start processInputs<<------");
		inputs.put("form", dataTrans.getInput("form"));
		logger.debug("------>> end processInputs<<------");
	}

	/* (non-Javadoc)
	 * @see gdc.item.controllers.response.ResponseGenerator#generate(gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public Object generate(DataTransfer dataTrans) {
		logger.debug("------>> start generate<<------");
		List _items = new ArrayList<>();
		try {
			List<Item> items = (List<Item>)dataTrans.getOutput("item_list");
			if(items!=null && items.size()>0) {
				items.stream().forEach(item->{
					HashMap _item = new HashMap();
					_item.put("id", item.getId());
					_item.put("name", item.getName());
					_item.put("code", item.getCode());
					_item.put("barcode", item.getBarcode());
					_item.put("sys_add_date", item.getSys_add_date());
					_item.put("status", item.getStatus());
					_items.add(_item);
				});
			}
		}catch(Exception e) {
			logger.error("------>> Error in finding Shop Object",e);
		}
		logger.debug("------>> end generate<<------");
		return _items;
	}

}
