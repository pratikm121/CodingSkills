package nl.pratik.java.practise.coding.hackerrank.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Solution {
	
	static String findNumber(List<Integer> arr, int k) {
		if(arr.contains(k)) {
			return "YES";
		}else {
			return "NO";
		}
    }
	
	static List<Integer> oddNumbers(int l, int r) {
		List<Integer> arr = new ArrayList<>();
		for (int i=l; i<=r; i++) {
			if(i % 2 !=0) {
				arr.add(i);
			}
		}
		
		return arr;
    }
	
	public static char maximumOccurringCharacter(String text) {
		int count[] = new int[256]; 
	       
        int len = text.length(); 
        for (int i=0; i<len; i++) {
        	count[text.charAt(i)]++; 
        }
            
       
        int max = -1; 
        char output = ' '; 
       
        for (int i = 0; i < len; i++) { 
            if (max < count[text.charAt(i)]) { 
                max = count[text.charAt(i)]; 
                output = text.charAt(i); 
            } 
        } 
       
        return output; 
	}
	
	//Maximum difference in an array
	 public static int maxDifference(List<Integer> arr) {
		   int result = -1;
		   List<Integer> diff = new ArrayList<>();
		   double maxSize = 2* Math.pow(10,5);
		   double minIndexValue = Math.pow(-10,5);
		   double maxIndexValue = Math.pow(10,5);
		  
		   if(arr.size()>1 && arr.size() <=maxSize) {
			   for(int i=1; i<arr.size(); i++) {
				   if(arr.get(i) >= minIndexValue && arr.get(i) <= maxIndexValue) {
					   for(int j=1; j<arr.size(); j++) {
						   if(arr.indexOf(arr.get(j)) < arr.indexOf(arr.get(i))
								  && (arr.get(i) > arr.get(j))	   ) {
								diff.add(arr.get(i) - arr.get(j));
						   }							   
					   }
				   }				   
				}
			   
				Optional<Integer> max = diff.stream().reduce(Integer::max);
				if(max.isPresent()) {
				   result = max.get();
				}
		   }  

		   return result;
	 }

	public static void main(String[] args) {
		List<Integer> arr = new ArrayList<>();
		
		arr.add(6);
		arr.add(7);
		arr.add(9);
		arr.add(5);
		arr.add(6);
		arr.add(3);
		arr.add(2);
		
		
		/*arr.add(2);
		arr.add(3);
		arr.add(-56);
		arr.add(10);
		arr.add(2);
		arr.add(4);
		arr.add(56);
		arr.add(8);
		arr.add(1);*/
		
		System.out.println(maxDifference(arr));
		
		//List<Integer> arr1 = oddNumbers(3,9);
		//arr1.forEach(i -> System.out.println(i));
		
		//System.out.println("bbaabac = " + maximumOccurringCharacter("bbaabac"));
		//System.out.println("bbAAAc = " + maximumOccurringCharacter("bbAAAc"));
		//System.out.println("ssDDD99999XX = " + maximumOccurringCharacter("ssDDD99999XX"));
		//System.out.println("bbaabac = " + maximumOccurringCharacter("bbaabac"));
		
		//System.out.println(findNumber(arr, 2));
	}

}
