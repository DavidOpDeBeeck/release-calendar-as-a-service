import {Dialog, Transition} from "@headlessui/react";
import NewReleaseButton from "./NewReleaseButton.tsx";
import {useProject} from "../queries/project/useProject.ts";
import {Fragment, useRef} from "react";
import NewReschedulingButton from "./NewReschedulingButton.tsx";
import DeleteProjectButton from "./DeleteProjectButton.tsx";
import DeleteReschedulingButton from "./DeleteReschedulingButton.tsx";

type Props = {
    showModal: boolean;
    closeModal: () => void;
}

export default function SettingsModal({showModal, closeModal}: Props) {
    const {data: project} = useProject();
    const cancelButtonRef = useRef(null);

    return (
        <Transition.Root show={showModal} as={Fragment}>
            <Dialog as="div" className="relative z-10" initialFocus={cancelButtonRef} onClose={() => closeModal()}>
                <Transition.Child
                    as={Fragment}
                    enter="ease-out duration-300"
                    enterFrom="opacity-0"
                    enterTo="opacity-100"
                    leave="ease-in duration-200"
                    leaveFrom="opacity-100"
                    leaveTo="opacity-0">
                    <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"/>
                </Transition.Child>
                <div className="fixed inset-0 z-10 overflow-y-auto">
                    <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
                        <Transition.Child
                            as={Fragment}
                            enter="ease-out duration-300"
                            enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
                            enterTo="opacity-100 translate-y-0 sm:scale-100"
                            leave="ease-in duration-200"
                            leaveFrom="opacity-100 translate-y-0 sm:scale-100"
                            leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95">
                            <Dialog.Panel
                                className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:w-full sm:max-w-xl">
                                <div className="bg-white px-4 pt-5 pb-4 dark:bg-slate-700 sm:p-6 sm:pb-4">
                                    <div className="mt-3 text-center sm:mt-0 sm:text-left">
                                        <Dialog.Title as="h3"
                                                      className="border-b border-gray-200 pb-3 text-2xl font-bold leading-6 text-gray-800 dark:border-slate-600 dark:text-gray-200">
                                            Settings
                                        </Dialog.Title>
                                        <div>
                                            <div className="flex flex-col py-2 space-y-2">
                                                {project?.specifications.map((specification, specificationIndex) => (
                                                    <div key={specificationIndex}
                                                         className="rounded-md border bg-gray-50 p-4 shadow-sm dark:border-slate-700 dark:bg-slate-800">
                                                        <div className="flex items-center justify-between">
                                                            <h1 className="text-lg font-semibold text-gray-800 dark:text-gray-200">{specification.sprintBased.version.environment}</h1>
                                                            <div className="flex space-x-1">
                                                                <NewReschedulingButton specificationIndex={specificationIndex}/>
                                                                <DeleteProjectButton specificationIndex={specificationIndex}/>
                                                            </div>
                                                        </div>
                                                        <h2 className="mt-2 py-2 font-medium dark:text-gray-200">Configuration</h2>
                                                        <div className="grid grid-cols-2 gap-x-3 gap-y-1 px-2">
                                                            <div className="flex justify-between text-sm space-x-2 dark:text-gray-200">
                                                                <span>Version</span>
                                                                <span>{specification.sprintBased.version.value}</span>
                                                            </div>
                                                            <div className="flex justify-between text-sm space-x-1 dark:text-gray-200">
                                                                <span>Start Date</span>
                                                                <span>{specification.sprintBased.startDate}</span>
                                                            </div>
                                                            <div className="flex justify-between text-sm space-x-1 dark:text-gray-200">
                                                                <span>Sprint Length</span>
                                                                <span>{specification.sprintBased.sprintLength}</span>
                                                            </div>
                                                            <div className="flex items-center justify-between text-sm space-x-1 dark:text-gray-200">
                                                                <span>Color</span>
                                                                <div
                                                                    className={`w-4 h-4 rounded-full bg-${specification.sprintBased.version.color}-400 border-2 border-${specification.sprintBased.version.color}-500`}></div>
                                                            </div>
                                                        </div>
                                                        <h2 className="mt-2 py-2 font-medium dark:text-gray-200">Reschedulings</h2>
                                                        <div className="px-2">
                                                            <table className="w-full table-fixed">
                                                                <thead>
                                                                <tr className="border-b border-gray-200 text-left text-sm dark:border-gray-700 dark:text-gray-200">
                                                                    <th className="w-2/5 py-2 font-medium">From Date</th>
                                                                    <th className="w-2/5 py-2 font-medium">To Date</th>
                                                                    <th className="w-1/5 py-2 font-medium"></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody className="text-left text-sm">
                                                                {specification?.sprintBased.reschedulings.map((rescheduling, reschedulingIndex) => (
                                                                    <tr key={reschedulingIndex}
                                                                        className="border-b border-gray-200 dark:border-gray-700 dark:text-gray-200">
                                                                        <td className="py-2">{rescheduling.from}</td>
                                                                        <td className="py-2">{rescheduling.to}</td>
                                                                        <td>
                                                                            <DeleteReschedulingButton
                                                                                specificationIndex={specificationIndex}
                                                                                reschedulingIndex={reschedulingIndex}/>
                                                                        </td>
                                                                    </tr>
                                                                ))}
                                                                {specification?.sprintBased.reschedulings.length === 0 && (<tr>
                                                                    <td colSpan={5} className="py-2 text-xs italic dark:text-gray-200">No reschedulings
                                                                        available
                                                                    </td>
                                                                </tr>)}
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                ))}
                                                {project?.specifications.length === 0 && (<div>
                                                    <span className="py-2 text-xs italic dark:text-gray-200">No releases available</span>
                                                </div>)}
                                                <div className="flex flex-row-reverse">
                                                    <NewReleaseButton/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="bg-gray-50 px-4 py-3 dark:bg-slate-800 sm:flex sm:flex-row-reverse sm:px-6">
                                    <button
                                        type="button"
                                        className="rounded-lg border-2 border-gray-200 bg-white p-2 font-semibold text-gray-800 hover:bg-gray-50 focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-800 dark:bg-slate-700 dark:text-gray-200 dark:hover:bg-slate-600"
                                        onClick={() => closeModal()}
                                        ref={cancelButtonRef}>
                                        Close
                                    </button>
                                </div>
                            </Dialog.Panel>
                        </Transition.Child>
                    </div>
                </div>
            </Dialog>
        </Transition.Root>
    )
}

