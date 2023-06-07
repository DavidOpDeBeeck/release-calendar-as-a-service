import {useProjectId} from "./useProjectId.ts";
import {useAtomValue} from "jotai/index";
import {useHttpQuery} from "../useHttpQuery.ts";
import {CalendarTO} from "../../domain/CalendarTO.ts";
import {yearMonthAtom} from "../../store.ts";

export const useCalendar = () => {
    const projectId = useProjectId();
    const yearMonth = useAtomValue(yearMonthAtom);

    return useHttpQuery<CalendarTO>({
        key: ['project', 'calendar', projectId, yearMonth.toFormattedString()],
        path: `/projects/${projectId}/calendar?yearMonth=${yearMonth.toFormattedString()}`
    });
};