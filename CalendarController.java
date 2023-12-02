package fbg.fittrack;
import java.util.*;
import java.time.*;

public class CalendarController {
    //strings associated with each month
    static String[] months ={"January","February","March","April","May","June","July","August","September","October","November","December"};

    public static void generateCal(int year) {
        //setting up variables to highlight the current date
        //The goal in the future will be to assign workouts to specific dates, and have them display with the calendar
        LocalDate userDate = LocalDate.now();
        int userYear = userDate.getYear();
        int userMonth = userDate.getMonthValue();
        int userDay = userDate.getDayOfMonth();
        
        //initialize calendar instance
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);

        //rudimentary display showing that the code works
        //looping through the months and then the years
        for (int i = 0; i < 12; i++){
            System.out.println("\n" + months[i] + " " + year);
            System.out.println("Sun\tMon\tTue\tWed\tThu\tFri\tSat");
            
            calendar.set(Calendar.MONTH, i);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
      
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
      
            for (int j = Calendar.SUNDAY; j < dayOfWeek; j++) {
             System.out.print("\t");
            }

            int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            for (int j = 1; j <= lastDay; j++) {
                if( j == userDay && i == userMonth -1 && year == userYear){
                    System.err.print("\\u001B[34m" + j + "\\u001B[34m");
                }
                System.out.print(j +"\t");
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                    System.out.println();
                }
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
    }
    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        generateCal(year);
      }
}