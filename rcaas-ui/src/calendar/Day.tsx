import {DayTO} from "../domain/CalendarTO.ts";
import Version from "./Version.tsx";
import {useMemo, useState} from "react";
import {showVersionsForEachDayAtom} from "../store.ts";
import {useAtomValue} from "jotai";
import Release from "./Release";
import {Flex, GridItem, Spacer, Text, VStack} from "@chakra-ui/react";

function dayBg(day: DayTO): string {
    if (day.today) {
        return singleColor("blue.500");
    }
    if (day.weekend) {
        if (day.otherMonth) {
            return striped("blackAlpha.400", "whiteAlpha.50");
        }
        return singleColor("blackAlpha.400");
    }
    if (day.otherMonth) {
        return striped("blackAlpha.200", "whiteAlpha.50");
    }
    return singleColor("blackAlpha.200");
}

function singleColor(color: string) {
    return `linear(to-r, ${color}, ${color})`;
}

function striped(color: string, stripes: string) {
    return `linear-gradient(45deg, ${stripes} 12.50%, ${color} 12.50%, ${color} 50%, ${stripes} 50%, ${stripes} 62.50%, ${color} 62.50%, ${color} 100%)`;
}

interface Props {
    day: DayTO;
    dayIndex: number;
    showDayName: boolean;
}

export default function Day({day, dayIndex, showDayName}: Props) {
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

    return (
        <GridItem w='100%'
                  h="100%"
                  bgGradient={dayBg(day)}
                  bgSize='5.66px 5.66px'
                  p={2}
                  borderRadius={10}
                  onMouseOver={() => setIsHovering(true)}
                  onMouseOut={() => setIsHovering(false)}>
            <VStack>
                <Flex w='100%'>
                    <Text fontWeight="extrabold">{new Date(day.date).getDate()}</Text>
                    <Spacer/>
                    {showDayName && (<Text fontWeight="bold">{daysOfTheWeek[dayIndex]}</Text>)}
                </Flex>
                <VStack w="100%" gap={1} fontSize="sm">
                    {versionsToShow.map((value, index) => (releases.indexOf(value.version) > -1
                        ? <Release key={index} version={value.version}/>
                        : <Version key={index} version={value.version} visible={value.visible}/>))}
                </VStack>
            </VStack>
        </GridItem>
    );
}
