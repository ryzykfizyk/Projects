import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Compare
{

	public static void main(String[] args)
	{
		ArrayList<String> lst1 = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
		ArrayList<String> lst2 = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
		ArrayList<String> lst3 = new ArrayList<>(Arrays.asList("4", "3", "2", "1"));
		ArrayList<String> lst4 = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "6"));
		ArrayList<String> lst5 = new ArrayList<>(Arrays.asList("1", "2"));

		compare2(lst1, lst2);// not - brakuje jednego elementu
		compare2(lst1, lst3);// ok
		compare2(lst1, lst5);// ok
		compare2(lst5, lst1);// not - brakuje dwóch elementów
		compare2(lst4, lst5);// ok
		compare2(lst3, lst5);// ok
		compare2(lst3, lst4);// not - brakuje jednego elem

		List<String> list1 = lst1;
		List<String> list2 = lst2;

		boolean b = compare(list1, list2);
	}

	public static void compare(ArrayList<String> lstIn, ArrayList<String> lstOut)
	{
		boolean value = true;
		int countOut = 0;

		for (String sIn : lstIn)
		{
			countOut = 0;
			for (String sOut : lstOut)
			{
				if (sIn.matches(sOut))
				{
					break;
				}
				countOut++;
			}
			if (countOut == lstOut.size())
			{
				value = false;
				break;
			}
		}
		if (value == true)
		{
			System.out.println("LstIn: " + lstIn.toString() + " does NOT contain LstOut: " + lstOut.toString());
		} else
		{
			System.out.println("LstIn: " + lstIn.toString() + " contains LstOut: " + lstOut.toString());
		}
	}
	
	public static void compare2(ArrayList<String> lstIn, ArrayList<String> lstOut)
	{
		if(!lstIn.containsAll(lstOut)) {
			System.out.println("LstIn: " + lstIn.toString() + " does NOT contain LstOut: " + lstOut.toString());
		} else
		{
			System.out.println("LstIn: " + lstIn.toString() + " contains LstOut: " + lstOut.toString());
		}
	}

	public static boolean compare(List<String> source, List<String> actual)
	{
		Set<String> sourceDifference = source.stream().filter(t -> !actual.contains(t)).collect(Collectors.toSet());
		for (String t : sourceDifference)
		{
			System.out.println(String.format("String %s not present in actual", t));
		}

		Set<String> actualDifference = actual.stream().filter(t -> !source.contains(t)).collect(Collectors.toSet());
		for (String t : actualDifference)
		{
			System.out.println(String.format("String %s not present in source", t));
		}

		return sourceDifference.isEmpty() && actualDifference.isEmpty();
	}

}
