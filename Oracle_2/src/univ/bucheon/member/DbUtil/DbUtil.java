/**
 * 
 */
package univ.bucheon.member.DbUtil;

/**
 * @author Jooyong
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {
	public static Connection Connect() {
		Connection con = null;
		//final String driver = "oracle.jdbc.driver.OracleDriver";
		final String driver = "oracle.jdbc.OracleDriver";
		final String url = "jdbc:oracle:thin:@localhost:1521:xe";
		final String id = "java";
		final String pw = "java";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,id,pw);
		}catch(ClassNotFoundException e) {
			System.out.println("DbUtil connect CNFE : ");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("DbUtil"
					+ " connect SE : ");
			e.printStackTrace();
		}
		return con;
	}
	 public static void close(Connection con,
             				  PreparedStatement pstmt,
             				  ResultSet rs) {
		 try {
			 if (rs != null) rs.close();
			 // if (!rs.isClosed()) rs.close();
			 if (pstmt != null) pstmt.close();
			 if (con != null) con.close();
	
		 } catch(SQLException e) {
			 System.out.println("DbUtil close SE : ");
			 e.printStackTrace();
		 } //
	
	 } //

} 

