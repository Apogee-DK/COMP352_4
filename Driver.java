public class Driver {
	
	public static void main(String [] args){
		
		HashMap h1 = new HashMap(100);
		h1.setEmptyMarkerScheme('R');
		h1.setCollisionHandling('D');
		h1.setRehashFactor("1.3");
		h1.setRehashThreshold(0.4);
		
		h1.put("i", "i");
		h1.put("dex", "dex");
		h1.put("a", "a");
		h1.put("e", "e");
		h1.put("lenn", "lenn");
		h1.put("knn", "knn");
		h1.put("dex", "dex1");
		h1.put("bn", "bn");
		h1.put("ann", "ann");
		h1.put("lna", "lna");
		h1.put("dn", "dn");
		h1.put("dex", "dex2");
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
		
		h1.remove("dex");
		
		System.out.println();
		
		for(HashEntry h: h1.values()){
			System.out.println(h);
		}
		
		
		h1.printHastableStatistic();
		
		
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
		
		
		
		/*
		
		// 1st text file
		//1st case , N, Q, resize = 1.3, loadFactor = 0.75
		deliverableC('R','Q',"1.3",0.75, "hash_test_file1.txt" );
		
		// 2nd text file
		//1st case , N, D, resize = 1.2, loadFactor = 0.5
		deliverableC('N','D',"1.2",0.5, "hash_test_file2.txt" );
*/
	}
	
}
