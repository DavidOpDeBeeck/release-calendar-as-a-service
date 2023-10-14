import {VersionTO} from "./VersionTO.ts";
import {ReleaseTO} from "./ReleaseTO.ts";

export interface CalendarTO {
    weeks: WeekTO[];
}

export interface WeekTO {
    days: DayTO[];
}

export interface DayTO {
    date: string;
    weekend: boolean;
    otherMonth: boolean;
    today: boolean;
    versions: VersionTO[];
    releases: ReleaseTO[];
}

