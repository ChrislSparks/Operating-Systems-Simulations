// Chrstopher Sparks
// Comp 322 Spring 2026
// Project 1

import java.util.Scanner;


public class Project1 {
      //Global Variables
      private static Scanner input = new Scanner(System.in);
      private static Process[] table;
      
      //Main Method
      public static void main(String[] args) {
               //Enter the table size
               System.out.println("Enter the table size:");
               if (!input.hasNextInt()) { // Data type check
                  System.out.println("Invalid Input.");
                  System.exit(0);
                  }
               int tableSize = input.nextInt();
               input.nextLine(); // input flushing
               if (tableSize <= 0) { // Valid data check
                  System.out.println("Invalid table size.");
                  System.exit(0);
               }
               
               //Create the process table
               table = new Process[tableSize];
               for (int i = 0; i < table.length; i++) {
                  table[i] = new Process();
               }
               
               //Initialize the first process in the table
               table[0].setParentIndex(0);
               
               //Main Loop - could be a while statement or an or statement    
               int option = 0; //Any value other than 4
               while (option != 4) {
                  System.out.println("1) Print the hierarchy from the table");
                  System.out.println("2) Add a child process to the hierarchy");
                  System.out.println("3) Remove a process's descendants from the hierarchy");
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
                           printHierarchy();
                           break;
                     case 2:
                           addProcess();
                           break;
                     case 3:
                           removeProcess();
                           break;
                     case 4:
                           System.out.println("Goodbye.");
                           break;
                     default:
                           System.out.println("Invalid option, try again.");
                    } //end of switch statement
               } // End of main loop
      } // End of main method
      //Print Hierarchy
      public static void printHierarchy() {
         // Print the header row
         System.out.println("Index\tParent\tFirst\tYounger");
         // Print every valid process
         for (int i = 0; i < table.length; i++) {
            if (table[i].getParentIndex() == -1) {
            continue;
            }
            System.out.print(i + "\t\t" + table[i].getParentIndex() + "\t\t\t");
            if (table[i].getFirstChildIndex() != -1) {
               System.out.print(table[i].getFirstChildIndex() + "\t");
               }
            System.out.print("\t");
            if (table[i].getYoungerSiblingIndex() != -1) {
               System.out.print(table[i].getYoungerSiblingIndex());
               }
            System.out.println();
          }
      } // End of print Hierarchy
      
      //Add Process
      public static void addProcess() {
         // Enter the parent proess Index
         System.out.println("Enter the parent process index for the child process:");
         if (!input.hasNextInt()) { // Data type check
               System.out.println("Invalid Input.");
               input.nextLine(); // Input flushing
               return;
               }
         int parentIndex= input.nextInt();
         input.nextLine(); // Input flushing
               if (parentIndex < 0 || parentIndex >= table.length) { // Valid Data Check
                  System.out.println("Invalid process index.");
                  return;
               }
               if (table[parentIndex].getParentIndex() == -1) { 
                  System.out.println("Process index is not active");
                  return;
               }
         
         
         // Calculate the next available index as the child process index
         int childIndex = 1; // can also be 0
         while (table[childIndex].getParentIndex() != -1){
            childIndex++;
            if (childIndex == table.length) {
               System.out.println("Unable to assugn an index for the child process.");
               return;
            }
         }
         
         // Set the parent index for the child process
         table[childIndex].setParentIndex(parentIndex);
         
         // Set the child process for the parent process
         if(table[parentIndex].getFirstChildIndex() == -1) {
            table[parentIndex].setFirstChildIndex(childIndex);
               // Set the child process index as the parent process' first child
         } else {
               // Find the youngest sibling process among the parent process' children
               // Note - Start with the oldest and travel towards the youngest
            int youngestIndex = table[parentIndex].getFirstChildIndex();
            while (table[youngestIndex].getYoungerSiblingIndex() != -1){
               youngestIndex = table[youngestIndex].getYoungerSiblingIndex();
            }
               
               // Assign the youngest sibling process index as the youngest sibling process' younger sibling
               // note - this now makes the child process the new youngest sibling process
            table[youngestIndex].setYoungerSiblingIndex(childIndex);
         }
         // Confirm that the child process was successfully added
         System.out.println("Process " + childIndex + " was added as a child process of " + parentIndex + ".");
         
      } // End of add process
      
      //Remove Process
      public static void removeProcess() {
         
         //Enter a valid Parent process index
                  System.out.println("Enter the parent process index whose descendants will be removed:");
         if (!input.hasNextInt()) { // Data type check
               System.out.println("Invalid Input.");
               input.nextLine(); // Input flushing
               return;
               }
         int parentIndex= input.nextInt();
         input.nextLine(); // Input flushing
               if (parentIndex < 0 || parentIndex >= table.length) { // Valid Data Check
                  System.out.println("Invalid process index.");
                  return;
               }
               if (table[parentIndex].getParentIndex() == -1) { 
                  System.out.println("Process index is not active");
                  return;
               }
               
           // Call remove process recursively on with the parent process' first child index 
           // representing the targeted process for removal (not the parent process itself).
           int firstChild = table[parentIndex].getFirstChildIndex();
               if (firstChild == -1){
               System.out.println("There were no descendants to remove");
               return;
               }
           removeProcessRecursively(firstChild);
           
           
           // Set value of the parent process' first child index is initialized to -1, 
           // followed by a message that prints the success of the operation.
           table[parentIndex].setFirstChildIndex(-1);
           System.out.println("All descendants of process " + parentIndex +  " were removed.");
           
         
      } // End of Remove process
      
      // Remove Process Recursively
      public static void removeProcessRecursively (int currentIndex) {
         
         // First, check to see if currentIndex is valid or invalid. If it is invalid (-1), 
         // then the method terminates immediately
         
         if (currentIndex == -1){
         return;
         }
         
                  if (currentIndex < 0 || currentIndex >= table.length) { // Valid Data Check
         System.out.println("Invalid process index.");
         return;
         }
         
         int sibling = table[currentIndex].getYoungerSiblingIndex();
         int child = table[currentIndex].getFirstChildIndex();
         
         // If currentIndex is valid (not -1), then call the recursive method on the 
         // younger sibling index of table[currentIndex] 
         // (this continues until the youngest sibling process is reached).
         
         removeProcessRecursively(sibling);         
         
         // call the recursive method on the first child index of table[currentIndex]
         // (this means that any given process' siblings will be destroyed before its children are).
         
         removeProcessRecursively(child);
         
         // Once the recursive travel returns back to the current process, 
         // assign -1 into all three variables of table[currentIndex] 
         // (this includes parentIndex, firstChildIndex, and youngerSiblingIndex).
         table[currentIndex].setParentIndex(-1);
         table[currentIndex].setYoungerSiblingIndex(-1);
         table[currentIndex].setFirstChildIndex(-1);
         
      } // End of recursive Remove process
     
} // End of Class
