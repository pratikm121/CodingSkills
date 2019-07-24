package nl.pratik.java.practise.coding.games.beginner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * 
The Goal
Destroy the mountains before your starship collides with one of them. For that, shoot the highest mountain on your path.

The Rules
At the start of each game turn, you are given the height of the 8 mountains from left to right.
By the end of the game turn, you must fire on the highest mountain by outputting its index (from 0 to 7).

Firing on a mountain will only destroy part of it, reducing its height. Your ship descends after each pass.  

Constraints
0 <= mountainH <= 9
Response time per turn <= 100ms

Initial Template code

public class ShhotMountains {

	public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        // game loop
        while (true) {
            for (int i = 0; i < 8; i++) {
                int mountainH = in.nextInt(); // represents the height of one mountain.
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println("4"); // The index of the mountain to fire on.
        }
    }
}
 
*/

public class ShhotMountains {
	
	public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int[] mountain = new int[8];
        Map<Integer, Integer> myMap = new HashMap<>();
        
        // game loop
        for (int i = 0; i < 8; i++) {
            int mountainH = in.nextInt(); // represents the height of one mountain.
            mountain[i] = mountainH;
            myMap.put(mountain[i] , i);
        } 
        Arrays.sort(mountain);        
        System.out.println(myMap.get(mountain[7]));      
        in.close();
        
    }
}