/**
 * 
 */
package gdc.item.controllers.response;

import java.util.HashMap;

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
public class ResponseForItemGetBy extends ResponseGeneratorImpl{

	private static final Logger logger = LoggerFactory.getLogger(ResponseForItemGetBy.class);
	
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
			Item item = (Item)dataTrans.getOutput(Key.POJO_ITEM);
			if(item!=null) {
				HashMap _item = new HashMap();
				_item.put("id", item.getId());
				_item.put("name", item.getName());
				_item.put("code", item.getCode());
				_item.put("barcode", item.getBarcode());
				_item.put("sys_add_date", item.getSys_add_date());
				_item.put("status", item.getStatus());
				outputs.put("item", _item);
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
		HashMap _item = null;
		try {
			Item item = (Item)dataTrans.getOutput(Key.POJO_ITEM);
			if(item!=null) {
				_item = new HashMap();
				_item.put("id", item.getId());
				_item.put("name", item.getName());
				_item.put("code", item.getCode());
				_item.put("sys_add_date", item.getSys_add_date());
				_item.put("status", item.getStatus());
			}
		}catch(Exception e) {
			logger.error("------>> Error in finding Shop Object",e);
		}
		logger.debug("------>> end generate<<------");
		return _item;
	}

}
