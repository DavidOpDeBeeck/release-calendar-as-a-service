import {VersionTO} from "../domain/VersionTO.ts";
import {HStack, Spacer, Text} from "@chakra-ui/react";

interface Props {
    version: VersionTO;
    visible: boolean
}

export default function Version({version, visible}: Props) {
    return (
        <HStack w="full"
                px={2}
                bg={`${version.color}.500`}
                borderRadius={3}
                opacity="75%"
                visibility={visible ? "visible" : "hidden"}>
            <Text fontWeight="bold">{version.environment}</Text>
            <Spacer/>
            <Text fontWeight="bold">{version.value}</Text>
        </HStack>
    )
}
