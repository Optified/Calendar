import java.util.Scanner;
import java.util.Calendar;

public class ATMyCalendar7{
     
     
public static void main(String[] args){

   Calendar c = Calendar.getInstance();
   getFirstDayOfMonth(c);
   int i = -1; //i starts at -1. If it continues to be -1, then N or P command can't run. Only first doing E or T will change i's initial value. 
   boolean calendarFlag = false; //Is false on default. If it turns true, then the code stops looping and quits out.
   
   do{
   menu();
   i = choice(c, i); //i records the current day that is returned from the input command of N, P, T, or E.
   if(i == -2){
      calendarFlag = true;
   }
   
   } while(calendarFlag == false);
   
   
}
   
   
public static void menu(){ //Tells the user what commands work with this program. 
      System.out.println("Please type a command");
      System.out.println("\t\"e\" to enter a date and display the corresponding calendar");
      System.out.println("\t\"t\" to get todays date and display the today's calendar");
      System.out.println("\t\"n\" to display the next month");
      System.out.println("\t\"p\" to display the previous month");
      System.out.println("\t\"q\" to quit the program");
} 
public static int choice(Calendar c, int i){ //Takes the user input and runs the corresponding method
   int currentDay = i;
   String test = "";
   Scanner input = new Scanner(System.in);
      String choice = input.next();
         switch(choice){
            case "E":
            case "e": currentDay = 0;
            currentDay = calendarAllInOne(c, currentDay); //If e, runs standard calendar that takes an input, and outputs the day and month.
            break;
         
            case "t":
            case "T": currentDay = 0;
            currentDay = dateStamp(c);//If t, takes this month and day.
            break;
         
            case "n": 
            case "N":   if(currentDay == -1){ 
                           System.out.println("Please enter a valid command."); //Will not run N until currentDay stops returning -1
                        }
                        else{
                        currentDay = nextMonth(c, currentDay); //Takes the month after the current one, and takes in the current day inputted/from today as well.
                        }//display next month
            break;
         
            case "p":
            case "P":   if(currentDay == -1){//Will not run P until currentDay stops returning -1
                           System.out.println("Please enter a valid command.");
                        }
                        else{
                        currentDay = prevMonth(c, currentDay);
                        } //display the previous month
            break;
         
            case "q":
            case "Q": currentDay = -2; //Turns currenDay to -2 means the code will stop running/looping.
            break;
         }
         if(!choice.equals("E") && !choice.equals("e") && !choice.equals("t") && !choice.equals("T") && !choice.equals("n") && !choice.equals("N") && !choice.equals("p") && !choice.equals("P") && !choice.equals("q") && !choice.equals("Q")){
            System.out.println("Please enter a valid command."); //If E/T/N/P/Q are not inputted, it's an invalid command. Reprompts the user.
         }
      return currentDay; //Returns the current that was displayed on either E or T. Return -2 = quit out of program, -1 = N or P cannot be run.
}
  
public static void line(){ //Adds an equal spacing in between each week
   for (int i = 1; i <= 57; i++){
      System.out.print("=");
   }
   System.out.println();
   }
 
public static void spacing(){ //Adds the vertical edges left and right of the calendar, prints the day on the top left.
   System.out.println("|"); //prints a blank space      
   for(int i = 0; i < 7; i++){
      System.out.print("|       ");
    }   
    System.out.println("|"); //prints a blank space
      
   for(int i = 0; i < 7; i++){
      System.out.print("|       ");
   }   
   System.out.println("|"); //prints a blank space  
}

public static void date(int begin, int end, int currentDay, Calendar c){ //Prints the month with the proper amount of days, and proper starting day of the week.
int gap = getFirstDayOfMonth(c); //Returns the day of week the first day of the month is on
int newGap = 1;
         for(int i = begin - gap; i <= end - gap; i++){
         newGap = i + 1; //Offsets the starting day by 1, so day 1 doesn't start on 0.
         if(i < 0){
            System.out.print("|       "); //If the values are negative, they will print a blank box instead of a number.
         }
         else if(i == currentDay && i < 10){ //If the increment matches the currentDay + is 1 digit, then it prints a box around the day.
            System.out.print("|[" + newGap +  "]    ");
         }
         else if(i == currentDay){
            System.out.print("|[" + i +  "]   "); //If increment matches the currentDay + is 2 digits, then it prints a box around it
         }
         else{
         if(i < 9){
         System.out.print("|" + newGap + "      "); //Since newGap offsets everything by 1, anything less than 9 for i will print an extra space.
         
         }   
         else if(newGap > c.getActualMaximum(Calendar.DATE)){ //Prints blank boxes if the increment exceeds the actual amount of days within the current month
            System.out.print("|       ");
         }
         else{
         System.out.print("|" + (newGap) + "     "); //
         }
         }
 }
 }
public static int getFirstDayOfMonth(Calendar c){
   //Calendar C is my main calendar that I'm not editing
   //Calendar d im setting the focus on the first day of the month, and then getting what day of the week the first day of week 1 is
   Calendar d = Calendar.getInstance();
   int i = c.get(Calendar.MONTH);
   //i is the month that we're currently on
   
   d.set(Calendar.MONTH, i);
   //setting the month of the dummy calendar to the month we're currently on.
   d.set(Calendar.DAY_OF_MONTH, 1);
   //setting the focus of the month that we're on to day 1
   return d.get(Calendar.DAY_OF_WEEK);
   //returns the first day of the week of the first week
   
}
 
 
 public static int calendarAllInOne(Calendar c, int currentDay){ //Runs the scanner method, assigns a begin and end day for a week, and runs a for loop 5 to represent 5 boxes/weeks of lines/spacings/days.
      Scanner input = new Scanner(System.in);
      
      System.out.println("What date would you like to look at? (mm/dd)");
      String ask = input.next();
      
      char m1 = ask.charAt(0); //takes the first character of the String and puts it into a char variable
      char m2 = ask.charAt(1);
      char d1 = ask.charAt(3);
      char d2 = ask.charAt(4);
      
         if(m1 == '0'){ //If the first char of m1 is equal to 0, then it'll mean that it is months 1-9. It'll only print that instead of print 01 or 09.
      System.out.println("                           " + m2);
   }
      else{
      System.out.println("                           " + m1 + m2); //If it passes the first if statement, then the month is above 09, so it'll print the first and second digit
      }
      int start = 0;
      int begin = 1;
      int end = 7;
      for(int j = 0; j < 5; j++){
      line();
      date(begin, end, currentDay, c);
      spacing();
      begin += 7;
      end += 7;
 
 }
     String convertToIntMonth = ""; //variables to convert the month chars into integers
     String convertToIntDay = ""; //variables to convert the day chars into integers
      line();
      if(m1 == '0'){
         System.out.println("Month: " + m2); //Prints out month if it's single digit
         convertToIntMonth = convertToIntMonth + m2; //Adds the char value to the string.
      }
      else{
         System.out.println("Month: " + m1 + m2); //Prints out month if it's 2 digits
         convertToIntMonth = convertToIntMonth + m1 + m2; //Adds both char values to the string
      }
      if(d1 == '0'){
         System.out.println("Day: " + d2); //Prints out day if it's 1
         convertToIntDay = convertToIntDay + d2; //Adds char
      }
      else{
         System.out.println("Day: " + d1 + d2); //Prints out both days 
         convertToIntDay = convertToIntDay + d1 + d2; //Adds both chars
  }
      int conversionMonth = Integer.parseInt(convertToIntMonth); //Converts char month values to int
      if(conversionMonth >= c.get(Calendar.MONTH)){ //Adjusts the Calendar class's calendar to the inputted month.
         conversionMonth = conversionMonth - c.get(Calendar.MONTH);
         c.add(Calendar.MONTH, conversionMonth);
      }
      else{
         conversionMonth = c.get(Calendar.MONTH) - conversionMonth; 
         c.add(Calendar.MONTH, -conversionMonth);
      }
      int conversionDay = Integer.parseInt(convertToIntDay); //Converts char day values to int
      
      return conversionDay; //Returns the day back to menu, and then back to main.
      
  
  
 } 
 
public static int dateStamp(Calendar c){ //Gets the current month and day (based on the computer).
   int day = c.get(Calendar.DATE); //Gets the current day of the month
   int month = c.get(Calendar.MONTH) + 1; //Month on calendar class starts at 0 for january, so everything is offset by 1 to show the actual month.

   System.out.println("This Month:");
   System.out.println("                           " + month); //prints value of the actual month (without changing the true month of the calendar object

      int begin = 1; //prints day 1 first
      int end = 7; //prints day 2 first
      for(int j = 0; j < 5; j++){
      line(); //prints a line for the upper border of calendar
      date(begin, end, day, c); //date that takes in the begin, end, the currentDay, and the Calendar object C from menu, which is taken from main.
      spacing();
      begin += 7;
      end += 7;
 
 }
      line();
      System.out.println("Month: " + month); //Prints current month
      System.out.println("Day: " + day); //prints current day
      return c.get(Calendar.DAY_OF_MONTH); //returns the current day of the month
}

public static int nextMonth(Calendar c, int currentDay){ //Prints the next month, but keeps the same day.
    c.add(Calendar.MONTH, 1); //Adds 1 to the calendar object, displaying the value for the next month
    int month = c.get(Calendar.MONTH) + 1; //Offsets the month by 1, since calendar object starts counting at 0
    System.out.println("                           " + month);

      int begin = 1;
      int end = 7;
      for(int j = 0; j < 5; j++){
      line();
      date(begin, end, currentDay, c);
      spacing();
      begin += 7;
      end += 7;
      }
      
      line();
      System.out.println("Month: " + month);
      System.out.println("Day: " + currentDay);
      return currentDay; //returns current day to be recorded in the main

}

public static int prevMonth(Calendar c, int currentDay){ //Prints previous month
    c.add(Calendar.MONTH, -1);
    int month = c.get(Calendar.MONTH) + 1;
    if(month == 0){
      System.out.println("                           12");
    }
    else if(month == -1){
      System.out.println("                           11");
    }
    else{
    System.out.println("                           " + month);
    }
      int begin = 1;
      int end = 7;
      for(int j = 0; j < 5; j++){
      line();
      date(begin, end, currentDay, c);
      spacing();
      begin += 7;
      end += 7;
      }
      
      line();
      if(month == 0){
      System.out.println("Month: 12");
      }
      else if(month == -1){
      System.out.println("Month: 11");
      }
      else{
      System.out.println("Month: " + month);
      }
      System.out.println("Day: " + currentDay);
      return currentDay;

}


 
 }
 
 
 
   
   