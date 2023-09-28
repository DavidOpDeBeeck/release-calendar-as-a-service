import {useAtom} from "jotai";
import {darkModeAtom} from "../store.ts";
import {useEffect} from "react";
import {MoonIcon, SunIcon} from "@heroicons/react/24/outline";

export default function DarkModeButton() {
    const [isDarkMode, setIsDarkMode] = useAtom(darkModeAtom);

    useEffect(() => {
        document.documentElement.classList.toggle('dark', isDarkMode);
    }, [isDarkMode]);

    return (
        <button onClick={() => setIsDarkMode((prev) => !prev)}
                type="button"
                className="rounded-lg border-2 border-gray-200 p-2 font-semibold text-gray-800 bg-white hover:bg-gray-50 focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-800 dark:text-gray-200 dark:bg-slate-700 dark:hover:bg-slate-600">
            {isDarkMode ? <SunIcon className="h-4 w-4"/> : <MoonIcon className="h-4 w-4"/>}
        </button>
    );
}