/**
 * 
 */
package gdc.item.controllers.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author suhada
 *
 */
@Component
public class ResponseGeneratorFactoryUtil {

	public enum ResponseKey{
		ADD_ITEM,GET_ITEMS,GET_ITEM,GET_COUNT
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseGeneratorFactoryUtil.class);
	
	public Class getResponseClass(ResponseKey key) {
		Class type = null;
		switch (key) {
		case ADD_ITEM:
			type = ResponseForItemGetBy.class;
			break;
		case GET_ITEM:
			type = ResponseForItemGetBy.class;
			break;
		case GET_ITEMS:
			type = ResponseForItemGetList.class;
			break;
		case GET_COUNT:
			type = ResponseForItemGetCount.class;
			break;
		default:
			type = null;
			break;
		}
		return type;
	}
}
