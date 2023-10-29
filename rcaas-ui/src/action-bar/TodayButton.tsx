import {useSetAtom} from "jotai";
import {YearMonth} from "../domain/YearMonth.ts";
import {yearMonthAtom} from "../store.ts";
import Button from "../components/Button.tsx";

export default function TodayButton() {
    const setYearMonth = useSetAtom(yearMonthAtom);

    return (
        <Button onClick={() => setYearMonth(YearMonth.parseDate(new Date()))}>Today</Button>
    )
}
