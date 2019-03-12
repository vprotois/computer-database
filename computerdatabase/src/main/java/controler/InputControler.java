package controler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputControler {

	public static Integer getInputInt() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer inputInt = null;
		System.out.println("Enter a number");
		try {
			inputInt = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Error format, null will be taken");
		} catch (IOException e) {
			System.out.println("Error IOstream, null will be taken");
		}
		return inputInt;
	
	}

	public static Long getInputLong() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Long inputInt = null;
		System.out.println("Enter a number");
		try {
			inputInt = Long.parseLong(br.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Error format, null will be taken");
		} catch (IOException e) {
			System.out.println("Error IOstream, null will be taken");
		}
		return inputInt;
	
	}

	public static int getInputInt(int min, int max) {
		Integer inputInt;
		do {
			inputInt = getInputInt();
		}while (inputInt == null || 
				inputInt.intValue() < min ||
				inputInt.intValue() > max);
		return inputInt.intValue();
	}

	public static String getInputString() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputString = null;
		System.out.println("Input String");
		try {
			inputString = br.readLine();
		} catch (IOException e) {
			System.out.println("Error IOstream");
		}
		return inputString;
	}

	public static String getInputStringNotNull() {
		String inputString = null;
		do {
			inputString = getInputString();
		}while(inputString == null);
		return inputString;
	}

}
