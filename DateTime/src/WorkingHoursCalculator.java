import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;

public class WorkingHoursCalculator {
    public static long calculateWorkingHours(LocalDateTime start, LocalDateTime end) {

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Время начала должно быть раньше времени окончания");
        }

        long totalHours = 0;
        LocalDateTime current = start;
        LocalTime workStart = LocalTime.of(8, 0);
        LocalTime workEnd = LocalTime.of(21, 0);

        while (current.isBefore(end)) {
            if (current.getDayOfWeek() != DayOfWeek.SATURDAY && current.getDayOfWeek() != DayOfWeek.SUNDAY) {
                LocalDateTime startOfWorkDay = current.with(workStart);
                LocalDateTime endOfWorkDay = current.with(workEnd);

                if (current.isBefore(startOfWorkDay)) {
                    current = startOfWorkDay;
                }
                if (current.isBefore(endOfWorkDay) && end.isAfter(current)) {
                    LocalDateTime effectiveEnd = end.isBefore(endOfWorkDay) ? end : endOfWorkDay;
                    totalHours += Duration.between(current, effectiveEnd).toHours();
                }
            }
            current = current.plusDays(1).with(workStart);
        }

        return totalHours;
    }
}