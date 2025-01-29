import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Текущая дата и время: " + currentDateTime.format(formatter));

        LocalDate date1 = LocalDate.of(2025, 1, 1);
        LocalDate date2 = LocalDate.of(2025, 1, 4);
        System.out.println("Сравнение:" + DateComparison.compareDates(date1, date2));

        System.out.println("Дней до Нового года: " + DaysUntilNewYear.daysToNewYear());

        int year = 2025;
        System.out.println("Год " + year + " високосный: " + LeapYearChecker.isLeapYear(year));

        int month = 1;
        System.out.println("Выходные за месяц: " + WeekendCounter.countWeekends(month, 2025));

        ExecutionTimeCalculator.measureExecutionTime(() -> {
            for (int i = 0; i < 1_000_000; i++) {
            }
        });

        String dateString = "29-01-2025";
        System.out.print("Новая дата после добавления 10 дней: ");
        DateParser.parseAndAddDays(dateString);

        ZonedDateTime utcTime = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime moscowTime = TimeZoneConverter.convertToZone(utcTime, "Europe/Moscow");
        System.out.println("Время в Москве: " + moscowTime);

        LocalDate birthDate = LocalDate.of(2006, 7, 13);
        System.out.println("Возраст: " + AgeCalculator.calculateAge(birthDate) + " лет");

        System.out.println("Календарь на январь 2025:");
        MonthlyCalendar.printMonthlyCalendar(1, 2025);

        LocalDate randomStart = LocalDate.of(2025, 1, 1);
        LocalDate randomEnd = LocalDate.of(2025, 12, 31);
        LocalDate randomDate = RandomDateGenerator.generateRandomDate(randomStart, randomEnd);
        System.out.println("Случайная дата в диапазоне: " + randomDate);

        LocalDateTime eventTime = LocalDateTime.of(2025, 7, 13, 0, 0);
        Duration durationUntilEvent = TimeUntilEventCalculator.timeUntilEvent(eventTime);
        System.out.println("Время до события: " + durationUntilEvent.toHours() + " часов, "
                + durationUntilEvent.toMinutesPart() + " минут и "
                + durationUntilEvent.toSecondsPart() + " секунд.");

        LocalDateTime workStart = LocalDateTime.of(2025, 1, 1, 8, 0);
        LocalDateTime workEnd = LocalDateTime.of(2025, 1, 9, 20, 0);

        long workingHours = WorkingHoursCalculator.calculateWorkingHours(workStart, workEnd);
        System.out.println("Рабочие часы: " + workingHours);

        Locale locale = new Locale("ru");
        System.out.println("Форматированная дата: " + LocaleAwareFormatter.formatWithLocale(currentDateTime.toLocalDate(), locale));

        System.out.println("День недели для текущей даты: "
                + WeekdayNameFinder.getWeekdayName(currentDateTime.toLocalDate()));
    }
}