package javaPro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class filereaderreview {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String hostname1,hostname2,string1,string2;
		int sentinel = 0,sentinel2=0,index;
		ArrayList<String> stringArray = new ArrayList<String>();
		ArrayList<String> cve1 = new ArrayList<String>();
		ArrayList<String> cve2 = new ArrayList<String>();
		ArrayList<String> hostnames = new ArrayList<String>();
		ArrayList<Integer> numberOfMatches = new ArrayList<Integer>();
		ArrayList<Integer> indexOfHostnames = new ArrayList<Integer>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("fileTester.txt"));
			int lines = 0;
			string1 = reader.readLine();
			while (string1 != null) {
				lines++;
				stringArray.add(string1);
				reader.readLine();
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		e.printStackTrace();
			}
		catch(IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		for(int i = 0; i < stringArray.size(); i++) {
			System.out.println(stringArray.get(i));
		}
	}

}
