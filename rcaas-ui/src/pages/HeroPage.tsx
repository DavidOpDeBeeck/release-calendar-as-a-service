import CreateProject from "../project/CreateProject.tsx";
import {useEffect} from "react";
import {Heading, Text, VStack} from '@chakra-ui/react'

export default function HeroPage() {
    useEffect(() => {
        document.title = 'Release Calendar';
    }, []);

    return (
        <VStack height="100vh" justifyContent="center" spacing={4}>
            <Heading fontSize="6xl" fontWeight="extrabold">
                releasecalendar<Text as='span' fontSize="6xl" color="blue.600">.app</Text>
            </Heading>
            <Text fontSize="2xl" color="gray.600" maxW="xl" textAlign="center">
                Create, manage and share your release calendar with your team and your clients.
            </Text>
            <CreateProject/>
        </VStack>
    )
}
