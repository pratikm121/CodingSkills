package nl.pratik.java.practise.coding.algorithms;

public class BinarySearchAlgo {

	public static int recursivebinarySearch(int a[],int first, int last, int x) {
		if(first > last) {
			return -1;
		}else {
			int q = (first+last)/2;
			if(a[q] == x) {
				return q;
			}else if(x < a[q]) {
				last = q-1;
				return recursivebinarySearch(a,first, last, x);
			}else if (x > a[q]) {
				first = q+1;
				return recursivebinarySearch(a,first, last, x);
			}
		}
		
		return -1;
	}
	
	public static int binarySearch(int a[], int x) {
		int p =0;
		int r = a.length -1;
		int index = -1;
		
		while(p <=r) {
			int q = (p+r)/2;
			if(x < a[q]) r = q-1;
			else if (x > a[q]) p = q+1;
			else return q;			
		}
		
		return index;
	}

}
