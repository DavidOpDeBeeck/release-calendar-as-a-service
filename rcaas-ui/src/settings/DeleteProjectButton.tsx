import {TrashIcon} from "@heroicons/react/24/outline";
import {useUpdateProjectMutation} from "../queries/project/useUpdateProjectMutation.ts";
import {useProject} from "../queries/project/useProject.ts";
import Button from "../components/Button.tsx";

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
        <Button onClick={() => onDelete()}
                style="error">
            <TrashIcon className="h-4 w-4"/>
        </Button>
    )
}

