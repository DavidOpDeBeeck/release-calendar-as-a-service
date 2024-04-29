import {showVersionsForEachDayAtom} from "../store.ts";
import {useAtom} from "jotai";
import {IconButton} from "@chakra-ui/react";
import {ViewIcon, ViewOffIcon} from "@chakra-ui/icons";

export default function ShowVersionsForEachDayButton() {
    const [showVersionsForEachDay, setShowVersionsForEachDay] = useAtom(showVersionsForEachDayAtom);

    return (
        <IconButton aria-label="Show all versions for each day"
                    onClick={() => setShowVersionsForEachDay(prev => !prev)}
                    icon={showVersionsForEachDay ? <ViewOffIcon/> : <ViewIcon/>}>
        </IconButton>
    );
}
