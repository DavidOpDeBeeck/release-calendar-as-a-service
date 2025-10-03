import {HStack, Text} from "@chakra-ui/react";
import {useProject} from "../queries/project/useProject.ts";
import {Calendar} from "lucide-react";

export default function ProjectTitle() {
    const {data: project} = useProject();

    return (
        <HStack>
            <Calendar/>
            <Text fontSize="xl" fontWeight="bold">{project?.name}</Text>
        </HStack>
    )
}
