package github.evertonbrunosds.looqbox.util;

import java.security.InvalidParameterException;
import java.time.Duration;
import java.time.LocalDateTime;

public class Cache<T> {

    private T data;

    private LocalDateTime invalidAt;

    private final long timeInterval;

    private final Process<T> onUpdate;

    private final TimeMeasure timeMeasure;

    public Cache(final long timeInterval, final TimeMeasure timeMeasure, final Process<T> onUpdate) {
        if (timeInterval <= 0) {
            throw new InvalidParameterException("the 'timeInterval' parameter must be greater than zero");
        }
        this.data = null;
        this.invalidAt = LocalDateTime.now();
        this.timeInterval = timeInterval;
        this.onUpdate = onUpdate;
        this.timeMeasure = timeMeasure;
    }

    public Alternator<T> getData() {
        final boolean updateData = Duration.between(invalidAt, LocalDateTime.now()).toNanos() >= 0;
        try {
            if (updateData) {
                data = onUpdate.get();
                switch (timeMeasure) {
                    case SECOND:
                        invalidAt = LocalDateTime.now().plusSeconds(timeInterval);
                        break;
                    case MINUTE:
                        invalidAt = LocalDateTime.now().plusMinutes(timeInterval);
                        break;
                    case HOUR:
                        invalidAt = LocalDateTime.now().plusHours(timeInterval);
                        break;
                }
            }
            return (alternativeData) -> data;
        } catch (final Throwable throwable) {
            return (alternativeData) -> alternativeData;
        }
    }

    @FunctionalInterface
    public interface Alternator<A> {

        A or(A alternative);

    }

    public enum TimeMeasure {
        SECOND, MINUTE, HOUR
    }

    @FunctionalInterface
    public interface Process<O> {

        public O get() throws Throwable;

    }

}
