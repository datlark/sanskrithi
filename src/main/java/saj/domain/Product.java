package saj.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "product_id", length = 12)
	private String id;



	@Column(name = "type", length = 4)
	private String type;

	@Column(name = "name", length = 15)
	private String name;

	@Column(name = "descrip", length = 40)
	private String desc;

	@Column(name = "size", length = 1)
	private String size;
	
	@Column(name = "size_desc", length = 20)
	private String sizeDesc;

	@Column(name = "price")
	private double price;

	@Column(name = "discount")
	private int discount;
	
	@Column(name = "inventory")
	private int iventory;
	
	
	private double finalPrice = 0.00;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	/**
	 * @return the finalPrice
	 */
	public double getFinalPrice() {
		return finalPrice;
	}

	/**
	 * @param finalPrice the finalPrice to set
	 */
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = Math.round(finalPrice *100)/100.00;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSizeDesc() {
		return sizeDesc;
	}

	public void setDescSize(String sizeDesc) {
		this.sizeDesc = sizeDesc;
	}
	
}
