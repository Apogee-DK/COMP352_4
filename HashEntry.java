
public class HashEntry {
	
	private String key;
	private String value;
	
	HashEntry(String k, String v) {
		
		key = k;
		value = v;

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

}
