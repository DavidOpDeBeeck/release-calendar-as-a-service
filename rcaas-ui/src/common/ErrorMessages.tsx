import {Text, VStack} from "@chakra-ui/react";

type Props = {
    messages: string[] | null
};

export function ErrorMessages({messages}: Props) {
    if (messages === null || messages?.length === 0) {
        return <></>;
    }
    return (
        <VStack w="100%">
            {messages.map(message => (
                <Text key={message} color="red">{message}</Text>
            ))}
        </VStack>
    );
}