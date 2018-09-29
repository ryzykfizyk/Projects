import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
			con.close();
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

	public static void readList() throws Exception
	{
		try
		{
			BooksCollectionApp.list.clear();
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT title, author, description FROM books");
			ResultSet result = statement.executeQuery();

			while (result.next())
			{
				BooksCollectionApp.list.add("\"" + result.getString("title") + "\" | " + result.getString("author")
						+ " | " + result.getString("description"));
			}

			System.out.println("Selected all records!");
			con.close();
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public static void insert(String s) throws Exception
	{
		String[] tab = s.split("\\s\\|\\s");

		try
		{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO books (title, author, description) VALUES ('"
					+ tab[0] + "', '" + tab[1] + "','" + tab[2] + "')");
			ps.executeUpdate();
			con.close();
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public static void remove(int selectedIndex)
	{
		try
		{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT ROW_NUMBER() OVER (ORDER BY id) row_num, id, title, author, description FROM books ORDER BY id;");
			ResultSet result = ps.executeQuery();
			String id = "";
			int ident = 0;
			while (result.next())
			{
				if (result.getInt("row_num") == selectedIndex)
				{
					ident = result.getInt("id");
				}
			}

			PreparedStatement ps2 = con.prepareStatement("DELETE FROM books WHERE id = '" + ident + "'");
			ps2.executeUpdate();
			System.out.println("Deleted item: id = " + ident);
			con.close();
		} catch (Exception e)
		{
			System.out.println(e);
		}

	}
}
