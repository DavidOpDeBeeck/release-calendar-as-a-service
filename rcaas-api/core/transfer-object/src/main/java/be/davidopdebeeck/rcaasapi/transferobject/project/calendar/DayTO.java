package be.davidopdebeeck.rcaasapi.transferobject.project.calendar;

import be.davidopdebeeck.rcaasapi.transferobject.project.release.ReleaseTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.version.VersionTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.requireNonNull;

@JsonDeserialize(builder = DayTO.Builder.class)
public class DayTO {

    private final LocalDate date;
    private final boolean today;
    private final boolean weekend;
    private final boolean otherMonth;
    private final List<VersionTO> versions;
    private final List<ReleaseTO> releases;

    private DayTO(Builder builder) {
        date = requireNonNull(builder.date);
        today = requireNonNull(builder.today);
        weekend = requireNonNull(builder.weekend);
        otherMonth = requireNonNull(builder.otherMonth);
        versions = requireNonNull(builder.versions);
        releases = requireNonNull(builder.releases);
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isToday() {
        return today;
    }

    public boolean isWeekend() {
        return weekend;
    }

    public boolean isOtherMonth() {
        return otherMonth;
    }

    public List<VersionTO> getVersions() {
        return versions;
    }

    public List<ReleaseTO> getReleases() {
        return releases;
    }

    @JsonPOJOBuilder
    public static final class Builder {

        private LocalDate date;
        private Boolean today;
        private Boolean weekend;
        private Boolean otherMonth;
        private List<VersionTO> versions;
        private List<ReleaseTO> releases;

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withToday(boolean today) {
            this.today = today;
            return this;
        }

        public Builder withWeekend(boolean weekend) {
            this.weekend = weekend;
            return this;
        }

        public Builder withOtherMonth(boolean otherMonth) {
            this.otherMonth = otherMonth;
            return this;
        }

        public Builder withVersions(List<VersionTO> versions) {
            this.versions = versions;
            return this;
        }

        public Builder withReleases(List<ReleaseTO> releases) {
            this.releases = releases;
            return this;
        }

        public DayTO build() {
            return new DayTO(this);
        }
    }
}
