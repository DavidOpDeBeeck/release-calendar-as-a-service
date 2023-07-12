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
        <div className="flex h-screen w-screen flex-col bg-gray-100 p-4 dark:bg-slate-800">
            <ActionBar/>
            <Calendar/>
        </div>
    )
}
