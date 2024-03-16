import {InformationCircleIcon} from "@heroicons/react/24/outline";
import NewReschedulingButton from "./NewReschedulingButton.tsx";
import DeleteProjectButton from "./DeleteProjectButton.tsx";
import {ReleaseSpecificationTO} from "../domain/ReleaseTO.ts";
import {Fragment, useState} from "react";
import DeleteReschedulingButton from "./DeleteReschedulingButton.tsx";
import {Transition} from "@headlessui/react";
import Button from "../components/Button";

type Props = {
    specification: ReleaseSpecificationTO;
    specificationIndex: number;
}

export default function SpecificationDetail({specification, specificationIndex}: Props) {
    const [collapsed, setCollapsed] = useState(true);

    return <div key={specificationIndex}
                className="rounded-md border-2 border-gray-200 bg-white p-3 shadow-sm dark:border-slate-800 dark:bg-slate-700">
        <div className="flex items-center justify-between">
            <div className="flex items-center text-gray-800 space-x-2 dark:text-gray-200">
                <h1 className="text-lg font-semibold">{specification.sprintBased.version.environment}</h1>
            </div>
            <div className="flex space-x-1">
                <Button onClick={() => setCollapsed(prev => !prev)}>
                    <InformationCircleIcon className="h-4 w-4"/>
                </Button>
                <NewReschedulingButton specificationIndex={specificationIndex}/>
                <DeleteProjectButton specificationIndex={specificationIndex}/>
            </div>
        </div>
        <Transition.Root show={!collapsed} as={Fragment}>
            <Transition.Child
                as={Fragment}
                enter="ease-out duration-300"
                enterFrom="opacity-0"
                enterTo="opacity-100"
                leave="ease-in duration-200"
                leaveFrom="opacity-100"
                leaveTo="opacity-0">
                <div>
                    <h2 className="py-2 font-medium dark:text-gray-200">Configuration</h2>
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
            </Transition.Child>
        </Transition.Root>
    </div>;
}