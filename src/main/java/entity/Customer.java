package entity;
import java.io.Serializable;

public class Customer implements Serializable{
    private static final long serialVersionUID = 1L;
    private int customerId;
    private String customerName;
    private String password;
    private String email;
    private String phone;

    public int getCustomerId() {
        return this.customerId;
    }
    public void setCustomerId(int customerId)
    {
        this.customerId=customerId;
    }

    public String getCustomerName()
    {
        return this.customerName;
    }
    public void setCustomerName(String customerName)
    {
        this.customerName=customerName;
    }

    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }

    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }

    public String getPhone()
    {
        return this.phone;
    }
    public void setPhone(String phone)
    {
        this.phone=phone;
    }

}
