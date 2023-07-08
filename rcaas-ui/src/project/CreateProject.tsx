import {useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";

import {useCreateProjectMutation} from "../queries/project/useCreateProjectMutation.ts";
import {CreateProjectTO} from "../domain/ProjectTO.ts";

export default function CreateProject() {
    const navigate = useNavigate();
    const createProjectMutation = useCreateProjectMutation();
    const {register, handleSubmit} = useForm({values: {name: ""}});
    const onSubmit = (data: CreateProjectTO) => {
        createProjectMutation.mutate(data, {
            onSuccess: ({projectId}) => navigate("/" + projectId)
        });
    };

    return (
        <form onSubmit={handleSubmit(onSubmit)}
              className="flex rounded-lg border border-gray-200 bg-white p-3 text-xl shadow-md space-y-2 dark:border-slate-800 dark:bg-slate-700">
            <div className="flex flex-1 items-center space-x-3">
                <input id="name"
                       type="text"
                       placeholder="Project Name"
                       {...register("name", {required: true})}
                       autoComplete="off"
                       className="flex-1 rounded-lg p-3 text-gray-800 focus:outline focus:outline-2 focus:outline-blue-600 dark:bg-slate-700 dark:text-slate-200"/>
                <button type="submit"
                        className="rounded-lg border-2 border-gray-200 px-4 py-3 font-semibold text-gray-800 hover:bg-gray-50 focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-800 dark:text-gray-200 dark:hover:bg-slate-600">
                    Create
                </button>
            </div>
        </form>
    )
}
