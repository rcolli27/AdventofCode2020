package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PasswordChecker {

	public static void main(String[] args) {
		
		ArrayList<String> entries = new ArrayList<String>();
		Scanner fileScan = new Scanner(System.in);
		try {
			String fileName = "passwords.txt";
			File myFile = new File(fileName);
			fileScan = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			System.out.println("File name incorrect.");
		} 
		
		
		while(fileScan.hasNext()) {
			entries.add(fileScan.nextLine());
		}
		fileScan.close();
		
		int validEntries = 0;
		String[] entrySplit = null;
		
		for(int i = 0; i < entries.size(); i++ ) {
			entrySplit = splitEntry(entries.get(i));
			
			int numLetters = 0;
			char letter = entrySplit[2].charAt(0);

			if(entrySplit[3].charAt(Integer.parseInt(entrySplit[0]) - 1) == letter) numLetters++;
			if(entrySplit[3].charAt(Integer.parseInt(entrySplit[1]) - 1) == letter) numLetters++;
			System.out.println(numLetters);
			
			if (numLetters == 1) validEntries++;
		}
		
		System.out.println(validEntries);
	}
	
	public static String[] splitEntry(String entry) {
		String[] ret = new String[4];
		
		int indexDash = entry.indexOf('-');
		int indexColon = entry.indexOf(':');
		
		ret[0] = entry.substring(0, indexDash);
		ret[1] = entry.substring(indexDash + 1, indexColon - 2);
		ret[2] = entry.substring(indexColon - 1, indexColon);
		ret[3] = entry.substring(indexColon + 2);
		
		return ret;
	}
	
	public static int countNumLetters(String character, String password) {
		
		int numLetters = 0;
		char letter = character.charAt(0);

		for(int i = 0; i < password.length(); i++) {
			if(password.charAt(i) == letter) numLetters++;
		}
		
		
		return numLetters;
		
	}
	
}
