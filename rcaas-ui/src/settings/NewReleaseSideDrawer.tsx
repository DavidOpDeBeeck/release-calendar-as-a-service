import {useForm} from "react-hook-form";
import SideDrawer from "../common/SideDrawer.tsx";

import {useProject} from "../queries/project/useProject.ts";
import {useUpdateProjectMutation} from "../queries/project/useUpdateProjectMutation.ts";
import {ErrorMessages} from "../common/ErrorMessages.tsx";
import {FormControl, FormErrorMessage, FormLabel, Input, NumberInput, NumberInputField, Select, VStack} from "@chakra-ui/react";

interface NewRelease {
    version?: {
        value: string,
        environment?: string,
        color?: string
    },
    startDate?: string,
    sprintLength?: number
}

type Props = {
    showModal: boolean;
    closeModal: () => void;
}

export default function NewReleaseSideDrawer({showModal, closeModal}: Props) {
    const {data: project} = useProject();
    const mutation = useUpdateProjectMutation();

    const {register, formState: {errors}, reset, handleSubmit} = useForm({
        defaultValues: {
            version: {
                value: "",
                environment: "",
                color: "red"
            },
            startDate: undefined,
            sprintLength: undefined
        }
    });
    const onSubmit = (data: NewRelease) => {
        mutation.mutate({
            name: project!.name,
            specifications: [...(project!.specifications || []), {
                sprintBased: {
                    version: {
                        environment: data.version!.environment!,
                        value: data.version!.value,
                        color: data.version!.color!
                    },
                    startDate: data.startDate!,
                    sprintLength: data.sprintLength!,
                    reschedulings: [],
                }
            }]
        }, {
            onSuccess: () => {
                closeModal();
                reset();
            }
        });
    };

    return (
        <SideDrawer title={"New Release"}
                    submitLabel={"Create"}
                    closeLabel={"Cancel"}
                    open={showModal}
                    onSubmit={handleSubmit(onSubmit)}
                    onClose={() => closeModal()}>
            <VStack>
                <ErrorMessages messages={mutation.error}/>
                <FormControl isInvalid={!!errors.version?.environment}>
                    <FormLabel>Environment</FormLabel>
                    <Input placeholder="Environment"
                           {...register("version.environment", {required: true})}
                    />
                    <FormErrorMessage>Environment is required.</FormErrorMessage>
                </FormControl>
                <FormControl isInvalid={!!errors.version?.value}>
                    <FormLabel>Version</FormLabel>
                    <NumberInput min={0}>
                        <NumberInputField placeholder="Version"
                                          {...register("version.value", {required: true})}/>
                    </NumberInput>
                    <FormErrorMessage>Version is required.</FormErrorMessage>
                </FormControl>
                <FormControl isInvalid={!!errors.startDate}>
                    <FormLabel>Start Date</FormLabel>
                    <Input type="date"
                           placeholder="Start Date"
                           {...register("startDate", {required: true})}
                    />
                    <FormErrorMessage>Start date is required.</FormErrorMessage>
                </FormControl>
                <FormControl isInvalid={!!errors.sprintLength}>
                    <FormLabel>Sprint Length</FormLabel>
                    <Input placeholder="Sprint Length"
                           {...register("sprintLength", {required: true})}
                    />
                    <FormErrorMessage>Sprint length is required.</FormErrorMessage>
                </FormControl>
                <FormControl isInvalid={!!errors.version?.color}>
                    <FormLabel>Color</FormLabel>
                    <Select placeholder='Select color'
                            {...register("version.color", {required: true})}>
                        <option value="red">Red</option>
                        <option value="orange">Orange</option>
                        <option value="yellow">Yellow</option>
                        <option value="green">Green</option>
                        <option value="teal">Teal</option>
                        <option value="blue">Blue</option>
                        <option value="cyan">Cyan</option>
                        <option value="purple">Purple</option>
                        <option value="pink">Pink</option>
                    </Select>
                    <FormErrorMessage>Color is required.</FormErrorMessage>
                </FormControl>
            </VStack>
        </SideDrawer>
    )
}

