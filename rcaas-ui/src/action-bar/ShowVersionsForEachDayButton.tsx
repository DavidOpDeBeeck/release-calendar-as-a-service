import {EyeIcon, EyeSlashIcon} from "@heroicons/react/24/outline";
import {showVersionsForEachDayAtom} from "../store.ts";
import {useAtom} from "jotai";
import Button from "../components/Button.tsx";

export default function ShowVersionsForEachDayButton() {
    const [showVersionsForEachDay, setShowVersionsForEachDay] = useAtom(showVersionsForEachDayAtom);

    return (
        <Button onClick={() => setShowVersionsForEachDay(prev => !prev)}>
            {showVersionsForEachDay
                ? <EyeSlashIcon className="h-4 w-4"/>
                : <EyeIcon className="h-4 w-4"/>}
        </Button>
    );
}
