import {VersionTO} from "../domain/VersionTO.ts";
import {HStack, Spacer, Text} from "@chakra-ui/react";

interface Props {
    version: VersionTO;
}

export default function Release({version}: Props) {
    return (
        <HStack w="full"
                px={2}
                bg={`${version.color}.500`}
                borderLeftWidth="4px"
                borderColor={`${version.color}.600`}
                borderRadius={3}>
            <Text fontWeight="bold">{version.environment}</Text>
            <Spacer/>
            <Text fontWeight="bold">{version.value}</Text>
        </HStack>
    )
}
