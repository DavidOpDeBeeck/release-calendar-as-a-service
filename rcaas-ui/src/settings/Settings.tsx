import {Dialog, Transition} from "@headlessui/react";
import NewReleaseButton from "./NewReleaseButton.tsx";
import {useProject} from "../queries/project/useProject.ts";
import {showSettingsAtom} from "../store.ts";
import {useAtom} from "jotai";
import {Fragment, useRef} from "react";

export default function Settings() {
    const [showSettings, setShowSettings] = useAtom(showSettingsAtom);
    const {data: project} = useProject();
    const cancelButtonRef = useRef(null);

    return (
        <Transition.Root show={showSettings} as={Fragment}>
            <Dialog as="div" className="relative z-10" initialFocus={cancelButtonRef} onClose={() => setShowSettings(false)}>
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
                                                      className="pb-3 text-2xl font-bold leading-6 text-gray-800 dark:text-gray-200">
                                            Settings
                                        </Dialog.Title>
                                        <div>
                                            <div className="flex flex-col py-2">
                                                <h1 className="text-xl font-semibold text-gray-800 dark:text-gray-200">Releases</h1>
                                                <table className="table-auto">
                                                    <thead>
                                                    <tr className="border-b border-gray-200 text-left text-sm dark:border-gray-600 dark:text-gray-200">
                                                        <th className="py-2 font-medium">Environment</th>
                                                        <th className="py-2 font-medium">Version</th>
                                                        <th className="py-2 font-medium">Start Date</th>
                                                        <th className="py-2 font-medium">Sprint Length</th>
                                                        <th className="py-2 font-medium">Color</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody className="text-left text-sm">
                                                    {project?.specifications.map((specification, index) => (
                                                        <tr key={index} className="border-b border-gray-200 dark:border-gray-600 dark:text-gray-200">
                                                            <td className="py-2">{specification.sprintBased.version.environment}</td>
                                                            <td className="py-2">{specification.sprintBased.version.value}</td>
                                                            <td className="py-2">{specification.sprintBased.startDate}</td>
                                                            <td className="py-2">{specification.sprintBased.sprintLength}</td>
                                                            <td className="py-2">
                                                                <div
                                                                    className={`w-5 h-5 bg-${specification.sprintBased.version.color}-400 border-2 border-${specification.sprintBased.version.color}-500`}></div>
                                                            </td>
                                                        </tr>
                                                    ))}
                                                    {project?.specifications.length === 0 && (<tr>
                                                        <td colSpan={5} className="py-2 italic dark:text-gray-200">No releases available</td>
                                                    </tr>)}
                                                    </tbody>
                                                </table>
                                                <div className="mt-2 flex flex-row-reverse">
                                                    <NewReleaseButton/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="bg-gray-50 px-4 py-3 dark:bg-slate-800 sm:flex sm:flex-row-reverse sm:px-6">
                                    <button
                                        type="button"
                                        className="rounded-md border border-gray-200 px-3 py-2 text-sm font-bold text-gray-800 shadow space-x-1 hover:bg-gray-50 focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-800 dark:bg-slate-700 dark:text-gray-200 dark:hover:bg-slate-600"
                                        onClick={() => setShowSettings(false)}
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

