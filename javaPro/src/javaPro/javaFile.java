package javaPro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class javaFile {

	public static void main(String[] args) {
		try {
			//Declaration of variables
			String string1,string2,hostname1,hostname2;
			int sentinel = 0,index;
			ArrayList<String> stringArray = new ArrayList<String>();
			ArrayList<String> cve1 = new ArrayList<String>();
			ArrayList<String> cve2 = new ArrayList<String>();
			File newFile = new File("filename.txt");
			Scanner fileReader = new Scanner(newFile);
			
			//Reads file and puts all of the data into an array to reference later
			while (fileReader.hasNextLine()) {
				stringArray.add(fileReader.nextLine());
    		}
			fileReader.close();
			//Big loop for entirety of array, up to second last line
			for (int i = 0; i < stringArray.size()-1; i++) {
				string1 = stringArray.get(i);
				//Look for first CVE (notated with a comma)
				if(string1.contains(",")) {
					//Look for hostnames similar, take hostname from previous line
					hostname1 = stringArray.get(i-1);
					index = i;
					//Skip counter over to the end of the data set to compare other hostnames
					do {
						if(i == stringArray.size()) {
							string1 = "";
						}
						else {
							string1 = stringArray.get(i);
							i++;
						}

					}while (string1.contains(","));
					System.out.println();
					//Loop through rest of array to look for matches
					for(int z = i; z < stringArray.size(); z++) {
						//Grab line
						hostname2 = stringArray.get(z);
							//If hostnames match,put both sets of data into arraylists while deleting it simultaneously from the end of the initial array and mark the beginning of the second hostname with sentinel
							if (hostname1.equalsIgnoreCase(hostname2)) {
								//Fill arraylist with first set of CVEs
								string1 = stringArray.get(index);

								while(string1.contains(",")) {
									cve1.add(string1);
									index++;
									string1 = stringArray.get(index);
									
								}
								
								//Fill second arraylist with second sets of CVE and mark the beginning of CVEs
								z++;
								sentinel = z;
								string2 = stringArray.get(z);
								while(string2.contains(",")) {
									if(z!=stringArray.size()-1) {
										cve2.add(string2);
										z++;
										string2 = stringArray.get(z);
									}
									else {
										string2= "";
									}

								}
								
								//Compare two sets of data together and delete it from the original array if they're duplicates
								for(int y = 0; y < cve1.size(); y++) {
										string1 = cve1.get(y);
										for(int x = 0; x < cve2.size(); x++) {
											string2 = cve2.get(x);
											if (string1.equalsIgnoreCase(string2)) {
												stringArray.remove(sentinel+x-1);
												cve2.remove(x);
											}
										}
								}
								cve1.clear();
								cve2.clear();
							}
					}
				}
			}
			for(int i=0; i < stringArray.size(); i++) {
				System.out.println(stringArray.get(i));
			}
			System.out.println();
	} catch (FileNotFoundException e) {
		System.out.println("An error occurred.");
	e.printStackTrace();
		}
	}

}
