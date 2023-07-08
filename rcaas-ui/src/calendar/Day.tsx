import {DayTO} from "../domain/CalendarTO.ts";
import Version from "./Version.tsx";
import {useState} from "react";

function dayClass(day: DayTO): string {
    if (day.today) {
        return "bg-blue-300 dark:bg-blue-500 bg-stripes bg-stripes-blue-400 dark:bg-stripes-blue-600";
    }
    if (day.weekend) {
        return "bg-gray-300 dark:bg-slate-700 bg-stripes bg-stripes-white dark:bg-stripes-slate-800";
    }
    if (day.otherMonth) {
        return "bg-gray-200 dark:bg-slate-600 bg-stripes bg-stripes-white dark:bg-stripes-slate-700";
    }
    return "bg-white dark:bg-slate-700";
}

interface Props {
    day: DayTO;
    dayIndex: number;
    showDayName: boolean;
}

export default function Day({day, dayIndex, showDayName}: Props) {
    const [isHovering, setIsHovering] = useState(false);
    const daysOfTheWeek = ['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN'];

    const versions = day.versions.slice(0, 3);
    const releases = day.releases.slice(0, 3);

    return (
        <div className={`${dayClass(day)} grid grid-rows-4 gap-y-1 items-center p-2 duration-150 ease-in`}
             onMouseOver={() => setIsHovering(true)}
             onMouseOut={() => setIsHovering(false)}>
            <div className="flex justify-between">
                <span className="font-bold text-gray-800 dark:text-gray-200">{new Date(day.date).getDate()}</span>
                {showDayName && (<span className="font-bold text-gray-600 dark:text-gray-300">{daysOfTheWeek[dayIndex]}</span>)}
            </div>
            {isHovering
                ? versions.map((version, index) => (<Version key={index} version={version}/>))
                : releases.map((release, index) => (<Version key={index} version={release.version}/>))}
        </div>
    )
}
