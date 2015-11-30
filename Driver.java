import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Driver {
	
	public static void main(String [] args){
		
		PrintWriter outFile = null;
		

		
		// 1st text file
		//1st case , N, Q, resize = 1.3, loadFactor = 0.75
		deliverableC('A','Q',"1.3",0.75, "hash_test_file1.txt" ); //this works
		
		// 2nd text file
		//1st case , N, D, resize = 1.2, loadFactor = 0.5
		
		deliverableC('A','Q',"1.2",0.5, "hash_test_file2.txt" ); //this works
		
		deliverableC('A','D',"1.3",0.75, "hash_test_file1.txt" ); //this works
		
		deliverableC('A','D',"1.2",0.5, "hash_test_file2.txt" ); //this works
		
		
		deliverableC('N','D',"1.3",0.75, "hash_test_file1.txt" );
		
		deliverableC('N','D',"1.2",0.5, "hash_test_file2.txt" );
		
		deliverableC('N','Q',"1.3",0.75, "hash_test_file1.txt" );

		deliverableC('N','Q',"1.2",0.5, "hash_test_file2.txt" );
		
		
		deliverableC('R','D',"1.3",0.75, "hash_test_file1.txt" );
			
		deliverableC('R','D',"1.2",0.5, "hash_test_file2.txt" );
		
		deliverableC('R','Q',"1.3",0.75, "hash_test_file1.txt" );

		deliverableC('R','Q',"1.2",0.5, "hash_test_file2.txt" );

	}

	
	private static void setHashTable(HashMap h, char e, char c, String rf, double rt){
		
		h.setEmptyMarkerScheme(e);
		h.setCollisionHandling(c);
		h.setRehashFactor(rf);
		h.setRehashThreshold(rt);	
		
	}
	
	/**
	 * 
	 * @param h HashMap
	 * @param filename filename to load
	 * @param bound first N elements in the file to load, -1 for all
	 */
	private static void readFile (HashMap h, String filename, int bound)  {
		
		boolean bounded = true;
		int ctr=0;
		
		if (bound == -1)
			bounded = false;
		
		Scanner inFile = null;	
		
		try {
			inFile = new Scanner(new FileInputStream(filename));	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (inFile.hasNextLine() ) {
			
			if (bounded &&  ctr >= bound  )		//if bound given, break upon hitting
				break;
			
				String line = inFile.nextLine();		
				h.put(line, line);
				//System.out.println(line);			//##### UNCOMMENT IT TO SEE Strings ###
				
				ctr++;
		}

		inFile.close();
		
	}
	
	
	
	public static void deliverableC(char e, char c, String rf, double rt, String filename)
	{
		Scanner kb = new Scanner(System.in);
	
		double startTime = 0;		// 3 long variables used for measuring run time of each case
		double stopTime = 0;
		double elapsedTime = 0;
		double  totalElapsed = 0;
		
		
		//##################################
		//deliverable (c. i)
		
		HashMap h = new HashMap(100);
		setHashTable(h, e, c, rf, rt);
		
		//---------------------------------------------------------------------
		startTime = System.currentTimeMillis();	//TIMING###### get starting time before processing

		readFile(h, filename, 1000);		//first n elements
		System.out.println("First 1000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		stopTime = System.currentTimeMillis();	//TIMING######## get finishing time after call
		elapsedTime = stopTime - startTime;			//TIMING ######## get time elapsed
		totalElapsed += elapsedTime;
		
		System.out.println("Elapsed time for first 1000: " + elapsedTime + " ms");
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		//--------------------------------------------------------------------
		
		
		//---------------------------------------------------------------------
		setHashTable(h, e, c, rf, rt);
		startTime = System.currentTimeMillis();	//TIMING###### get starting time before processing

		readFile(h, filename, 3000);		//first n elements
		System.out.println("First 3000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		stopTime = System.currentTimeMillis();	//TIMING######## get finishing time after call
		elapsedTime = stopTime - startTime;			//TIMING ######## get time elapsed
		totalElapsed += elapsedTime;
		
		System.out.println("Elapsed time for first 3000: " + elapsedTime + " ms");
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		//--------------------------------------------------------------------
		
		//---------------------------------------------------------------------
		setHashTable(h, e, c, rf, rt);
		startTime = System.currentTimeMillis();	//TIMING###### get starting time before processing

		readFile(h, filename, 5000);		//first n elements
		System.out.println("First 5000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		stopTime = System.currentTimeMillis();	//TIMING######## get finishing time after call
		elapsedTime = stopTime - startTime;			//TIMING ######## get time elapsed
		totalElapsed += elapsedTime;
		
		System.out.println("Elapsed time for first 5000: " + elapsedTime + " ms");
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		//--------------------------------------------------------------------
		
		
		//---------------------------------------------------------------------
		setHashTable(h, e, c, rf, rt);
		startTime = System.currentTimeMillis();	//TIMING###### get starting time before processing

		readFile(h, filename, 10000);		//first n elements
		System.out.println("First 10000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		stopTime = System.currentTimeMillis();	//TIMING######## get finishing time after call
		elapsedTime = stopTime - startTime;			//TIMING ######## get time elapsed
		totalElapsed += elapsedTime;
		
		System.out.println("Elapsed time for first 10000: " + elapsedTime + " ms");
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		//--------------------------------------------------------------------
		
		//---------------------------------------------------------------------
		setHashTable(h, e, c, rf, rt);
		startTime = System.currentTimeMillis();	//TIMING###### get starting time before processing

		readFile(h, filename, 50000);		//first n elements
		System.out.println("First 50000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		stopTime = System.currentTimeMillis();	//TIMING######## get finishing time after call
		elapsedTime = stopTime - startTime;			//TIMING ######## get time elapsed
		totalElapsed += elapsedTime;
		
		System.out.println("Elapsed time for first 50000: " + elapsedTime + " ms");
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		//--------------------------------------------------------------------
		
		//---------------------------------------------------------------------
		setHashTable(h, e, c, rf, rt);
		startTime = System.currentTimeMillis();	//TIMING###### get starting time before processing

		readFile(h, filename, 100000);		//first n elements
		System.out.println("First 100000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		stopTime = System.currentTimeMillis();	//TIMING######## get finishing time after call
		elapsedTime = stopTime - startTime;			//TIMING ######## get time elapsed
		totalElapsed += elapsedTime;
		
		System.out.println("Elapsed time for first 100000: " + elapsedTime + " ms");
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		//--------------------------------------------------------------------
		
		//---------------------------------------------------------------------
		setHashTable(h, e, c, rf, rt);
		startTime = System.currentTimeMillis();	//TIMING###### get starting time before processing

		readFile(h, filename, 150000);		//first n elements
		System.out.println("First 150000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		stopTime = System.currentTimeMillis();	//TIMING######## get finishing time after call
		elapsedTime = stopTime - startTime;			//TIMING ######## get time elapsed
		totalElapsed += elapsedTime;
		
		System.out.println("Elapsed time for first 150000: " + elapsedTime + " ms");
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		//--------------------------------------------------------------------
		
		//---------------------------------------------------------------------
		setHashTable(h, e, c, rf, rt);
		startTime = System.currentTimeMillis();	//TIMING###### get starting time before processing

		readFile(h, filename, 200000);		//first n elements
		System.out.println("First 200000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		stopTime = System.currentTimeMillis();	//TIMING######## get finishing time after call
		elapsedTime = stopTime - startTime;			//TIMING ######## get time elapsed
		totalElapsed += elapsedTime;
		
		System.out.println("Elapsed time for first 200000: " + elapsedTime + " ms");
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		//--------------------------------------------------------------------
		
		
		
		//---------------------------------------------------------------------
		setHashTable(h, e, c, rf, rt);
		startTime = System.currentTimeMillis();	//TIMING###### get starting time before processing

		readFile(h, filename, -1);		//first n elements
		System.out.println("ALL of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		stopTime = System.currentTimeMillis();	//TIMING######## get finishing time after call
		elapsedTime = stopTime - startTime;			//TIMING ######## get time elapsed
		totalElapsed += elapsedTime;
		
		System.out.println("Elapsed time for ALL: " + elapsedTime + " ms");
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		//--------------------------------------------------------------------
		

		
		//--------------------------------------------------------------
		//remove 10000
		setHashTable(h, e, c, rf, rt);
		startTime = System.currentTimeMillis();	//TIMING###### get starting time before processing

		int counter = 0;
		
		//REMOVING STRINGS
		for(HashEntry entry: h.values()){
			if(counter > 10000){
				break;
			}
			if(entry == null){
				continue;
			}
			h.remove(entry.getKey());
			counter++;
		}
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		stopTime = System.currentTimeMillis();	//TIMING######## get finishing time after call
		elapsedTime = stopTime - startTime;			//TIMING ######## get time elapsed
		totalElapsed += elapsedTime;
		
		System.out.println("Elapsed time for Remove 10000: " + elapsedTime + " ms");
		
		//System.out.println("Press ENTER to continue to get the after removing 10000 from " + filename + "...");
		//kb.nextLine();
		
		//----------------------------------------------------------------
		
		//------------------------------------------------------------------
		//Get operations for all ~240000 AFTER removing 10000
		setHashTable(h, e, c, rf, rt);
		startTime = System.currentTimeMillis();	//TIMING###### get starting time before processing

		for(HashEntry entry: h.values()){
			if(counter == 0){
				System.out.println("Press ENTER to continue to get strings without the first 10000 from " + filename + "...");
				kb.nextLine();
				counter--;
			}
			else if(counter == -1){
				if(entry == null)
					System.out.println("null");
				else
					System.out.println(h.values()[h.get(entry.getValue())]);
			}
			
			else{
				if(entry == null)
					System.out.println("null");
				else
					System.out.println(h.values()[h.get(entry.getValue())]);
				counter--;
			}			
		}
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		
		stopTime = System.currentTimeMillis();	//TIMING######## get finishing time after call
		elapsedTime = stopTime - startTime;			//TIMING ######## get time elapsed
		totalElapsed += elapsedTime;
		
		System.out.println("Elapsed time for Get After remove 10000: " + elapsedTime + " ms");
		
		
		System.out.println("Press ENTER to continue to add the first 10000 strings from " + filename + "...");
		kb.nextLine();
		
		//------------------------------------------------------------------
		
		//-------------------------------------------------------------------
		//ADDING 10000 STRINGS
		setHashTable(h, e, c, rf, rt);
		startTime = System.currentTimeMillis();	//TIMING###### get starting time before processing

		
		readFile(h, filename, 10000);	
		System.out.println("Modified Hash Table of file " + filename);
		h.printHastableStatistic();		
		h.resetHashtableStatistics();
				
		
		for(HashEntry entry: h.values()){
			if(entry == null)
				System.out.println("null");
			else
				System.out.println(h.values()[h.get(entry.getValue())]);
		}
		
		stopTime = System.currentTimeMillis();	//TIMING######## get finishing time after call
		elapsedTime = stopTime - startTime;			//TIMING ######## get time elapsed
		totalElapsed += elapsedTime;
		
		System.out.println("Elapsed time for Adding 10000: " + elapsedTime + " ms");
		//------------------------------------------------------
		
		//-------------------------------------------------------
		//Get operations for all ~240000 AFTER adding 10000
		setHashTable(h, e, c, rf, rt);
		startTime = System.currentTimeMillis();	//TIMING###### get starting time before processing

		for(HashEntry entry: h.values()){
			if(counter == 0){
				System.out.println("Press ENTER to continue to get strings after adding the first 10000 from " + filename + "...");
				kb.nextLine();
				counter--;
			}
			else if(counter == -1){
				if(entry == null)
					System.out.println("null");
				else
					System.out.println(h.values()[h.get(entry.getValue())]);
			}
			
			else{
				if(entry == null)
					System.out.println("null");
				else
					System.out.println(h.values()[h.get(entry.getValue())]);
				counter--;
			}			
		}
		h.printHastableStatistic();		
		h.resetHashtableStatistics();
		
		stopTime = System.currentTimeMillis();	//TIMING######## get finishing time after call
		elapsedTime = stopTime - startTime;			//TIMING ######## get time elapsed
		totalElapsed += elapsedTime;
		
		System.out.println("Elapsed time for Get After Adding 10000: " + elapsedTime + " ms");

		//-------------------------------------------------------
		
		
		System.out.println("Total time of all performed operations for this case");
		System.out.println("(Excluding user input)");
		System.out.println("Total elapsed time: " + totalElapsed + " ms");
		
		
		System.out.println("Finished current case\nPress ENTER to continue to next test...");
		kb.nextLine();
		
		//------------------------------------------------------------------

	}
	
}
