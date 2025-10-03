import {useEffect} from "react";
import {Heading, Text, VStack} from '@chakra-ui/react'
import CreateProject from "../project/CreateProject.tsx";

export default function HeroPage() {
    useEffect(() => {
        document.title = 'Release Calendar';
    }, []);

    return (
        <VStack height="100vh" justifyContent="center" gap={6}>
            <Heading fontSize={{base: "4xl", md: "6xl"}} fontWeight="extrabold">
                releasecalendar<Text as='span' fontSize={{base: "4xl", md: "6xl"}} color="blue.600">.app</Text>
            </Heading>
            <Text fontSize={{base: "md", md: "2xl"}} color="gray.600" maxW={{base: "md", md: "xl"}} textAlign="center">
                Create, manage and share your release calendar with your team and your clients.
            </Text>
            <CreateProject/>
        </VStack>
    )
}
