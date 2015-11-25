//This will allow us to know if the hash cell had an entry or not
public class DeletedEntry extends HashEntry{
	private static DeletedEntry delEntry = null;
	
	private DeletedEntry(){
		super("Held", "Held");
	}
	
	public static DeletedEntry getDeletedEntry(){
		if(delEntry == null)
			delEntry = new DeletedEntry();
		
		return delEntry;
	}
	
	//hey dex
	//How are you?

}
