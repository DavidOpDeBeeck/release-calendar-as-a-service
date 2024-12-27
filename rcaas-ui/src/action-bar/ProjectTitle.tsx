import {HStack, Text} from "@chakra-ui/react";
import {useProject} from "../queries/project/useProject.ts";

export default function ProjectTitle() {
    const {data: project} = useProject();

    return (
        <HStack>
            <Text fontSize="2xl" fontWeight="bold">{project?.name}</Text>
        </HStack>
    )
}
