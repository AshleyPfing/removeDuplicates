package javaPro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class javaDuplicateRemoval {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String hostname1,hostname2,string1,string2;
		int sentinel = 0,index,counter=0;
		ArrayList<String> stringArray = new ArrayList<String>();
		ArrayList<String> cve1 = new ArrayList<String>();
		ArrayList<String> cve2 = new ArrayList<String>();
		ArrayList<String> hostnames = new ArrayList<String>();
		ArrayList<Integer> numberOfMatches = new ArrayList<Integer>();
		ArrayList<Integer> indexOfHostnames = new ArrayList<Integer>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("prefiltered.txt"));
			do {
				string1 = reader.readLine();
				if (string1 != null) {
					stringArray.add(string1);
				}
			}while (string1 != null);
			reader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		e.printStackTrace();
			}
		catch(IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		
		
		//loop to find hostnames
		for(int i = 0; i < stringArray.size(); i++) {
			string1 = stringArray.get(i);
			if(string1.contains(",")) {
				indexOfHostnames.add((i-1));
				string1 = stringArray.get(i-1);
				hostnames.add(string1);
				while(i < stringArray.size() && sentinel == 0) {
					string1 = stringArray.get(i);
					if(!string1.contains(",")) {
						sentinel = 1;
					}
					i++;
				}
				sentinel = 0;
			}
		}
		
		
		//Loop to find duplicate hostnames
		for(int i = 0; i < hostnames.size(); i++) {
			hostname1 = hostnames.get(i);
			numberOfMatches.add(0);
			for(int z = (i+1); z < hostnames.size(); z++) {
				hostname2 = hostnames.get(z);
				if(hostname1.equalsIgnoreCase(hostname2)) {
					sentinel = numberOfMatches.get(i);
					sentinel++;
					numberOfMatches.set(i, sentinel);
				}
			}
		}
		sentinel = 0;
		
		//Hostname comparison
		for(int i = 0; i < hostnames.size(); i++) {
			cve1.clear();
			sentinel = 0;
			hostname1 = hostnames.get(i);
			index = indexOfHostnames.get(i);
			//Populate cve array
			for(int z = index+1; z < stringArray.size() && sentinel != 1; z++) {
				if(stringArray.get(z).contains(",")) {
					cve1.add(stringArray.get(z));
				}
				else {
					sentinel = 1;
				}
			}

			
			
			
			
			//Find a hostname match
			for(int z = i+1; z < hostnames.size(); z++) {
				hostname2 = hostnames.get(z);
				
				//if match
				if (hostname1.equals(hostname2)) {
					//grab index of second hostname and populate second array
					index = indexOfHostnames.get(z);
					sentinel = 0;
					for(int y = index+1; y < stringArray.size() && sentinel != 1; y++) {
						if(stringArray.get(y).contains(",")) {
							cve2.add(stringArray.get(y));
						}
						else {
							sentinel = 1;
						}
					}//end of populate 2nd array

					//Comparison
					for(int y = 0; y < cve1.size(); y++) {
						//grab 1st string
						string1 = cve1.get(y);
						for (int x = 0 ; x < cve2.size(); x++) {
							//grab 2nd string
							string2 = cve2.get(x);
							//Compares two strings together and removes if same
							if (string1.equalsIgnoreCase(string2)) {
								cve2.remove(x);
								stringArray.remove(index+x+1);
								x--;
								counter++;
							}//end of compare and remove
						}//end of for loop for second string
					}//End of comparison
					for(int y = z ; y < indexOfHostnames.size(); y++) {
						index = indexOfHostnames.get(y);
						index -= counter;
						indexOfHostnames.set(y, index);
					}
					cve2.clear();
					counter = 0;
					
					
				}//End of if match
				
			}//End of find hostname match
			

		}//End of hostname comparison

		
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("filtered.txt"));
			for(int i = 0 ; i < stringArray.size(); i++) {
				bWriter.write(stringArray.get(i)+"\n");
				
			}
			bWriter.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		e.printStackTrace();
			}
		catch(IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
