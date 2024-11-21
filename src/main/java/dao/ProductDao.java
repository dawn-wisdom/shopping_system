package dao;
import entity.Order;
import entity.OrderItem;
import entity.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDao {
    // 添加商品
    public void addProduct(Product p) throws SQLException {
        String sql = "insert into products(productName,productImage,productPrice,category,store,pnum,description) values(?,?,?,?,?,?,?)";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, p.getProductName(), p.getProductImage(), p.getProductPrice(),
                p.getCategory(), p.getStore(),p.getPnum(),  p.getDescription());
    }
    //删除商品
    public void deleteProduct(int productId)throws SQLException{
        String sql="delete from products where productId=?";
        QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql,productId);
    }
    // 查找所有商品
    public List<Product> listAll() throws SQLException {
        String sql = "select * from products";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Product>(Product.class));
    }
    //通过id找到产品
    public Product findProductById(int productId) throws SQLException {
        String sql = "select * from products where productId=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanHandler<Product>(Product.class), productId);
    }
    //通过种类找到产品
    public List<Product>findProductByCategory(String category) throws SQLException {
        String sql = "select * from products where category=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Product>(Product.class), category);
    }
    //通过店家找到产品
    public List<Product>findProductByStore(String store) throws SQLException {
        String sql = "select * from products where store=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Product>(Product.class), store);
    }
    // 生成订单时，将商品数量减少
    public void changeProductNum(Order order) throws SQLException {
        String sql = "update products set pnum=pnum-? where productId=?";
        QueryRunner runner = new QueryRunner();
        List<OrderItem> items = order.getOrderItems();
        Object[][] params = new Object[items.size()][2];

        for (int i = 0; i < params.length; i++) {
            params[i][0] = items.get(i).getQuantity();
            params[i][1] = items.get(i).getProductId();
        }
        runner.batch(DataSourceUtils.getConnection(), sql, params);
    }

    //编辑商品
    public void editProduct(Product product) throws SQLException {
        //1.创建集合并将商品信息添加到集合中
        List<Object> obj = new ArrayList<Object>();
        obj.add(product.getProductName());
        obj.add(product.getProductPrice());
        obj.add(product.getCategory());
        obj.add(product.getPnum());
        obj.add(product.getDescription());
        String sql  = "update products " +
                "set  productName=?,productPrice=?,category=?,pnum=?,description=? ";
        //判断是否有图片
        if (product.getProductImage() != null && product.getProductImage().trim().length() > 0) {
            sql += " ,productImage=?";
            obj.add(product.getProductImage());
        }
        sql += " where productId=?";
        obj.add(product.getProductId());
        System.out.println(sql);
        System.out.println(obj);
        //3.创建QueryRunner对象
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        //4.使用QueryRunner对象的update()方法更新数据
        runner.update(sql, obj.toArray());
    }
}
