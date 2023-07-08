import {useSetAtom} from "jotai";
import {YearMonth} from "../domain/YearMonth.ts";
import {yearMonthAtom} from "../store.ts";

export default function TodayButton() {
    const setYearMonth = useSetAtom(yearMonthAtom);

    return (
        <button onClick={() => setYearMonth(YearMonth.parseDate(new Date()))}
                type="button"
                className="rounded-lg border-2 border-gray-200 p-2 font-semibold text-gray-800 hover:bg-gray-50 focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-800 dark:text-gray-200 dark:hover:bg-slate-600">
            Today
        </button>
    )
}
