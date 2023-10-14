import {useProjectId} from "../calendar/useProjectId.ts";
import {useHttpQuery} from "../useHttpQuery.ts";
import {ProjectTO} from "../../domain/ProjectTO.ts";

export const useProject = () => {
    const projectId = useProjectId();

    return useHttpQuery<ProjectTO>({
        key: ['project', projectId],
        path: `/projects/${projectId}`
    });
};