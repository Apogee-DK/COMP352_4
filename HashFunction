
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int N = 10;			//capacity of array /hash table
		
		String str = "hi";
		
		System.out.println( "final hash of " + str + " : " +  hashMe(str, N));
	}
	
	
	public static int hashMe(String k, int N) {
		//k is the key, N is the capacity of array/ hash table
		
		
		//############# HASH CODE MAP ##############
		// converts strings to integers
		
		
		int len = k.length();
		
		int z = 33;		//good prime number to avoid collisions
		
		int total = 0;
		
		for (int i=0; i < len; i++) {
			int val = (int) k.charAt(i);
			val = val *  ((int)  (Math.pow(z, i)) );
			total += val;
		}
		
		//now total is the integer equivalent of the string
		
		System.out.println("Integer equiv of " + k + " is " + total);
		
		//######## COMPRESSION MAP
		// integers to array index
		// we use MAD 
		
		int a = N-1;	// a mod N != 0
		int b = 2;		// b can be any nonnegative int
		
		
		int finalKey = ((a * total) + b ) % N;
		
		return finalKey;
		
	}

}
