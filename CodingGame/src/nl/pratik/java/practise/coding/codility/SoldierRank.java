package nl.pratik.java.practise.coding.codility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

/*
 * In the army each soldier has an assignment rank. A soldier of rank X
 * can report to any soldier of rank (X+1). Many soldiers to report to same
 * superior. Write a function that given an array ranks consisting of soldier ranks,
 * returns the number of soldiers who can report to some superior.
 * Example 1 
 * Given ranks = [3,4,3,0,2,2,3,0,0]. Your function should return 5 
 * Reason :- (3 soldiers of ranks 3 can report to soldier of rank 4) + 
 * 			 (2 soldiers of ranks 2 can report to soldier of rank 3)
 * 
 * Example 1 
 * Given ranks = [4,2,0]. Your function should return 0 
 * Reason :- No soldier is reporting to their immidiate superior
 * 
 * Example 1 
 * Given ranks = [4,4,3,3,1,0]. Your function should return 3 
 * Reason :- (1 soldiers of ranks 0 can report to soldier of rank 1) + 
 * 			 (2 soldiers of ranks 3 can report to soldier of rank 4)
 * 
 * Conditions
 * N is an integer within the range [2 ... 100,000]
 * Each element of array ranks is an integer within the range [0 .. 1000,000,000]
 * */

public class SoldierRank {
	
	private Map<Integer, Integer> getDuplicateRanks(int[] ranks){
		Map<Integer, Integer> rankCount = new HashMap<>();
		for (int name : ranks) {
			if(name > 0 && name < 1000000000) {
				Integer count = rankCount.get(name);
				if (count == null) { 
					rankCount.put(name, 1); 
				} else { 
					rankCount.put(name, ++count);
				} 
			}				
		}
		return rankCount;
	}
	
	private int getCountOfReportingSoldiers(Map<Integer, Integer> rankCount,int[] ranks) {
		int sum = 0;
		Iterator<?> it = rankCount.entrySet().iterator();
		while (it.hasNext()) {
	        Map.Entry<Integer, Integer> pair = (Entry<Integer, Integer>)it.next();
	        int key = (int) pair.getKey();
	        int value = (int) pair.getValue();
	        if(IntStream.of(ranks).anyMatch(x -> x == (key+1))) {
	        	sum = sum + value;
	        }
	    }
		return sum;
	}

	public int solution(int[] ranks) {		
		
		boolean validRanksLenght = false;
		int sum = 0;
		
		if(ranks.length >1 && ranks.length < 100000) {
			validRanksLenght = true;
		}
		
		
		if(validRanksLenght) {
			Map<Integer, Integer> rankCount = getDuplicateRanks(ranks);
			if(rankCount.size() >0) {
				return getCountOfReportingSoldiers(rankCount , ranks);
			}else {
				return sum;
			}
		}else {
			return sum;
		}
    }
	
	public static void main(String[] args) {
		
		SoldierRank r = new SoldierRank();
		int[] ranks = new int[]{3,4,3,0,2,2,3,0,0};
		System.out.println("Rank 3,4,3,0,2,2,3,0,0=" + r.solution(ranks));
		
		ranks = new int[]{4,2,0};
		System.out.println("Rank 4,2,0=" + r.solution(ranks));
		
		ranks = new int[]{4,4,3,3,1,0};
		System.out.println("Rank 4,4,3,3,1,0=" + r.solution(ranks));
		
		ranks = new int[]{4};
		System.out.println("Rank 4=" + r.solution(ranks));
		
		ranks = new int[]{-1,-3,-2};
		System.out.println("Rank -1,-3,-2=" + r.solution(ranks));
		
		ranks = new int[]{-1,2,3,-2,-2, 4};
		System.out.println("Rank -1,2,3,-2,-2, 4=" + r.solution(ranks));
		
		System.out.println("=======================================");
		
	}

}
