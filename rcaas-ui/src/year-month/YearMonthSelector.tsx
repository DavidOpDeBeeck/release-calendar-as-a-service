import {useSetAtom} from "jotai";
import {ChevronLeftIcon, ChevronRightIcon} from "@heroicons/react/24/outline";
import {yearMonthAtom} from "../store.ts";
import {useEffect} from "react";

export default function YearMonthSelector() {
    const setYearMonth = useSetAtom(yearMonthAtom);

    useEffect(() => {
        const handleKeyDown = (e: KeyboardEvent) => {
            if (e.code === "ArrowLeft") {
                setYearMonth(prev => prev.previous())
            } else if (e.code === "ArrowRight") {
                setYearMonth(prev => prev.next())
            }
        }

        document.addEventListener('keydown', handleKeyDown);
        return () => {
            document.removeEventListener('keydown', handleKeyDown);
        }
    }, [setYearMonth]);

    return (
        <div className="flex space-x-2">
            <button onClick={() => setYearMonth(prev => prev.previous())}
                    type="button"
                    className="rounded-lg border-2 border-gray-200 p-2 font-semibold text-gray-800 bg-white dark:bg-slate-700 hover:bg-gray-50 focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-800 dark:text-gray-200 dark:hover:bg-slate-600">
                <ChevronLeftIcon className="h-4 w-4"/>
            </button>
            <button onClick={() => setYearMonth(prev => prev.next())}
                    type="button"
                    className="rounded-lg border-2 border-gray-200 p-2 font-semibold text-gray-800 bg-white dark:bg-slate-700 hover:bg-gray-50 focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-800 dark:text-gray-200 dark:hover:bg-slate-600">
                <ChevronRightIcon className="h-4 w-4"/>
            </button>
        </div>
    )
}
