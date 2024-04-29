import NewReschedulingButton from "./NewReschedulingButton.tsx";
import DeleteProjectButton from "./DeleteProjectButton.tsx";
import {ReleaseSpecificationTO} from "../domain/ReleaseTO.ts";
import DeleteReschedulingButton from "./DeleteReschedulingButton.tsx";
import {
    Box,
    Card,
    CardBody,
    CardHeader,
    Flex,
    Heading,
    SimpleGrid,
    Spacer,
    Stack,
    StackDivider,
    Table,
    TableContainer,
    Tbody,
    Td,
    Text,
    Th,
    Thead,
    Tr
} from "@chakra-ui/react";

type Props = {
    specification: ReleaseSpecificationTO;
    specificationIndex: number;
}

export default function SpecificationDetail({specification, specificationIndex}: Props) {
    return <Card key={specificationIndex}
                 w="100%"
                 variant="outline">
        <CardHeader>
            <Flex>
                <Heading size='md'>{specification.sprintBased.version.environment}</Heading>
                <Spacer/>
                <Flex gap={2}>
                    <NewReschedulingButton specificationIndex={specificationIndex}/>
                    <DeleteProjectButton specificationIndex={specificationIndex}/>
                </Flex>
            </Flex>
        </CardHeader>
        <CardBody>
            <Stack divider={<StackDivider/>} spacing='4'>
                <Box>
                    <Heading size='xs' textTransform='uppercase'>
                        Configuration
                    </Heading>
                    <SimpleGrid fontSize='sm' pt={2} columns={2} columnGap={2} rowGap={2}>
                        <Flex>
                            <Text>Version</Text>
                            <Spacer/>
                            <Text>{specification.sprintBased.version.value}</Text>
                        </Flex>
                        <Flex>
                            <Text>Start Date</Text>
                            <Spacer/>
                            <Text>{specification.sprintBased.startDate}</Text>
                        </Flex>
                        <Flex>
                            <Text>Sprint Length</Text>
                            <Spacer/>
                            <Text>{specification.sprintBased.sprintLength}</Text>
                        </Flex>
                        <Flex>
                            <Text>Color</Text>
                            <Spacer/>
                            <Text>{specification.sprintBased.version.color}</Text>
                        </Flex>
                    </SimpleGrid>
                </Box>
                <Box>
                    <Heading size='xs' textTransform='uppercase'>
                        Reschedulings
                    </Heading>
                    {specification?.sprintBased.reschedulings.length === 0 && (
                        <Text pt='2' fontSize='sm'>No reschedulings available</Text>)}
                    {specification?.sprintBased.reschedulings.length > 0 && (
                        <TableContainer pt='2'>
                            <Table variant='simple' size='sm'>
                                <Thead>
                                    <Tr>
                                        <Th>From Date</Th>
                                        <Th>To Date</Th>
                                        <Th></Th>
                                    </Tr>
                                </Thead>
                                <Tbody>
                                    {specification?.sprintBased.reschedulings.map((rescheduling, reschedulingIndex) => (
                                        <Tr key={reschedulingIndex}>
                                            <Td><Text fontSize='sm'>{rescheduling.from}</Text></Td>
                                            <Td><Text fontSize='sm'>{rescheduling.to}</Text></Td>
                                            <Td>
                                                <DeleteReschedulingButton
                                                    specificationIndex={specificationIndex}
                                                    reschedulingIndex={reschedulingIndex}/>
                                            </Td>
                                        </Tr>
                                    ))}
                                </Tbody>
                            </Table>
                        </TableContainer>)}
                </Box>
            </Stack>
        </CardBody>
    </Card>;
}