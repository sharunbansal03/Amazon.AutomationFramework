package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;


public class DatabaseUtility {

	public static void executeQuery() throws SQLException {
		// Step 1: Connect to DB
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new", "root", "root");

		// Step 2: Create statement
		Statement st = con.createStatement();

		// Step 3: execute SQL query
		int row = st.executeUpdate("insert into ORDERS(username) values('saurabh luthra')");
		Assert.assertTrue(row == 1);
		ResultSet rs = st.executeQuery("select * from orders where username='nitin kadian'");
			while(rs.next()) {
				System.out.println("order id: "+rs.getInt("ORDERID"));
				System.out.println("username: "+rs.getString("USERNAME"));
			}
			
		// Step 4: close the connection
			con.close();
	}
	
	public static void main(String[] args) throws SQLException {
		joinTest();
	}

	public static void joinTest() throws SQLException {
		// Step 1: Connect to DB
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new", "root", "root");

		// Step 2: Create statement
		Statement st = con.createStatement();

		// Step 3: execute SQL query
		ResultSet rs = st.executeQuery("SELECT ENAME,DNAME FROM EMP LEFT JOIN DEPT ON EMP.DEPTNO=DEPT.DEPTNO;");
	
		
			while(rs.next()) {
				System.out.println("employee name : "+rs.getString("ENAME"));
				System.out.println("department name: "+rs.getString("DNAME"));
			}
			
		// Step 4: close the connection
			con.close();
	}
	
}
