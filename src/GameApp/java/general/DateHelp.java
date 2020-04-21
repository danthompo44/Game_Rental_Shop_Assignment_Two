package GameApp.java.general;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelp {
    public DateHelp(){
    }

    public static String getOneMonthLater(){
        return formatDate(LocalDate.now().plusMonths(1));
    }

    private static String formatDate(LocalDate d){
        final String pattern = "dd-MM-yyyy";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
        return dateFormat.format(d);
    }
}
