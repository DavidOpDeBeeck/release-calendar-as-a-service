import {TrashIcon} from "@heroicons/react/24/outline";
import {useUpdateProjectMutation} from "../queries/project/useUpdateProjectMutation.ts";
import {useProject} from "../queries/project/useProject.ts";

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
        <button type="button"
                onClick={() => onDelete()}
                className="rounded-lg border-2 border-red-700 bg-red-600 px-2 text-xs font-semibold text-gray-200 hover:bg-red-500 focus:outline focus:outline-2 focus:outline-blue-600">
            <TrashIcon className="h-4 w-4"/>
        </button>
    )
}

