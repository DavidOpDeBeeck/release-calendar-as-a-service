import {atom} from "jotai";
import {YearMonth} from "./domain/YearMonth.ts";

export const showSettingsAtom = atom(false);
export const yearMonthAtom = atom<YearMonth>(YearMonth.parseDate(new Date()));

