import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database
{

	public static void create() throws Exception
	{
		try
		{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS books(id int NOT NULL AUTO_INCREMENT, title varchar(255), author varchar(255), description varchar(255), PRIMARY KEY(id))");
			ps.executeUpdate();
			System.out.println("Table exists.");
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public static Connection getConnection() throws Exception
	{
		try
		{
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost/booksDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String username = "ryzykfizyk";
			String password = "password";
			Class.forName(driver);

			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to localhost.");
			return con;
		} catch (Exception e)
		{
			System.out.println(e);
		}

		return null;
	}
}
