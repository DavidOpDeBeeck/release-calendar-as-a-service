import {useForm} from "react-hook-form";
import Modal from "../modal/Modal.tsx";
import {useState} from "react";

import {useProject} from "../queries/project/useProject.ts";
import {useUpdateProjectMutation} from "../queries/project/useUpdateProjectMutation.ts";
import {ChevronDownIcon, PlusIcon} from "@heroicons/react/24/outline";
import Version from "../calendar/Version.tsx";

interface NewRelease {
    version?: {
        value: string,
        environment?: string,
        color?: string
    },
    startDate?: string,
    sprintLength?: number
}

export default function NewReleaseButton() {
    const {data: project} = useProject();
    const mutation = useUpdateProjectMutation();
    const [open, setOpen] = useState(false)

    const {register, watch, formState: {errors}, reset, handleSubmit} = useForm({
        defaultValues: {
            version: {
                value: "",
                environment: "",
                color: "red"
            },
            startDate: undefined,
            sprintLength: undefined
        }
    });
    const version = watch("version");
    const onSubmit = (data: NewRelease) => {
        mutation.mutate({
            name: project!.name,
            specifications: [...(project!.specifications || []), {
                sprintBased: {
                    version: {
                        environment: data.version!.environment!,
                        value: data.version!.value,
                        color: data.version!.color!
                    },
                    startDate: data.startDate!,
                    sprintLength: data.sprintLength!
                }
            }]
        }, {
            onSettled: () => {
                setOpen(false);
                reset();
            }
        });
    };

    return (
        <>
            <button onClick={() => setOpen(true)}
                    type="button"
                    className="flex items-center rounded-md border border-gray-200 px-3 py-2 text-sm font-bold text-gray-800 shadow space-x-1 hover:bg-gray-50 focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-800 dark:bg-slate-700 dark:text-gray-200 dark:hover:bg-slate-600">
                <PlusIcon className="h-4 w-4"/>
                <span>Release</span>
            </button>
            <Modal title={"New Release"}
                   submitLabel={"Create"}
                   open={open}
                   onSubmit={handleSubmit(onSubmit)}
                   onClose={() => setOpen(false)}>
                <div className="grid grid-cols-2 gap-2">
                    <div className="flex flex-col space-y-2">
                        <label htmlFor="environment" className="text-sm font-bold text-gray-800 dark:text-gray-200">Environment</label>
                        <input id="environment"
                               type="text"
                               placeholder="Environment"
                               {...register("version.environment", {required: true})}
                               className="rounded-lg border border-gray-200 p-2 text-sm text-gray-800 shadow focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-600 dark:bg-slate-700 dark:text-gray-200"/>
                        <span className="text-xs font-semibold text-red-700 dark:text-red-400">
                        {errors.version?.environment?.type === 'required' && "Environment is required"}
                        </span>
                    </div>
                    <div className="flex flex-col space-y-2">
                        <label htmlFor="version" className="text-sm font-bold text-gray-800 dark:text-gray-200">Version</label>
                        <input id="version"
                               type="number"
                               placeholder="Version"
                               {...register("version.value", {required: true})}
                               className="rounded-lg border border-gray-200 p-2 text-sm text-gray-800 shadow focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-600 dark:bg-slate-700 dark:text-gray-200"/>
                        <span className="text-xs font-semibold text-red-700 dark:text-red-400">
                        {errors.version?.type === 'required' && "Version is required"}
                        </span>
                    </div>
                    <div className="flex flex-col space-y-2">
                        <label htmlFor="startDate" className="text-sm font-bold text-gray-800 dark:text-gray-200">Start Date</label>
                        <input id="startDate"
                               type="date"
                               placeholder="Start Date"
                               {...register("startDate", {required: true})}
                               className="rounded-lg border border-gray-200 text-sm text-gray-800 shadow p-[7px] focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-600 dark:bg-slate-700 dark:text-gray-200"/>
                        <span className="text-xs font-semibold text-red-700 dark:text-red-400">
                        {errors.startDate?.type === 'required' && "Start date is required"}
                        </span>
                    </div>
                    <div className="flex flex-col space-y-2">
                        <label htmlFor="sprint-length" className="text-sm font-bold text-gray-800 dark:text-gray-200">Sprint Length</label>
                        <input id="sprint-length"
                               type="number"
                               placeholder="Sprint Length"
                               {...register("sprintLength", {required: true})}
                               className="rounded-lg border border-gray-200 p-2 text-sm text-gray-800 shadow focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-600 dark:bg-slate-700 dark:text-gray-200"/>
                        <span className="text-xs font-semibold text-red-700 dark:text-red-400">
                        {errors.sprintLength?.type === 'required' && "Sprint length is required"}
                        </span>
                    </div>
                    <div className="flex flex-col space-y-2">
                        <label htmlFor="color" className="text-sm font-bold text-gray-800 dark:text-gray-200">Color</label>
                        <div className="relative">
                            <select
                                id="color"
                                {...register("version.color", {required: true})}
                                className="w-full appearance-none rounded-lg border border-gray-200 p-2 text-sm focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-600 dark:bg-slate-700 dark:text-gray-200 shadow">
                                <option value="red">Red</option>
                                <option value="orange">Orange</option>
                                <option value="amber">Amber</option>
                                <option value="yellow">Yellow</option>
                                <option value="lime">Lime</option>
                                <option value="green">Green</option>
                                <option value="emerald">Emerald</option>
                                <option value="teal">Teal</option>
                                <option value="cyan">Cyan</option>
                                <option value="sky">Sky</option>
                                <option value="blue">Blue</option>
                                <option value="indigo">Indigo</option>
                                <option value="violet">Violet</option>
                                <option value="purple">Purple</option>
                                <option value="fuchsia">Fuchsia</option>
                                <option value="pink">Pink</option>
                                <option value="rose">Rose</option>
                            </select>
                            <ChevronDownIcon
                                className="pointer-events-none absolute top-0 right-2 flex h-full w-4 items-center justify-center text-center text-gray-600 dark:text-gray-200"/>
                        </div>
                        <span className="text-xs font-semibold text-red-700 dark:text-red-400">
                        {errors.version?.color?.type === 'required' && "Color is required"}
                        </span>
                    </div>
                    <div></div>
                    <div className="flex flex-col space-y-2">
                        <span className="text-sm font-bold text-gray-800 dark:text-gray-200">Preview</span>
                        {version.value && version.environment && version.color
                            ? (<Version version={version}/>)
                            : <span className="text-xs italic dark:text-gray-200">Preview not available</span>}
                    </div>
                </div>
            </Modal>
        </>
    )
}

