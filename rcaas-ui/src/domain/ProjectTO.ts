import {ReleaseSpecificationTO} from "./ReleaseTO.ts";

export interface ProjectTO {
    projectId: string;
    name: string;
    specifications: ReleaseSpecificationTO[];
}

export interface ProjectIdTO {
    projectId: string;
}

export interface CreateProjectTO {
    name: string;
}

export interface UpdateProjectTO {
    name: string;
    specifications: ReleaseSpecificationTO[];
}