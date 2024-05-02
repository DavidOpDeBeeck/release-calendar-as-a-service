import {useForm} from "react-hook-form";
import SideDrawer from "../common/SideDrawer.tsx";

import {useProject} from "../queries/project/useProject.ts";
import {useUpdateProjectMutation} from "../queries/project/useUpdateProjectMutation.ts";
import {ErrorMessages} from "../common/ErrorMessages.tsx";
import {FormControl, FormErrorMessage, FormLabel, Input, VStack} from "@chakra-ui/react";

type NewRescheduling = {
    from: string;
    to: string;
}

type Props = {
    showModal: boolean;
    closeModal: () => void;
    specificationIndex: number;
}

export default function NewReschedulingSideDrawer({showModal, closeModal, specificationIndex}: Props) {
    const {data: project} = useProject();
    const mutation = useUpdateProjectMutation();

    const {register, formState: {errors}, reset, handleSubmit} = useForm({
        defaultValues: {
            from: "",
            to: ""
        }
    });
    const onSubmit = (data: NewRescheduling) => {
        mutation.mutate({
            name: project!.name,
            specifications: [
                ...project!.specifications.slice(0, specificationIndex),
                {
                    sprintBased: {
                        ...project!.specifications[specificationIndex].sprintBased,
                        reschedulings: [
                            ...(project!.specifications[specificationIndex].sprintBased.reschedulings || []),
                            {
                                from: data.from,
                                to: data.to
                            }
                        ]
                    }
                },
                ...project!.specifications.slice(specificationIndex + 1)
            ]
        }, {
            onSuccess: () => {
                closeModal();
                reset();
            },
        });
    };

    return (
        <SideDrawer title={"New Rescheduling"}
                    submitLabel={"Create"}
                    closeLabel={"Cancel"}
                    open={showModal}
                    onSubmit={handleSubmit(onSubmit)}
                    onClose={() => closeModal()}>
            <VStack>
                <ErrorMessages messages={mutation.error}/>
                <FormControl isInvalid={!!errors.from}>
                    <FormLabel>From Date</FormLabel>
                    <Input type="date"
                           placeholder="From date"
                           {...register("from", {required: true})}
                    />
                    <FormErrorMessage>From date is required.</FormErrorMessage>
                </FormControl>
                <FormControl isInvalid={!!errors.to}>
                    <FormLabel>To Date</FormLabel>
                    <Input type="date"
                           placeholder="To date"
                           {...register("to", {required: true})}
                    />
                    <FormErrorMessage>To date is required.</FormErrorMessage>
                </FormControl>
            </VStack>
        </SideDrawer>
    )
}

