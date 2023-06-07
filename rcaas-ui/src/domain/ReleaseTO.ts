import {VersionTO} from "./VersionTO.ts";

export interface ReleaseTO {
    version: VersionTO;
}

export interface ReleaseSpecificationTO {
    sprintBased: SprintBasedReleaseSpecificationTO;
}

export interface SprintBasedReleaseSpecificationTO {
    version: VersionTO;
    startDate: string;
    sprintLength: number;
}