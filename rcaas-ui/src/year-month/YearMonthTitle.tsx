import {useAtomValue} from "jotai";
import {yearMonthAtom} from "../store.ts";

export default function YearMonthTitle() {
    const yearMonth = useAtomValue(yearMonthAtom);

    return (
        <div className="flex items-center px-1 text-2xl font-semibold space-x-2">
            <span className="text-gray-800 dark:text-gray-200">{yearMonth.getMonthName()}</span>
            <span className="text-gray-500 dark:text-gray-300">{yearMonth.year}</span>
        </div>
    )
}
