import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Books
{

	public static void main(String[] args)
	{
		addToDatabase();//ATTENTION: THIS DELETES TABLE AND POPULATE IT WITH NEW RECORDS!
	}

	private static void addToDatabase()
	{
		try
		{
			Connection con = Database.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate("TRUNCATE TABLE books;");
			//List of Books:
			st.executeUpdate("INSERT INTO books (title, author, description) VALUES ('Unweaving the Rainbow', 'Richard Dawkins', 'Author discusses the relationship between science and the arts from the perspective of a scientist. ');");
			st.executeUpdate("INSERT INTO books (title, author, description) VALUES ('The End of Faith', 'Sam Harris', 'Book delivers a startling analysis of the clash of faith and reason in the modern world.');");
			st.executeUpdate("INSERT INTO books (title, author, description) VALUES ('The Demon-Haunted World', 'Carl Sagan', 'Author aims to explain the scientific method to laypeople, and to encourage people to learn critical and skeptical thinking.');");
			st.executeUpdate("INSERT INTO books (title, author, description) VALUES ('Breaking the Spell', 'Daniel Dennett', 'A sharp synthesis of a library of evolutionary, anthropological and psychological research on the origin and spread of religion.');");
			st.executeUpdate("INSERT INTO books (title, author, description) VALUES ('The God Delusion', 'Richard Dawkins', 'Author contends that a supernatural creator almost certainly does not exist and that belief in a personal god qualifies as a delusion.');");
			st.executeUpdate("INSERT INTO books (title, author, description) VALUES ('God Is Not Great', 'Christopher Hitchens', 'Author makes a case against organized religion.');");
			st.executeUpdate("INSERT INTO books (title, author, description) VALUES ('Cosmos', 'Carl Sagan', 'Book explores the mutual development of science and civilization.');");
			
			con.close();
			System.out.println("Books' records inserted!");
		} catch (Exception e)
		{
			System.out.println(e);
		}
			

	}

}
