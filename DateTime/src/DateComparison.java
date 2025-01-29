import java.time.LocalDate;

class DateComparison {
    public static String compareDates(LocalDate date1, LocalDate date2) {
        if (date1.isBefore(date2)) {
            return "Первая дата меньше второй.";
        } else if (date1.isAfter(date2)) {
            return "Первая дата больше второй.";
        } else {
            return "Даты равны.";
        }
    }
}