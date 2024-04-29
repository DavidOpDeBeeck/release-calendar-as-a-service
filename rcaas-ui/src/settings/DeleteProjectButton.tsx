import {useUpdateProjectMutation} from "../queries/project/useUpdateProjectMutation.ts";
import {useProject} from "../queries/project/useProject.ts";
import {IconButton} from "@chakra-ui/react";
import {DeleteIcon} from "@chakra-ui/icons";

type Props = {
    specificationIndex: number;
}

export default function DeleteProjectButton({specificationIndex}: Props) {
    const {data: project} = useProject();
    const mutation = useUpdateProjectMutation();
    const onDelete = () => {
        mutation.mutate({
            name: project!.name,
            specifications: [
                ...project!.specifications.slice(0, specificationIndex),
                ...project!.specifications.slice(specificationIndex + 1)
            ]
        });
    };

    return (
        <IconButton aria-label="Delete specification"
                    icon={<DeleteIcon/>}
                    colorScheme="red"
                    size="sm"
                    onClick={() => onDelete()}>
        </IconButton>
    )
}

