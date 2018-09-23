import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TranslateApp_ES
{
	static ArrayList<String[]> list;

	public static void main(String[] args) throws FileNotFoundException
	{
		readList();
		Gui_ES gui = new Gui_ES();
	}

	static void readList() throws FileNotFoundException
	{
		list = new ArrayList<>();

		String fileName = "words_spanish.txt";
		String next = "";
		int index1 = 0, index2 = 0;

		File textFile = new File(fileName);

		Locale loc = new Locale("es", "ES");
		Scanner in = new Scanner(textFile, "UTF-8");	
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
