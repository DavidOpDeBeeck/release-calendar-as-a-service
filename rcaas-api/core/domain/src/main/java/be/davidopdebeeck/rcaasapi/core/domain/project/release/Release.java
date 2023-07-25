package be.davidopdebeeck.rcaasapi.core.domain.project.release;

import be.davidopdebeeck.rcaasapi.core.domain.project.version.Version;

import static java.util.Objects.requireNonNull;

public class Release {

    public static Release release(Version version) {
        return new Builder()
            .withVersion(version)
            .build();
    }

    private final Version version;

    private Release(Builder builder) {
        version = requireNonNull(builder.version);
    }

    public Version getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Release release = (Release) o;
        return version.equals(release.version);
    }

    @Override
    public int hashCode() {
        return version.hashCode();
    }

    public static final class Builder {

        private Version version;

        public Builder withVersion(Version version) {
            this.version = version;
            return this;
        }

        public Release build() {
            return new Release(this);
        }
    }
}
