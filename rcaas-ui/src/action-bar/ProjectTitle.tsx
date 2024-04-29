import {HStack, IconButton, Link, Text} from "@chakra-ui/react";
import {useProject} from "../queries/project/useProject.ts";
import {ChevronLeftIcon} from "@chakra-ui/icons";

export default function ProjectTitle() {
    const {data: project} = useProject();

    return (
        <HStack>
            <Link href='/'>
                <IconButton
                    aria-label="Go to home page"
                    icon={<ChevronLeftIcon/>}
                    variant="ghost">
                </IconButton>
            </Link>
            <Text fontSize="xl" fontWeight="extrabold">{project?.name}</Text>
        </HStack>
    )
}
