//This will allow us to know if the hash cell had an entry or not
public class DeletedEntry extends HashEntry{
	private static DeletedEntry delEntry = null;
	
		
	//For entries that are removed!
	public DeletedEntry(String k){
		super("- " + k, "-1 " + k);
	}
	
	
	
	
	/*
	public static DeletedEntry getDeletedEntry(){
		if(delEntry == null)
			delEntry = new DeletedEntry();
		
		return delEntry;
	}
	*/

}
