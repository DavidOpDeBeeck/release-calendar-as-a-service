import {Cog6ToothIcon} from "@heroicons/react/24/outline";
import {useSetAtom} from "jotai";
import {showSettingsAtom} from "../store.ts";

export default function SettingsButton() {
    const setShowSettings = useSetAtom(showSettingsAtom);

    return (
        <>
            <button onClick={() => setShowSettings(prev => !prev)}
                    type="button"
                    className="rounded-md border border-gray-200 p-2 text-gray-800 shadow hover:bg-gray-50 focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-800 dark:text-gray-200 dark:hover:bg-slate-600">
                <Cog6ToothIcon className="h-5 w-5"/>
            </button>
        </>
    );
}
