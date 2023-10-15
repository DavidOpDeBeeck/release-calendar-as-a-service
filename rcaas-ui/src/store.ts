import {atom} from "jotai";
import {YearMonth} from "./domain/YearMonth.ts";
import {atomWithStorage} from 'jotai/utils'

export const showVersionsForEachDayAtom = atomWithStorage("showVersionsForEachDay", false);
export const yearMonthAtom = atom<YearMonth>(YearMonth.parseDate(new Date()));
export const darkModeAtom = atomWithStorage("darkMode", true);