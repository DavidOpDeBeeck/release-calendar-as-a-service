import {useUpdateProjectMutation} from "../queries/project/useUpdateProjectMutation.ts";
import {useProject} from "../queries/project/useProject.ts";
import {IconButton} from "@chakra-ui/react";
import {Trash2} from "lucide-react";

type Props = {
    specificationIndex: number;
}

export default function DeleteReleaseButton({specificationIndex}: Props) {
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
                    colorPalette="red"
                    variant="subtle"
                    size="sm"
                    onClick={() => onDelete()}>
            <Trash2/>
        </IconButton>
    )
}

