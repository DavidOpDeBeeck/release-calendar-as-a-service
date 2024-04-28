import {useProjectId} from "../calendar/useProjectId.ts";
import {useHttpMutation} from "../useHttpMutation.ts";

import {ProjectIdTO, UpdateProjectTO} from "../../domain/ProjectTO.ts";

export const useUpdateProjectMutation = () => {
    const projectId = useProjectId();

    return useHttpMutation<UpdateProjectTO, ProjectIdTO>({
        key: ['updateProject', projectId],
        path: `/projects/${projectId}`,
        method: "PUT",
        onSuccess: (queryClient) => {
            void queryClient.invalidateQueries({
                queryKey: ['project']
            })
        }
    });
}