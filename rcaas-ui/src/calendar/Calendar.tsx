import Week from "./Week.tsx";
import {useCalendar} from "../queries/calendar/useCalendar.ts";

export default function Calendar() {
    const {data: calendar} = useCalendar();

    return (
        <div
            className="grid flex-1 grid-rows-6 gap-1 bg-white p-1 shadow dark:bg-slate-600">
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
