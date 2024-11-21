package entity;
import java.io.Serializable;
public class Seller implements Serializable{
    private static final long serialVersionUID = 1L;
        private int sellerId;
        private String sellerName;
        private String storeName;
        private String password;
        private String email;
        private String phone;

        public int getSellerId()
        {
            return this.sellerId;
        }
        public void setSellerId(int id)
        {
            this.sellerId=id;
        }

        public String getSellerName()
        {
            return this.sellerName;
        }
        public void setSellerName(String sellerName)
        {
            this.sellerName=sellerName;
        }

        public String getStoreName()
        {
            return this.storeName;
        }
        public void setStoreName(String storeName)
        {
            this.storeName=storeName;
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
