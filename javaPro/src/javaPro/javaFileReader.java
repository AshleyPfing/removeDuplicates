package javaPro;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class javaFileReader {

	public static void main(String[] args) {
		//Declaration of variables
		String hostname,string1;
		int sentinel = 0,index;
		ArrayList<String> stringArray = new ArrayList<String>();
		ArrayList<String> cve1 = new ArrayList<String>();
		ArrayList<String> cve2 = new ArrayList<String>();
		File newFile = new File("filename.txt");
		try {
			Scanner fileReader = new Scanner(newFile);
			while (fileReader.hasNextLine()) {
				stringArray.add(fileReader.nextLine());
    		}
			fileReader.close();
			//Loop to find hostname
			for(int i = 0; i < stringArray.size()-1; i++) {
				//if hostname is found index it
				index = i-1;
				string1 = stringArray.get(i);
				if(string1.contains(",")) {
					//Grab hostname
					hostname = stringArray.get(i-1);
					//Loop to populate a second arraylist with values up to the empty space, notifying end of data
					while(i < stringArray.size() && sentinel == 0) {
						string1 = stringArray.get(i);
						if(string1.contains(",")) {
							cve1.add(string1);
						}
						else {
							sentinel = 1;
						}
						i++;
					}
					sentinel = 0;
					//Look for hostname match through entire initial array
					for(int z = i+1; z < stringArray.size(); z++) {
						//If match is found then begin comparisons between CVEs
						string1 = stringArray.get(z);
						if(string1.equalsIgnoreCase(hostname)) {
							z++;
							//Populate second array
							for(int y = z; y < stringArray.size() && sentinel == 0; y++) {
								string1 = stringArray.get(y);
								if(string1.contains(",")) {
									cve2.add(string1);
								}
								else {
									sentinel = 1;
								}
							}
							sentinel = 0;
							//Compare between the two arrays for matches
							//First array to grab line
							for(int y = 0; y < cve1.size(); y++) {
								//Second array loop
								string1 = cve1.get(y);
								for(int x = 0; x < cve2.size(); x++) {
									//If match,remove elements
									
									if(string1.equalsIgnoreCase(cve2.get(x))) {
										cve2.remove(x);
										stringArray.remove(x+i+2);
										x--;
										sentinel = 1;
									}
								}
							}
							if (sentinel == 1) {
								i = index;
								System.out.println();
							}
							sentinel = 0;
							cve1.clear();
							cve2.clear();
						}//end of comparison

					}

				}
			}
			for(int a = 0; a < stringArray.size(); a++) {
				System.out.println(stringArray.get(a));
			}
	} catch (FileNotFoundException e) {
		System.out.println("An error occurred.");
	e.printStackTrace();
		}
	}
}
