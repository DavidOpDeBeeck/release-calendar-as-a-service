import {useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";

import {useCreateProjectMutation} from "../queries/project/useCreateProjectMutation.ts";
import {CreateProjectTO} from "../domain/ProjectTO.ts";
import {Button, FormControl, FormErrorMessage, HStack, Input} from '@chakra-ui/react'

export default function CreateProject() {
    const navigate = useNavigate();
    const createProjectMutation = useCreateProjectMutation();
    const {register, handleSubmit, formState: {errors, isSubmitting},} = useForm({values: {name: ""}});
    const onSubmit = (data: CreateProjectTO) => {
        createProjectMutation.mutate(data, {
            onSuccess: ({projectId}) => navigate("/" + projectId)
        });
    };

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <HStack spacing={2}
                    paddingLeft={5}
                    paddingRight={2}
                    paddingY={2}
                    shadow="md"
                    border="1px"
                    borderColor="blackAlpha.200"
                    borderRadius={10}
                    width="md">
                <FormControl isInvalid={!!errors.name}>
                    <Input id="name"
                           type="text"
                           placeholder="Project Name"
                           variant='unstyled'
                           size="lg"
                           {...register("name", {
                               required: 'Please enter a project name',
                           })}
                           autoComplete="off"/>
                    <FormErrorMessage>
                        {errors.name && errors.name.message}
                    </FormErrorMessage>
                </FormControl>
                <Button type="submit" size="lg" colorScheme='blue' isLoading={isSubmitting}>
                    Create
                </Button>
            </HStack>
        </form>
    )
}
