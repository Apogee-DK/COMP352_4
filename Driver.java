
public class Driver {

	
	public static void main(String [] args){
		
		HashMap h1 = new HashMap(32);
		
		h1.put("i", "i");
		h1.put("dex", "dex");
		
		
		for(HashEntry h: h1.values()){
			System.out.println(h);
		}
		
		System.out.println(h1.size());
		
		
		System.out.println(h1.get("i"));
		
		h1.remove("i");
		
		System.out.println("Should remove the first i");
		
		for(HashEntry h: h1.values()){
			System.out.println(h);
		}
		
	}
}
