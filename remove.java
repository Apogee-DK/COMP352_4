	//REMOVE A CERTAIN STRING BY USING THE KEY
	public void remove(String k){
		
		int quadCtr=0;
		
		System.out.println("Trying to remove " + k + ". Searching for it in the Hash Table...");
		if (get(k) == null){
			System.out.println(k + "was not found in the Table.");
		}
		else{
			//int hashVal = hashMe(k);
			
			
			int hashValToBeRemoved = hashMe(k);
			
			int tempHashVal = hashValToBeRemoved;	// this will jump through the array looking for next entry with same hash
			
			
			//############ rehash after remove ###############
			
			// get the next hash (Q or D) from this current hash, then 
			
			while(hashTable[tempHashVal] != null && !hashTable[tempHashVal].getKey().equals(k)){
				
				if (collisionHandlingType == 'D')
				{
					tempHashVal = (tempHashVal + hashSec(k))%capacity; 
				}
					
				else if (collisionHandlingType == 'Q'){
					tempHashVal = (tempHashVal + ((int)Math.pow(quadCtr, 2)))%capacity; 
					quadCtr++;
				}
			}
			
			//at this point we have the key of the next one that should be in this place
			
			//place that entry in the same place as the old one, effectively DELETING the previous
			hashTable[hashValToBeRemoved] = new HashEntry(hashTable[tempHashVal].getKey(), hashTable[tempHashVal].getValue()  );
			
			//empty the location of the moved entry
			hashTable[tempHashVal]	= null;
			
			//hashTable[hashVal] = new DeletedEntry(k); //LEAVING A TRACE, SO WE KNOW THERE WAS AN ELEMENT THERE		
			
			numOfElements--;
		}


	}
