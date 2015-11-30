/*NAMES: 
 * LENMOR DIMANALATA
 * DEXTER KWOK 27709110
 * 
 *COMP 352 - ASSIGNMENT 4
 * 
 * Class HashMap
 * 
 * Objective:
 * - Create a hash table which will accept entries consisting of a key and a value
 * - Use the key to partition the corresponding entries into its table
 * - Avoid as many collisions as possible by implementing a hash function which will decrease the likelihood of collisions
 * - Allow user to choose between different hashing options to view the difference in execution time and collision numbers
 * 
 * The HashMap is the core of this program. It will accept an entry that contains a key and a value. The key will serve as a value which will determine the 
 * position at which the entry should be placed. This process will be associated with a hash function which will turn the key into an integer value. 
 * The resulting integer value will act as the index at which the entry will be placed. 
 * 
 * More information about the hash function can be found in the PDF file.
 * 
 * In short, we use a prime number and the MOD function to hash the key value into an integer and we use that value to place the entry in its respective index.
 * 
 * In this design, we have decided to use a prime number to represent the capacity (size) of our hash table. According to certain mathematicians, using a prime
 * number as the size of the hash table will reduce the number collisions. Also, the use of 33, 37, or 41 in our hash function will also reduce collisions.
 * 
 * To find these prime numbers, we have included two methods which determines if the number is prime and find the next prime number from a given value:
 * - isPrime(integer)
 * - findNextPrime(integer)
 * 
 * We also keep track of many factors such as the emptyMarkerScheme, collisionHandlingType, maxCollisionCtr,...
 * The above attributes are used for our statistics method which displays interesting information about different hashing methods.
 * We are able to display these statistics since we keep track of all collisions.
 * 
 * For further information, please consult the PDF file which was included with this program
 * 
 * 
 */
public class HashMap {

	private int capacity; 						//size of the hash table
	private HashEntry[] hashTable;				//array of hash entries
	private int numOfElements = 0;				//counter for the number of elements in the hash table
	private double loadFactor = 0.5; 			//load factor will tell us when to resize the array
	private String factorOrNumber = "";			//string which tells us if the value is an integer or a double
	private int incNumber = 1;					//Resize the array by adding this value 
	private double incFactor = 1.2;				//Resize the array by multiplying this value
	private char collisionHandlingType = ' '; 	//Tells us the collision handler type
	private char emptyMarkerScheme = ' ';		//Tells us the marker scheme
	private int maxCollisionCtr = 0; 			//maximum number of collisions for one cell
	private int numOfCollisionCtr = 0; 			//number of collisions in the whole table
	private int p;								//prime number to be used in MAD compression

	public HashMap(){
		capacity = 101; //a prime number for the size of the array
		hashTable = new HashEntry[capacity];
		for (int i=0; i < capacity; i++)
			hashTable[i] = null;

		//#################
		p = findNextPrime(capacity);
	}

	public HashMap(int cap){
		//make sure the size of the array is a prime number
		if(isPrime(cap))
			capacity = cap;
		else
			capacity = findNextPrime(cap); //find the next prime number if the given value was not prime
		hashTable = new HashEntry[capacity]; 
		for (int i=0; i < capacity; i++)
			hashTable[i] = null; //set every entry in the array as null first

		//#################
		p = findNextPrime(capacity);
	}


	//GETTING THE VALUE FROM A SPECIFIC KEY	
	public String get(String k) {
		int hashVal = getHash(k); //get the hash value for this specific string

		if (hashVal == -1) //the string is not an element of the hash table
			return "Element not found.";

		return hashTable[hashVal].toString(); //display the information of the entry
	}

	
	//METHOD WHICH RETURNS THE HASHED VALUE OF A STRING
	public int getHash(String k){
		int hashVal = hashMe(k); 							//hash the string into an integer value

		int quadCtr=0; 										//used for quadratic collision handler

		//checks if the element at the index of the hash value is the same
		while(hashTable[hashVal] != null && !hashTable[hashVal].getValue().equals(k)){ 

			//Do the following if they are not the same			
			if (collisionHandlingType == 'D') 				//for double hashing
				hashVal = (hashVal + hashSec(k))%capacity;  
			else if (collisionHandlingType == 'Q'){ 		//for quadratic 
				quadCtr++;
				hashVal = (hashVal + ((int)Math.pow(quadCtr, 2)))%capacity; 				
			}
		}

		//In case the element cannot be found, return it as -1
		if(hashTable[hashVal] == null){
			return -1;
		}

		return hashVal; //return the hash value at which the entry is found
	}



	//ADDING VALUES INTO THE TABLE
	public void put(String k, String v) {

		//**********************************************************************
		//LOAD FACTOR CHECK
		//**********************************************************************
		
		//Check if the load factor is exceeded, if it is, then resize the hash table
		if(loadFactor < (double) size()/capacity){
			
			//depending on the factor, it could either be a multiplication or an addition for the resize
			if(factorOrNumber.equals("Multiply by ")){

				capacity = (int)(capacity * incFactor) + 1;
				capacity = findNextPrime(capacity); //prime numbers are the best for the size of a hash table, make sure it's prime

				//#################
				p = findNextPrime(capacity); //used for the hash function

			}
			else if(factorOrNumber.equals("Add ")){

				capacity += incNumber;		
				capacity = findNextPrime(capacity);
				//#################
				p = findNextPrime(capacity);

			}

			HashEntry [] tempTable = new HashEntry[capacity]; //create a temporary hash table which will hold all the values


			//RESET THE NUMBER OF COLLISIONS FOR THE NEW ARRAY
			maxCollisionCtr = 0;
			numOfCollisionCtr = 0; 

			for(HashEntry h : hashTable){ //rehashing everything into the newly created array
				//if the cell contains an "empty" cell, there is no need to copy it
				if(h == null || h.getKey().equals("_AVAILABLE_") || (h.getKey().charAt(0)) == '-' && h.getKey().charAt(1) == ' '){ 
					continue;
				}					
				else{
					h.resetCollisionNumber(); //different table means different amount of collisions for each entry
					int quadCtr = 0;
					int hashVal = hashMe(h.getValue()); //get the hash value for each entry in the current hash Table

					//Check if there is an entry which is located in the temporary hash table
					while(!isEmptyCell(tempTable, hashVal, h.getValue())){ 
						
						//If there is do the following, CHECK WHICH COLLISION HANDLER WAS CHOSEN
						if (collisionHandlingType == 'D')
							hashVal = (hashVal + hashSec(h.getKey())) % capacity; 
						else if (collisionHandlingType == 'Q'){
							quadCtr++;
							hashVal = (hashVal + ((int)Math.pow(quadCtr, 2)))%capacity;
						}
						//The above will get the correct hash value which will be used to place the entry in the temporary hash table
					}
					tempTable[hashVal] = h; //associate the entry in the hashTable to the correct index in the temporary Hash Table
				}
			}
			hashTable = tempTable; //let the current hash table point to the temporary table, resize is complete
		}
		//*************************************************************************
		//ADD THE NEW ELEMENT TO THE ARRAY

		int quadCtr=0;
		int hashVal = hashMe(k); 
		int realHashVal = hashVal; 	//keep track of the real hashValue in case we need to use it for the Replace marker scheme
		int prevColHashVal = -1; 	//set the previous collision hashing to -1

		while(!isEmptyCell(hashTable, hashVal, k)){	

			//check if the hash value is not null and that the hash table actually contains that entry
			if(hashTable[hashVal] != null && hashTable[hashVal].getHashValue() == realHashVal)
				prevColHashVal = hashVal; //set the previous collision to the hash value

			//*************************************************************************
			//SETTING UP ALL THE COLLISION NUMBERS 
			setCollisionNumber(hashTable, hashVal);			

			//*************************************************************************
			//SAME AS ABOVE FOR THE HASHING FUNCTION
			if (collisionHandlingType == 'D')
				hashVal = (hashVal + hashSec(k)) % capacity; 
			else if (collisionHandlingType == 'Q'){
				quadCtr++;
				hashVal = (hashVal + ((int)Math.pow(quadCtr, 2)))%capacity;

			}		
		}

		hashTable[hashVal] = new HashEntry(k, v); 		//associate the new entry with the hash table at hash value index
		hashTable[hashVal].setHashValue(realHashVal); 	//set the REAL hash value to the current entry
		//associate the hash value of the entry where collision happened before the entry was placed into its respective hash value index
		hashTable[hashVal].setPrevHashCollisionValue(prevColHashVal); 
 		//############# collision happened, take note
		hashTable[realHashVal].setLastHashCollisionValue(hashVal); //associate the hash value of the entry when the last collision happened
		numOfElements++; //increase the number of elements in the hash table
	}


	//REMOVE A CERTAIN STRING BY USING THE KEY
	public void remove(String k){

		System.out.println("Trying to remove " + k + ". Searching for it in the Hash Table...");
		int hashValToBeRemoved = getHash(k);
		if (hashValToBeRemoved == -1){
			System.out.println(k + " was not found in the Table.");
		}
		else{
			if(emptyMarkerScheme == 'A'){
				hashTable[hashValToBeRemoved]	= new DeletedEntry(new Available(), k);
			}
			else if(emptyMarkerScheme == 'N'){
				hashTable[hashValToBeRemoved]	= new DeletedEntry(k, k);
			}
			else if(emptyMarkerScheme =='R'){

				replaceCurrentHash(hashValToBeRemoved);

			}
			numOfElements--;
		}
	}

	//Replace the current cell with the requested hash value index
	//METHOD USED FOR REPLACING SCHEME
	private void replaceCurrentHash(int hashVal){

		//if the last collision value is null, -1 or if the lastHashCollisionValue is equal to its current hash value then do not do anything
		//if(hashVal == -1 || hashTable[hashVal] == null || hashTable[hashVal].getLastHashCollisionValue() == hashTable[hashVal].getHashValue()){
		//	return;
		//}
		
		//Check if the last collision value is not -1 and not null
		 if (hashTable[hashVal].getLastHashCollisionValue() != -1 && hashTable[hashTable[hashVal].getLastHashCollisionValue()] != null){

			hashTable[hashVal] = hashTable[hashTable[hashVal].getLastHashCollisionValue()]; //swap the last collision entry with the current entry

			//replaceCurrentHash(hashTable[hashVal].getLastHashCollisionValue());
			
			hashTable[hashVal].setLastHashCollisionValue(hashTable[hashVal].getPrevHashCollisionValue()); //set the last collision attribute of the swapped entry as its previous collision entry

			hashTable[hashVal].setPrevHashCollisionValue(-1); //set the previous hash value entry to -1

		}



	}

	//RETURN AN ITERABLE COLLECTION OF HASH ENTRIES FROM THE TABLE
	public HashEntry[] values(){
		return hashTable;
	}

	//DETERMINE THE SIZE OF THE HASH TABLE
	public int size(){
		return numOfElements;		
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
	public int hashMe(String k) {
		//k is the key, N is the capacity of array/ hash hashTable
		//############# HASH CODE MAP ##############
		// converts strings to integers

		if(k.charAt(0)=='-' && k.charAt(1)==' '){
			k = k.substring(2);
		}


		int len = k.length();
		int z = 33;		//good prime number to avoid collisions
		double total = 0;

		for (int i=0; i < len; i++) {
			double val = (double) k.charAt(i);
			val = val *  ((double)  (Math.pow(z, i)) );
			total += val;
		}

		//now total is the integer equivalent of the string
		//System.out.println("Integer equiv of " + k + " is " + total);

		//######## MAD COMPRESSION MAP
		// integers to array index
		// we use MAD 
		int a = capacity/33;	// a mod N != 0
		int b = capacity/23;	// b can be any nonnegative int

		//double finalKey = ((a * total) + b ) % capacity;

		//#################
		double finalKey = (((a * total) + b ) % p) % capacity;

		return (int) finalKey;

	}

	//DOUBLE HASHING FUNCTION
	public int hashSec(String k) {

		if(k.charAt(0)=='-' && k.charAt(1)==' '){
			k = k.substring(2);
		}

		int len = k.length();
		int z = 33;		//good prime number to avoid collisions
		double total = 0;

		for (int i=0; i < len; i++) {
			double val = ((Math.pow(z, i)));
			total += val;
		}

		//total is the integer equiv

		return  (z - (int)(total % z) );

	}

	//CHECKS IF THE CELL IS EMPTY
	public boolean isEmptyCell(HashEntry[] arr, int hashVal, String k){
		if(arr[hashVal] == null || arr[hashVal].equals("- " + k)) //WHEN I REMOVE A VALUE FROM THE TABLE, I INPUT -1 TO LEAVE A TRACE
			return true;		
		return false;
	}

	//****************************************************************************************************************************************  
	// ADDITIONAL METHODS
	//****************************************************************************************************************************************

	//ACCESSOR METHODS FOR LOAD FACTORS, THRESHOLDS, COLLISION HANDLERS, MARKER SCHEMES
	public void setRehashThreshold(double lf){
		loadFactor = lf;
	}

	public void setRehashFactor(String fN){
		if(isInteger(fN)){
			factorOrNumber = "Add ";
			incNumber = Integer.parseInt(fN);
		}
		else if(isReal(fN)){
			factorOrNumber = "Multiply by ";
			incFactor = Double.parseDouble(fN);
		}
		else
			System.out.println("Not a valid number. Please try again!");
	}

	public void setCollisionHandling(char type){
		if(type != 'Q' && type != 'D'){
			System.out.println("Wrong Collision Handler. Please try again!");
		}
		collisionHandlingType = type;
	}


	public void setEmptyMarkerScheme(char type){
		if(type != 'A' && type != 'N' && type != 'R'){
			System.out.println("Wrong Empty Scheme Handler. Please try again!");
		}
		emptyMarkerScheme = type;
	}

	public char getEmptyMarkerScheme(){
		return emptyMarkerScheme;
	}


	//RETURN THE AVERAGE NUMBER OF COLLISION BETWEEN ENTRIES THAT HAD COLLISIONS
	public float getAverageNumCollision(){	
		int tempElt = 0;
		int sum = 0;

		for(HashEntry h : hashTable){
			if(h != null && h.getNumOfCollision() > 0){
				tempElt++;
				sum += h.getNumOfCollision();
			}
		}

		if(tempElt == 0){
			return 0;
		}

		return (float) sum/tempElt;
	}


	//DISPLAY THE STATISTICS WITH ALL THE VALUES WHICH WERE TRACKED
	public void printHastableStatistic(){
		System.out.println("\n- Hash Statistic - \nLoad factor: " + loadFactor + "\nRehash factor: " + factorOrNumber + incFactor 
				+ "\nCollision handling type: " + collisionHandlingType + "\nEmpty marker scheme: " + emptyMarkerScheme 
				+ "\nSize of table: " + hashTable.length + "\nNumber of elements: " + numOfElements 
				+ "\nNumber of collisions: " + numOfCollisionCtr + "\nMaximum number of collisions (single cell): " + maxCollisionCtr 
				+ "\nAverage number of collision: " + getAverageNumCollision());	
	}

	//RESET ALL THE STATISTICS
	public void resetHashtableStatistics(){
		numOfElements = 0;
		loadFactor = 0.5; 	
		factorOrNumber = "";
		incNumber = 0;
		incFactor = 0;
		collisionHandlingType = ' ';
		emptyMarkerScheme = ' ';
		maxCollisionCtr = 0;
		numOfCollisionCtr = 0;
		for (int i=0; i < capacity; i++)
			hashTable[i] = null;
		capacity = 101;
	}


	//****************************************************************************************************************************************  
	// AUXILIARY METHODS - FINDING PRIME, SETTING MAX COLLISION NUMBERS, CHECKING IF IT IS INTEGER OR REAL NUMBER
	//****************************************************************************************************************************************

	
	//THE TWO METHODS BELOW WILL ALLOW ME TO SEE IF I SHOULD ADD OR MULTIPLY THE CAPACITY (TO INCREASE THE SIZE OF THE HASH TABLE)
	public boolean isInteger(String fN){
		try{
			Integer.parseInt(fN); //try to parse the string into an integer
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

	public boolean isReal(String fN){
		try{
			Double.parseDouble(fN); //try to parse the string into an integer
		}
		catch(Exception e){
			return false;
		}
		return true;
	}


	//SETTING UP THE MAX NUMBER OF COLLISIONS FOR THE STATISTICS
	public void setCollisionNumber(HashEntry[] arr, int hashVal){
		numOfCollisionCtr++; //keep count of all collisions
		arr[hashVal].incNumOfCollision(); //add 1 collision to the element
		if(maxCollisionCtr < arr[hashVal].getNumOfCollision()){
			maxCollisionCtr = arr[hashVal].getNumOfCollision();
		}


	}

	//CHECKING IF A NUMBER IS PRIME
	public boolean isPrime(int n){
		for(int i = 2; i < Math.sqrt(n); i++){
			if(n%i == 0)
				return false;		
		}		
		return true;		
	}

	//METHOD TO FIND THE NEXT PRIME
	public int findNextPrime(int n){
		for(int i = n + 1; i < 2*n; i++){	//i < 2*n because if we arrive at 2*n then i is no longer a prime
			if(isPrime(i)){
				return i;
			}
		}
		return -1; //this shouldn't happen
	}



}
