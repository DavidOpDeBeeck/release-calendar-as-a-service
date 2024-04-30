import {VersionTO} from "../domain/VersionTO.ts";
import {Flex, Spacer, Text} from "@chakra-ui/react";

interface Props {
    version: VersionTO;
}

export default function Release({version}: Props) {
    return (
        <Flex w="100%"
              px={2}
              bg={`${version.color}.400`}
              borderLeft="4px"
              borderColor={`${version.color}.500`}
              borderRadius={3}>
            <Text fontWeight="bold">{version.environment}</Text>
            <Spacer/>
            <Text fontWeight="bold">{version.value}</Text>
        </Flex>
    )
}
