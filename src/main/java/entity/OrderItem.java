package entity;
import java.io.Serializable;
import java.util.List;

public class OrderItem implements Serializable{
    private static final long serialVersionUID = 1L;
    private int orderId;
    private int productId;
    private int quantity;
    private Product product;
    public int getOrderId()
    {
        return this.orderId;
    }
    public void setOrderId(int orderId)
    {
        this.orderId=orderId;
    }

    public int getProductId()
    {
        return this.productId;
    }
    public void setProductId(int productId)
    {
        this.productId=productId;
    }

    public int getQuantity()
    {
        return this.quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity=quantity;
    }

    public Product getProduct()
    {
        return  this.product;
    }

    public void setProduct(Product product)
    {
        this.product=product;
    }
}
