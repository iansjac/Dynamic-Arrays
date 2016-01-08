/******
 * AList ADT
 * Original Author: Clifford Shaffer
 * Modified by: Ian Jacobs, Janine Jay, Chris Cherry
 * Defines the basic Array-based list implementation
 ******/
class AList<E> implements List<E> {
	private static final int defaultSize = 10; // Default size
	private int maxSize;    // Maximum size of list
	private int listSize;   // Current # of list items
	private int curr;       // Position of current element
	private E[] listArray;  // Array holding list elements


	/** Constructors */
	/** Create a list with the default capacity. */
	AList() { this(defaultSize); }

	/** Create a new list object.
	@param size Max # of elements list can contain. */
	@SuppressWarnings("unchecked") // Generic array allocation
	AList(int size) {
		maxSize = size; //set maxSize of listArray
		listSize = curr = 0;  //set variables used in insert method
		listArray = (E[])new Object[size]; // Create listArray

	}

	/** Reinitialize the list */
	public void clear() {  
		listSize = curr = 0;    // Simply reinitialize values
	}

	/*
	 * create a method that will be used 
	 * by the grow method to calculate the
	 * the size of the temp array. Takes 
	 * and int as a parameter and returns
	 * and int.
	 */
	public int calculateGrowth(int len) {
		return len+1;
	}
	
	/*Takes an array and a parameter in as input and 
	 * returns an array with the content copied into it 
	 * into a new array of length.
	 */
	@SuppressWarnings("unchecked")
	public void grow(){
		int nSize = calculateGrowth(listArray.length);		//get the value for the size of the new array using calculateGrowth();
		E[] superSize = (E[])new Object[nSize];				//create a new temp array of larger size superSize
		System.arraycopy(listArray, 0, superSize, 0, listArray.length);		//copy the array into the temp array
		listArray = superSize;												//set listArray equal to the temp array
		maxSize = nSize;													//increment maxSize
	}

	/** Insert "it" at current position */
	public void insert(E it) {						
		if(listSize >= maxSize){			//check if the array is full
			grow();							//if full grow the array
		}
		assert listSize < maxSize : "Error";	//error handling
		for (int i=listSize; i>curr; i--)  // Shift elements up
			listArray[i] = listArray[i-1]; // to make room
		listArray[curr] = it;				//add "it" to the array
		listSize++;                        // Increment list size
	}

	/** Append "it" to list 
	 * takes in a parameter of type E
	 * */
	public void append(E it) {
		if(listSize >= maxSize){		//check if list is full
			grow();						//if full grow array
		}
		assert listSize < maxSize : "Error";	//error handling
		listArray[listSize++] = it;				//add "it" to the end of the list
	}

	/** Remove and return the current element */
	public E remove() {
		if ((curr<0) || (curr>=listSize)) // No current element
			return null;
		E it = listArray[curr];
		// Copy the elem
		for(int i=curr; i<listSize-1; i++) // Shift them down
			listArray[i] = listArray[i+1];
		listSize--; // Decrement size
		return it;
	}

	/** Set curr to the front */
	public void moveToStart() { curr = 0; }

	/** Set curr to the end */
	public void moveToEnd() { curr = listSize; }

	/** Back up */
	public void prev() { if (curr > 0) curr--; }

	/** Go forward */
	public void next() { if (curr < listSize) curr++; }

	/** @return List size */
	public int length() { return listSize; }

	/** @return Current position */
	public int currPos() { return curr; }

	/** Set current list position to "pos" */
	public void moveToPos(int pos) {
		assert (pos>=0) && (pos<=listSize) : "Pos out of range";
		curr = pos;
	}

	/** @return Current element */
	public E getValue() {
		assert (curr>=0) && (curr<listSize) :
			"No current element";
		return listArray[curr];
	}
}