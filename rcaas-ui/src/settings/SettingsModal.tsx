import NewReleaseButton from "./NewReleaseButton.tsx";
import {useProject} from "../queries/project/useProject.ts";
import NewReschedulingButton from "./NewReschedulingButton.tsx";
import DeleteProjectButton from "./DeleteProjectButton.tsx";
import DeleteReschedulingButton from "./DeleteReschedulingButton.tsx";
import Modal from "../modal/Modal.tsx";

type Props = {
    showModal: boolean;
    closeModal: () => void;
}

export default function SettingsModal({showModal, closeModal}: Props) {
    const {data: project} = useProject();

    return (
        <Modal title={"Settings"}
               closeLabel={"Close"}
               open={showModal}
               onClose={() => closeModal()}>
            <div className="flex flex-col py-2 space-y-2">
                {project?.specifications.map((specification, specificationIndex) => (
                    <div key={specificationIndex}
                         className="rounded-md border-2 border-gray-200 bg-white p-4 shadow-sm dark:border-slate-800 dark:bg-slate-600">
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
                                <tr className="border-b border-gray-200 text-left text-sm dark:border-gray-800 dark:text-gray-200">
                                    <th className="w-2/5 py-2 font-medium">From Date</th>
                                    <th className="w-2/5 py-2 font-medium">To Date</th>
                                    <th className="w-1/5 py-2 font-medium"></th>
                                </tr>
                                </thead>
                                <tbody className="text-left text-sm">
                                {specification?.sprintBased.reschedulings.map((rescheduling, reschedulingIndex) => (
                                    <tr key={reschedulingIndex}
                                        className="border-b border-gray-200 dark:border-gray-800 dark:text-gray-200">
                                        <td className="py-2">{rescheduling.from}</td>
                                        <td className="py-2">{rescheduling.to}</td>
                                        <td className="py-1">
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
                    <span className="text-xs italic dark:text-gray-200">No releases available</span>
                </div>)}
                <div className="flex flex-row-reverse">
                    <NewReleaseButton/>
                </div>
            </div>
        </Modal>
    )
}

