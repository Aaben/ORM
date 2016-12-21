package model;

public class ProductBean {

	@Override
	public String toString() {
		return "ProductBean [id=" + id + ", name=" + name + ", price=" + price + ", make=" + make + ", expire="
				+ expire + "]\n";
	}

	private int id;
	private String name;
	private Double price; // double¼Æ¦r¸û¤j
	private java.util.Date make;
	private int expire;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public java.util.Date getMake() {
		return make;
	}

	public void setMake(java.util.Date make) {
		this.make = make;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}


}
