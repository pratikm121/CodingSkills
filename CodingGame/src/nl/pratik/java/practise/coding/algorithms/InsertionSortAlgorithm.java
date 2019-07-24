package nl.pratik.java.practise.coding.algorithms;

public class InsertionSortAlgorithm {
	
	public static int [] insertionSort(int[] a){  
        for (int i = 1; i < a.length; i++){  
            int index = a[i];  
            int j=i-1;
            while (j >=0 && a[j] > index) {
            	a[j+1] = a[j];
            	j--;
            }
            a[j+1] = index;
        } 
        
        return a;
    }

}
