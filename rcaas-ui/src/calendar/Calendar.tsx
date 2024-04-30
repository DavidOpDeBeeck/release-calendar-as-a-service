import Week from "./Week.tsx";
import {useCalendar} from "../queries/calendar/useCalendar.ts";
import {Grid} from "@chakra-ui/react";

export default function Calendar() {
    const {data: calendar} = useCalendar();

    return (
        <Grid width="100%" height="100%" templateRows='repeat(6, 1fr)' p={2} rowGap={1}>
            {
                (calendar?.weeks || []).map((week, weekIndex) => (
                    <Week key={weekIndex}
                          week={week}
                          weekIndex={weekIndex}/>
                ))
            }
        </Grid>
    )
}
