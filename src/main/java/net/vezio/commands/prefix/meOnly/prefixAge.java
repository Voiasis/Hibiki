package net.vezio.commands.prefix.meOnly;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import net.dv8tion.jda.api.entities.Message;

public class prefixAge {
    public static void age(Message message) {
        LocalDate now = LocalDate.now();
        LocalDate birthday = LocalDate.of(2002, Month.JANUARY, 5);
        DateTimeFormatter dtfYear = DateTimeFormatter.ofPattern("yyyy");
        int year = Integer.parseInt(dtfYear.format(now));
        DateTimeFormatter dtfDay = DateTimeFormatter.ofPattern("dd");
        int day = Integer.parseInt(dtfDay.format(now));
        DateTimeFormatter dtfMonth = DateTimeFormatter.ofPattern("MM");
        String month = dtfMonth.format(now);
	    LocalDate dateBefore = LocalDate.of(year, month(month), day);
        LocalDate dateAfter = LocalDate.of(year + 1, Month.JANUARY, 5);
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        long thing1 = 365 - noOfDaysBetween;
        long thing2 = (thing1 * 100) / 365;
        double thingy3 = thing2 / 100.0;
        int age = calculateAge(birthday, now);
        double exactAge = age + thingy3;
        message.reply(String.valueOf(exactAge)).mentionRepliedUser(false).queue();
    }
    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
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
