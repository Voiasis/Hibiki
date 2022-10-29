package net.vezio.commands.prefix.meOnly;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import net.dv8tion.jda.api.entities.Message;

public class prefixAge {
    public static void age(Message message) {
        LocalDate birthday = LocalDate.of(2002, Month.JANUARY, 5); //my birthday
        LocalTime birthtime = LocalTime.of(15, 43); //my birthtime
        LocalDateTime now = LocalDateTime.now(); //get current date and time
        DateTimeFormatter dtfYear = DateTimeFormatter.ofPattern("yyyy"); //year format
        int year = Integer.parseInt(dtfYear.format(now)); //current year integer
        DateTimeFormatter dtfDay = DateTimeFormatter.ofPattern("dd"); //day format
        int day = Integer.parseInt(dtfDay.format(now)); //current day integer
        DateTimeFormatter dtfMonth = DateTimeFormatter.ofPattern("MM"); //month format
        String month = dtfMonth.format(now); //current month
	    LocalDate dateBefore = LocalDate.of(year, month(month), day); //current date
        LocalDate dateAfter = LocalDate.of(year + 1, Month.JANUARY, 5); //my next birthday
        LocalDateTime after = LocalDateTime.of(dateAfter, birthtime);
        double between = ChronoUnit.MINUTES.between(now, after); //get minutes between now and my next birthday
        double thing1 = 525960 - between;
        double thing2 = (thing1 * 100) / 525960;
        double thing3 = thing2 / 100.0;
        double age = calculateAge(birthday, dateBefore);
        double exactAge = age + thing3;
        String reply = String.valueOf(exactAge);
        message.reply(reply).mentionRepliedUser(false).queue();
    }
    public static double calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
    private static Month month(String month) {
        if (month.equals("01")) {
            return Month.JANUARY;
        } else if (month.equals("02")) {
            return Month.FEBRUARY;
        } else if (month.equals("03")) {
            return Month.MARCH;
        } else if (month.equals("04")) {
            return Month.APRIL;
        } else if (month.equals("05")) {
            return Month.MAY;
        } else if (month.equals("06")) {
            return Month.JUNE;
        } else if (month.equals("07")) {
            return Month.JULY;
        } else if (month.equals("08")) {
            return Month.AUGUST;
        } else if (month.equals("09")) {
            return Month.SEPTEMBER;
        } else if (month.equals("10")) {
            return Month.OCTOBER;
        } else if (month.equals("11")) {
            return Month.NOVEMBER;
        } else {
            return Month.DECEMBER;
        }
    }
}
