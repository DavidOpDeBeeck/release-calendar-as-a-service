import {ErrorMessage} from "./ErrorMessage.ts";
import {Text, VStack} from "@chakra-ui/react";

type Props = {
    errorMessages: ErrorMessage[] | null
};

export function ErrorMessages({errorMessages}: Props) {
    if (errorMessages === null || errorMessages?.length === 0) {
        return <></>;
    }
    return <VStack w="100%">
        {errorMessages.map(value => (
            <Text key={value.message} color="red">
                {value.message}
            </Text>
        ))}
    </VStack>;
}