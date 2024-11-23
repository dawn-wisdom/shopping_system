package utils;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据源工具
 */
public class DataSourceUtils {
    private static final DataSource dataSource = new ComboPooledDataSource();
    private static final ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    static {
        try {
            ComboPooledDataSource cpds = (ComboPooledDataSource) dataSource;
            cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/javaweb?serverTimezone=GMT-8");
            cpds.setUser("root");
            cpds.setPassword("root");
            cpds.setInitialPoolSize(10);
            cpds.setMaxPoolSize(100);
            cpds.setMinPoolSize(10);
            cpds.setMaxIdleTime(30);
            cpds.setMaxStatements(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDataSource() {
        System.out.println("获取数据源");
        return dataSource;
    }
    /**
     * 当DBUtils需要手动控制事务时，调用该方法获得一个连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection con = tl.get();
        if (con == null) {
            con = dataSource.getConnection();
            tl.set(con);
        }
        return con;
    }
    /**
     * 开启事务
     * @throws SQLException
     */
    public static void startTransaction() throws SQLException {
        Connection con = getConnection();
        if (con != null)
            con.setAutoCommit(false);
    }
    /**
     * 从ThreadLocal中释放并且关闭Connection,并结束事务
     * @throws SQLException
     */
    public static void releaseAndCloseConnection() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.commit();
            tl.remove();
            con.close();
        }
    }
    /**
     * 事务回滚
     * @throws SQLException
     */
    public static void rollback() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.rollback();
        }
    }
}