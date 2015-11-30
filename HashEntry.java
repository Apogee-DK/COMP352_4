
public class HashEntry extends Available{
	
	private String key;
	private String value;
	private int collisionNumber;
	
	int lastCollidedHashValue = -1;
	 int prevCollidedHashValue = -1 ;
	
	HashEntry(String k, String v) {
		
		key = k;
		value = v;
	}

	HashEntry(Available av, String k){
		key = "_AVAILABLE_";
		value = k;
	}
	
	public String getKey() {
		
		return key;
	}
	
	
	public void setValue(String v){
		value = v;
		
	}
	
	public void setKey(String k){
		key = k;
	}
	
	public String getValue() {
		return value;
	}
	
	
	public void resetCollisionNumber(){
		collisionNumber = 0;
	}
	
	public void incNumOfCollision(){
		collisionNumber++;
	}
	
	public int getNumOfCollision(){
		return collisionNumber;
	}

	public String toString() {
		return "[" + key + "] " + value; 
	}
}
