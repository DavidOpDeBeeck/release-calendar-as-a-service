import {useAtom} from "jotai";
import {darkModeAtom} from "../store.ts";
import {useEffect} from "react";
import {MoonIcon, SunIcon} from "@heroicons/react/24/outline";
import Button from "../components/Button.tsx";

export default function DarkModeButton() {
    const [isDarkMode, setIsDarkMode] = useAtom(darkModeAtom);

    useEffect(() => {
        document.documentElement.classList.toggle('dark', isDarkMode);
    }, [isDarkMode]);

    return (
        <Button onClick={() => setIsDarkMode((prev) => !prev)}>
            {isDarkMode ? <SunIcon className="h-4 w-4"/> : <MoonIcon className="h-4 w-4"/>}
        </Button>
    );
}