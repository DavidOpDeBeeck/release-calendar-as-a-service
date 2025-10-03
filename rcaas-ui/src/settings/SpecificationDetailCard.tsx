import NewReschedulingButton from "./NewReschedulingButton.tsx";
import DeleteReleaseButton from "./DeleteReleaseButton.tsx";
import {ReleaseSpecificationTO} from "../domain/ReleaseTO.ts";
import DeleteReschedulingButton from "./DeleteReschedulingButton.tsx";
import {Box, Card, Flex, Heading, HStack, SimpleGrid, Spacer, Stack, StackSeparator, Table, Text} from "@chakra-ui/react";

type Props = {
    specification: ReleaseSpecificationTO;
    specificationIndex: number;
}

export default function SpecificationDetailCard({specification, specificationIndex}: Props) {
    return (
        <Card.Root key={specificationIndex}
                   w="full"
                   size="sm"
                   variant="outline">
            <Card.Header>
                <HStack>
                    <Heading>{specification.sprintBased.version.environment}</Heading>
                    <Spacer/>
                    <Flex gap={1}>
                        <NewReschedulingButton specificationIndex={specificationIndex}/>
                        <DeleteReleaseButton specificationIndex={specificationIndex}/>
                    </Flex>
                </HStack>
            </Card.Header>
            <Card.Body>
                <Stack separator={<StackSeparator/>} gap='4'>
                    <Box>
                        <Heading size='sm' textTransform='uppercase'>Configuration</Heading>
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
                        <Heading size='sm' textTransform='uppercase'>Reschedulings</Heading>
                        {specification?.sprintBased.reschedulings.length === 0 && (
                            <Text pt='2' fontSize='sm'>No reschedulings available</Text>)}
                        {specification?.sprintBased.reschedulings.length > 0 && (
                            <Table.Root variant='line' size='sm'>
                                <Table.Header>
                                    <Table.Row>
                                        <Table.ColumnHeader>From Date</Table.ColumnHeader>
                                        <Table.ColumnHeader>To Date</Table.ColumnHeader>
                                        <Table.ColumnHeader></Table.ColumnHeader>
                                    </Table.Row>
                                </Table.Header>
                                <Table.Body>
                                    {specification?.sprintBased.reschedulings.map((rescheduling, reschedulingIndex) => (
                                        <Table.Row key={reschedulingIndex}>
                                            <Table.Cell><Text fontSize='sm'>{rescheduling.from}</Text></Table.Cell>
                                            <Table.Cell><Text fontSize='sm'>{rescheduling.to}</Text></Table.Cell>
                                            <Table.Cell>
                                                <DeleteReschedulingButton
                                                    specificationIndex={specificationIndex}
                                                    reschedulingIndex={reschedulingIndex}/>
                                            </Table.Cell>
                                        </Table.Row>
                                    ))}
                                </Table.Body>
                            </Table.Root>)}
                    </Box>
                </Stack>
            </Card.Body>
        </Card.Root>
    );
}