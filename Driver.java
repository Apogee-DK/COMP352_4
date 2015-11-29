
public class Driver {

	public static void main(String args[]) {
		
	HashMap h1 = new HashMap(50);
	
	h1.setCollisionHandling('D');
	
	h1.setEmptyMarkerScheme('R');
		
		h1.put("i", "ibc");
		h1.put("i", "iaa");
		h1.put("i", "irt");


		
		for(HashEntry h: h1.values()){
			System.out.println(h);
		}
		
		System.out.println(h1.get("i"));
		
		System.out.println(h1.size());
		
		
		h1.remove("i");
		
		
		for(HashEntry h: h1.values()){
			System.out.println(h);
		}
	}
	

}
