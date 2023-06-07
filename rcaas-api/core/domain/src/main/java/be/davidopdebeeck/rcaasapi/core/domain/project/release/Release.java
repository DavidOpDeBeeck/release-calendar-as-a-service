package be.davidopdebeeck.rcaasapi.core.domain.project.release;

import be.davidopdebeeck.rcaasapi.core.domain.project.version.Version;

import static java.util.Objects.requireNonNull;

public class Release {

    private final Version version;

    private Release(Builder builder) {
        version = requireNonNull(builder.version);
    }

    public Version getVersion() {
        return version;
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
