import Week from "./Week.tsx";
import {useCalendar} from "../queries/calendar/useCalendar.ts";

export default function Calendar() {
    const {data: calendar} = useCalendar();

    return (
        <div
            className="grid flex-1 grid-rows-6 gap-1 rounded-lg border border-gray-200 bg-gray-300 p-1 shadow dark:border-slate-800 dark:bg-slate-600">
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
