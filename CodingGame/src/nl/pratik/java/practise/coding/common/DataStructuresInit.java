package nl.pratik.java.practise.coding.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStructuresInit {
	public static void main(String[] args) {
		int[] A = new int[]{1, 3, 4,6, 1, 2};
		String [] s = new String [] {"","",""};
		Arrays.sort(A);
		Arrays.sort(s);
		List<String> sList = new ArrayList<>();
		sList.add("");
		Collections.sort(sList);
		
		Map<String,Integer> myMap = new HashMap<>();
		myMap.put("1", 1);
		myMap.put("2", 1);
		myMap.put("3", 1);
		
		myMap.forEach((k,v) -> System.out.println("key: "+k+" value:"+v));
	}

}
