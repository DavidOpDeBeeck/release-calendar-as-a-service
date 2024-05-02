import TodayButton from "./TodayButton.tsx";
import SettingsButton from "../settings/SettingsButton";
import ShowVersionsForEachDayButton from "./ShowVersionsForEachDayButton.tsx";
import ColorModeButton from "./ColorModeButton.tsx";
import YearMonthSelector from "./YearMonthSelector.tsx";
import {Flex, HStack, Spacer} from "@chakra-ui/react";
import ProjectTitle from "./ProjectTitle.tsx";

export default function ActionBar() {
    return (
        <Flex width="100%" p={2} shadow="md">
            <ProjectTitle/>
            <Spacer/>
            <HStack>
                <YearMonthSelector/>
                <TodayButton/>
                <ShowVersionsForEachDayButton/>
                <ColorModeButton/>
                <SettingsButton/>
            </HStack>
        </Flex>
    )
}
