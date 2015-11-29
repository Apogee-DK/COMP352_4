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
						quadCtr++;
						hashVal = (hashVal + ((int)Math.pow(quadCtr, 2)))%capacity;
						
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
				quadCtr++;
				hashVal = (hashVal + ((int)Math.pow(quadCtr, 2)))%capacity;
				
			}		
		}

		hashTable[hashVal] = new HashEntry(k, v);
		numOfElements++;
	}
