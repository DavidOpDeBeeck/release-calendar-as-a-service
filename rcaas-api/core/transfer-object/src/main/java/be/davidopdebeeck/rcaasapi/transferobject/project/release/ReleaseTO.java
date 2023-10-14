package be.davidopdebeeck.rcaasapi.transferobject.project.release;

import be.davidopdebeeck.rcaasapi.transferobject.project.version.VersionTO;

import static java.util.Objects.requireNonNull;

public class ReleaseTO {

    private final VersionTO version;

    private ReleaseTO(Builder builder) {
        version = requireNonNull(builder.version);
    }

    public VersionTO getVersion() {
        return version;
    }

    public static final class Builder {

        private VersionTO version;

        public Builder withVersion(VersionTO version) {
            this.version = version;
            return this;
        }

        public ReleaseTO build() {
            return new ReleaseTO(this);
        }
    }
}
