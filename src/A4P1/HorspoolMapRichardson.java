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

public class HorspoolMapRichardson 
{
	
	/*
	 * This method is the constructor method. It reads in the data from the file and implements the
	 * Boyer-Moore-Horspool algorithm.
	 * @param fileName The string of the input file name.
	 */
	public HorspoolMapRichardson(String fileName) throws FileNotFoundException
	{
				
		//String for text.
		String text;
		
		//String for pattern.
		String pattern;
		
		//Create a new scanner with the file name.
		Scanner sc = new Scanner(new File(fileName));
		
		//Create a new writer for the output file.
		PrintWriter writer = new PrintWriter("output.txt");
		
		//While the file has a next line.
		while(sc.hasNextLine())
		{
			
			//Read the first line.
			String line1 = sc.nextLine();
			
			//If the line is not empty.
			if(line1.length() !=0)
			{
				//Create the BMT HashMap. Create a new one for each problem.
				HashMap<Character, Integer> bmt = new HashMap<Character, Integer>();
				
				//Get text and pattern.
				text = line1;
				pattern = sc.nextLine();;
				
				//Write the text and pattern to the output file.
				writer.println("text: " + text + "\t" + "pattern: " + pattern);
				
				//If the pattern is bigger than the text.
				if(pattern.length() > text.length())
				{
					//Write an error message.
					writer.println("Pattern is longer than the text.\n");
				}
				else 
				{
				
					//Construct the BTM.
					
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
					
					//Add wildcard to the BMT.
					bmt.put('*', pattern.length());
					
					//Write the beginning of the bmt table to the output file.
					writer.print("bmt Table: {");
					
					//For each entry in the hashmap.
			        for (Map.Entry<Character, Integer> entry : bmt.entrySet()) {
			        	//Write the key and the value to the file.
			        	writer.print(entry.getKey() + "=" + entry.getValue() + ", ");
			        } 
					
			        //Write the closing of the bmt table to the output file.
			        writer.print("}\n");
			        
			        
			        //Use Boyer-Moore-Horspool algorithm to find pattern.
			        
			        
			        //Variable to keep the index of the text.
			        int textIndex = pattern.length() - 1;

					//Variable to keep the index of the pattern.
			        int patternIndex = pattern.length() - 1;
			        
			        //Varible to count the number of matched chars.
			        int matchedChars = 0;
			        
			        //Variable for the matched position of the pattern.
			        int matchedPos = 0;
			        
			        //While index is less than the pattern.
			        while(textIndex < text.length())
			        {		        	
			        	//If the characters match.
			        	if(text.charAt(textIndex) == pattern.charAt(patternIndex))
			        	{
			        		//Increment matched chars.
			        		matchedChars++;
			        		//Decrement pattern index.
			        		patternIndex--;
			        		//If number of matched characters equals the leng of the pattern.
			        		if(matchedChars == pattern.length())
			        		{
			        			//Set the matched position equal to the text index.
			        			matchedPos = textIndex;
			        			//Break out of the loop.
			        			break;
			        		}
			        		//Decrement index by 1.
			        		textIndex--;
			        	}
			        	//If this character exists in the pattern.
			        	else if (pattern.indexOf(text.charAt(textIndex)) >= 0 )
			        	{
			        		//Increment the pattern by the value of the btm of that character.
			        		textIndex += bmt.get(text.charAt(textIndex));
			        		
			        		//Reset the number of matched characters.
			        		matchedChars = 0;
			        		
			        		//Reset the pattern index.
			        		patternIndex = pattern.length() - 1;
			        	}
			        	else
			        	{
			        		//Increment index by the length of the pattern.
			        		textIndex += bmt.get('*');
			        		
			        		//Reset the number of matched characters.
			        		matchedChars = 0;
			        		
			        		//Reset the pattern index.
			        		patternIndex = pattern.length() - 1;
			        	}
			        }
			        
			        //If the matched position does not equal 0.
			        if(matchedPos != 0)
			        {
			        	//Write the results to the output file.
				        writer.println("Found match for the pattern: " + pattern + " at pos: " + matchedPos + "\n");
			        }  
			        else 
			        {
			        	//Write that the pattern does not exist in the text to the output file.
			        	writer.println("The pattern does not exist in the text.\n\n\n");
			        }
				}
			}
		}
		
		//Clsoe the scanner and the writer.
		sc.close();
		writer.close();
	}
	
	/*
	 * This is the main method of the class. If verfies the amount of arugments and instaties the constructor.
	 * @ args[] Arguements from the commmand line.
	 */
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
		
		//Create a new HorspoolMap passing in the file name from the command line argument.
		new HorspoolMapRichardson(args[0]);
	}
}