import {VersionTO} from "../domain/VersionTO.ts";
import {Flex, Spacer, Text} from "@chakra-ui/react";

interface Props {
    version: VersionTO;
    visible: boolean
}

export default function Version({version, visible}: Props) {
    return (
        <Flex w="100%"
              px={2}
              shadow="md"
              bg={`${version.color}.400`}
              border="2px"
              borderColor={`${version.color}.500`}
              borderRadius={5}
              opacity="75%"
              visibility={visible ? "visible" : "hidden"}>
            <Text fontWeight="bold">{version.environment}</Text>
            <Spacer/>
            <Text fontWeight="bold">{version.value}</Text>
        </Flex>
    )
}
