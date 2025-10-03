import {useSetAtom} from "jotai";
import {YearMonth} from "../domain/YearMonth.ts";
import {yearMonthAtom} from "../store.ts";
import {Button} from "@chakra-ui/react";

export default function TodayButton() {
    const setYearMonth = useSetAtom(yearMonthAtom);

    return (
        <Button variant='subtle'
                fontWeight="semibold"
                onClick={() => setYearMonth(YearMonth.parseDate(new Date()))}>
            Today
        </Button>
    )
}
