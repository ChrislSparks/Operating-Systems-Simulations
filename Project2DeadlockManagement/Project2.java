// Christopher Sparks
// Comp 322 Spring 2026
// Project 2

import java.util.Scanner;

public class Project2{
      //Global Constants
      private static final int EMPTY = 0; //None
      private static final int REQUEST = 1; //Process requested resource
      private static final int ALLOCATE = 2; //Process allocated resource
      
      //Global Variables
      private static Scanner input = new Scanner(System.in);
      private static Relation[] process;
      
      
      //Main Method
      public static void main(String[] args) {
            //Enter number of processes
                           System.out.println("Enter the number of processes:");
               if (!input.hasNextInt()) { // Data type check
                  System.out.println("Invalid Input.");
                  System.exit(0);
                  }
               int processes = input.nextInt();
               input.nextLine(); // input flushing
               if (processes <= 0) { // Valid data check
                  System.out.println("Invalid number of processes");
                  System.exit(0);
               }
            
            //Enter the number of resources
            System.out.println("Enter the number of resources:");
               if (!input.hasNextInt()) { // Data type check
                  System.out.println("Invalid Input.");
                  System.exit(0);
                  }
               int resources = input.nextInt();
               input.nextLine(); // input flushing
               if (resources <= 0) { // Valid data check
                  System.out.println("Invalid number of resources");
                  System.exit(0);
               }
               System.out.println("");
                           
            //Create the process-to-resource relations
            process = new Relation[processes];
            for (int i = 0; i < process.length; i++){
                  process[i] = new Relation(resources);
            }
            
            //Main Loop
            int option = 0; //Any value other than 4
               while (option != 4) {
                  System.out.println("1) Print the process-to-resource relations");
                  System.out.println("2) Change a process-to-resource relation");
                  System.out.println("3) Detect the system for deadlock");
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
                           printRelations();
                           break;
                     case 2:
                           changeRelation();
                           break;
                     case 3:
                           detectDeadlock();
                           break;
                     case 4:
                           System.out.println("Goodbye.");
                           break;
                     default:
                           System.out.println("Invalid option, try again.");
                           System.out.println(""); // blank line
                    } //end of switch statement
               } // End of main loop

      
      } // End of Main Method
      
      
      //Print Relations
      public static void printRelations() {
            // Print the header row
            for (int j = 0; j < process[0].getResourceLength(); j++) {
                  System.out.print("\tr" + j);
            }
            System.out.println();
            
            //Print every process-to-resource as a row
            for (int i = 0; i < process.length; i++) {
                  System.out.print("p" + i);
                  for (int j = 0; j < process[0].getResourceLength(); j++) {
                  System.out.print("\t");
                  if (process[i].getResource(j) != EMPTY) {
                  System.out.print(process[i].getResource(j));
                  }
                  }
                  System.out.println();
            }
            System.out.println("");

      } // End of printRelations
      
      //Change Relation
      public static void changeRelation(){
      
            // represents integer located in process
            System.out.println("Enter the process index:"); // Change later
            if (!input.hasNextInt()) { // Data type check
               System.out.println("Invalid Input.\n");
               input.nextLine(); // Input flushing
               return;
               }

            int processIndex = input.nextInt();
            input.nextLine(); // Input flushing
            if (processIndex < 0 || processIndex >= process.length){ // Valid Data Check
                  System.out.println("Invalid process index.\n");
                  return;
                  }
            
            // represents integer located in resource
            System.out.println("Enter the resource index:"); // Change later
            if (!input.hasNextInt()) { // Data type check
               System.out.println("Invalid Input.\n");
               input.nextLine(); // Input flushing
               return;
               }

            int resourceIndex = input.nextInt();
            input.nextLine();
            if (resourceIndex < 0 || resourceIndex >= process[0].getResourceLength()) { // Valid Data Check
                  System.out.println("Invalid resource index.\n");
                  return;
               }

            
            // Enter the new relation between the process and resource
            System.out.println("Enter new relation (0: None, 1: P requests R, 2: P allocated R):"); // Change later
            if (!input.hasNextInt()) { // Data type check
               System.out.println("Invalid Input.\n");
               input.nextLine(); // Input flushing
               return;
               }

            int newRelation = input.nextInt();
            input.nextLine(); // input flishing
            
            // Perform a different action based on the new relation

            if (newRelation == 0) {
               process[processIndex].setResource(resourceIndex,0);
               System.out.println("There is now no relation between process " + processIndex + " and resource " + resourceIndex + ".\n");
            }
            else if (newRelation == 1) {
               process[processIndex].setResource(resourceIndex,1);
               System.out.println("Process " + processIndex + " is now requesting to use resource " + resourceIndex + ".\n");
               }
            
            else if (newRelation == 2) {
               // check for allocation of another process
               
               boolean occupied = false;
               int processConflict = -1;
               for (int i = 0; i < process.length; i++) {
                     if (process[i].getResource(resourceIndex) == 2 && i != processIndex) {
                     occupied = true;
                     processConflict = i;
                     }
               }                  
                  if (occupied) {
                         System.out.println("Process " + processIndex + " cannot be allocated resource " + resourceIndex + " because process " + processConflict + " is using it.\n"); 
               return;
                  }
                  else {
                          process[processIndex].setResource(resourceIndex, 2);
                          System.out.println("Process " + processIndex + " has been allocated resource " + resourceIndex + " for usage.\n"); 
                  }
            } // End of else if statement
                  else {
                           System.out.println("Invalid relation value.\n");       
                           
                  } // End else statement

      
      } // End of changeRelaton
      
      //Detect Deadlock
      public static void detectDeadlock(){
            for (int i = 0; i < process.length; i++){
                  for (int j = 0; j < process[0].getResourceLength(); j++){
                        if (process[i].getResource(j) == 2){
                           for (int k = 0; k < process.length; k++){
                                 if (k != i && process[k].getResource(j) == 1) {
                                       if (detectDeadlockRecursively(k,i)){
                                             System.out.println("Base:");
                                             System.out.println("Process " + k + " is requesting resource " + j + ".");
                                             System.out.println("Process " + i + " is allocated resource " + j + ".\n");
                                             return;
                                       
                                       } // end recursive call loop
                                       
                                 } // end if loop
                           } // end for loop
                        } // end if loop
                  } // end J loop
            } // End I loop
            System.out.println("The system is not deadlocked, and is currently safe.\n");
            
      
      } // End of detectDeadlock
      
      //Detect Deadlock Recursively
      public static boolean detectDeadlockRecursively(int currentIndex, int targetIndex) {
            for (int j = 0; j < process[0].getResourceLength(); j++) {
            // currentIndex is allocated resource j
                  if (process[currentIndex].getResource(j) == 2) {
                        for (int k = 0; k < process.length; k++) {
                        // k is requesting resource j
                              if (process[k].getResource(j) == 1) {
                                 // cycle closes
                                    if (k == targetIndex) {
                                       System.out.println("The system is deadlocked due to the following relations:");
                                       System.out.println("Process " + k + " is requesting resource " + j + ".");
                                       System.out.println("Process " + currentIndex + " is allocated resource " + j + ".");
                                       return true;
                                    }

                                    // keep searching
                                    if (detectDeadlockRecursively(k, targetIndex)) {
                                       System.out.println("Backtrack:");
                                       System.out.println("Process " + k + " is requesting resource " + j + ".");
                                       System.out.println("Process " + currentIndex + " is allocated resource " + j + ".");
                                       return true;
                                    }
                              } //  end if loop
                           } // end for loop
                        } // end if loop
                  } // end for loop

               return false;
        } // end recursive call     
     
      
      
} // End of class