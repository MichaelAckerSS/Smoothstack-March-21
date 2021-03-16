package com.ss.weekone.fri;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateAndTime {

    public static void main(String[] args) {
        DateAndTime dateTime = new DateAndTime();
        //"Which class would you use to store your birthday in years, months, days, seconds, and nanoseconds?"
        //I'd use LocalDateTime
        LocalDateTime birthday = LocalDateTime.of(1998,8,1,14,35,12,127386);
        //I wonder what the most recent Thursday before my birthday was...
        LocalDate thursBeforeBirth = dateTime.lastThursday(birthday.toLocalDate());
        System.out.print("I was born: ");
        System.out.print(birthday.format(DateTimeFormatter.ISO_DATE));
        System.out.print(" at ");
        System.out.println(birthday.format(DateTimeFormatter.ISO_TIME));
        System.out.print("The Thursday before that was:");
        System.out.println(thursBeforeBirth.format(DateTimeFormatter.ISO_DATE));
        //Let's see how long each month was that year
        dateTime.listMonthLength(Year.from(birthday));
        //What about all of the mondays this August?
        dateTime.listMondays(8);
        //Is my birthday this year Friday the 13th??
        if(dateTime.isFTT(LocalDate.of(2021,8,1))){
            System.out.println("8-1-2021 is Friday the 13!!");
        } else {
            System.out.println("8-1-2021 is not Friday the 13th");
        }
    }

    //Given a random date, how would you find the date of the previous Thursday?
    //Like this:
    private LocalDate lastThursday(LocalDate date){
        DayOfWeek thursday = DayOfWeek.THURSDAY;
        for (int i = 1; i < 7; i++){
            LocalDate check = date.minusDays(i);
            DayOfWeek checkDate = check.getDayOfWeek();
            if (checkDate.equals(thursday)){
                return check;
            }
        }
        return null;
    }

    //What is the difference between a ZoneId and a ZoneOffset?
    //I'm not sure how to format this as a code example so I'll just type it.
    //A ZoneID describes a specific timezone, whereas a ZoneOffset simply describes an offset in hours from UTC/GMT

    //How would you convert an Instant to a ZonedDateTime? How would you convert a ZonedDateTime to an Instant?
    //Both Instant and ZonedDateTime are TemporalAccessors, so they can be converted between each other with .from()
    //These methods are unnecessary considering how quick the conversion is, but I'll write them anyway.
    //Here's one
    private ZonedDateTime instantToZonedDateTime(Instant instant){
        ZonedDateTime zdt = ZonedDateTime.from(instant);
        return zdt;
    }

    //And the other
    private Instant zonedDateTimeToInstant(ZonedDateTime zdt){
        Instant instant = Instant.from(zdt);
        return instant;
    }

    //Write an example that, for a given year, reports the length of each month within that year.
    //I'm assuming that means length in days.
    private void listMonthLength(Year year){
        System.out.printf("Months in %d:%n", year.getValue());
        for (int i = 1; i < 13; i++){
            System.out.print(year.atMonth(i).getMonth().name() + ": ");
            System.out.printf("%d days.%n", year.atMonth(i).getMonth().length(year.isLeap()));
        }
    }

    //Write an example that, for a given month of the current year, lists all of the Mondays in that month.
    //Similar to the above
    private void listMondays(int month){
        Year currentYear = Year.now();
        YearMonth yearMonth = currentYear.atMonth(month);
        System.out.println("Here are the Mondays in " + yearMonth.getMonth().name() + " of this year:");
        int days = yearMonth.lengthOfMonth();
        for (int i = 1; i <= days; i++){
            LocalDate day = yearMonth.atDay(i);
            if (day.getDayOfWeek().equals(DayOfWeek.MONDAY)){
                System.out.println(day.format(DateTimeFormatter.ISO_DATE));
            }
        }
    }

    //Write an example that tests whether a given date occurs on Friday the 13th.
    //Okay
    private Boolean isFTT(LocalDate date){
        return (date.getDayOfWeek().equals(DayOfWeek.FRIDAY) && date.getDayOfMonth() == 13);
    }
}
