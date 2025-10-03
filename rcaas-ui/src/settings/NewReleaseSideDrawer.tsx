import {useForm} from "react-hook-form";
import SideDrawer from "../common/SideDrawer.tsx";

import {useProject} from "../queries/project/useProject.ts";
import {useUpdateProjectMutation} from "../queries/project/useUpdateProjectMutation.ts";
import {ErrorMessages} from "../common/ErrorMessages.tsx";
import {createListCollection, Field, Input, NumberInput, Select, VStack} from "@chakra-ui/react";

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
    const colors = createListCollection({
        items: [
            {label: "Red", value: "red"},
            {label: "Orange", value: "orange"},
            {label: "Yellow", value: "yellow"},
            {label: "Green", value: "green"},
            {label: "Teal", value: "teal"},
            {label: "Blue", value: "blue"},
            {label: "Cyan", value: "cyan"},
            {label: "Purple", value: "purple"},
            {label: "Pink", value: "pink"}
        ],
    })

    return (
        <SideDrawer title={"New Release"}
                    submitLabel={"Create"}
                    closeLabel={"Cancel"}
                    open={showModal}
                    onSubmit={handleSubmit(onSubmit)}
                    onClose={() => closeModal()}>
            <VStack>
                <ErrorMessages messages={mutation.error}/>
                <Field.Root invalid={!!errors.version?.environment}>
                    <Field.Label>Environment</Field.Label>
                    <Input placeholder="Environment"
                           {...register("version.environment", {required: true})}
                    />
                    <Field.ErrorText>Environment is required.</Field.ErrorText>
                </Field.Root>
                <Field.Root invalid={!!errors.version?.value}>
                    <Field.Label>Version</Field.Label>
                    <NumberInput.Root min={0} width="full">
                        <NumberInput.Control/>
                        <NumberInput.Input placeholder="Version"
                                           {...register("version.value", {required: true})}/>
                    </NumberInput.Root>
                    <Field.ErrorText>Version is required.</Field.ErrorText>
                </Field.Root>
                <Field.Root invalid={!!errors.startDate}>
                    <Field.Label>Start Date</Field.Label>
                    <Input type="date"
                           placeholder="Start Date"
                           {...register("startDate", {required: true})}
                    />
                    <Field.ErrorText>Start date is required.</Field.ErrorText>
                </Field.Root>
                <Field.Root invalid={!!errors.sprintLength}>
                    <Field.Label>Sprint Length</Field.Label>
                    <Input placeholder="Sprint Length"
                           {...register("sprintLength", {required: true})}
                    />
                    <Field.ErrorText>Sprint length is required.</Field.ErrorText>
                </Field.Root>
                <Field.Root invalid={!!errors.version?.color}>
                    <Field.Label>Color</Field.Label>
                    <Select.Root collection={colors} size="sm"
                                 {...register("version.color", {required: true})}>
                        <Select.HiddenSelect/>
                        <Select.Control>
                            <Select.Trigger>
                                <Select.ValueText placeholder="Select color"/>
                            </Select.Trigger>
                            <Select.IndicatorGroup>
                                <Select.Indicator/>
                                <Select.ClearTrigger/>
                            </Select.IndicatorGroup>
                        </Select.Control>
                        <Select.Positioner>
                            <Select.Content>
                                {colors.items.map((color) => (
                                    <Select.Item item={color} key={color.value}>
                                        {color.label}
                                        <Select.ItemIndicator/>
                                    </Select.Item>
                                ))}
                            </Select.Content>
                        </Select.Positioner>
                    </Select.Root>
                    <Field.ErrorText>Color is required.</Field.ErrorText>
                </Field.Root>
            </VStack>
        </SideDrawer>
    )
}

