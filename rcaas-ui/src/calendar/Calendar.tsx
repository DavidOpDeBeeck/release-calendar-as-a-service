import Week from "./Week.tsx";
import {useCalendar} from "../queries/calendar/useCalendar.ts";

export default function Calendar() {
    const {data: calendar} = useCalendar();

    return (
        <div
            className="grid flex-1 grid-rows-6 rounded-b-md border border-gray-200 bg-white shadow divide-y divide-gray-300 dark:divide-slate-600 dark:border-slate-800">
            {
                (calendar?.weeks || []).map((week, weekIndex) => (
                    <Week key={weekIndex}
                          week={week}
                          weekIndex={weekIndex}/>
                ))
            }
        </div>
    )
}
