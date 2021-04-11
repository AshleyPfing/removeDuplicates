package javaPro;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class instancedJavaFileLoops {
	public static void writeComparison(ArrayList<String> stringArray) {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hostname1,hostname2,string1,string2;
		int sentinel = 0,sentinel2=0,index;
		ArrayList<String> stringArray = new ArrayList<String>();
		ArrayList<String> cve1 = new ArrayList<String>();
		ArrayList<String> cve2 = new ArrayList<String>();
		ArrayList<String> hostnames = new ArrayList<String>();
		ArrayList<Integer> numberOfMatches = new ArrayList<Integer>();
		ArrayList<Integer> indexOfHostnames = new ArrayList<Integer>();
		File newFile = new File("filename.txt");
		
		try {
			Scanner fileReader = new Scanner(newFile);
			while (fileReader.hasNextLine()) {
				stringArray.add(fileReader.nextLine());
    		}
			fileReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		e.printStackTrace();
			}
		
		
		//loop to find hostnames
		for(int i = 0; i < stringArray.size()-1; i++) {
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
		
		for(int i = 0; i < hostnames.size(); i++) {
			hostname1 = hostnames.get(i);
			sentinel = numberOfMatches.get(i);
			while(sentinel!=0) {
				sentinel--;
				for(int z = (i+1); z < hostnames.size(); z++) {
					hostname2 = hostnames.get(z);
					if (hostname1.equalsIgnoreCase(hostname2)) {
						index = indexOfHostnames.get(i);
						for(int y = index+1; y < stringArray.size() && sentinel2 == 0; y++) {
							string1 = stringArray.get(y);

							if(string1.contains(",")) {
								cve1.add(string1);
							}
							else {
								sentinel2 = 1;
							}
						}
						
						sentinel2=0;
						index = indexOfHostnames.get(z);
						System.out.println(index);
						for(int y = index+1; y < stringArray.size() && sentinel2 == 0; y++) {
							string1 = stringArray.get(y);
							if(string1.contains(",")) {
								cve2.add(string1);

							}
							else {
								sentinel2 = 1;
							}
						}
						
						sentinel2=0;
						for(int x = 0; x < cve1.size(); x++) {
							string1 = cve1.get(i);
							
							for(int w = 0; w < cve2.size(); w++) {
								string2 = cve2.get(w);
								if(string1.equalsIgnoreCase(string2)) {
									cve2.remove(w);
									stringArray.remove(w+index);
									w--;
								}//end of comparison statement and removal
							}//end of for loop for removal

						}//end of for string comparison
						
						
						cve1.clear();
						cve2.clear();
					}//end of if statement
				}//end of second hostname loop
			}//end of while sentinel is not equal to 0
		}//end of I for loop
	}

}
