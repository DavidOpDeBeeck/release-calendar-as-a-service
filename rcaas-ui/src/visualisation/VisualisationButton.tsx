import {EyeIcon, EyeSlashIcon} from "@heroicons/react/24/outline";
import {showVersionsForEachDayAtom} from "../store.ts";
import {useAtom} from "jotai";

export default function VisualisationButton() {
    const [showVersionsForEachDay, setShowVersionsForEachDay] = useAtom(showVersionsForEachDayAtom);

    return (
        <button onClick={() => setShowVersionsForEachDay(prev => !prev)}
                type="button"
                className="rounded-lg border-2 border-gray-200 p-2 font-semibold text-gray-800 hover:bg-gray-50 focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-800 dark:text-gray-200 dark:hover:bg-slate-600">
            {showVersionsForEachDay
                ? <EyeSlashIcon className="h-4 w-4"/>
                : <EyeIcon className="h-4 w-4"/>}
        </button>
    );
}
