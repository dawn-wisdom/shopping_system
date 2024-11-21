package entity;
import java.io.Serializable;

public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	private int productId;
	private String productName;
	private String productImage;
	private double productPrice;
	private String category;
	private String description;
	private String store;
	private int pnum;//库存
	
	public int getProductId()
	{
		return this.productId;
	}
	public void setProductId(int productId)
	{
		this.productId=productId;
	}

	public String getProductName()
	{
		return this.productName;
	}
	public void setProductName(String productName)
	{
		this.productName=productName;
	}

	public String getProductImage()
	{
		return this.productImage;
	}
	public void setProductImage(String productImage)
	{
		this.productImage=productImage;
	}
	public double getProductPrice()
	{
		return this.productPrice;
	}
	public void setProductPrice(double productPrice)
	{
		this.productPrice=productPrice;
	}

	public String getCategory()
	{
		return this.category;
	}
	public void setCategory(String category)
	{
		this.category=category;
	}

	public String  getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description=description;
	}

	public String getStore()
	{
		return this.store;
	}
	public void setStore(String store)
	{
		this.store=store;
	}

	public int getPnum()
	{
		return this.pnum;
	}
	public void setPnum(int pnum)
	{
		this.pnum=pnum;
	}

	@Override
	public int hashCode() {
		return this.productId; // 或者使用 Integer.hashCode(this.id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (this.productId == other.getProductId()) {

			return true;
		}else{
			return false;
		}
	}
}
