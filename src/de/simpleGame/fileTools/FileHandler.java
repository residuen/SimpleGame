package de.simpleGame.fileTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler
{
	public static String getTextFile(File file)
	{
		StringBuffer str = new StringBuffer();

		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNext()) {
				str.append(scanner.nextLine() + "\n");
			}

			// System.out.println(str.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return str.toString().substring(0, str.length() - 1);

	}
}
