/**
 * 
 */
package gdc.item.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suhada
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping(value="/test")
	public Object test() {
		return "Test";
	}
}
