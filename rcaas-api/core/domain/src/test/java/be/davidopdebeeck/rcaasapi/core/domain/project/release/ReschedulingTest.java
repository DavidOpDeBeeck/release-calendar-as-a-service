package be.davidopdebeeck.rcaasapi.core.domain.project.release;

import be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReschedulingTest {

    @Test
    void fromDateShouldBeBeforeToDate() {
        assertThatThrownBy(() ->
            new Rescheduling.Builder()
                .withFrom(LocalDate.of(2023, 1, 2))
                .withTo(LocalDate.of(2023, 1, 1))
                .build())
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("FROM_DATE_SHOULD_BE_BEFORE_TO_DATE");
    }

    @Test
    void merge_adjacent() {
        List<Rescheduling> actual = Rescheduling.merge(List.of(
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2)),
            rescheduling(LocalDate.of(2023, 1, 3), LocalDate.of(2023, 1, 4)),
            rescheduling(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 6)),
            rescheduling(LocalDate.of(2023, 1, 7), LocalDate.of(2023, 1, 8))
        ));

        assertThat(actual).containsExactly(
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 8))
        );
    }

    @Test
    void merge_adjacent_outOfOrder() {
        List<Rescheduling> actual = Rescheduling.merge(List.of(
            rescheduling(LocalDate.of(2023, 1, 3), LocalDate.of(2023, 1, 4)),
            rescheduling(LocalDate.of(2023, 1, 7), LocalDate.of(2023, 1, 8)),
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2)),
            rescheduling(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 6))
        ));

        assertThat(actual).containsExactly(
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 8))
        );
    }

    @Test
    void merge_adjacent_withGap() {
        List<Rescheduling> actual = Rescheduling.merge(List.of(
            rescheduling(LocalDate.of(2023, 1, 3), LocalDate.of(2023, 1, 4)),
            rescheduling(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 6)),
            rescheduling(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 2)),
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2))
        ));

        assertThat(actual).containsExactly(
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 6)),
            rescheduling(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 2))
        );
    }

    @Test
    void merge_overlapping() {
        List<Rescheduling> actual = Rescheduling.merge(List.of(
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 3)),
            rescheduling(LocalDate.of(2023, 1, 2), LocalDate.of(2023, 1, 4)),
            rescheduling(LocalDate.of(2023, 1, 3), LocalDate.of(2023, 1, 6))
        ));

        assertThat(actual).containsExactly(
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 6))
        );
    }

    @Test
    void merge_overlapping_outOfOrder() {
        List<Rescheduling> actual = Rescheduling.merge(List.of(
            rescheduling(LocalDate.of(2023, 1, 2), LocalDate.of(2023, 1, 4)),
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 3)),
            rescheduling(LocalDate.of(2023, 1, 3), LocalDate.of(2023, 1, 6))
        ));

        assertThat(actual).containsExactly(
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 6))
        );
    }

    @Test
    void merge_overlapping_withGap() {
        List<Rescheduling> actual = Rescheduling.merge(List.of(
            rescheduling(LocalDate.of(2023, 1, 2), LocalDate.of(2023, 1, 4)),
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 3)),
            rescheduling(LocalDate.of(2023, 1, 3), LocalDate.of(2023, 1, 6)),
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2))
        ));

        assertThat(actual).containsExactly(
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 6))
        );
    }

    @Test
    void merge_removesDuplicates() {
        List<Rescheduling> actual = Rescheduling.merge(List.of(
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 3)),
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 3)),
            rescheduling(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 6)),
            rescheduling(LocalDate.of(2023, 1, 6), LocalDate.of(2023, 1, 7)),
            rescheduling(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 7))
        ));

        assertThat(actual).containsExactly(
            rescheduling(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 3)),
            rescheduling(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 7))
        );
    }

    private Rescheduling rescheduling(LocalDate from, LocalDate to) {
        return new Rescheduling.Builder()
            .withFrom(from)
            .withTo(to)
            .build();
    }
}