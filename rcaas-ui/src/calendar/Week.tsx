import {WeekTO} from "../domain/CalendarTO.ts";
import Day from "./Day.tsx";
import {Grid, GridItem} from "@chakra-ui/react";

interface Props {
    week: WeekTO;
    weekIndex: number;
}

export default function Week({week, weekIndex}: Props) {
    return (
        <GridItem w='100%'>
            <Grid h="100%" templateColumns='repeat(7, 1fr)' columnGap={2}>
                {
                    week.days.map((day, dayIndex) => (
                        <Day key={weekIndex + dayIndex}
                             day={day}
                             dayIndex={dayIndex}
                             showDayName={weekIndex == 0}/>
                    ))
                }
            </Grid>
        </GridItem>
    )
}
