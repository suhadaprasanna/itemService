/**
 * 
 */
package gdc.utility.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author suhada
 *
 */
@Component
public class Validation {
	
	private static final Logger logger = LoggerFactory.getLogger(Validation.class);

	String regex_email = "^(.+)@(.+)$";
	String regex_nic_sl = "^([\\d]{9}[v|V|x|X])+$|^([\\d]{13})+$";
	String regex_name = "^([A-Za-z\\s])+$";
	String regex_string = "^([A-Za-z])+$";
	String regex_number= "^([0-9])+$";
	String regex_number_decimal= "^\\d*\\.?\\d*$";
	String regex_date= "^([\\d]{4}[-|.|/|\\\\][\\d]{2}[-./\\\\][\\d]{2})$|^([\\d]{2}[-./\\\\][\\d]{2}[-./\\\\][\\d]{4})$";
	String regex_contact_number= "^\\d*$";
	
	/**
	 * 
	 * @param email check if email in corect format
	 * @return if match return true ela false
	 */
	public boolean isEmail(String email) {
		Pattern pattern = Pattern.compile(regex_email);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	/**
	 * 
	 * @param nic check if nic in correct format
	 * @return if correct return true ela false
	 */
	public boolean isNIC(String nic) {
		Pattern pattern = Pattern.compile(regex_nic_sl);
		Matcher matcher = pattern.matcher(nic);
		return matcher.matches();
	}
	
	/**
	 * 
	 * @param name check if given string is a name 
	 * acceptable only word characters and space.
	 * exclude special characters
	 * ex :  abc def, abc
	 * @return if name return true else false
	 */
	public boolean isName(String name) {
		Pattern pattern = Pattern.compile(regex_name);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}
	
	/**
	 * 
	 * @param text check if given string is a name 
	 * acceptable only word characters.
	 * exclude special characters
	 * @return
	 */
	public boolean isString(String text) {
		Pattern pattern = Pattern.compile(regex_string);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}
	
	/**
	 * 
	 * @param number check if it is a number
	 * only a number not accepting dot(.)
	 * @return
	 */
	public boolean isNumber(String number) {
		Pattern pattern = Pattern.compile(regex_number);
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}
	
	/**
	 * 
	 * @param number check if it is a number or is a decimal number
	 * ex : 123, 25.26, 0.5, .2
	 * @return
	 */
	public boolean isDecimalNumber(String number) {
		Pattern pattern = Pattern.compile(regex_number_decimal);
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}
	
	/**
	 * 
	 * @param date check if date
	 * types : year/month/date or date/month/year
	 * seperate character (-,\,/,.)
	 * @return
	 */
	public boolean isDate(String date) {
		Pattern pattern = Pattern.compile(regex_date);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}

	/**
	 * @param number
	 * @return
	 */
	public boolean isContactNumber(String number) {
		Pattern pattern = Pattern.compile(regex_contact_number);
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}
}
