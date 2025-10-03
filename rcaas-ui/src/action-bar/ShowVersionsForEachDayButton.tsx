import {showVersionsForEachDayAtom} from "../store.ts";
import {useAtom} from "jotai";
import {IconButton} from "@chakra-ui/react";
import {Eye, EyeOff} from "lucide-react";

export default function ShowVersionsForEachDayButton() {
    const [showVersionsForEachDay, setShowVersionsForEachDay] = useAtom(showVersionsForEachDayAtom);

    return (
        <IconButton aria-label="Show all versions for each day"
                    variant='subtle'
                    onClick={() => setShowVersionsForEachDay(prev => !prev)}>
            {showVersionsForEachDay ? <EyeOff/> : <Eye/>}
        </IconButton>
    );
}
