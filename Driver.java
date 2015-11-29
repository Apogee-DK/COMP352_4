import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	
	public static void main(String [] args){
		

		/*
		HashMap h1 = new HashMap(100);
		h1.setEmptyMarkerScheme('N');
		h1.setCollisionHandling('D');
		h1.setRehashFactor("1.3");
		h1.setRehashThreshold(0.4);
		
		h1.put("i", "i");
		h1.put("dex", "dex");
		h1.put("a", "a");
		h1.put("e", "e");
		h1.put("lenn", "lenn");
		h1.put("knn", "knn");
		h1.put("bn", "bn");
		h1.put("ann", "ann");
		h1.put("lna", "lna");
		h1.put("dn", "dn");
		h1.put("z", "z");
		h1.put("trial", "trial");
		h1.put("hcbn", "hcbn");
		h1.put("n", "n");
		h1.put("8EtSyfiwy5qScLq7jkLFmonomaniac0QtIj0LleC","aaa");
		
		System.out.println("First output");
		for(HashEntry h: h1.values()){
			System.out.println(h);
		}
		
		System.out.println(h1.size());
		
		System.out.println();
		System.out.println(h1.get("stew"));
		
		h1.remove("zzenn");
		
		System.out.println();
		
		for(HashEntry h: h1.values()){
			System.out.println(h);
		}
		
		
		h1.printHastableStatistic();
		*/
		
		/*
		HashMap ha = new HashMap(100);
		ha.setEmptyMarkerScheme('N');
		ha.setCollisionHandling('D');
		ha.setRehashFactor("1.3");
		ha.setRehashThreshold(0.7);
		
		for (int i=0; i < 900; i++) {
			ha.put("put", "put");
		}
		*/
		
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
		
		//##################################
		//deliverable (c. i)

		HashMap h = new HashMap(100);
		
		setHashTable(h, e, c, rf, rt);	
		readFile(h, filename, 1000);		//first n elements
		System.out.println("First 1000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		
		setHashTable(h, e, c, rf, rt);
		readFile(h, filename, 3000);	
		System.out.println("First 3000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		
		setHashTable(h, e, c, rf, rt);
		readFile(h, filename, 5000);	
		System.out.println("First 5000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		
		setHashTable(h, e, c, rf, rt);
		readFile(h, filename, 10000);	
		System.out.println("First 10000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		
		setHashTable(h, e, c, rf, rt);
		readFile(h, filename, 50000);	
		System.out.println("First 50000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		
		setHashTable(h, e, c, rf, rt);
		readFile(h, filename, 100000);
		System.out.println("First 100000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		
		setHashTable(h, e, c, rf, rt);
		readFile(h, filename, 150000);
		System.out.println("First 150000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		
		setHashTable(h, e, c, rf, rt);
		readFile(h, filename, 200000);
		System.out.println("First 200000 of file " + filename);
		h.printHastableStatistic();
		h.resetHashtableStatistics();
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		
		setHashTable(h, e, c, rf, rt);
		readFile(h, filename, -1);		// ALL
		System.out.println("ALL of file " + filename);
		h.printHastableStatistic();
		
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();
		
		int counter = 0;
		//REMOVING STRINGS
		for(HashEntry entry: h.values()){
			if(counter > 1000){
				break;
			}
			if(entry == null){
				continue;
			}
			h.remove(entry.getKey());
			counter++;
		}
		
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
		
		System.out.println("Press ENTER to continue to add the first 10000 strings from " + filename + "...");
		kb.nextLine();
		
		
		//ADDING 10000 STRINGS
		readFile(h, filename, 10000);	
		System.out.println("Modified Hash Table of file " + filename);
		h.printHastableStatistic();		
		
		for(HashEntry entry: h.values()){
			if(entry == null)
				System.out.println("null");
			else
				System.out.println(h.values()[h.get(entry.getValue())]);
		}
		System.out.println("Press ENTER to continue to next test...");
		kb.nextLine();

	}
	
}
