package ac.cr.ucenfotec.encripcion.ui.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class InputUtil {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static final String dateFormatString = "M/d/yyyy";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(dateFormatString);

    public static String getString(String message, String invalidMessage, int max, int min) throws IOException
    {

        String input = "";

        if (invalidMessage == null)
        {
            invalidMessage = "Invalid input. Max permitted length: " + max + "|Min permitted length" + min;
        }

        System.out.println(message);
        input = reader.readLine();

        while (input.length() < min || input.length() > max)
        {
            System.out.println(invalidMessage);
            input = reader.readLine();
        }

        return input;

    }
    
    public static int getInt(String message,int max, int min) throws IOException
    {
    	try {
        int num = 0;
        String numString;
        while (true)
        {
        	if(tryParseInt(numString = reader.readLine()) && !((num = Integer.parseInt(numString)) < min || num > max)) {
                return num;
        	}
            System.out.println(message);
        }
    	}catch(NumberFormatException e) {
    		//Java, why do you make me do this? :(
    	}
		return 0;
    }

    public static double getDouble(String message,double max, double min) throws IOException
    {
    	try {
    		double num = 0;
    	    String numString;
    	    while (true)
    	    {
    	    	if(tryParseDouble(numString = reader.readLine()) && !((num = Double.parseDouble(numString)) < min || num > max)) {
    	    		return num;
    	        }
    	    	System.out.println(message);
    	    }
    	}catch(NumberFormatException e) {
    		// ;(
    	}
		return 0;
    }
    
	public static LocalDate getDate(String message, String invalidMessage) throws IOException {
		
		String dateString = "";
		
		while(true) {
			
			System.out.println(message);
			System.out.println("Format: " + dateFormatString);
			if(tryParseDate((dateString = reader.readLine()))){
				return LocalDate.parse(dateString, dateFormat);
			}
			
			System.out.println(invalidMessage);
		}
		
	}
	
	private static boolean tryParseDate(String dateString){
		try {
			dateFormat.parse(dateString);
			return true;
		}catch(DateTimeParseException e) {
			return false;
		}
	}
	
	private static boolean tryParseInt(String intString){
		try {
			Integer.parseInt(intString);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	private static boolean tryParseDouble(String doubleString){
		try {
			Double.parseDouble(doubleString);
			return true;
		}catch(NumberFormatException|NullPointerException e) {
			return false;
		}
	}
}
	