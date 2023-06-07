import {useProject} from "../queries/project/useProject.ts";

export default function ProjectName() {
    const {data: project} = useProject();

    return (
        <h1 className="text-2xl font-semibold text-gray-800 dark:text-gray-200">{project?.name}</h1>
    )
}
