import {WeekTO} from "../domain/CalendarTO.ts";
import Day from "./Day.tsx";

interface Props {
    week: WeekTO;
    weekIndex: number;
}

export default function Week({week, weekIndex}: Props) {
    return (
        <div className="grid grid-cols-7 divide-x divide-gray-300 dark:divide-slate-600">
            {
                week.days.map((day, dayIndex) => (
                    <Day key={weekIndex + dayIndex}
                         day={day}
                         dayIndex={dayIndex}
                         showDayName={weekIndex == 0}/>
                ))
            }
        </div>
    )
}
