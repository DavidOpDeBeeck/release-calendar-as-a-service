import YearMonthSelector from "../year-month/YearMonthSelector.tsx";
import YearMonthTitle from "../year-month/YearMonthTitle.tsx";
import TodayButton from "./TodayButton.tsx";

export default function ActionBar() {
    return (
        <div
            className="flex flex-none flex-col rounded-t-md border-t border-r border-l border-gray-200 bg-white px-3 py-2 shadow dark:border-slate-800 dark:bg-slate-700">
            <div className="flex justify-between">
                <YearMonthTitle/>
                <div className="flex space-x-2">
                    <TodayButton/>
                    <YearMonthSelector/>
                </div>
            </div>
        </div>
    )
}
