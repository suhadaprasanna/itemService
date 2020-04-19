/**
 * 
 */
package gdc.item.common.form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gdc.item.common.form.validation.FormValidationUtil.FormKey;
import gdc.item.common.form.validation.FormValidation;
import gdc.item.common.form.validation.FormValidatorFactory;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
public abstract class Form {

	private static final Logger logger = LoggerFactory.getLogger(Form.class);
	
	public boolean validate(FormKey key,DataTransfer dataTrans) {
		logger.debug("------>>Start validate()<<------");
		FormValidation formValidation = new FormValidatorFactory().getFormValidator(key);
		if(formValidation != null) {
			logger.debug("------start validating------");
			return formValidation.validate(this, dataTrans);
		}else {
			logger.warn("----not found form validation-----");
			return false;
		}
	}
	
}
