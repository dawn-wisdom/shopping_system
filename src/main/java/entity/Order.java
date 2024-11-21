package entity;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	int orderId;
	int customerId;
	double total_cost;
	int status;
	Date order_time;
	String receive_name;
	String receive_phone;
	String receive_address;
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	private Customer customer;
	public int getOrderId()
	{
		return this.orderId;
	}
	public void setOrderId(int orderId)
	{
		this.orderId=orderId;
	}

	public int getCustomerId()
	{
		return this.customerId;
	}
	public void setCustomerId(int customerId)
	{
		this.customerId=customerId;
	}

	public double getTotal_cost()
	{
		return this.total_cost;
	}
	public void setTotal_cost(double total_cost)
	{
		this.total_cost=total_cost;
	}

	public int getStatus()
	{
		return this.status;
	}
	public void setStatus(int status)
	{
		this.status=status;
	}

	public Date getOrder_time()
	{
		return this.order_time;
	}
	public void setOrder_time(Date order_time)
	{
		this.order_time=order_time;
	}

	public String getReceive_name()
	{
		return this.receive_name;
	}
	public void setReceive_name(String receive_name)
	{
		this.receive_name=receive_name;
	}

	public String getReceive_phone()
	{
		return this.receive_phone;
	}
	public void setReceive_phone(String phone)
	{
		this.receive_phone=phone;
	}

	public String getReceive_address()
	{
		return this.receive_address;
	}
	public void setReceive_address(String receive_address)
	{
		this.receive_address=receive_address;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Customer getCustomer()
	{
		return this.customer;
	}
	public void setCustomer(Customer customer)
	{
		this.customer=customer;
	}


}
