import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Recipes
{

	public static void main(String[] args) throws FileNotFoundException
	{
		ArrayList<ArrayList<String>> listOfRecipies = new ArrayList<>();
		ArrayList<String> listOfAvailableIngredients = new ArrayList<>();
		ArrayList<String> listOfAvailableRecipies = new ArrayList<>();

		readList(listOfRecipies);
		readAvailableIngredients(listOfAvailableIngredients);
		analyse(listOfRecipies, listOfAvailableIngredients);
		print(listOfRecipies);

		// pepper, eggs, spoon of butter, onion, tomato, ham slices, salt, orange

	}

	private static void print(ArrayList<ArrayList<String>> listOfRecipies)
	{
		int size = listOfRecipies.size();
		if (size != 0)
		{
			System.out.println("\nList of possible recipes (" + size / 3 + "):\n\nxxxxxxxxxxxxxxxxxxxxxxxxx\n");
		}
		int i = 0;
		int j = 0;
		while ((size - i) != 0)
		{
			j = 0;
			while ((listOfRecipies.get(i).size() - j) != 0)
			{
				System.out.println(listOfRecipies.get(i).get(j));

				j++;
			}
			System.out.println("");
			if (i % 3 == 0)
				System.out.println("Ingredients:");
			if ((i + 2) % 3 == 0)
				System.out.println("Preparation:");
			if ((i + 1) % 3 == 0)
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx\n");
			i++;
		}

	}

	private static void analyse(ArrayList<ArrayList<String>> listOfRecipes,
			ArrayList<String> listOfAvailableIngredients)
	{
		String nameOfIngridient = "";
		String nameOfAvailableIngridient = "";
		String[] split = null;
		int length = 0;
		ArrayList<String> list;

		for (int j = 1; j < listOfRecipes.size(); j += 3)
		{
			list = new ArrayList<>();

			for (int k = 0; k < (listOfRecipes.get(j).size()); k++)
			{
				split = listOfRecipes.get(j).get(k).split(" ");
				length = split.length;

				if (split[length - 1].matches("\\d+"))
					for (int l = 0; l < length - 1; l++)
					{
						nameOfIngridient += split[l];
						if (l != length - 2)
							nameOfIngridient += " ";
					}
				else
					for (int l = 0; l < length; l++)
					{
						nameOfIngridient += split[l];
						if (l != length - 1)
							nameOfIngridient += " ";
					}
				list.add(nameOfIngridient);

				nameOfIngridient = "";
			}
			if (!compare(listOfAvailableIngredients, list))
			{
				listOfRecipes.get(j - 1).set(0, "*remove*");
			}
			list.clear();

		}

		Iterator<ArrayList<String>> it = listOfRecipes.iterator();

		while (it.hasNext())
		{
			ArrayList<String> lst = it.next();
			if (lst.get(0).equals("*remove*"))
			{
				it.remove();
				it.next();
				it.remove();
				it.next();
				it.remove();
			} else
			{
				it.next();
				it.next();
			}
		}
	}

	private static void readAvailableIngredients(ArrayList<String> listOfAvailableIngredients)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Specify available ingredients (separated with comma): ");
		String nextLine = scanner.nextLine();

		if (nextLine.isEmpty())
			System.exit(0);

		listOfAvailableIngredients.addAll(Arrays.asList(nextLine.split(", ")));

		scanner.close();

	}

	private static void readList(ArrayList<ArrayList<String>> listOfRecipies) throws FileNotFoundException
	{
		String fileName = "src/recipes.txt";

		File textFile = new File(fileName);

		Scanner in = new Scanner(textFile);

		String nameOfMeal = "";
		String next = "";
		String recipe = "";
		ArrayList<String> list;

		while (in.hasNextLine())
		{
			nameOfMeal = in.nextLine();
			in.nextLine();
			in.nextLine();
			list = new ArrayList<>();

			do
			{
				next = in.nextLine();
				if (!next.isEmpty())
					list.add(next);
			} while (!next.isEmpty());

			in.nextLine();
			recipe = in.nextLine();
			in.nextLine();
			in.nextLine();
			in.nextLine();

			listOfRecipies.add(new ArrayList<String>(Arrays.asList(nameOfMeal)));
			listOfRecipies.add(list);
			listOfRecipies.add(new ArrayList<String>(Arrays.asList(recipe)));
		}

		in.close();

	}

	public static boolean compare(ArrayList<String> lstIn, ArrayList<String> lstOut)
	{
		return lstIn.containsAll(lstOut);
	}

}