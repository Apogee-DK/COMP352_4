
public class HashMap {

	private int capacity;
	private HashEntry[] hashTable;
	private int counter = 0;
		
	
	public HashMap(int cap){
		capacity = cap;
		hashTable = new HashEntry[cap];
		for (int i=0; i < cap; i++)
			hashTable[i] = null;
	}
	
	
	
	
	//GETTING THE VALUE FROM A SPECIFIC KEY	
	public String get(String k) {
		int hashVal = hashMe(k, capacity);
        if (hashTable[hashVal] == null){
              return null;
        }
        else
              return hashTable[hashVal].getValue();
	}
	
	//ADDING VALUES INTO THE TABLE
	public void put(String k, String v) {
		int hashVal = hashMe(k, capacity);
        hashTable[hashVal] = new HashEntry(k, v);
        counter++;
	
	}
	
	//REMOVE A CERTAIN STRING BY USING THE KEY
	public void remove(String k){
		System.out.println("Trying to remove " + k + ". Searching for it in the Hash Table...");
		if (get(k) == null){
			System.out.println(k + "was not found in the Table.");
		}
		else{
			int hashVal = hashMe(k, capacity);
			hashTable[hashVal] = new DeletedEntry(k); //LEAVING A TRACE, SO WE KNOW THERE WAS AN ELEMENT THERE		
			counter--;
		}
			
		
	}
	
	//RETURN AN ITERABLE COLLECTION OF HASH ENTRIES FROM THE TABLE
	public HashEntry[] values(){
		return hashTable;
	}
		
	
	//DETERMINE THE SIZE OF THE HASH TABLE
	public int size(){
		return counter;		
	}
	
	
	
	//DETERMINE IF THE TABLE IS EMPTY
	public boolean isEmpty(){
		for(HashEntry h: hashTable){
			if(h != null)
				return false;
		}
		return true;
	}
	
	
	//HASHING FUNCTION - I ADDED THIS TO THE HASHMAP CLASS
	public int hashMe(String k, int cap) {
		//k is the key, N is the capacity of array/ hash hashTable
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
		int a = cap-1;	// a mod N != 0
		int b = 2;		// b can be any nonnegative int
		
		int finalKey = ((a * total) + b ) % cap;
		
		while(isEmptyCell(finalKey) == false){
			//LENMOR, CHECK THIS IF YOU ARE REWORKING THE HASHME FUNCTION, I BASICALLY MOD THE WHOLE THING AGAIN IF THE CELL IS NOT EMPTY
			finalKey = ((a * finalKey) + b) % cap;  
		}	
		
		return finalKey;
		
	}
	
	//CHECKS IF THE CELL IS EMPTY
	public boolean isEmptyCell(int hashVal){
		if(hashTable[hashVal] == null || hashTable[hashVal].equals("-1")) //WHEN I REMOVE A VALUE FROM THE TABLE, I INPUT -1 TO LEAVE A TRACE
			return true;		
		return false;
	}
}
