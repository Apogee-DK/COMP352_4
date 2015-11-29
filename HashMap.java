
public class HashMap {

	private int capacity;
	private HashEntry[] hashTable;
	private int numOfElements = 0;
	private double loadFactor = 0.5; 	
	private String factorOrNumber = "";
	private int incNumber = 1;
	private double incFactor = 1.2;
	private char collisionHandlingType = ' ';
	private char emptyMarkerScheme = ' ';
	private int maxCollisionCtr = 0; //maximum number of collisions for one cell
	private int numOfCollisionCtr = 0; //number of collisions in the whole table


	public HashMap(){
		capacity = 100;
		hashTable = new HashEntry[capacity];
		for (int i=0; i < capacity; i++)
			hashTable[i] = null;
	}

	public HashMap(int cap){
		capacity = cap;
		hashTable = new HashEntry[cap];
		for (int i=0; i < cap; i++)
			hashTable[i] = null;
	}


	//GETTING THE VALUE FROM A SPECIFIC KEY	
	public String get(String k) {
		int hashVal = hashMe(k);

		int quadCtr=0;

		while(hashTable[hashVal] != null && !hashTable[hashVal].getKey().equals(k)){

			if(hashTable[hashVal].getKey().equals("- " + k)){
				System.out.println("Word removed. Not in table anymore!");
				return null;
			}

			if (collisionHandlingType == 'D')
				hashVal = (hashVal + hashSec(k))%capacity; 
			else if (collisionHandlingType == 'Q'){
				hashVal = (hashVal + ((int)Math.pow(quadCtr, 2)))%capacity; 
				quadCtr++;
			}
		}
		return hashTable[hashVal].getValue();
	}



	//ADDING VALUES INTO THE TABLE
	public void put(String k, String v) {

		//**********************************************************************
		//MUST ADD EXTEND ARRAY METHOD HERE
		if(loadFactor < size()/capacity){
			if(factorOrNumber.equals("factor")){
				capacity *= incFactor;
			}
			else if(factorOrNumber.equals("number")){
				capacity += incNumber;				
			}

			HashEntry [] tempTable = new HashEntry[capacity];


			//RESET THE NUMBER OF COLLISIONS FOR NEW ARRAY
			maxCollisionCtr = 0;
			numOfCollisionCtr = 0; 

			for(HashEntry h : hashTable){ //rehashing everything into the newly created array
				int quadCtr = 0;
				int hashVal = hashMe(k); 
				while(!isEmptyCell(hashVal, k)){
					setCollisionNumber(hashVal); //setting the number of collision for all the elements	again							
					//CHECK WHICH COLLISION HANDLER WAS CHOSEN
					if (collisionHandlingType == 'D')
						hashVal = (hashVal + hashSec(k)) % capacity; 
					else if (collisionHandlingType == 'Q'){
						hashVal = (hashVal + ((int)Math.pow(quadCtr, 2)))%capacity;
						quadCtr++;
					}
				}

				tempTable[hashVal] = h;
			}
			hashTable = tempTable; 
		}
		//*************************************************************************

		int quadCtr=0;
		int hashVal = hashMe(k); 


		while(!isEmptyCell(hashVal, k)){	

			//*************************************************************************
			//SETTING UP ALL THE COLLISION NUMBERS 
			setCollisionNumber(hashVal);			

			//*************************************************************************

			if (collisionHandlingType == 'D')
				hashVal = (hashVal + hashSec(k)) % capacity; 
			else if (collisionHandlingType == 'Q'){
				hashVal = (hashVal + ((int)Math.pow(quadCtr, 2)))%capacity;
				quadCtr++;
			}		
		}

		hashTable[hashVal] = new HashEntry(k, v);
		numOfElements++;
	}


	//REMOVE A CERTAIN STRING BY USING THE KEY
	public void remove(String k){

		int quadCtr=0;

		System.out.println("Trying to remove " + k + ". Searching for it in the Hash Table...");
		if (get(k) == null){
			System.out.println(k + "was not found in the Table.");
		}
		else{

			int hashValToBeRemoved = hashMe(k);

			if(emptyMarkerScheme == 'A')
				hashTable[hashValToBeRemoved]	= new DeletedEntry(new Available());

			else if(emptyMarkerScheme == 'N')
				hashTable[hashValToBeRemoved]	= new DeletedEntry("- " + k);

			else if(emptyMarkerScheme =='R'){
				int tempHashVal = hashValToBeRemoved;	// this will jump through the array looking for next entry with same hash

				//############ rehash after remove ###############

				// get the next hash (Q or D) from this current hash, then 

				while(hashTable[tempHashVal] != null && hashTable[tempHashVal].getKey().equals(k)){
					if (collisionHandlingType == 'D')
						tempHashVal = (tempHashVal + hashSec(k))%capacity; 	
					else if (collisionHandlingType == 'Q')
					{
						tempHashVal = (tempHashVal + ((int)Math.pow(quadCtr, 2))) % capacity; 
						quadCtr++;
					}

					//at this point we have the key of the next one that should be in this place

					//place that entry in the same place as the old one, effectively DELETING the previous
					hashTable[hashValToBeRemoved] = new HashEntry(hashTable[tempHashVal].getKey(), hashTable[tempHashVal].getValue());
					hashValToBeRemoved = tempHashVal;
				}
				//empty the location of last copied entry
				hashTable[hashValToBeRemoved] = null;
				//##########################################################
			}
			numOfElements--;
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

		int len = k.length();
		int z = 31;		//good prime number to avoid collisions
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
		int a = capacity-1;	// a mod N != 0
		int b = 2;		// b can be any nonnegative int

		int finalKey = ((a * total) + b ) % capacity;

		return finalKey;

	}

	//DOUBLE HASHING FUNCTION
	public int hashSec(String k) {

		int len = k.length();
		int z = 31;		//good prime number to avoid collisions
		int total = 0;

		for (int i=0; i < len; i++) {
			int val = (int) k.charAt(i);
			val = val *  ((int)  (Math.pow(z, i)) );
			total += val;
		}

		//total is the integer equiv

		int q = 33 ;	// q < N, q is prime, ASSUME N is GREATER THAN 33

		return (q - (total % q) );

	}

	//CHECKS IF THE CELL IS EMPTY
	public boolean isEmptyCell(int hashVal, String k){
		if(hashTable[hashVal] == null || hashTable[hashVal].equals("- " + k)) //WHEN I REMOVE A VALUE FROM THE TABLE, I INPUT -1 TO LEAVE A TRACE
			return true;		
		return false;
	}

	//****************************************************************************************************************************************  
	// ADDITIONAL METHODS
	//****************************************************************************************************************************************


	public void setRehashThreshold(double lf){
		loadFactor = lf;
	}

	public void setRehashFactor(String fN){
		if(isInteger(fN)){
			factorOrNumber = "number";
			incNumber = Integer.parseInt(fN);
		}
		else if(isReal(fN)){
			factorOrNumber = "factor";
			incFactor = Double.parseDouble(fN);
		}
		else
			System.out.println("Not a valid number. Please try again!");
	}

	public void setCollisionHandling(char type){
		if(type != 'Q' || type != 'D'){
			System.out.println("Wrong Collision Handler. Please try again!");
		}
		collisionHandlingType = type;
	}


	public void setEmptyMarkerScheme(char type){
		if(type != 'A' || type != 'N' || type != 'R'){
			System.out.println("Wrong Collision Handler. Please try again!");
		}
		emptyMarkerScheme = type;
	}

	public char getEmptyMarkerScheme(){
		return emptyMarkerScheme;
	}


	public double getAverageNumCollision(){	
		int tempElt = 0;
		int sum = 0;

		for(HashEntry h : hashTable){
			if(h.getNumOfCollision() > 0){
				tempElt++;
				sum += h.getNumOfCollision();
			}
		}

		return sum/tempElt;
	}


	public void printHastableStatistic(){
		System.out.println("- Hash Statistic - \nLoad factor: " + loadFactor + "\nRehash factor: " + factorOrNumber +"\nCollision handling type: " + collisionHandlingType 
				+ "\nEmpty marker scheme: " + emptyMarkerScheme + "\nSize of table: " + hashTable.length + "\nNumber of elements: " + numOfElements 
				+ "\nNumber of collisions: " + numOfCollisionCtr + "\nMaximum number of collisions (single cell): " + maxCollisionCtr 
				+ "\nAverage Number of Collision: " + getAverageNumCollision());	
	}

	public void resetHashtableStatistics(){
		numOfElements = 0;
		loadFactor = 0.5; 	
		factorOrNumber = "";
		incNumber = 1;
		incFactor = 1.2;
		collisionHandlingType = ' ';
		emptyMarkerScheme = ' ';
		maxCollisionCtr = 0;
		numOfCollisionCtr = 0; 	
	}


	//****************************************************************************************************************************************  
	// AUXILIARY METHODS
	//****************************************************************************************************************************************

	public boolean isInteger(String fN){
		try{
			Integer.parseInt(fN);
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

	public boolean isReal(String fN){
		try{
			Double.parseDouble(fN);
		}
		catch(Exception e){
			return false;
		}
		return true;
	}


	public void setCollisionNumber(int hashVal){
		numOfCollisionCtr++; //keep count of all collisions
		hashTable[hashVal].incNumOfCollision(); //add 1 collision to the element
		if(maxCollisionCtr < hashTable[hashVal].getNumOfCollision()){
			maxCollisionCtr = hashTable[hashVal].getNumOfCollision();
		}


	}




}
