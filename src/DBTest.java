import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {

	static {
		try {

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LOGIN_DB?serverTimezone=UTC", 
					"root","1234");
			System.out.println("성공");
			Statement stmt = conn.createStatement();

		} catch (SQLException ex) {
			System.out.println("SQL오류" + ex);
		}
		
	}

}
