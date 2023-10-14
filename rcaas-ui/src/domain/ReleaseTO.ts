import {VersionTO} from "./VersionTO.ts";

export type Rescheduling = {
    from: string;
    to: string;
}

export type ReleaseTO = {
    version: VersionTO;
}

export type ReleaseSpecificationTO = {
    sprintBased: SprintBasedReleaseSpecificationTO;
}

export type SprintBasedReleaseSpecificationTO = {
    version: VersionTO;
    startDate: string;
    sprintLength: number;
    reschedulings: Rescheduling[];
}