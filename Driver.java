
public class Driver {

	
	public static void main(String [] args){
		
		HashMap h1 = new HashMap(32);
		h1.setEmptyMarkerScheme('A');
		h1.setCollisionHandling('D');
		h1.setRehashFactor("1.3");
		h1.setRehashThreshold(0.4);
		
		h1.put("i", "i");
		h1.put("dex", "dex");
		h1.put("i", "i");
		h1.put("dex", "dex");
		h1.put("lenn", "lenn");
		h1.put("zzenn", "zzenn");
		h1.put("zzenn", "zzenn");
		h1.put("zzenn", "zzenn");
		h1.put("claire", "claire");
		h1.put("justine", "justine");


		
		
		System.out.println("First output");
		for(HashEntry h: h1.values()){
			System.out.println(h);
		}
		
		System.out.println(h1.size());
		
		System.out.println();
		System.out.println(h1.get("stew"));
		
		h1.remove("i");
		h1.remove("zzenn");
		
		System.out.println();
		
		for(HashEntry h: h1.values()){
			System.out.println(h);
		}
		
		h1.printHastableStatistic();
	}
}
