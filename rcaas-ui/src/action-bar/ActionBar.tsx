import TodayButton from "./TodayButton.tsx";
import SettingsButton from "../settings/SettingsButton";
import ShowVersionsForEachDayButton from "./ShowVersionsForEachDayButton.tsx";
import DarkModeButton from "./DarkModeButton.tsx";
import YearMonthTitle from "./YearMonthTitle.tsx";
import YearMonthSelector from "./YearMonthSelector.tsx";

export default function ActionBar() {
    return (
        <div
            className="flex flex-none justify-between border-b-2 border-gray-200 bg-white p-2 shadow dark:border-slate-800 dark:bg-slate-700">
            <YearMonthTitle/>
            <div className="flex space-x-2">
                <TodayButton/>
                <YearMonthSelector/>
                <ShowVersionsForEachDayButton/>
                <DarkModeButton/>
                <SettingsButton/>
            </div>
        </div>
    )
}
