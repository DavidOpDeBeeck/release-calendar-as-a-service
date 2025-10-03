import {useEffect} from "react";
import {Field, HStack, IconButton, Input} from '@chakra-ui/react'
import {useNavigate} from "react-router-dom";
import {useCreateProjectMutation} from "../queries/project/useCreateProjectMutation.ts";
import {useForm} from "react-hook-form";
import {CreateProjectTO} from "../domain/ProjectTO.ts";
import {ArrowRight} from "lucide-react";

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
            <HStack alignItems="start">
                <Field.Root invalid={!!errors.name}>
                    <Input id="name"
                           type="text"
                           placeholder="Project Name"
                           variant='subtle'
                           size={{base: "md", md: "lg"}}
                           {...register("name", {
                               required: 'Please enter a project name',
                           })}
                           autoComplete="off"/>
                    <Field.ErrorText>
                        {errors.name && errors.name.message}
                    </Field.ErrorText>
                </Field.Root>
                <IconButton aria-label="Create Project"
                            type="submit"
                            size={{base: "md", md: "lg"}}
                            colorPalette='blue'
                            loading={isSubmitting}>
                    <ArrowRight/>
                </IconButton>
            </HStack>
        </form>
    )
}
