package day4;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class passportChecker {

	public static void main(String[] args) {
		
		ArrayList<String> passports = new ArrayList<String>();
		Scanner fileScan = new Scanner(System.in);
		try {
			String fileName = "passports.txt";
			File myFile = new File(fileName);
			fileScan = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			System.out.println("File name incorrect.");
		} 
		
		String passport = "";
		String temp = "";
		
		while(fileScan.hasNext()) {
			
			temp = fileScan.nextLine();
			if (!temp.equals("")) {
				passport += temp + " ";
			} else {
				passports.add(passport);
				passport = "";
			}
		}
		
		passports.add(passport);
		fileScan.close();
		
		
		int numValidPassports = 0;
		
		for (int i = 0; i < passports.size(); i++) {
			String s = passports.get(i);
			
			if(s.contains("byr") && s.contains("iyr") && s.contains("eyr") && s.contains("hgt")
					&& s.contains("hcl") && s.contains("ecl") && s.contains("pid")) {
				int ret = passValidation(s);
				if (ret == 1) numValidPassports++;
			}
		}
		System.out.println(passports);
		System.out.println(numValidPassports);
	}
	
	public static String[] organizePassport(String s) {
		
		String[] data = new String[7];
		String[] rawData = s.split(" ");
		
		for (int i = 0; i < rawData.length; i++) {
			if      (rawData[i].contains("byr")) data[0] = rawData[i].substring(4);
			else if (rawData[i].contains("iyr")) data[1] = rawData[i].substring(4);
			else if (rawData[i].contains("eyr")) data[2] = rawData[i].substring(4);
			else if (rawData[i].contains("hgt")) data[3] = rawData[i].substring(4);
			else if (rawData[i].contains("hcl")) data[4] = rawData[i].substring(4);
			else if (rawData[i].contains("ecl")) data[5] = rawData[i].substring(4);
			else if (rawData[i].contains("pid")) data[6] = rawData[i].substring(4);
		}
		return data;
	}
	
	public static int passValidation(String s) {
		
		String[] data = organizePassport(s);
		
		//byr validation
		if(data[0].length() != 4) return -1;
		try {
			int i = Integer.parseInt(data[0]);
			if (i > 2002 || i < 1920) return -1;
		} catch (Exception e) {
			return -10;
		}

		//iyr validation
		if(data[1].length() != 4) return -2;
		try {
			int i = Integer.parseInt(data[1]);
			if (i > 2020 || i < 2010) return -2;
		} catch (Exception e) {
			return -20;
		}
		
		//eyr validation
		if(data[2].length() != 4) return -3;
		try {
			int i = Integer.parseInt(data[2]);
			if (i > 2030 || i < 2020) return -3;
		} catch (Exception e) {
			return -30;
		}
		
		//hgt validation
		if(data[3].length() == 4) {
			try {
				int i = Integer.parseInt(data[3].substring(0, 2));	//get inch number then test param
				if (i > 76 || i < 59) return -41;
				if (!data[3].substring(2).equals("in")) return -400; //test that the number is followed by in
			} catch (Exception e) {
				return -40;										// if the text can't be parsed to Integer
			}
		} else if(data[3].length() == 5) {
			try {
				int i = Integer.parseInt(data[3].substring(0, 3));   // get centimeter number then test param
				if (i > 193 || i < 150) return -42;
				if(!data[3].substring(3).equals("cm")) return -4000; //test that the number is followed by cm
			} catch (Exception e) {
				return -40;
			}
		}
		
		//hcl validation		
		Pattern eyr = Pattern.compile("^([#]){1}[a-f0-9]{6}$");
		Matcher m = eyr.matcher(data[4]);
		
		if (!m.matches()) return -5;
		
		//ecl validation
		boolean match = false;

		if      (data[5].equals("amb")) match = true;
		else if (data[5].equals("blu")) match = true;
		else if (data[5].equals("brn")) match = true;
		else if (data[5].equals("gry")) match = true;
		else if (data[5].equals("grn")) match = true;
		else if (data[5].equals("hzl")) match = true;
		else if (data[5].equals("oth")) match = true;

		if (!match) return -6;
		
		//pid validation
		Pattern pid = Pattern.compile("^(\\d){9}$");
		m = pid.matcher(data[6]);
		
		if(!m.matches()) return -7;
		
		
		//everything passed
		return 1;
	}
}
