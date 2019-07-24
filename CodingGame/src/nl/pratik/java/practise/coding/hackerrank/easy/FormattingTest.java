package nl.pratik.java.practise.coding.hackerrank.easy;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FormattingTest {
	
	// Complete the staircase function below.
    static void staircase(int n) {
    	StringBuilder sb = new StringBuilder();
    	for(int i=n; i>0; i--) {
    		sb.append("#");
    		System.out.printf("%"+n+"s %n", sb.toString());
    	}

    }
    
 // Complete the miniMaxSum function below.
    static void miniMaxSum(int[] arr) {
    	Arrays.sort(arr);
    	BigDecimal minSum = new BigDecimal(0);
    	BigDecimal maxSum = new BigDecimal(0);
    	
    	for(int i=0; i<arr.length-1;i++) {
    		minSum = minSum.add(new BigDecimal(arr[i]));
    		maxSum = maxSum.add(new BigDecimal(arr[arr.length-(i+1)]));
    	}
    	System.out.println(minSum + " "+ maxSum);
    	
    	
    	System.out.println(minSum + " "+ maxSum);

    }
    
    static int birthdayCakeCandles(int[] ar) {
    	int result = 0;
    	Arrays.sort(ar);
    	int max = ar[ar.length-1];
    	
    	for(int i=0; i<ar.length;i++) {
    		if(max == ar[i]) {
    			result++;
    		}
    	}
    	return result;
    }
    
    static String timeConversion(String s) {
       String output = "";
       int hour = Integer.parseInt(s.substring(0, 2));
       System.out.println(hour);
       
       return output;
    }

	public static void main(String[] args) {
		/*Scanner sc=new Scanner(System.in);
        System.out.println("================================");
        for(int i=0;i<3;i++){
            String s1=sc.next();
            int x=sc.nextInt();            
            System.out.printf( "%-14s %03d %n", s1,x);            
        }
        System.out.println("================================");
        sc.close();
		
		staircase(6);*/
		int[] arr = {4,5,5,7,9,9,1,9,2};
		//System.out.println(Arrays.toString(arr));
		//miniMaxSum(arr);
		//System.out.println(birthdayCakeCandles(arr)); 
		
		System.out.println(timeConversion("07:05:45PM"));
	}

}
