import YearMonthSelector from "../year-month/YearMonthSelector.tsx";
import YearMonthTitle from "../year-month/YearMonthTitle.tsx";
import TodayButton from "./TodayButton.tsx";
import SettingsButton from "../settings/SettingsButton";
import VisualisationButton from "../visualisation/VisualisationButton.tsx";
import DarkModeButton from "./DarkModeButton.tsx";

export default function ActionBar() {
    return (
        <div
            className="flex flex-none flex-col rounded-t-lg border border-gray-300 bg-gray-100 p-2 shadow dark:border-slate-800 dark:bg-slate-700">
            <div className="flex justify-between">
                <YearMonthTitle/>
                <div className="flex space-x-2">
                    <TodayButton/>
                    <YearMonthSelector/>
                    <SettingsButton/>
                    <VisualisationButton/>
                    <DarkModeButton/>
                </div>
            </div>
        </div>
    )
}
