package nl.pratik.java.practise.coding.hackerrank.easy;


/*
 Input Format

The first line contains an integer, , denoting the number of test cases. 
Each test case, , is comprised of a single line with an integer, , which can be arbitrarily large or small.

Output Format

For each input variable  and appropriate primitive , you must determine if the given primitives are capable of storing it. If yes, then print:

n can be fitted in:
* dataType
If there is more than one appropriate data type, print each one on its own line and order them by size (i.e.: ).

If the number cannot be stored in one of the four aforementioned primitives, print the line:

n can't be fitted anywhere.


Sample Input

5
-150
150000
1500000000
213333333333333333333333333333333333
-100000000000000
Sample Output

-150 can be fitted in:
* short
* int
* long
150000 can be fitted in:
* int
* long
1500000000 can be fitted in:
* int
* long
213333333333333333333333333333333333 can't be fitted anywhere.
-100000000000000 can be fitted in:
* long

 */
import java.util.Scanner;

public class JavaDatatypes {
	
	public static final String BYTE = "* byte";
	public static final String SHORT = "* short";
	public static final String INT = "* int";
	public static final String LONG = "* long";
	
	public static void main(String []argh) {
        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();

        for(int i=0;i<t;i++) {
            try{
                long x=sc.nextLong();
                System.out.println(x+" can be fitted in:");
                if(x>=-128 && x<=127){
                    System.out.println(BYTE);
                    System.out.println(SHORT);
                    System.out.println(INT);
                    System.out.println(LONG);
                }else if (x >= -32768 && x <= 32767){
                	 System.out.println(SHORT);
                     System.out.println(INT);
                     System.out.println(LONG);
                }else if (x >= -2147483648 && x <= 2147483647){
                	 System.out.println(INT);
                     System.out.println(LONG);
                }else{
                	System.out.println(LONG);
                }
                    
                //Complete the code
            }catch(Exception e){
                System.out.println(sc.next()+" can't be fitted anywhere.");
            }

        }
    }

}
