package nl.pratik.java.practise.coding.algorithms;

public class MainClass {
	
	public static void printArray(int [] result) {
		for(int i=0; i<result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}

	public static void main(String[] args) {
		
		int [] sortedInput = new int []{2,5,7,8, 9, 34};
		int [] unSortedInput = new int []{7,2,5,34,8, 9, 56, 23, 78, 91};
		
		// Binary Search algo examples
	//	System.out.println(BinarySearchAlgo.binarySearch(sortedInput, 8));
	//	System.out.println(BinarySearchAlgo.recursivebinarySearch(sortedInput,0,5, 8));
		
		// Selection sort algo examples
	//	printArray(SelectionSrotAlgorithm.selectionSort (unSortedInput));
		
		// Insertion sort algo examples
	//	printArray(InsertionSortAlgorithm.insertionSort (unSortedInput));
		
		
		//Merge sort algo example
		MergeSortAlgorithm.mergeSort(unSortedInput, unSortedInput.length);
		printArray(unSortedInput);

	}

}
