package manifesto.atm.utils;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

	public static List<String> regexRemover(String[] arrayOfStrings) {

		List<String> regexRemovedStrings = new ArrayList<String>();

		for (String string : arrayOfStrings) {
			string = string.replaceAll("[^\\s\\w]*", "");
			regexRemovedStrings.add(string);

		}

		return regexRemovedStrings;

	}

}
