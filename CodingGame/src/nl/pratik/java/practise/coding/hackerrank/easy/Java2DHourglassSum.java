package nl.pratik.java.practise.coding.hackerrank.easy;

import java.io.IOException;
import java.util.Scanner;

/* Input Array
 *  1 5 9 8 4 5
*	4 8 9 64 1 6
*	1 2 5 9 8 7
*	1 5 7 9 2 4
*	12 45 69 4 5 6
*	14 5 5 69 5 8 
*/

public class Java2DHourglassSum {
	
	private static final Scanner scanner = new Scanner(System.in);
	
	// Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
    	int sum= -63;
    	for(int i=0; i< arr.length-2;i++) {    		
    		for(int j=0; j<arr.length-2;j++) {
    			int temp= arr[i][j]+ arr[i][j+1]+ arr[i][j+2] + arr[i+1][j+1] + arr[i+2][j]+ arr[i+2][j+1]+ arr[i+2][j+2];
    			if (temp > sum) {
    				sum = temp;
    			}
    		}
    	}
    	return sum;
    }
       
	public static void main(String[] args) throws IOException {
        
        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }
        scanner.close();
       // System.out.println(arr.length);

        System.out.println(hourglassSum(arr));

        
    }

}
