
public class HashMap {

	private int capacity;
	
	private HashEntry[] table;
	
	public HashMap(int cap) {
		
		table = new HashEntry[cap];
		
		for (int i=0; i < cap; i++)
			table[i] = null;
		
	}
	
	public String get(String k) {
		
		//hash function to decode
		
		
		//sample - linear probing
		
		//need to compute hash for given k
		// use java's hashcode for now
		int kValue = k.hashCode();
		
        int hash = (kValue % capacity);
        
        while (table[hash] != null && table[hash].getKey() != k)
              hash = (hash + 1) % capacity;
        if (table[hash] == null)
              return " ";
        else
              return table[hash].getValue();
        
        
	}
	
	
	
	public void put (String k, String v) {
		
		
		//hash function  to encode
		
		//sample - linear probing
		
		//need to compute hash for given k
		// use java's hashcode for now
		int kValue = k.hashCode();
		
        int hash = (kValue % capacity);
        while (table[hash] != null && table[hash].getKey() != k)
              hash = (hash + 1) % capacity;
        table[hash] = new HashEntry(k, v);
	
        
		
	}
	
	
	public void remove(String k){
		
		get(k);
		
		
	}
	
}
