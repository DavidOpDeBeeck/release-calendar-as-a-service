import {useForm} from "react-hook-form";
import Modal from "../modal/Modal.tsx";

import {useProject} from "../queries/project/useProject.ts";
import {useUpdateProjectMutation} from "../queries/project/useUpdateProjectMutation.ts";
import {useAtom} from "jotai";
import {showNewReschedulingAtom} from "../store.ts";

type NewRescheduling = {
    from: string;
    to: string;
}

type Props = {
    index: number;
}

export default function NewReschedulingModal({index}: Props) {
    const {data: project} = useProject();
    const mutation = useUpdateProjectMutation();
    const [showNewRescheduling, setShowNewRescheduling] = useAtom(showNewReschedulingAtom)

    const {register, formState: {errors}, reset, handleSubmit} = useForm({
        defaultValues: {
            from: "",
            to: ""
        }
    });
    const onSubmit = (data: NewRescheduling) => {
        mutation.mutate({
            name: project!.name,
            specifications: [
                ...project!.specifications.slice(0, index),
                {
                    sprintBased: {
                        ...project!.specifications[index].sprintBased,
                        reschedulings: [
                            ...(project!.specifications[index].sprintBased.reschedulings || []),
                            {
                                from: data.from,
                                to: data.to
                            }
                        ]
                    }
                },
                ...project!.specifications.slice(index + 1)
            ]
        }, {
            onSettled: () => {
                setShowNewRescheduling(false);
                reset();
            }
        });
    };

    return (
        <Modal title={"New Rescheduling"}
               submitLabel={"Create"}
               open={showNewRescheduling}
               onSubmit={handleSubmit(onSubmit)}
               onClose={() => setShowNewRescheduling(false)}>
            <div className="grid grid-cols-2 gap-2">
                <div className="flex flex-col space-y-2">
                    <label htmlFor="from" className="text-sm font-bold text-gray-800 dark:text-gray-200">From Date</label>
                    <input id="from"
                           type="date"
                           placeholder="Start Date"
                           {...register("from", {required: true})}
                           className="rounded-lg border border-gray-200 text-sm text-gray-800 shadow p-[7px] focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-600 dark:bg-slate-700 dark:text-gray-200"/>
                    <span className="text-xs font-semibold text-red-700 dark:text-red-400">
                        {errors.from?.type === 'required' && "From date is required"}
                        </span>
                </div>
                <div className="flex flex-col space-y-2">
                    <label htmlFor="to" className="text-sm font-bold text-gray-800 dark:text-gray-200">To Date</label>
                    <input id="to"
                           type="date"
                           placeholder="Start Date"
                           {...register("to", {required: true})}
                           className="rounded-lg border border-gray-200 text-sm text-gray-800 shadow p-[7px] focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-600 dark:bg-slate-700 dark:text-gray-200"/>
                    <span className="text-xs font-semibold text-red-700 dark:text-red-400">
                        {errors.to?.type === 'required' && "To date is required"}
                        </span>
                </div>
            </div>
        </Modal>
    )
}

