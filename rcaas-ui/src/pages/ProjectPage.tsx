import {useEffect} from "react";
import ActionBar from "../action-bar/ActionBar.tsx";
import {useProject} from "../queries/project/useProject.ts";
import {VStack} from "@chakra-ui/react";
import Calendar from "../calendar/Calendar.tsx";

export default function ProjectPage() {
    const {data: project} = useProject();

    useEffect(() => {
        document.title = project?.name || '';
    }, [project]);

    return (
        <VStack height="100vh" width="100vw">
            <ActionBar/>
            <Calendar/>
        </VStack>
    )
}
