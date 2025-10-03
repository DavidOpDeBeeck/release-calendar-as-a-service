import {DayTO} from "../domain/CalendarTO.ts";
import Version from "./Version.tsx";
import {useMemo, useState} from "react";
import {showVersionsForEachDayAtom} from "../store.ts";
import {useAtomValue} from "jotai";
import Release from "./Release";
import {Flex, GridItem, Spacer, Text, VStack} from "@chakra-ui/react";
import {ColorMode, useColorMode, useColorModeValue} from "../components/ui/color-mode.tsx";

function dayBg(day: DayTO, colorMode: ColorMode): string {
    if (day.today) {
        return singleColor("colors.blue.500");
    }
    if (day.weekend && day.otherMonth) {
        return colorMode === "light"
            ? striped("colors.whiteAlpha.50", "colors.blackAlpha.300")
            : striped("colors.blackAlpha.400", "colors.whiteAlpha.50");
    }
    if (day.weekend) {
        return colorMode === "light"
            ? singleColor("colors.blackAlpha.300")
            : singleColor("colors.whiteAlpha.50");
    }
    if (day.otherMonth) {
        return colorMode === "light"
            ? striped("colors.whiteAlpha.50", "colors.blackAlpha.200")
            : striped("colors.blackAlpha.400", "colors.whiteAlpha.200");
    }
    return colorMode === "light"
        ? singleColor("colors.blackAlpha.200")
        : singleColor("colors.whiteAlpha.200");
}

function singleColor(color: string) {
    return `linear-gradient(to right, {${color}}, {${color}})`;
}

function striped(color: string, stripes: string) {
    return `linear-gradient(45deg, {${color}} 25%, {${stripes}} 25%, {${stripes}} 50%, {${color}} 50%, {${color}} 75%, {${stripes}} 75%, {${stripes}} 100%)`;
}

interface Props {
    day: DayTO;
    dayIndex: number;
    showDayName: boolean;
}

export default function Day({day, dayIndex, showDayName}: Props) {
    const {colorMode} = useColorMode()
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

    const borderColor = useColorModeValue("whiteAlpha.50", "blackAlpha.50")

    return (
        <GridItem w='full'
                  h="full"
                  p={2}
                  bgImage={dayBg(day, colorMode)}
                  bgSize='14.14px 14.14px'
                  borderWidth="1px"
                  borderColor={borderColor}
                  borderRadius={5}
                  onMouseOver={() => setIsHovering(true)}
                  onMouseOut={() => setIsHovering(false)}>
            <VStack gap={1}>
                <Flex w="100%" fontSize="sm" fontWeight="extrabold">
                    <Text>{new Date(day.date).getDate()}</Text>
                    <Spacer/>
                    {showDayName && (<Text>{daysOfTheWeek[dayIndex]}</Text>)}
                </Flex>
                <VStack w="100%" gap={1} fontSize={{base: "xs", md: "sm"}}>
                    {versionsToShow.map((value, index) => (releases.indexOf(value.version) > -1
                        ? <Release key={index} version={value.version}/>
                        : <Version key={index} version={value.version} visible={value.visible}/>))}
                </VStack>
            </VStack>
        </GridItem>
    );
}
