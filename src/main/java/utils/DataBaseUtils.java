package utils;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 数据源工具
 */
public class DataBaseUtils {
    private static DataSource dataSource = new ComboPooledDataSource();//创建数据库连接池
    static {
        try {
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/javaweb");
            cpds.setUser("root");
            cpds.setPassword("root");
            dataSource = cpds;
        } catch (Exception e) {
            e.printStackTrace(); // 处理异常
        }
    }
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    public static DataSource getDataSource() {
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
}

