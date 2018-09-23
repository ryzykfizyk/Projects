import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TranslateApp
{
	static ArrayList<String[]> list;

	public static void main(String[] args) throws FileNotFoundException
	{
		readList();
		Gui gui = new Gui();
		/*for (String[] s : list)
		{
			System.out.println(s[0] + ", " + s[1]);
		}*/
	}

	static void readList() throws FileNotFoundException
	{
		list = new ArrayList<>();

		String fileName = "words.txt";
		String next = "";
		int index1 = 0, index2 = 0;

		File textFile = new File(fileName);

		Locale loc = new Locale("pl", "PL");
		Scanner in = new Scanner(textFile);	
		in.useLocale(loc);

		while (in.hasNextLine())
		{
			next = in.nextLine();

			index1 = next.indexOf('[');
			index2 = next.indexOf(']');

			list.add(new String[]
			{ next.substring(0, index1 - 1), next.substring(index1 + 1, index2) });
		}

		in.close();
	}

}
