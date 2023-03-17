/* File: Histogram.java
 * Author: Alice Han
 * alicehan@bu.edu
 * 
 * Purpose: This is a potential solution to the
 * Histogram problem.
 */

import java.util.Scanner;
import java.util.Arrays;

public class Histogram { 
    
    private static final int SENTINAL = -999;          // sentinal value to signal endo of input
    private static final int MAX_NUMBERS = 20;         // maximum number of numbers to input
    private static final double UPPER_BOUND = 100.0;   // largest numbers accepted as data
    private static final double LOWER_BOUND = 0.0;     // smallest numbers accepted as adata
    private static final int NUM_BINS = 10;            // number of bins in range [0..100]

    private static int totalNumbers = 0;                // number of values in added to the numbers array

    private static final int BIN_SIZE = (int)UPPER_BOUND/NUM_BINS;           // size of each bin


    /*
     * Method to show an example of using StringBuilder.
     *
     * You will also notice that this method is called from the 
     * main function. 
     *
     */
    public static String getHeaderAsString( String me ) {

	// Create an instance of the StringBuilder class
	// which allows us to create an object of 
	// a series of strings that can then be converted 
	// into one large string via the toString method.
	//
    	StringBuilder sb=new StringBuilder();

        sb.append( System.getProperty("line.separator") );
        sb.append( "Welcome to the Histogram Program " + me + "!" );
	    me = getFirstName(me);
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "This program will print out a histogram of the numbers" );
        sb.append( System.getProperty("line.separator") );
        sb.append( "input by you " + getFirstName(me) + "." );
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "Please enter up to " + MAX_NUMBERS + " doubles or " + SENTINAL + " to stop input!" );
        sb.append( System.getProperty("line.separator") );

        return sb.toString();
    }

    /* 
     * Method to return the first name of the user in case
     * the full name was entered. 
     */
    public static String getFirstName(String name ) {
        // Note that add the " " to string to be sure
        // there is something to split
	    return (name+" ").split(" ")[0]; 
    }

    /* 
     * local main test driver
     *
     */
    public static void main(String[] args) {  

	// Connect to the keyboard as the input stream
        Scanner userInput = new Scanner( System.in );

        System.out.print( "And who am I working with today? " );
        String user = userInput.nextLine();

	    String heading = getHeaderAsString( user );

        // Print out welcome message
        System.out.println( heading ); 
        
        // Call the method which prompts the user
        // to input the numbers that will be used
        // to build the histogram.
        double[] numbers = inputNumber(userInput);
        

        // Call the method to reate the array histogram
        int[] histogram = calculateHistogram( numbers );

        // Print the historgram
        System.out.println( toString( histogram ) );
    }

  



     // This method should create and return an array of integers
    // that represents the resulting histogram from the numbers
    // entered and passed to the method.
    //
    public static int [] calculateHistogram( double [] numbers ) {
        int[] arr = new int[NUM_BINS];

        for(int i = 0; i < totalNumbers; i++){
            int n = findBin(numbers[i]);
            if(numbers[i] == UPPER_BOUND){
                arr[arr.length - 1]++;
            }else if(numbers[i]%BIN_SIZE == 0 && numbers[i] != 0){
                arr[n-1]++;
            }else{
                arr[n]++;
            }
        }

        return arr;
    }

    //returns index of Histogram that the number belongs in
    public static int findBin( double num ) {
        return (int)num / (BIN_SIZE);
    }

    //converts the array to a printable String
    public static String toString( int [] histogram ) {

        StringBuilder s  = new StringBuilder();
        
        for(int i = 0; i < histogram.length; i++){
            if(i == 0){
                s.append("[0.." + (int)BIN_SIZE + "]: ");
                for(int j = 0; j < histogram[i]; j++){
                    s.append("*");
                }
                s.append(System.getProperty("line.separator"));
            }else{
                s.append("(" + (int)(BIN_SIZE)*(i) + ".." + (int)(BIN_SIZE)*(i+1) + "]: ");
                for(int j = 0; j < histogram[i]; j++){
                    s.append("*");
                }
                s.append(System.getProperty("line.separator"));
            }
        }

        return s.toString();
    }

    //checks if the input is between the bounds
    public static boolean validInput(double num){
        return (num < UPPER_BOUND && num > LOWER_BOUND);
    }

    //creates an array of numbers inputed by the user
    public static double[] inputNumber(Scanner scan){
        double[] nums = new double[MAX_NUMBERS];
        for(int i = 0; i < nums.length; i++){
            double x = scan.nextDouble();
            if(x == SENTINAL){
                return nums;
            }
            if(validInput(x)){
                nums[i] = x;
                totalNumbers++;
            }
        }
        return nums;
    }

} // end of class
