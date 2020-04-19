/**
 * 
 */
package gdc.utility.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author suhada
 *
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		ctx = appContext;

	}

	public static ApplicationContext getApplicationContext() {
		System.out.println("inside ctx util :"+ctx);
		return ctx;
	}
}
