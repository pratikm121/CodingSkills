package nl.pratik.java.practise.coding.hackerrank.easy;

import java.util.Scanner;


public class JavaArrayLeftRotation {
	 private static final Scanner scanner = new Scanner(System.in);
	
	// Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
    	for(int i=0; i<a.length; i++) {
    		 int newLocation = (i + (a.length - d)) % a.length;
    		// System.out.println(a[i] + " will nbe placed at "+a[newLocation] );
    		 a[newLocation] = a[i];
    	}    	
    	
    	for(int i=0; i<a.length; i++) {
        	System.out.print(i + " ");
        }
    	
    	return a;
    }

	public static void main(String[] args) {
		int[] a = new int[] {1,2,3,4,5};
		int d= 4;
        int[] result = rotLeft(a, d);
        for(int i=0; i<result.length; i++) {
        	System.out.print(i + " ");
        }


	}

}
