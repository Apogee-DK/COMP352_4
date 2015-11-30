/*
 * Class DeletedEntry
 * 
 * Objective:
 * - Determine a method to have different ways of removing an entry from a Hash Table
 * 
 * The DeletedEntry class extends the Hash Entry class to create an entry which specifies that a cell no longer holds a key.
 * There are three different ways to confront the removal of an entry:
 * - Replacing
 * - Marker object (Available)
 * - Negative sign
 * 
 * The DeletedEntry class provides two different constructors which will allow us to use a marker and a negative sign 
 * Instead of creating a normal entry, we instantiate a DeletedEntry object which will:
 * 
 * > Add a negative sign before the key to show the user that the key has been removed from the table
 * 
 * OR
 * 
 * > Create an entry with an Available object which will mark the cell as "_AVAILABLE_"
 * 
 * The marking of the cell means that there was an element present in this current location but it has now been removed. The marking will prevent logic errors such
 * as stopping the search of an entry because a cell is null.
 * 
 * The Replacing marker scheme is explained in detial in the HashEntry and HashMap class. 
 * 
 * 
 */
public class DeletedEntry extends HashEntry{
	//For entries that are removed!
	public DeletedEntry(String k, String v){
		super("- " + k, v);
	}
	
	public DeletedEntry(Available av, String k){
		super(av, k);
	}

}
