package test.java;

import java.util.ArrayList;
import java.util.Scanner;

public class RomanToArabicAndArabicToRoman {
	final int I=1;
	final int V=5;
	final int X=10;
	final int L=50;
	final int C=100;
	final int D=500;
	final int M=1000;
	
	private boolean checkStringIsRoman(String str) {
				
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i)!= 'I' 
				&& str.charAt(i)!= 'V'
					&& str.charAt(i)!= 'X'
						&& str.charAt(i)!= 'L'
							&& str.charAt(i)!= 'C'
								&& str.charAt(i)!= 'D'
									&& str.charAt(i)!= 'M') {
				log("Please enter a valid Number: " +str);
				return false;
			}
		}
		
		
		// Rule
		//* The symbols "I", "X", "C", and "M" can be repeated two times in succession, but no more.
		if(str.contains("III") 
				|| str.contains("XXX") 
				|| str.contains("CCC") 
				|| str.contains("MMM")) {
			log("Invalid! The symbols I, X, C, and M can be repeated two times in succession, but no more.");
			return false;
		}
		
		//"D", "L", and "V" can never be repeated.
		if(str.contains("DD") 
				|| str.contains("LL") 
				|| str.contains("VV")) {
			log("Invalid! D, L, and V can never be repeated.");
			return false;
		}

		return true;
	}
	
	private int romanToArabic(String romanNumber){
		ArrayList<String> arabicValueList = new ArrayList<String>();
		
		for(int i = 0; i < romanNumber.length(); i++){
			if(romanNumber.charAt(i)=='I') {
				arabicValueList.add(I+"");
			}
			if(romanNumber.charAt(i)=='V') {
				arabicValueList.add(V+"");
			}
			if(romanNumber.charAt(i)=='X') {
				arabicValueList.add(X+"");
			}
			if(romanNumber.charAt(i)=='L') {
				arabicValueList.add(L+"");
			}
			if(romanNumber.charAt(i)=='C') {
				arabicValueList.add(C+"");
			}
			if(romanNumber.charAt(i)=='D') {
				arabicValueList.add(D+"");
			}
			if(romanNumber.charAt(i)=='M') {
				arabicValueList.add(M+"");
			}
		}
		
		int result = 0;
		int k = 1;
		for(int i = 0; i < arabicValueList.size(); i+=k) {
			k = 1;
			/************* "I" can be subtracted from "V" and "X" only ************/
			if(Integer.parseInt(arabicValueList.get(i)) == I) {
				
				try {
					if(Integer.parseInt(arabicValueList.get(i+1)) == I) {
						try {
							// IIV or IIX
							if(Integer.parseInt(arabicValueList.get(i+2)) == V || Integer.parseInt(arabicValueList.get(i+2)) == X) {
								result += Integer.parseInt(arabicValueList.get(i+2)) - I - I;
								k=3;
							}
							else {
								result += I + I;
								k=2;
							}
						}
						catch(IndexOutOfBoundsException ioobeII) {
							ioobeII.getMessage();
							result += I + I;
							k=2;
						}
					}
					// IV or IX
					else if(Integer.parseInt(arabicValueList.get(i+1)) == V || Integer.parseInt(arabicValueList.get(i+1)) == X) {
						result += Integer.parseInt(arabicValueList.get(i+1)) - Integer.parseInt(arabicValueList.get(i));
						k=2;						
					}
					else {
						result += Integer.parseInt(arabicValueList.get(i));
					}
				}
				// if only one I
				catch (IndexOutOfBoundsException ioobeI) {
					ioobeI.getMessage();
					result += Integer.parseInt(arabicValueList.get(i));
				}
			}
			
			/********************* "X" can be subtracted from "L" and "C" only. *************************/
			if(Integer.parseInt(arabicValueList.get(i)) == X) {
				try {
					if(Integer.parseInt(arabicValueList.get(i+1)) == X) {
						//tokenValue = X + X;
						try {
							// XXL or XXC
							if(Integer.parseInt(arabicValueList.get(i+2)) == L || Integer.parseInt(arabicValueList.get(i+2)) == C) {
								result += Integer.parseInt(arabicValueList.get(i+2)) - X - X;
								k=3;
							}
							else {
								result += X + X;
								//tokenValue =0;
								k=2;
							}						
						}
						catch(IndexOutOfBoundsException ioobeXX) {
							ioobeXX.getMessage();
							result += X + X;
							k=2;
						}
					}
					// XL or XC
					else if(Integer.parseInt(arabicValueList.get(i+1)) == L || Integer.parseInt(arabicValueList.get(i+1)) == C) {
						result += Integer.parseInt(arabicValueList.get(i+1)) - Integer.parseInt(arabicValueList.get(i));
						k=2;
					}
					else {
						result += Integer.parseInt(arabicValueList.get(i));
					}
				}
				catch (IndexOutOfBoundsException ioobeX) {
					ioobeX.getMessage();
					result += Integer.parseInt(arabicValueList.get(i));
				}
			}
			/*********** "C" can be subtracted from "D" and "M" only ****************/
			if(Integer.parseInt(arabicValueList.get(i)) == C) {
				try {
					if(Integer.parseInt(arabicValueList.get(i+1)) == C) {
						//tokenValue = C + C;
						try {
							// CCD or CCM
							if(Integer.parseInt(arabicValueList.get(i+2)) == D || Integer.parseInt(arabicValueList.get(i+2)) == M) {
								result += Integer.parseInt(arabicValueList.get(i+2)) - C - C;
								k=3;
							}
							else {
								result += C + C;
								k=2;
							}
						}
						catch(IndexOutOfBoundsException ioobeCC) {
							ioobeCC.getMessage();
							result += C + C;
							k=2;
						}
					}
					// CD or CM
					else if(Integer.parseInt(arabicValueList.get(i+1)) == D || Integer.parseInt(arabicValueList.get(i+1)) == M) {
						result += Integer.parseInt(arabicValueList.get(i+1)) - Integer.parseInt(arabicValueList.get(i));
						k=2;
					}
					else {
						result += Integer.parseInt(arabicValueList.get(i));
					}
				}
				catch (IndexOutOfBoundsException ioobeC) {
					ioobeC.getMessage();
					result += Integer.parseInt(arabicValueList.get(i));
				}
			}
			
			/*************** "V", "L", and "D" can never be subtracted.
			 * If M is first value in loop ************/
			if(Integer.parseInt(arabicValueList.get(i)) == V || 
					Integer.parseInt(arabicValueList.get(i)) == L ||
					Integer.parseInt(arabicValueList.get(i)) == D ||
					Integer.parseInt(arabicValueList.get(i)) == M) {
				
				result += Integer.parseInt(arabicValueList.get(i));
			}
		}
		return result;		
	}
	
	/**************** Arabic to Roman ********************/
	private String arabicToRoman(int arabicNumber){
		String romanNumber = "";
		int leftArabicNumber = arabicNumber;
		
		while(leftArabicNumber > 0) {
			
			if(leftArabicNumber >= 1000) {
				romanNumber += "M";
				leftArabicNumber -= 1000;
			}
			else if(leftArabicNumber >= 900) {
				romanNumber += "CM";
				leftArabicNumber -= 900;
			}
			else if(leftArabicNumber >= 800) {
				romanNumber += "CCM";
				leftArabicNumber -= 800;
			}
			else if(leftArabicNumber >= 500) {
				romanNumber += "D";
				leftArabicNumber -= 500;
			}
			else if(leftArabicNumber >= 400) {
				romanNumber += "CD";
				leftArabicNumber -= 400;
			}
			else if(leftArabicNumber >= 300) {
				romanNumber += "CCD";
				leftArabicNumber -= 300;
			}
			else if(leftArabicNumber >= 100) {
				romanNumber += "C";
				leftArabicNumber -= 100;
			}
			else if(leftArabicNumber >= 90) {
				romanNumber += "XC";
				leftArabicNumber -= 90;
			}
			else if(leftArabicNumber >= 80) {
				romanNumber += "XXC";
				leftArabicNumber -= 80;
			}
			else if(leftArabicNumber >= 50) {
				romanNumber += "L";
				leftArabicNumber -= 50;
			}
			else if(leftArabicNumber >= 40) {
				romanNumber += "XL";
				leftArabicNumber -= 40;
			}
			else if(leftArabicNumber >= 30) {
				romanNumber += "XXL";
				leftArabicNumber -= 30;
			}
			else if(leftArabicNumber >= 10) {
				romanNumber += "X";
				leftArabicNumber -= 10;
			}
			else if(leftArabicNumber >= 9) {
				romanNumber += "IX";
				leftArabicNumber -= 9;
			}
			else if(leftArabicNumber >= 5) {
				romanNumber += "V";
				leftArabicNumber -= 5;
			}
			else if(leftArabicNumber >= 4) {
				romanNumber += "IV";
				leftArabicNumber -= 4;
			}
			else if(leftArabicNumber >= 3) {
				romanNumber += "IIV";
				leftArabicNumber -= 3;
			}
			else if(leftArabicNumber >= 1) {
				romanNumber += "I";
				leftArabicNumber -= 1;
			}
		}
		return romanNumber;
	}
	
	/****************************************************/	
	private void getUserInputValue(String intputValue) {
		String romanNumber = intputValue.toUpperCase().trim();
		
		if(!romanNumber.equals("")) {
			try {
				int arabicNumber = (int) Double.parseDouble(romanNumber);
				if(arabicNumber < 3000 && arabicNumber > 0){
					log("Arabic to Roman: ");
					log(arabicNumber +" >> " + arabicToRoman(arabicNumber));
				}
				else 
					log("Please input your number between 1 to 2999");
			}
			catch(NumberFormatException nfe) {
				nfe.getMessage();
				if(checkStringIsRoman(romanNumber)) {
					log("Roman to Arabic: ");
					log(romanNumber + " >> " +romanToArabic(romanNumber));
				}
			}
		}
		else
			log("Please input a valid number, either Roman or Arabic");
	}

	public static void main(String[] args) {
		RomanToArabicAndArabicToRoman rta = new RomanToArabicAndArabicToRoman();
		
		Scanner input = new Scanner(System.in);
		log("You can input either Arabic Integer Number or Roman number. It will convert in its corresponding number system." +
				"\nIf you enter Roman number, program will understand that you want to convert your Roman number to Arabic number. " +
				"\nSame as for the Arabic Number, the program will convert it in Roman Number System.");
		
		//String str="100.1";
		//log("Input:" + str);
		log("Please enter your number(Arabic/Roman):");
				
		String str = input.nextLine().toUpperCase().trim();
		rta.getUserInputValue(str);
		//log("Input:" + str);
	}
	
	public static void log(Object obj){
		System.out.println(String.valueOf(obj));
	}
}
