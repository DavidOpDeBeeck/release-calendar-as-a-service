import {useHttpMutation} from "../useHttpMutation.ts";

import {CreateProjectTO, ProjectIdTO} from "../../domain/ProjectTO.ts";

export const useCreateProjectMutation = () => {
    return useHttpMutation<CreateProjectTO, ProjectIdTO>({
        key: ['createProject'],
        path: `/projects`
    });
}