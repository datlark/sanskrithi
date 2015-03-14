package saj.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dress")
public class Dress {

	@Id
	@Column(name = "id", length = 12)
	private String id;

	@Column(name = "sort_order", length = 10)
	private long orderID;

	@Column(name = "type", length = 4)
	private String type;

	@Column(name = "name", length = 15)
	private String name;

	@Column(name = "descrip", length = 40)
	private String desc;

	@Column(name = "price")
	private double price;

	@Column(name = "discount")
	private int discount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	

}
