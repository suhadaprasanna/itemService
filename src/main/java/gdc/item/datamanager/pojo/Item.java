/**
 * 
 */
package gdc.item.datamanager.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author suhada
 *
 */
@Entity
@Table(name = "item", catalog = "gdc_item")
public class Item {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	@Column(name = "code", nullable = false, length = 100)
	private String code;
	@Column(name = "item_code", nullable = false, length = 100)
	private String item_code;
	@Column(name = "barcode", nullable = false, length = 100)
	private String barcode;
	@Column(name = "status", length = 5)
	private String status;
	@Temporal(TemporalType.DATE)
	@Column(name = "sys_add_date")
	private Date sys_add_date;
	
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
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	
	
}
