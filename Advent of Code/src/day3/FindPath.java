package day3;

import java.io.*;
import java.util.*;

public class FindPath {
	
	public static void main(String[] args) {
		
		String[] landscape = new String[323];
		Scanner fileScan = new Scanner(System.in);
		try {
			String fileName = "path.txt";
			File myFile = new File(fileName);
			fileScan = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			System.out.println("File name incorrect.");
		} 
		
		int i = 0;
		while(fileScan.hasNext()) {
			landscape[i] = fileScan.nextLine();
			i++;
		}
		int col1 = 0, col2 = 0, col3 = 0, col4 = 0;
		
		long numTrees = 0, numTrees1 = 0, numTrees2 = 0, numTrees3 = 0, numTrees4 = 0;
		for (int row = 0; row < 323; row++, col1+=1, col2+=3, col3+=5, col4+=7) {
			if (col1 > 30) col1 -= 31;
			if (col2 > 30) col2 -= 31;
			if (col3 > 30) col3 -= 31;
			if (col4 > 30) col4 -= 31;
			if (landscape[row].charAt(col1) == '#') numTrees1++;
			if (landscape[row].charAt(col2) == '#') numTrees2++;
			if (landscape[row].charAt(col3) == '#') numTrees3++;
			if (landscape[row].charAt(col4) == '#') numTrees4++;
		}
		for (int row = 0, col = 0; row < 323; col++, row+=2) {
			if (col > 30) col -= 31;
			if (landscape[row].charAt(col) == '#') numTrees++;
		}
		System.out.println("1: " + numTrees1 + "\n2: " + numTrees2 + "\n3: " + numTrees3 + "\n4: " + numTrees4 + "\n5: " + numTrees);
		System.out.println(numTrees*numTrees1*numTrees2*numTrees3*numTrees4);
		
	}
}
