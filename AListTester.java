
/******
 * AListTester
 * Author: Ian Jacobs
 * 
 * Tests out the AList ADT
 ******/

public class AListTester {
	static final int SIZE = 10;
	private static final double MAX_SIZE = 1000000;
	public static int numReps = 99;

	public static void main(String[] args) {

		//create the size variable
		double size;
		
		//create an int to add to the array
		int i;

		//print out the array sizes to be tested
		//using a for loop sizes start at 10 and 
		//are doubled until they reach MAX_SIZE
		for (size = 10; size <= MAX_SIZE; size *= 2) System.out.print(size + ",");
		System.out.println();
		
		
		//loop to run each of the five methods for appending techniques
		for (int method = 0; method < 5; method++) {
			int reps;							//create a var for repetitions of the experiment
			Integer val = new Integer(2);		//create value to be appended
			for (size = 10; size <= MAX_SIZE; size *= 2) {		//Run the experiment using differnet sizes for the array
				double averageTime = 0;							//create a varible to store average time
				int NUM_REPS = 99;								//set number of repetitions
				for (reps = 0; reps < NUM_REPS; reps++) {		//run the experiment for each size array with 99 repetitions for trials
					List<Integer> currList = createNewAList(method);	//creates a new list for each trial
					long startTime = System.nanoTime();                //start the timer
					for (i = 1; i <= size; i++) {						
						currList.append(val);							//append the values to the array
					}
					long estimatedTime = System.nanoTime() - startTime;		//get the total time of experiment

					averageTime = (averageTime*reps + estimatedTime)/(reps+1);  //calculate the average time of each trial 
				}

				// Output the result for the given size (in CSV format)
				System.out.print(averageTime + ",");	
				if (averageTime > 10000000) break;  // Taking too long - stop the test
			}
			System.out.println();
		}
	}

	/*
	 * Method that takes an in an int to signify which method to
	 * use when doing the append function. Each case creates and returns
	 * a new list with different calculate growth methods for each case.
	 */
	
	static List<Integer> createNewAList(int method) {
		switch (method) {
		case 0: return new AList<Integer>();
		case 1: return new AList<Integer>() { @Override public int calculateGrowth(int len) { return len+100; } };
		case 2: return new AList<Integer>() { @Override public int calculateGrowth(int len) { return len*2; } };
		case 3: return new AList<Integer>() { @Override public int calculateGrowth(int len) { return len*len; } };
		case 4: return new AList<Integer>() { @Override public int calculateGrowth(int len) { return (int) (len + Math.sqrt(len)); } };
		default: return new AList<Integer>();
		}
	}
}
