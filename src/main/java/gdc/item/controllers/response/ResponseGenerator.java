/**
 * 
 */
package gdc.item.controllers.response;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
public interface ResponseGenerator {

	public HashMap<String, Object> generate(DataTransfer dataTrans,HashMap<String,Object> res);
	
	public Object generate(DataTransfer dataTrans);
	
	public HashMap<String, Object> defaultBehaviour(DataTransfer dataTrans,HashMap<String,Object> res);
	
	public Object getResponse(DataTransfer dataTrans,HttpServletRequest request);
}
