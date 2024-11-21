package service;

import dao.ProductDao;
import entity.Product;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ProductService {
    private ProductDao dao = new ProductDao();
    // 添加商品
    public void addProduct(Product p){
        try {
            dao.addProduct(p);
            System.out.println("添加商品成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("添加商品失败");
        }
    }
    //删除商品
    public void deleteProduct(int productId)
    {
        try{
            dao.deleteProduct(productId);
            System.out.println("删除商品成功");
        }catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("删除商品失败");
        }
    }
    // 查找所有商品信息
    public List<Product> listAll(){
        List<Product> products=null;
        try {
            products= dao.listAll();
            System.out.println("查看商品成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("查看商品失败");
        }
        return products;
    }
    //根据id查找商品
    public Product findProductById(int productId)
    {
        Product product=new Product();
        try {
            product=dao.findProductById(productId);
            System.out.println("查询成功");
        }catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("查询失败");
        }
        return product;
    }
    //通过种类查找商品
    public List<Product>findProductByCategory(String category)
    {
        List<Product> products=null;
        try{
            products=dao.findProductByCategory(category);
            System.out.println("按种类查询成功");
        }catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("按种类查询失败");
        }
        return products;
    }
    public List<Product>findProductByStore(String store)
    {
        List<Product> products=null;
        try{
            products=dao.findProductByStore(store);
            System.out.println("店家视图查询成功");
        }catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("店家视图查询失败");
        }
        return products;
    }
    public boolean editProduct(Product product)
    {
        try {
            dao.editProduct(product);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
