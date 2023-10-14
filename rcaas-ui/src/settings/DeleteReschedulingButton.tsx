import {TrashIcon} from "@heroicons/react/24/outline";
import {useUpdateProjectMutation} from "../queries/project/useUpdateProjectMutation.ts";
import {useProject} from "../queries/project/useProject.ts";

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
        <button type="button"
                onClick={() => onDelete()}
                className="rounded-lg border-2 border-red-700 bg-red-600 p-1 text-xs font-semibold text-gray-200 hover:bg-red-500 focus:outline focus:outline-2 focus:outline-blue-600">
            <TrashIcon onClick={() => onDelete()} className="h-4 w-4"/>
        </button>
    )
}

