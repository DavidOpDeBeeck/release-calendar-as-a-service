import {atom} from "jotai";
import {YearMonth} from "./domain/YearMonth.ts";
import {atomWithStorage} from 'jotai/utils'

export const showSettingsAtom = atom(false);
export const showVersionsForEachDayAtom = atomWithStorage("showVersionsForEachDay", false);
export const yearMonthAtom = atom<YearMonth>(YearMonth.parseDate(new Date()));