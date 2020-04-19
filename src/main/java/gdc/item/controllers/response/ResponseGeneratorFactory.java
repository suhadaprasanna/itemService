/**
 * 
 */
package gdc.item.controllers.response;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import gdc.item.controllers.response.ResponseGeneratorFactoryUtil.ResponseKey;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Component
public class ResponseGeneratorFactory {

	private static final Logger logger = LoggerFactory.getLogger(ResponseGeneratorFactory.class);
	
	@Autowired
	private ResponseGeneratorFactoryUtil util;
	
	@Autowired
	ApplicationContext ctx;
	
	public ResponseGenerator getResponseGenerator(ResponseKey key) {
		Class type = util.getResponseClass(key);
		logger.debug("------>>CLASS TYPE : "+type);
		if(type!=null) {
			return (ResponseGenerator)ctx.getBean(type);
		}
		logger.warn("------>>NOT FOUND CLASS<<------");
		return null;
	}
	
	public Object getResponse(DataTransfer dataTrans, ResponseKey key,HttpServletRequest request) {
		ResponseGenerator generator = this.getResponseGenerator(key);
		if(generator!=null) {
			return generator.getResponse(dataTrans,request);
		}else {
			return new HashMap();
		}
	}
	
}
