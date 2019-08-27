package nl.pratik.java.practise.coding.codility;

/*
 * We are given a string S representing a phoneNumber. S consists of N characters
 * digits, spaces and or dashes. It contains at least 2 digits. 
 * Spaces and dashes in S can be ignored.We want to format the S in such a way 
 * that the digits are grouped in blocks of length 3, separated by single dash.
 * If necessary final block or last 2 blocks can be of length 2.
 * 
 * Example 1 
 * S = "00-44  48 5555 8361" the function should return "004-448-555-583-61" 
 * 
 * Example 2 
 * S = "0 - 22 1985--324" the function should return "022-198-53-24"
 * 
 * Example 3 
 * S = "555372654" the function should return "555-372-654"
 * */

public class PhoneNumberFormtter {
	
	/*My Solution*/
	public String modifiedSolution(String S) {
        StringBuilder phoneNumber = new StringBuilder();
        phoneNumber = phoneNumber.append(S.replaceAll("[^0-9]", ""));
        boolean isRemainingNumber = phoneNumber.length() % 3 == 1;
        return outputNumber(phoneNumber, isRemainingNumber);
    }
	
	private String outputNumber(StringBuilder phoneNumber, boolean isRemainingNumber) {
		StringBuilder outputNumber = new StringBuilder();
		int counter = 0;
		for (int i = 0; i < phoneNumber.length(); i++) {
            if (counter < 3) {
            	outputNumber = outputNumber.append(phoneNumber.substring(i, i + 1));
                counter++;
            } else if (counter == 3){
            	outputNumber = outputNumber.append("-");
            	outputNumber = outputNumber.append(phoneNumber.substring(i, i + 1));
                counter = 1;
            }
        }
		
        if (isRemainingNumber) {
            char[] tempArray =  outputNumber.toString().toCharArray();
            tempArray[tempArray.length - 2] = tempArray[tempArray.length - 3];
            tempArray[tempArray.length - 3] = '-';
            outputNumber.delete(0, outputNumber.length()).append(tempArray);
        }
		
		return outputNumber.toString();
	}
	
	/*online Solution*/
	public String solution(String S) {
        S = S.replaceAll("[^0-9]", "");
        boolean isRemainingNumber = S.length() % 3 == 1;
        return outputNumber(S, isRemainingNumber);
    }
  
	private String outputNumber(String s, boolean isRemainingNumber) {
	    String outputNumber = "";
	    int counter = 0;
	    for (int i = 0; i < s.length(); i++) {
	            if (counter < 3) {
	              outputNumber = outputNumber.concat(s.substring(i, i + 1));
	                counter++;
	            } else if (counter == 3){
	              outputNumber = outputNumber.concat("-");
	              outputNumber = outputNumber.concat(s.substring(i, i + 1));
	                counter = 1;
	            }
	        }
	        if (isRemainingNumber) {
	            char[] tempArray =  outputNumber.toCharArray();
	            tempArray[tempArray.length - 2] = tempArray[tempArray.length - 3];
	            tempArray[tempArray.length - 3] = '-';
	            outputNumber = new String(tempArray);
	        }
	    
	    
	    return outputNumber;
	 }

	public static void main(String[] args) {
		PhoneNumberFormtter t = new PhoneNumberFormtter();
		System.out.println("00-44  48 5555 8361 = " + t.solution("00-44  48 5555 8361"));
		System.out.println("0 - 22 1985--324 = " + t.solution("0 - 22 1985--324"));
		System.out.println("555372654 = " + t.solution("555372654"));
		System.out.println("00-44  AA 5555 8361 = " + t.solution("00-44  AA 5555 8361"));
		System.out.println("0 - #%22 1985--324 = " + t.solution("0 - #%22 1985--324"));
		System.out.println("5553sdef72654 = " + t.solution("5553sdef72654"));
		
		System.out.println("=====================================");
		System.out.println("00-44  48 5555 8361 = " + t.modifiedSolution("00-44  48 5555 8361"));
		System.out.println("0 - 22 1985--324 = " + t.modifiedSolution("0 - 22 1985--324"));
		System.out.println("555372654 = " + t.modifiedSolution("555372654"));
		System.out.println("00-44  AA 5555 8361 = " + t.modifiedSolution("00-44  AA 5555 8361"));
		System.out.println("0 - #%22 1985--324 = " + t.modifiedSolution("0 - #%22 1985--324"));
		System.out.println("5553sdef72654 = " + t.modifiedSolution("5553sdef72654"));

	}

}
