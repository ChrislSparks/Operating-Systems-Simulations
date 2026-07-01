// Christopher Sparks
// Comp 322 Spring 2026
// Project 3

import java.util.Scanner;

public class Project3 {
      //Global Variables
      private static Scanner input = new Scanner(System.in);
      private static Block[] table;
      private static int headIndex = -1;
      
      //Main Method
      public static void main(String[] args) {
      
            //Enter the table size
               System.out.println("Enter the table size:");
               if (!input.hasNextInt()) { // Data type check
                  System.out.println("Invalid Input.\n");
                  System.exit(0);
                  }
               int tableSize = input.nextInt();
               input.nextLine(); // input flushing
               if (tableSize <= 0) { // Valid data check
                  System.out.println("Invalid table size.\n");
                  System.exit(0);
               }
               
             //Create the file allocation table
             table = new Block[tableSize];
             for (int i = 0; i < table.length; i++){
                  table[i] = new Block();
             }
             
             //Main Loop
             int option = 0; //Any value other than 4
               while (option != 4) {
                  System.out.println("1) Print the table and the file");
                  System.out.println("2) Add a block to the file");
                  System.out.println("3) Remove a block from the file");
                  System.out.println("4) Quit the program");
                  System.out.println("Enter selection:");
                  if (input.hasNextInt()){ //Data Type Check
                     option = input.nextInt();
                  } 
                  else {
                     option = 0; // any value other than 1,2,3,4
                  }
                  input.nextLine(); // Input flushing
                  switch (option) {
                     case 1:
                           printTableAndFile();
                           break;
                     case 2:
                           addBlock();
                           break;
                     case 3:
                           removeBlock();
                           break;
                     case 4:
                           System.out.println("Goodbye.");
                           break;
                     default:
                           System.out.println("Invalid option, try again.\n");
                    } //end of switch statement
               } // End of main loop

      
      } // End of Main Method
      
      //Print Table and File
      public static void printTableAndFile() {
            // Print the table view header row
            System.out.println("Table View:");
            System.out.println("Index\tData\tNext");

            // Print every block from the table out as a row
            for (int i = 0; i < table.length; i++) {
                  System.out.println(i + "\t" + table[i].getData() + "\t" + table[i].getNextIndex());
            }
            
            // Print the file view header row
           System.out.println("File View:");
           System.out.println("Index\tData\tNext");
            
            // Print every block from the file as a row
            int currentIndex = headIndex;
            while (currentIndex != -1) {
                  System.out.println(currentIndex + "\t" + table[currentIndex].getData() + "\t" + table[currentIndex].getNextIndex());
                  currentIndex = table[currentIndex].getNextIndex();
            }
            System.out.println(); // blank line
            
      } // End of Print Table and File
      
      //Add Block
      public static void addBlock() {
            System.out.println("Enter an index for the block to add:");
            if (!input.hasNextInt()) { // Data Type Check
                  System.out.println("Invalid input.\n");
                  input.nextLine(); // Input flushing
                  return;
                  }
            int newIndex = input.nextInt();
            input.nextLine(); // Input flushing
            if (newIndex < 0 || newIndex >= table.length) { // Valid Data Check
                  System.out.println("Invalid block index.\n");
                  return;
                  }
            // Enter the data for the new block
            System.out.println("Enter an unsigned integer of data:");
            if (!input.hasNextInt()) { // Data type check
                  System.out.println("Invalid input.\n");
                  input.nextLine(); // Input flushing
                  return;
                  }
            int newData = input.nextInt();
            input.nextLine(); // Input flushing
            if (newData < 0) { // Valid Data Check
                  System.out.println("Invalid data\n");
                  return;
                  }
            
            
            // Check the value of the head index
            if (headIndex == -1) {
                  // Assign the new block to the head of the file
                  headIndex = newIndex;
                  table[newIndex].setData(newData);
                  System.out.println("Added first block at index " + newIndex + ".\n");
                  }
            else {
                  // Iterate through the file and detect if the new index is in use or not
                  // Note - the new index will always be in use if the file is full
                  int currentIndex = headIndex;
                  while (currentIndex != newIndex) {
                        // the new index is not in use if the ned of the file is reached
                        if (table[currentIndex].getNextIndex() == -1) {
                              //Assign the new block to the end of the file
                              table[currentIndex].setNextIndex(newIndex);
                              table[newIndex].setData(newData);
                              System.out.println("Added new block at index " + newIndex + ".\n");
                              return;
                              }
                        currentIndex = table[currentIndex].getNextIndex();
                        }
                  System.out.println("Duplicate block index detected.\n");
                  } // end else statement
      } // End of Add Block
      
      //Remove Block
      public static void removeBlock() {
            System.out.println("Enter an index for the block to remove:");
            if (!input.hasNextInt()) { // Data Type Check
                  System.out.println("Invalid input.\n");
                  input.nextLine(); // Input flushing
                  return;
                  }
            int removalIndex = input.nextInt();
            input.nextLine(); // Input flushing
            if (removalIndex < 0 || removalIndex >= table.length) { // Valid Data Check
                  System.out.println("Invalid block index.\n");
                  return;
                  }
                  
            //If headIndex is removal index
            if (headIndex == removalIndex) {
                  int replacementIndex = table[removalIndex].getNextIndex();
                  table[removalIndex].setData(0);
                  table[removalIndex].setNextIndex(-1);
                  headIndex = replacementIndex;
                  System.out.println("Removed the block at head index: " + removalIndex + ".\n");
                  }
            else if (headIndex != removalIndex) {
                  // Traverse the array trying to find a matching index
                  int currentIndex = headIndex;
                  while (currentIndex != -1){
                        
                        if (table[currentIndex].getNextIndex() == removalIndex){
                              int replacementIndex = table[removalIndex].getNextIndex();
                              table[removalIndex].setData(0);
                              table[removalIndex].setNextIndex(-1);
                              table[currentIndex].setNextIndex(replacementIndex);
                              System.out.println("Removed the block at index: " + removalIndex + ".\n");
                              return;
                              }
                        currentIndex = table[currentIndex].getNextIndex();
                        }
                        
                        // If the block cant be found
                        if (currentIndex == -1) {
                              System.out.println("Unable to locate the block.\n");
                              return;
                              }
                  
                  // If index is not found
                  
                  }
                  

      } // End of Remove Block


} // end of class