import {useUpdateProjectMutation} from "../queries/project/useUpdateProjectMutation.ts";
import {useProject} from "../queries/project/useProject.ts";
import {IconButton} from "@chakra-ui/react";
import {DeleteIcon} from "@chakra-ui/icons";

type Props = {
    specificationIndex: number;
    reschedulingIndex: number;
}

export default function DeleteReschedulingButton({specificationIndex, reschedulingIndex}: Props) {
    const {data: project} = useProject();
    const mutation = useUpdateProjectMutation();
    const onDelete = () => {
        mutation.mutate({
            name: project!.name,
            specifications: [
                ...project!.specifications.slice(0, specificationIndex),
                {
                    sprintBased: {
                        ...project!.specifications[specificationIndex].sprintBased,
                        reschedulings: [
                            ...(project!.specifications[specificationIndex].sprintBased.reschedulings || []).slice(0, reschedulingIndex),
                            ...(project!.specifications[specificationIndex].sprintBased.reschedulings || []).slice(reschedulingIndex + 1)
                        ]
                    }
                },
                ...project!.specifications.slice(specificationIndex + 1)
            ]
        });
    };

    return (
        <IconButton aria-label="Delete specification"
                    icon={<DeleteIcon/>}
                    colorScheme="red"
                    size="xs"
                    onClick={() => onDelete()}>
        </IconButton>
    )
}

