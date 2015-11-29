
public class Driver {

	
	public static void main(String [] args){
		
		HashMap h1 = new HashMap(32);
		h1.setEmptyMarkerScheme('A');
		h1.setCollisionHandling('D');
		h1.setRehashFactor("1.2");
		h1.setRehashThreshold(0.4);
		
		h1.put("i", "i");
		h1.put("dex", "dex");
		
		System.out.println("First output");
		for(HashEntry h: h1.values()){
			System.out.println(h);
		}
		
		System.out.println(h1.size());
		
		h1.put("i", "i");
		
		System.out.println();
		System.out.println("Second output");
		for(HashEntry h: h1.values()){
			System.out.println(h);
		}
		
		System.out.println("=======================");
		
		System.out.println(h1.get("i"));
		
		h1.remove("i");
		
		System.out.println();
		System.out.println("Should remove the first i");
		
		for(HashEntry h: h1.values()){
			System.out.println(h);
		}
		
	}
}
