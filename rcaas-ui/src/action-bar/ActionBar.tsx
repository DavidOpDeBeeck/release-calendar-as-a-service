import TodayButton from "./TodayButton.tsx";
import SettingsButton from "../settings/SettingsButton";
import ShowVersionsForEachDayButton from "./ShowVersionsForEachDayButton.tsx";
import ColorModeButton from "./ColorModeButton.tsx";
import YearMonthSelector from "./YearMonthSelector.tsx";
import {HStack, Spacer} from "@chakra-ui/react";
import ProjectTitle from "./ProjectTitle.tsx";

export default function ActionBar() {
    return (
        <HStack width="full" p={2} pb={0}>
            <ProjectTitle/>
            <Spacer/>
            <HStack>
                <YearMonthSelector/>
                <TodayButton/>
                <ShowVersionsForEachDayButton/>
                <ColorModeButton/>
                <SettingsButton/>
            </HStack>
        </HStack>
    )
}
