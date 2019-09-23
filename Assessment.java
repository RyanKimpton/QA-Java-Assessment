package com.qa.javaAssessment;
import java.util.Arrays;
import java.util.regex.Pattern;


public class Assessment {

	// Given a string, return a string where
	// for every char in the original string,
	// there are three chars.

	// multChar("The") ==> "TTThhheee"
	// multChar("AAbb") ==> "AAAAAAbbbbbb"
	// multChar("Hi-There") ==> "HHHiii---TTThhheeerrreee"

	public String multChar(String input) {
		String word = "";
		for (int i = 0; i< input.length(); i++) {
			word += input.substring(i, i+1);
			word += input.substring(i, i+1);
			word += input.substring(i, i+1);
		}
		return word;
	}
	
	// Return the string (backwards) that is between the first and last appearance
	// of "bert"
	// in the given string, or return the empty string "" if there is not 2
	// occurances of "bert" - Ignore Case

	// getBert("bertclivebert") ==> "evilc"
	// getBert("xxbertfridgebertyy") ==> "egdirf"
	// getBert("xxBertfridgebERtyy") ==> "egdirf"
	// getBert("xxbertyy") ==> ""
	// getBert("xxbeRTyy") ==> ""

	public String getBert(String input) {
		if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(input, "bert")){

			input = org.apache.commons.lang3.StringUtils.reverse(input);
			//This mess of a RegEx command functions as a way of replacing the last instance of the marker
			//and then the rest of the string past the marker
			input = input.replaceFirst("(?s)"+"(?i)treb"+"(?!.*?"+"(?i)treb"+").*", "");

			//Reverse sting again to make it the correct way round for the next comparison
			input = org.apache.commons.lang3.StringUtils.reverse(input);

			if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(input, "bert")){

				//Remove second Bert and anything after it
				input = input.replaceFirst("(?i)bert.*", "");

			} else {
				//only one bert
				return "";
			}
		} else {
			//no berts
			return "";
		}

		return org.apache.commons.lang3.StringUtils.reverse(input);
	}

	// Given three ints, a b c, one of them is small, one is medium and one is
	// large. Return true if the three values are evenly spaced, so the
	// difference between small and medium is the same as the difference between
	// medium and large. Do not assume the ints will come to you in a reasonable
	// order.

	// evenlySpaced(2, 4, 6) ==> true
	// evenlySpaced(4, 6, 2) ==> true
	// evenlySpaced(4, 6, 3) ==> false
	// evenlySpaced(4, 60, 9) ==> false

	public boolean evenlySpaced(int a, int b, int c) {
		// Initialise and sort array
		int[] arr = {a, b, c};
		Arrays.sort(arr);

		// Test differences
		if (arr[1] - arr[0] == arr[2] - arr[1]) {
			return true;
		} else {
			return false;
		}
	}

	// Given a string and an int n, return a string that removes n letters from the 'middle' of the string.
	// The string length will be at least n, and be odd when the length of the input is odd.

	// nMid("Hello", 3) ==> "Ho"
	// nMid("Chocolate", 3) ==> "Choate"
	// nMid("Chocolate", 1) ==> "Choclate"

	public String nMid(String input, int a) {
		// Start of the string of interest is at midpoint - [(a - 1)/2]
		//Test to see if a is even
		double b = a;
		StringBuilder str = new StringBuilder(input);

		if(b/2 == Math.ceil(b/2)){
			// Even case, can use a rather than b
			int start =  input.length()/2 - a/2 + 1;

			return str.delete(start, start + a).toString();
		} else {
			// Odd case, must use b
			int start = (int) (Math.ceil(input.length()/2) - (b-1)/2);
			return str.delete(start, start + a).toString();
		}
	}


	// Given a string, return the length of the largest "block" in the string.
	// A block is a run of adjacent chars that are the same.
	//
	// superBlock("hoopplla") ==> 2
	// superBlock("abbCCCddDDDeeEEE") ==> 3
	// superBlock("") ==> 0

	public int superBlock(String input) {

		int len = input.length();
		int count = 0;

		for( int i=0; i<len; i++){
			int currentMax = 1;
			for( int j = i+1; j<len; j++){
				if(input.charAt(i) != input.charAt(j)){
					break;
				}
				currentMax++;
			}
			if(currentMax > count){
				count = currentMax;
			}
		}

		return count;

	}
	
	//given a string - return the number of times "am" appears in the String ignoring case -
	// BUT ONLY WHEN the word "am" appears without being followed or proceeded by other letters
	//
	//amISearch("Am I in Amsterdam") ==> 1
	//amISearch("I am in Amsterdam am I?") ==> 2
	//amISearch("I have been in Amsterdam") ==> 0

	public int amISearch(String arg1) {
		int counter = 0;
		int index = arg1.indexOf(" am ");
		arg1 = arg1.toLowerCase();


		//Test the two edge cases where "am" falls at the start or end of the string
		if(arg1.startsWith("am ")){
			counter++;
		}

		if(arg1.endsWith(" am")){
			counter++;
		}

		//Test all the middle cases
		while(index != -1){
			arg1 = arg1.substring(index + 1);
			index = arg1.indexOf(" am ");
			counter++;
		}

		return counter;
	}
	
	//given a number 
	// if this number is divisible by 3 return "fizz"
	// if this number is divisible by 5 return "buzz"
	// if this number is divisible by both 3  and 5return "fizzbuzz"
	//
	//fizzBuzz(3) ==> "fizz"
	//fizzBuzz(10) ==> "buzz"
	//fizzBuzz(15) ==> "fizzbuzz"
	
	public String fizzBuzz(int arg1) {
		StringBuilder str = new StringBuilder("");

		if(arg1%3 == 0){
			str.append("fizz");
		}

		if(arg1%5 == 0){
			str.append("buzz");
		}
		return str.toString();
		
	}
	
	//Given a string split the string into the individual numbers present
	//then add each digit of each number to get a final value for each number
	// String example = "55 72 86"
	//
	// "55" will = the integer 10
	// "72" will = the integer 9
	// "86" will = the integer 14
	//
	// You then need to return the highest vale
	//
	//largest("55 72 86") ==> 14
	//largest("15 72 80 164") ==> 11
	//largest("555 72 86 45 10") ==> 15
	
	public int largest(String arg1) {
		//Break up the string into an array of numbers
		String str[] = arg1.split(" ");
		//Current max is largest number value
		int currentMax = 0;


		for(int i = 0; i<str.length; i++) {
			//Break up each number into each individual Char
			String nums[] = str[i].split("");

			int comp = 0;
			for (int j = 0; j < nums.length; j++) {
				comp += Integer.parseInt(String.valueOf(nums[j]));
			}
			if (comp > currentMax) {
				currentMax = comp;
			}
		}
		return currentMax;
	}
}
