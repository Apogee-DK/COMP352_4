
public class HashEntry extends Available{
	
	private String key;
	private String value;
	private int collisionNumber;
	
	HashEntry(String k, String v) {
		
		key = k;
		value = v;

	}

	HashEntry(Available av){}
	
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
	
	public void incNumOfCollision(){
		collisionNumber++;
	}
	
	public int getNumOfCollision(){
		return collisionNumber;
	}

	public String toString(){
		return getValue();
	}
}
