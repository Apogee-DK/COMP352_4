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
		
		
		HashMap h2 = new HashMap(100);
		readFile(h2, "hash_test_file1.txt");
		
		HashMap h3 = new HashMap(100);
		readFile(h3, "hash_test_file2.txt");
		
	}

	public static void readFile (HashMap h, String filename)  {
		
		h.setEmptyMarkerScheme('N');
		h.setCollisionHandling('D');
		h.setRehashFactor("1.3");
		h.setRehashThreshold(0.4);
		
		Scanner inFile = null;	
		
		try {
			inFile = new Scanner(new FileInputStream(filename));	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (inFile.hasNextLine()) {
				String line = inFile.nextLine();	
				h.put(line, line);
				System.out.println(line);
		}

		inFile.close();
		
	}
	
}
