import {useEffect} from "react";
import ActionBar from "../action-bar/ActionBar.tsx";
import Calendar from "../calendar/Calendar.tsx";
import {useProject} from "../queries/project/useProject.ts";

export default function ProjectPage() {
    const {data: project} = useProject();

    useEffect(() => {
        document.title = project?.name || '';
    }, [project]);

    return (
        <div className="flex min-h-screen w-full flex-col">
            <ActionBar/>
            <Calendar/>
        </div>
    )
}
