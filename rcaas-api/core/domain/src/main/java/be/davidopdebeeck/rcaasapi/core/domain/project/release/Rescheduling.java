package be.davidopdebeeck.rcaasapi.core.domain.project.release;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static be.davidopdebeeck.rcaasapi.core.domain.project.ProjectValidationMessages.FROM_DATE_SHOULD_BE_BEFORE_TO_DATE;
import static be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationCondition.validateTrue;
import static be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationConstraints.validateConstraints;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.ObjectUtils.max;
import static org.apache.commons.lang3.ObjectUtils.min;

public class Rescheduling {

    public static List<Rescheduling> merge(List<Rescheduling> reschedulings) {
        if (reschedulings.isEmpty()) {
            return reschedulings;
        }

        List<Rescheduling> reschedulingsToProcess = new ArrayList<>(reschedulings);
        List<Rescheduling> mergedReschedulings = new ArrayList<>();

        Rescheduling reschedulingToMerge = reschedulings.get(0);
        List<Rescheduling> reschedulingsToRemove;

        do {
            reschedulingsToRemove = new ArrayList<>();
            for (Rescheduling reschedulingToCheck : reschedulingsToProcess) {
                if (reschedulingToMerge.isAdjacentTo(reschedulingToCheck) || reschedulingToMerge.overlapsWith(reschedulingToCheck)) {
                    reschedulingToMerge = reschedulingToMerge.merge(reschedulingToCheck);
                    reschedulingsToRemove.add(reschedulingToCheck);
                }
            }
            reschedulingsToProcess.removeAll(reschedulingsToRemove);
        } while (!reschedulingsToRemove.isEmpty());

        mergedReschedulings.add(reschedulingToMerge);
        if (!reschedulingsToProcess.isEmpty()) {
            mergedReschedulings.addAll(merge(reschedulingsToProcess));
        }

        return mergedReschedulings;
    }

    private final LocalDate from;
    private final LocalDate to;

    private Rescheduling(Builder builder) {
        from = requireNonNull(builder.from);
        to = requireNonNull(builder.to);
        validateFromDateIsBeforeToDate();
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public long getDaysBetween() {
        return DAYS.between(from, to);
    }

    public Rescheduling merge(Rescheduling rescheduling) {
        if (overlapsWith(rescheduling) || isAdjacentTo(rescheduling)) {
            return new Rescheduling.Builder()
                .withFrom(min(from, rescheduling.from))
                .withTo(max(to, rescheduling.to))
                .build();
        }
        throw new IllegalStateException("Can only merge overlapping or adjacent periods");
    }

    private boolean overlapsWith(Rescheduling other) {
        return (from.equals(other.to) || from.isBefore(other.to))
            && (other.from.equals(to) || other.from.isBefore(to));
    }

    private boolean isAdjacentTo(Rescheduling other) {
        return to.plusDays(1).equals(other.from)
            || other.to.plusDays(1).equals(from);
    }

    private void validateFromDateIsBeforeToDate() {
        validateConstraints(
            validateTrue(from.isBefore(to)).message(FROM_DATE_SHOULD_BE_BEFORE_TO_DATE)
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Rescheduling that = (Rescheduling) object;

        if (!from.equals(that.from)) return false;
        return to.equals(that.to);
    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Rescheduling{from=" + from + ", to=" + to + '}';
    }

    public static final class Builder {

        private LocalDate from;
        private LocalDate to;

        public Builder withFrom(LocalDate from) {
            this.from = from;
            return this;
        }

        public Builder withTo(LocalDate to) {
            this.to = to;
            return this;
        }

        public Rescheduling build() {
            return new Rescheduling(this);
        }
    }
}
