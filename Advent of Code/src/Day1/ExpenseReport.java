package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ExpenseReport {

	public static void main(String[] args) {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		Scanner fileScan = new Scanner(System.in);
		try {
			String fileName = "report.txt";
			File myFile = new File(fileName);
			fileScan = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			System.out.println("File name incorrect.");
		} 
		
		
		while(fileScan.hasNext()) {
			nums.add(fileScan.nextInt());
		}
		System.out.println(nums.size());
		
		for(int i = 0; i < nums.size() - 1; i++ ) {
			int a = nums.get(i);
			for(int j = i + 1; j < nums.size() - 1; j++ ) {
				int b = nums.get(j);
				for (int k = j + 1; k < nums.size() - 1; k++ ) {
					int c = nums.get(k);
					if(a + b + c == 2020) System.out.println("------------- " + (a * b * c));
				}
			}
			System.out.println("Not " + nums.get(i));
		}

	}
}
