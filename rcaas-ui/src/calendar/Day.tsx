import {DayTO} from "../domain/CalendarTO.ts";
import Version from "./Version.tsx";
import {useMemo, useState} from "react";
import {showVersionsForEachDayAtom} from "../store.ts";
import {useAtomValue} from "jotai";
import Release from "./Release";

function dayClass(day: DayTO): string {
    if (day.today) {
        return "bg-blue-400 dark:bg-blue-600";
    }
    if (day.weekend) {
        return `bg-gray-400 dark:bg-slate-800 ${day.otherMonth ? 'bg-stripes bg-stripes-gray-500 dark:bg-stripes-slate-900' : ''}`;
    }
    if (day.otherMonth) {
        return `bg-gray-300 dark:bg-slate-600 bg-stripes bg-stripes-gray-400 dark:bg-stripes-slate-700`;
    }
    return "bg-gray-100 dark:bg-slate-700";
}

interface Props {
    day: DayTO;
    dayIndex: number;
    showDayName: boolean;
}

export default function Day({day, dayIndex, showDayName}: Props) {
    const showVersionsForEachDay = useAtomValue(showVersionsForEachDayAtom);
    const [isHovering, setIsHovering] = useState(false);
    const daysOfTheWeek = ['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN'];

    const versions = day.versions.slice(0, 4);
    const releases = day.releases.slice(0, 4).map(release => release.version);

    const versionsToShow = useMemo(() => (
            [...releases, ...versions].filter((value, index, array) =>
                (array.findIndex(value1 => JSON.stringify(value) === JSON.stringify(value1)) === index))
                .map(value => ({version: value, visible: showVersionsForEachDay || isHovering}))),
        [showVersionsForEachDay, isHovering, releases, versions]);

    return (
        <div className={`${dayClass(day)} flex flex-col space-y-1 rounded-lg p-2 duration-150 ease-in`}
             onMouseOver={() => setIsHovering(true)}
             onMouseOut={() => setIsHovering(false)}>
            <div className="flex justify-between">
                <span className="font-bold text-gray-800 dark:text-gray-200">{new Date(day.date).getDate()}</span>
                {showDayName && (<span
                    className="rounded-md border border-gray-400 bg-gray-300 px-2 font-bold text-gray-600 shadow dark:border-slate-700 dark:bg-slate-600 dark:text-gray-300">{daysOfTheWeek[dayIndex]}</span>)}
            </div>
            {versionsToShow.map((value, index) => (releases.indexOf(value.version) > -1
                ? <Release key={index} version={value.version}/>
                : <Version key={index} version={value.version} visible={value.visible}/>))}
        </div>
    );
}
