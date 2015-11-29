
public class Driver {

	
	public static void main(String [] args){
		
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
		
		
		
	}
}
