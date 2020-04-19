/**
 * 
 */
package gdc.item.common.form.validation;

import org.springframework.stereotype.Component;

import gdc.item.common.form.Form;
import gdc.item.common.form.ItemForm;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Component
public class ItemFormValidation implements FormValidation{

	/* (non-Javadoc)
	 * @see gdc.item.common.form.validation.FormValidation#validate(gdc.item.common.form.Form, gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public boolean validate(Form _form, DataTransfer dataTrans) {
		boolean status = true;
		ItemForm form = (ItemForm)_form;
		
		if(form.getName()==null||form.getName().equals("")) {
			status = false;
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING, "item name required");
		}
		if(form.getCode()==null||form.getCode().equals("")) {
			status = false;
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING, "item code required");
		}
		
		return status;
	}

}
