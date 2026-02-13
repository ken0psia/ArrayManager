//Angel Baldovinos - COMP-SCI 303 - Assignment 1 - February 13, 2026
import java.io.File;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

//ASSIGNMENT 1 TO-DO LIST
//1. readFile (done)
//2. writeFile(done)
//3. checkValue (done)
//4. modifyValues (done)
//5. appendValue (done)
//6. deleteValue (done)
//try catch user inputs on modifyValues (must return new value and old value) & appendValue 

//only contains a "global" array length (int) variable to be used across all methods
//and demonstrate the functionality of appendValue's try/catch & doubling array
//every instance that uses the array's length should refer to this variable
abstract class arrLength {
    public static int length = 0;
}


public class ArrayManager {
    //Parses and reads all elements from the input file
    public Integer[] readFile() throws Exception {
        //Scans Input File
        Scanner input = new Scanner(new File("Assignment1/bin/A1input.txt"));
        //Create big-ish array to hold starting numbers 
        Integer[] numbers = new Integer[100];
        //read until end of file
        int index = 0;
        //for every space and enter key, read an integer
        while (input.hasNext()) {
            numbers[index] = input.nextInt();
            index++;
        }
        arrLength.length = index;
        input.close();
        return numbers;
    }

    //Writes all elements to an output file with the same formatting as the input file.
    //Needs array of integers
    public void writeFile(Integer[] numbers) throws Exception {
        File textFile = new File("Assignment1/bin/output.txt");
        //if it doesnt exist, create it
        if (!textFile.exists()) {
            textFile.createNewFile();
        }
        //creates object to write file object to actual file
        PrintWriter output = new PrintWriter(textFile);
        int count = 1;
        //write the entire array to file, on the 10th index,
        //make a new line, ignores/does not print null objects.
        for (Integer number : numbers) {
            if (count == 10) {
                output.print(number);
                output.println();
                count = 1;
            }
            else if (number == null) {
                continue;
            }
            else {
                output.print(number + " ");
                count++;
            }   
        }
        output.close();
    }

    //iterates through the array to find the first occurrence of a value given
    public int checkValue(Integer[] numbers, int value) {
        for (int i = 0; i < arrLength.length; i++) {
            if (numbers[i] != null && numbers[i] == value) {
                return i;
            }
        }
        return -1;
    }

    //modifies a value at a specific index, includes try/catch
    public static boolean modifyValues(Integer[] numbers, int index, int newValue) {
        try { 
            if (index >= 0 && index < arrLength.length && numbers[index] != null)
            numbers[index] = newValue;
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Error modifying value: " + e.getMessage());
        }
        return false;
    }

    // appends a value and doubles the "global" array size along with the array if it's full.
    // returns the (possibly new) array so callers can pick up the resized array.
    // includes try/catch
    public static Integer[] appendValue(Integer[] numbers, int newValue) {
        try {
            if (numbers == null) {
                Integer[] newNumbers = new Integer[1];
                newNumbers[0] = newValue;
                arrLength.length = 1;
                return newNumbers;
            }

            // Check if array is full (current length equals physical array size)
            if (arrLength.length >= numbers.length) {
                throw new Exception("Array is full");
            }
            // Append at the end of the used portion
            numbers[arrLength.length] = newValue;
            arrLength.length++;
            return numbers;
            
        } catch (Exception e) {
            // Array is full. Double its size and append the value.
            Integer[] doubled = new Integer[numbers.length * 2];
            System.arraycopy(numbers, 0, doubled, 0, arrLength.length);
            doubled[arrLength.length] = newValue;
            arrLength.length++;
            return doubled;
        }
    }

    //deletes a value at a specific index and shifts all elements left after
    public static void deleteValue(Integer[] numbers, int index) {
        numbers[index] = null;
        // Shift elements to the left to fill the gap
        for (int i = index; i < arrLength.length - 1; i++) {
            numbers[i] = numbers[i + 1];
            numbers[i + 1] = null;
        }
    }
    
    //menu screen for all choices & main.
    public static void main(String[] args) throws Exception {
        //create instance and display menu
        ArrayManager app = new ArrayManager();
        Integer[] numbers = app.readFile();
        System.out.print("\n\n\nWelcome to the Array Manager, Please enter an integer (0-4)\n\n");
        
        //gets user input anytime it is called
        Scanner choice = new Scanner(System.in);

        //main loop for menu, while valid, keep running
        //choices are split within their respective cases.
        boolean running = true;
        while (running) {
            System.out.print("\n1. Check if a VALUE exists in array\n2. Modify a value in the array");
            System.out.print("\n3. Append a value to the array\n4. Delete an value (w/ index) from the array\n0. Exit\n");
            System.out.print("Enter your choice (0-4): ");
            switch (choice.nextInt()) { //i love switches.
                case 0:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                case 1:
                    //check if value exists
                    System.out.print("\nEnter value to check: ");
                    int check = choice.nextInt();
                    if (app.checkValue(numbers, check) == -1) {
                        System.out.println("Value not found in array.");
                    }
                    else {
                        System.out.println("\nValue found at index: " + app.checkValue(numbers, check));
                    }
                    break;
                case 2:
                    //modify a value, handles invalid inputs
                    try {
                        System.out.print("\nEnter index to modify: ");
                        int index = choice.nextInt();
                        int old = numbers[index];
                        System.out.print("\nEnter new value: ");
                        int newValue = choice.nextInt();
                        Boolean modded = modifyValues(numbers, index, newValue);

                        //display result
                        if (modded) {
                            System.out.println("Value modified from " + old + " to " + newValue);
                        } else {
                            System.out.println("Failed to modify value.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        choice.next(); 
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid index. Please enter a valid index.");
                    }
                    break;
                case 3:
                    //append a value
                    try {
                        System.out.print("\nEnter value to append: ");
                        int append = choice.nextInt();
                        numbers = appendValue(numbers, append);
                        System.out.println("Value appended.");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        choice.next();
                    }
                    break;
                case 4:
                    //delete a value
                    try {
                        System.out.print("\nEnter index to delete: ");
                        int deleteIndex = choice.nextInt();
                        deleteValue(numbers, deleteIndex);
                        System.out.println("Value deleted.");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        choice.next();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid index. Please enter a valid index.");
                    }
                    break;
                default:
                    //invalid input, returns to main screen
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
        choice.close();
        app.writeFile(numbers);
        arrLength.length = numbers.length; 
        System.out.println("Array saved to file successfully. Final Array Length: " + arrLength.length);
    }
}

