import {VersionTO} from "../domain/VersionTO.ts";
import {Flex, Spacer, Text} from "@chakra-ui/react";

interface Props {
    version: VersionTO;
}

export default function Release({version}: Props) {
    return (
        <Flex w="100%"
              px={2}
              shadow="md"
              bg={`${version.color}.400`}
              border="2px"
              borderColor={`${version.color}.500`}
              borderRadius={5}>
            <Text fontWeight="bold">{version.environment}</Text>
            <Spacer/>
            <Text fontWeight="bold">{version.value}</Text>
        </Flex>
    )
}
