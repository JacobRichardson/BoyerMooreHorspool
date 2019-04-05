/* 
 * A4 P1 Boyer-Moore Horspool
 *  
 * Jacob Richardson 
 * Assignment 4 Part 1 
 * Assigned Date: March 4, 2019 
 * Due Date: April 11, 2019 
 * Submission Date: April 11, 2019 
 */ 

package A4P1;

//imports.
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class HorspoolMapRichardson 
{
	
	public HorspoolMapRichardson(String fileName) throws FileNotFoundException
	{
		
		//BMT HashMap.
		HashMap<Character, Integer> bmt = new HashMap<Character, Integer>();
		
		//String for text.
		String text;
		
		//String for pattern.
		String pattern;
		
		//Create a new scanner with the file name.
		Scanner sc = new Scanner(new File(fileName));
		
		//While the file has a next line.
		while(sc.hasNextLine())
		{
			//Get text and pattern.
			text = sc.nextLine();
			pattern = sc.nextLine();
			
			if(pattern.length() > text.length())
			{
				System.out.println("Pattern is shorter than the text.");
			}
			
			
			System.out.println(text);
			System.out.println(pattern);
			
			//Construct bmt.

			//Loop over the pattern.
			for(int i=0; i<pattern.length(); i++)
			{
				//If this is not the last character.
				if(i != pattern.length()-1)
				{
					//Put the character in as a key a use the formula to calculate the distance from the end of the string.
					bmt.put(pattern.charAt(i), pattern.length() - 1 - i);
				}
				//Check to see if this character is not already in the map.
				else if(!bmt.containsKey(pattern.charAt(i)))
				{
					//The last character is unique so set the value eqaul to the length of the pattern.
					bmt.put(pattern.charAt(i), pattern.length());
				}

			}
			
			//Add wildcard
			bmt.put('*', pattern.length());
			
			//Check to see if
			
			// Printing the charCountMap 
	        for (Map.Entry<Character, Integer> entry : bmt.entrySet()) { 
	            System.out.println(entry.getKey() + " " + entry.getValue()); 
	        } 
			
	        
		}
		
	}
	
	public static void main (String args[]) throws FileNotFoundException
	{
		
		//If there isnt one argument.
		if(args.length != 1)
		{
			//Print an error message.
			System.out.println("Please provide one argument.");
			//Exit with a non zero exit status.
			System.exit(1);
		}
		
		//Create a new HorspoolMap.
		new HorspoolMapRichardson(args[0]);
		
	}
}
