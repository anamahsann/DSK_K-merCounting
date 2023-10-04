package hashingLab2;
import java.lang.Math;
import java.util.Scanner;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Hashing {
	/**This package creates 11 hash tables using 11 unique schemes. 
	 * There are three collision handling schemes, linear probing, quadratic probing, and chaining.
	 * The program prints the filled hash tables as output.
	 * The program prints hash stable scheme info, collisions, and comparisons . 
	 * @author Anam Ahsan
	 * @param sourceFilepath is the file to be read
	 * @param destinationFilepath is the file to be written into
	 * @param user input for modulo and bucket size
	 */
	 public static int comparisonsL = 0; //Comparisons for Linear Division 
	 public static int comparisonsQ = 0; //Comparisons for Quadratic Division 
	 
	 public static int comparisonsM = 0; //Comparisons for Linear Multiplication 
	 public static int comparisonsN = 0; //Comparisons for Quadratic Multiplication 
	 
	 public static int comparisonsO = 0; //Comparisons for Chaining Division 
	 public static int comparisonsR = 0; //Comparisons for Chaining Multiplication 
	 
	 public static int comparisonsS = 0; //Comparisons for Linear Division - Bucket 3
	 public static int comparisonsT = 0; //Comparisons for Quadratic Division - Bucket 3
	  /*
	  * Main entry point for the application, reads input file and creates output file.
	  * Takes user entry for modulo and bucket size 
	 */
	public static void main(String[] args) throws IOException {
		 int moduloD;
         Scanner modDiv = new Scanner(System.in);
         System.out.println("Enter modulo divisor: ");
         moduloD = modDiv.nextInt();

         int bucketS;
         Scanner buckSize = new Scanner(System.in);
         System.out.println("Enter bucket size: ");
         bucketS = buckSize.nextInt();
         
         //Command line arguments for user to enter a source and destination file path
         String sourceFilePath = args[0]; 
         String destinationFilepath = args[1];
      			   	 
         BufferedReader reader; //Initializes the reader
      	 FileWriter outputFile = new FileWriter(destinationFilepath); //Initializing output file
      	 //FileWriter outputFile = new FileWriter("C:\\Users\\Jameel Ahsan\\Desktop\\Algorithms2022\\Program 2\\RESULT.txt");
      	 
      	 try {
    	     reader = new BufferedReader(new FileReader(sourceFilePath)); //Initializing file to be read
    	  	 //reader = new BufferedReader(new FileReader("C:\\Users\\Jameel Ahsan\\Desktop\\Algorithms2022\\Program 2\\ErrorInput.txt"));
    	     String line = reader.readLine(); //Read each line of file
    	     
    	     
    	     //Creation of 11 empty hash tables to be filled
    	     
    	     //Hash table for Linear Probing with division, bucket size 1
	         String [] aHash = new String[120];
	         for (int i = 0; i < 120; i++) {
	                  aHash[i] = null;
	         }
	         
	         //Hash table for Quadratic Probing with division, bucket size 1
	         String [] qHash = new String[120];
	         for (int i = 0; i < 120; i++) {
	                  qHash[i] = null;
	         }
	         
	         //Hash table for Quadratic Probing with multiplication, bucket size 1
	         String [] mHash = new String[120];
	         for (int i = 0; i < 120; i++) {
	                  mHash[i] = null;
	         }
	         
	         //Hash table for Linear Probing with multiplication, bucket size 1
	         String [] nHash = new String[120];
	         for (int i = 0; i < 120; i++) {
	                  nHash[i] = null;
	         }
	         
	         //Hash table for Chaining with division, bucket size 1, 
	         //Hash table is 2D to allow for open addressing
	     	 Stack<Integer> freeSpace; //Stack to keep track of free space in Chaining Hash table
	         freeSpace = new Stack<Integer>();
	         String [][] pHash = {{}};
	         pHash = new String[120][2];
	         for (int i = 0; i < 120; i++) {
	        	 freeSpace.push(i);
        		 for(int j=0; j < pHash[0].length; j++) {
        			 pHash[i][j] = null;	                  
        		 }
	         }
	         
	         //Hash table for Chaining with multiplication, bucket size 1, 
	         //Hash table is 2D to allow for open addressing
	         Stack<Integer> freeSpaceB; //Stack to keep track of free space in Chaining Hash table
	         freeSpaceB = new Stack<Integer>();
	         String [][] rHash = {{}};
	         rHash = new String[120][2];
	         for (int i = 0; i < 120; i++) {
	        	 freeSpaceB.push(i);
        		 for(int j=0; j < pHash[0].length; j++) {
        			 rHash[i][j] = null;	                  
        		 }
	         }
	         
	         //Hash table for Linear Probing with division, bucket size 3
	    	 String [][] bHash = {{}};
        	 bHash = new String[40][3];
        	 for (int i = 0; i < bHash.length; i++) {
        		 for(int j=0; j < bHash[0].length; j++) {
        			 bHash[i][j] = null;
        		 }
        	 }
        	 
        	 //Hash table for Quadratic Probing with division, bucket size 3
	    	 String [][] cHash = {{}};
        	 cHash = new String[40][3];
        	 for (int i = 0; i < cHash.length; i++) {
        		 for(int j=0; j < cHash[0].length; j++) {
        			 cHash[i][j] = null;
        		 }
        	 }
	         
        	 //Initializing all collision variables
	         int collisionL = 0;  //Collisions for Linear Division 
	         int collisionQ = 0;  //Collisions for Quadratic Division 
	         int collisionN = 0;  //Collisions for Linear Multiplication 
	         int collisionK = 0;  //Collisions for Quadratic Multiplication 
	         
	         int collisionP = 0;  //Collisions for Chaining Division
	         int collisionR = 0;  //Collision for Chaining Multiplication  
	         
	         int pcollisionB = 0;  //Primary Collisions for Linear Division Bucket 3
	         int scollisionB = 0; //Secondary Collisions for Linear Division Bucket 3
	         
	         int pcollisionC = 0;  //Primary Collisions for Quadratic Division Bucket 3
	         int scollisionC = 0;  //Secondary Collisions for Quadratic Division Bucket 3
	        
	         
    	     //Read file input
    	     while (line != null) { 
    	    	 if (line.length() > 5) {
    	    		 outputFile.write("\nThis item cannot be stored: " + line + "\n");
    	    	 }
    	    	 if (line.length() == 5) {   
    	    		 //If bucket size is 1, only do schemes 1,2,4,5,9,10
    	    		 //Run twice due to different modulo 
    	             if (bucketS == 1) {    	            	  
        	    		 int key = Integer.parseInt(line); //Turn key into integer
        	    		 int i = 1; //initialize variable for quadratic probing equation 
        	    		 int index = key % moduloD;  //initialize index with hashing scheme for Linear probing
        	    		 int qIndex = key % moduloD; //initialize index with hashing scheme for Quadratic probing
        	    		 
        	    		 //Calculate index multiplication hashing scheme
        	    		 double a = 120*(key*0.618033 % 1);
        	    		 int mIndex = (int) Math.floor(a); //initialize index for quadratic probing  
        	    		 int k = 1; //initialize variable for quadratic probing equations
        	    		        	    	
        	    		 int nIndex = (int) Math.floor(a); //initialize index for linear probing  
        	    		        	    		 
        	    		 
        	    		 //Collision handling scheme - Linear Probing with Division 
        	    		 while (aHash[index] != null) {
            	    		//Call Linear probing method with division scheme 
            	    		index = LinearProbe(moduloD, index, bucketS);	
            	    		collisionL++;
        	    		 }
        	    		 
        	    		 //Collision handling scheme - Linear Probing with Multiplication 
        	    		 while (nHash[nIndex] != null) {
             	    		//Call Linear probing method with my hashing scheme 
             	    		nIndex = LinearProbe(moduloD, nIndex, bucketS);	
             	    		collisionN++;
         	    		 }
        	    		 
        	    		 //Collision handling scheme - Quadratic Probing with Division 
        	    		 while (qHash[qIndex] != null) {
             	    		//Call Quadratic probing method with division scheme
             	    		qIndex = QuadraticProbe(moduloD, qIndex, bucketS, i);
             	    		i++;
             	    		collisionQ++;
         	    		 }
        	    		 
        	    		 //Collision handling scheme - Quadratic Probing with Multiplication  
        	    		 while (mHash[mIndex] != null) {
        	    			//Call Quadratic probing method with my hashing scheme 
        	    			 mIndex = QuadraticProbe(moduloD, mIndex, bucketS, k);
              	    		 k++;
              	    		 collisionK++;
          	    		 }
        	    		
        	    		//Insert key into respective hash table with calculated index
        	    		aHash[index] = line; 
        	    		comparisonsL++;
        	    		
                 	    qHash[qIndex] = line; 
                 	    comparisonsQ++; 
                 	    
                 	    mHash[mIndex] = line;
                 	    comparisonsM++; 
                 	    
                 	    nHash[nIndex] = line;      
                 	    comparisonsN++; 
                 	                 	    
                  	    line = reader.readLine(); //Move on to next key
                  	    
    	             }  
    	             
    	    		 //If bucket size is 2 (to allow for open address chaining) 
    	             //only do schemes 3,6,11 (Chaining) 
    	    		 //Run twice due to different modulo 
    	             else if (bucketS == 2) {
    	            	 	int key = Integer.parseInt(line);
    	            	 	int p = 0; //For hash table of division scheme containing key (bucket 1) 	            	 	
    	            	 	int l = 1; //For hash table of division scheme containing pointer (bucket 2) 
    	            	 	
    	            	 	int m = 0; //For hash table of multiplication scheme containing key (bucket 1) 	
    	            	 	int u = 1; //For hash table of multiplication scheme containing pointer (bucket 2) 
    	            	 	
    	            	 	int pIndex = key % moduloD; //Initialize index of division scheme 
    	            	 	
    	            	 	////Initialize index of multiplication scheme 
    	            	 	double b = 120*(key*0.618033 % 1);
    	            	 	int rIndex = (int) Math.floor(b); 
    	            	 	
    	            	 	int avail = 0; //Initialize variable for open space in stack for division scheme
    	            	 	int availR = 0; //Initialize pointer of open space in stack multiplication scheme
    	            	 	 
    	            	 	
    	            	 	 //Wrap around if collision handling scheme outside of array range 
    	            	 	 if (pIndex > 119) {
    	            	 		 pIndex = 0;
    	            	 	 }
    	            	 								
    	            	 	 //With Division scheme and Chaining, fill hash table
						     while (pHash[pIndex][p] != null) { 

						    	 if (freeSpace.empty()) {
						    		 comparisonsO++;
						    		 break;
						    	 }
						    	 else {
						    	 avail = (Integer)freeSpace.pop(); 
						    	 String pointer = Integer.toString(avail);
						    	 pHash[pIndex][l] = pointer;
						    	 pIndex = avail;
						    	 collisionP++;
						    	 comparisonsO++;
						    	 }
						     }
							  
						     //With Multiplication scheme and Chaining, fill hash table
						     while (rHash[rIndex][m] != null) {
						    	 if (freeSpace.empty()) {
						    		 comparisonsR++;
						    		 break;
						    	 }
						    	 else {
						    	 availR = (Integer)freeSpaceB.pop(); 
						    	 String pointerR = Integer.toString(availR);
						    	 rHash[rIndex][u] = pointerR;
						    	 rIndex = availR;
						    	 collisionR++;
						    	 comparisonsR++;
						    	 }
						     }
						    
						    //Insert key into respective hash table with calculated index
                     	    pHash[pIndex][p] = line;                 	  
                     	    deleteStackspot(freeSpace, pIndex, moduloD); //delete space from stack 
                     	    comparisonsO++;
                     	    
                     	    rHash[rIndex][m] = line;                 	  
                     	    deleteStackspot(freeSpaceB, rIndex, moduloD); //delete space from stack 
                     	    comparisonsR++;
                     	                 	    
                      	    line = reader.readLine(); //Move on to next key
    	             }
    	             
    	    		 //If bucket size is 3 only do schemes 7,8 
    	    		 //Division Modulo 41, Linear Probing and Quadratic Probing 
    	             else {
    	            	 int key = Integer.parseInt(line);
        	    		 int n = 0; //Initialize variable for buckets of array in linear probing 
        	    		 int m = 0; //Initialize variable for buckets of array in quadratic probing 
        	    		 int l = 1; //Initialize variable for quadratic probing equations
        	    		 
        	    		 int index = key % moduloD;  //Initialize index of division scheme in linear probing
        	    		 int qIndex = key % moduloD;  //Initialize index of division scheme in quadratic probing
        	    		 
        	    		//Items that cannot be inserted despite collision scheme 
        	    		 if (index > 39 && qIndex > 39) {
        	    			 System.out.println("\nThis item cannot be stored: " + line);
        	    			 outputFile.write("\nThis item cannot be stored: " + line);
        	    			 
        	    			 line = reader.readLine();
        	    			 line = reader.readLine();
        	    			 continue;
        	    		 }
        	    		 
        	    		 
        	    		 //Bucket Size 3 
        	    		 //Collision handling scheme - Linear Probing with Division      
        	    		 while (bHash[index][n] != null) {
             	    		//Call Linear probing method 
        	    			n++;
        	    			pcollisionB++;
        	    			if (n == 2) {
             	    		index = LinearProbe(moduloD, index, bucketS);
             	    		n = 0; 
             	    		scollisionB++;
        	    			}
        	    			comparisonsS++;
         	    		 }
         	    		 
        	    		 //Bucket Size 3 
        	    		 //Collision handling scheme - Quadratic Probing with Division 
         	    		 while (cHash[qIndex][m] != null) {
              	    		//Call Quadratic probing method 
         	    			m++; 
         	    			pcollisionC++;
         	    			if (m == 2) {
         	    				qIndex = QuadraticProbe(moduloD, qIndex, bucketS, l);
         	    				m = 0;
         	    				l++;
         	    				scollisionC++;
            	    			}
         	    			comparisonsT++;
          	    		 }
         	    		 
						//Insert key into respective hash table with calculated index and bucket
                  	    bHash[index][n] = line;                 	    		
                  	    comparisonsS++;
                  	    cHash[qIndex][m] = line; 
                  	    comparisonsT++;
                  	    line = reader.readLine(); //Move on to next key
    	         }
    	    	 }
    	    	 else {
    	    		 line = reader.readLine(); 
       	    	 }

    		 }
    	     
    	     //Print hash tables of bucket size 1 to output file 
    	     if (bucketS == 1) {
     	     
     	     outputFile.write("\nHash Table 1 | Bucket Size: 1"); 
     	     outputFile.write("\nMethod: Division, Mod: " + moduloD); 
     	     outputFile.write("\nCollision Handling: Linear Probing"); 
     	     outputFile.write("\nCollisions: " + collisionL); 
     	    outputFile.write("\nComparisons: " + comparisonsL + "\n"); 
    	     printList(aHash.length, aHash, outputFile);
    	    
     	     
     	     outputFile.write("\nHash Table 2 | Bucket Size: 1"); 
     	     outputFile.write("\nMethod: Division, Mod: " + moduloD ); 
     	     outputFile.write("\nCollision Handling: Quadratic Probing"); 
     	     outputFile.write("\nCollisions: " + collisionQ); 
     	    outputFile.write("\nComparisons: " + comparisonsQ + "\n");
    	     printList(qHash.length, qHash, outputFile);
     	     
     	     outputFile.write("\nHash Table 3 | Bucket Size: 1"); 
     	     outputFile.write("\nMethod: My Scheme- Multiplication"); 
     	     outputFile.write("\nCollision Handling: Linear Probing"); 
     	     outputFile.write("\nCollisions: " + collisionN); 
     	     outputFile.write("\nComparisons: " + comparisonsM + "\n");
    	     printList(nHash.length, nHash, outputFile);    	    
     	     
     	    outputFile.write("\nHash Table 4 | Bucket Size: 1"); 
     	    outputFile.write("\nMethod: My Scheme- Multiplication"); 
     	    outputFile.write("\nCollision Handling: Quadratic Probing"); 
     	    outputFile.write("\nCollisions: " + collisionK); 
     	    outputFile.write("\nComparisons: " + comparisonsN + "\n");
    	     printList(mHash.length, mHash, outputFile);
    	     

      	 }
    	   //Print hash tables of bucket size 2 (due to open address chaining) to output file 
    	     else if (bucketS == 2) {
           	     outputFile.write("\nHash Table 7 | Bucket Size: 1*"); 
           	     outputFile.write("\nMethod: Division, Mod: " + moduloD); 
           	     outputFile.write("\nCollision Handling: Chaining"); 
           	     outputFile.write("\nCollisions: " + collisionP);  
           	     outputFile.write("\nComparisons: " + comparisonsO + "\n");
    	    	 printCList(pHash.length, pHash, outputFile);
    	    	   
             	 outputFile.write("\nHash Table 7 | Bucket Size: 1*"); 
               	 outputFile.write("\nMethod: My Scheme- Multiplication "); 
                 outputFile.write("\nCollision Handling: Chaining"); 
          	     outputFile.write("\nCollisions: " + collisionR); 
          	     outputFile.write("\nComparisons: " + comparisonsR + "\n");
    	    	 printCList(rHash.length, rHash, outputFile);
    	     }
    	     
    	    //Print hash tables of bucket size 3 to output file 
    	     else {
         	     
         	     outputFile.write("\nHash Table 5 | Bucket Size: 3"); 
         	     outputFile.write("\nMethod: Division, Mod: " + moduloD); 
         	     outputFile.write("\nCollision Handling: Linear Probing"); 
         	     outputFile.write("\nPrimary Collisions: " + pcollisionB + " Secondary Collisions: " + scollisionB); 
         	     outputFile.write("\nComparisons: " + comparisonsS + "\n");
         	     printBList(bHash.length, bHash, outputFile);
         	                	     
         	     outputFile.write("\nHash Table 6 | Bucket Size: 3"); 
         	     outputFile.write("\nMethod: Division, Mod: " + moduloD); 
         	     outputFile.write("\nCollision Handling: Quadratic Probing"); 
         	     outputFile.write("\nPrimary Collisions: " + pcollisionC + " Secondary Collisions: " + scollisionC);
         	     outputFile.write("\nComparisons: " + comparisonsT + "\n");
         	     printBList(cHash.length, cHash, outputFile);
    	     }    
    	     //Close input reader
    	     reader.close();
     	     outputFile.close();
    			      
    	     }
      	     //This allows exceptions to be caught and traced to the line of code
    	     catch (IOException e) {
   		      e.printStackTrace();
   		     }
         
	}
	

    /*
     * This method calculates new index using linear probing 
     * It wraps around if index is outside the range of the array
     * Then return the new index 
   */
	private static Integer LinearProbe(int moduloD, int index, int bucketSize) {
			index = (index+1) % moduloD; 
			//System.out.println("Linear:" + index);
				comparisonsL++;
				comparisonsM++; 
			if (index > 119) {
				index = 0;
				comparisonsL++;
				comparisonsM++; 
			}
			if (bucketSize == 3 && index > 39 ) { 
				index = 0;
				comparisonsL++;
				comparisonsM++; 
			}
			return index;
		}
	
    /*
     * This method calculates new index using quadratic probing 
     * It wraps around if index is outside the range of the array
     * Then return the new index 
   */
	private static Integer QuadraticProbe(int moduloD, int index, int bucketSize, int i) {
		index = (index+ i*i) % moduloD; 
		comparisonsQ++;
		comparisonsN++;
		if (index > 119) {
			index = 0;	
			comparisonsQ++;
			comparisonsN++; 
		}
		if (bucketSize == 3 && index > 39 ) { 
			index = 0;
			comparisonsQ++;
			comparisonsN++; 
		}
		
		return index;
	}
	
    /*
     * This method deletes used spaces from the stack that keeps track of free space
     * If it has already been popped off, then it just skips
   */
	private static void deleteStackspot(Stack<Integer> freeSpace, int index,  int moduloD) {
		int deletion = freeSpace.indexOf(index);
		if (deletion > 0) {	
			freeSpace.remove(deletion);
		}
		}
	
    /*
     * This method prints hash tables that are bucket size 3
   */
	public static void printBList(int len, String[][] aHash, FileWriter outputFile) throws IOException {
		for(int i = 0; i< aHash.length; i++){
			for(int j = 0; j<aHash[0].length; j++){	
		        String b = aHash[i][j];
		        outputFile.write(b + " ");
		        }
		    System.out.println();		    
		    outputFile.write("\n");
		        }
	}
	
    /*
     * This method prints hash tables that are bucket size 1
   */
	public static void printList(int len, String[] aHash, FileWriter outputFile) throws IOException {
	    for (int i = 0; i < len; i++) {
	        String sep = "\t"; // usually have a tab after each integer
	        if ((i+1) % 5 == 0 || i+1 == len)
	            sep = "\n"; // but periodically use a newline instead     
	        outputFile.write(aHash[i] + sep);
	    } 
	}
	
    /*
     * This method prints hash tables that are bucket size 2 (due to open address chaining) 
   */
	private static void printCList(int len, String[][] pHash, FileWriter outputFile) throws IOException {
		for(int i = 0; i< pHash.length; i++){
			for(int j = 0; j<pHash[0].length; j++){	
		        String b = pHash[i][j];
		        outputFile.write(b + "-->");
		        }
		    System.out.println();		    
		    outputFile.write("\n");
		        }
	    
	}
} //end
