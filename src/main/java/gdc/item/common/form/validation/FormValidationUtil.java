
/**
 * 
 */
package gdc.item.common.form.validation;


/**
 * @author suhada
 *
 */

public class FormValidationUtil {
	
	public enum FormKey {
		ITEMFORM
	}
	
	public static Class getFormClass(FormKey key) {
		if(key == FormKey.ITEMFORM) {
			return ItemFormValidation.class;
		}else {
			return null;
		}
	}
}
