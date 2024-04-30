import {useEffect} from "react";
import {FormControl, FormErrorMessage, HStack, IconButton, Input} from '@chakra-ui/react'
import {useNavigate} from "react-router-dom";
import {useCreateProjectMutation} from "../queries/project/useCreateProjectMutation.ts";
import {useForm} from "react-hook-form";
import {CreateProjectTO} from "../domain/ProjectTO.ts";
import {ArrowForwardIcon} from "@chakra-ui/icons";

export default function CreateProject() {
    const navigate = useNavigate();
    const createProjectMutation = useCreateProjectMutation();
    const {register, handleSubmit, formState: {errors, isSubmitting},} = useForm({values: {name: ""}});
    const onSubmit = (data: CreateProjectTO) => {
        createProjectMutation.mutate(data, {
            onSuccess: ({projectId}) => navigate("/" + projectId)
        });
    };

    useEffect(() => {
        document.title = 'New Project';
    }, []);

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <HStack>
                <FormControl isInvalid={!!errors.name}>
                    <Input id="name"
                           type="text"
                           placeholder="Project Name"
                           variant='filled'
                           size={{base: "md", md: "lg"}}
                           {...register("name", {
                               required: 'Please enter a project name',
                           })}
                           autoComplete="off"/>
                    <FormErrorMessage pos="absolute">
                        {errors.name && errors.name.message}
                    </FormErrorMessage>
                </FormControl>
                <IconButton aria-label="Create Project"
                            icon={<ArrowForwardIcon/>}
                            type="submit"
                            size={{base: "md", md: "lg"}}
                            colorScheme='blue'
                            isLoading={isSubmitting}>
                </IconButton>
            </HStack>
        </form>
    )
}
