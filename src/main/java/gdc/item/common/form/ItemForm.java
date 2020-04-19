/**
 * 
 */
package gdc.item.common.form;

import java.io.File;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author suhada
 *
 */
public class ItemForm extends Form{

	private long id;
	private String name;
	private String code;
	private String barcode;
	private String status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sys_add_date;
	private String image_name;
	private File image_source;
	
	private int start;
	private int page;
	private int count;
	private String order;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getSys_add_date() {
		return sys_add_date;
	}
	public void setSys_add_date(Date sys_add_date) {
		this.sys_add_date = sys_add_date;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
