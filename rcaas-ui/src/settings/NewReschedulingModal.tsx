import {useForm} from "react-hook-form";
import Modal from "../modal/Modal.tsx";

import {useProject} from "../queries/project/useProject.ts";
import {useUpdateProjectMutation} from "../queries/project/useUpdateProjectMutation.ts";
import {ErrorMessages} from "../error-message/ErrorMessages.tsx";

type NewRescheduling = {
    from: string;
    to: string;
}

type Props = {
    showModal: boolean;
    closeModal: () => void;
    specificationIndex: number;
}

export default function NewReschedulingModal({showModal, closeModal, specificationIndex}: Props) {
    const {data: project} = useProject();
    const mutation = useUpdateProjectMutation();

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
                ...project!.specifications.slice(0, specificationIndex),
                {
                    sprintBased: {
                        ...project!.specifications[specificationIndex].sprintBased,
                        reschedulings: [
                            ...(project!.specifications[specificationIndex].sprintBased.reschedulings || []),
                            {
                                from: data.from,
                                to: data.to
                            }
                        ]
                    }
                },
                ...project!.specifications.slice(specificationIndex + 1)
            ]
        }, {
            onSuccess: () => {
                closeModal();
                reset();
            },
        });
    };

    return (
        <Modal title={"New Rescheduling"}
               submitLabel={"Create"}
               open={showModal}
               onSubmit={handleSubmit(onSubmit)}
               onClose={() => closeModal()}>
            <div>
                <ErrorMessages errorMessages={mutation.error}/>
                <div className="grid grid-cols-2 gap-2">
                    <div className="flex flex-col space-y-2">
                        <label htmlFor="from" className="text-sm font-bold text-gray-800 dark:text-gray-200">From Date</label>
                        <input id="from"
                               type="date"
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
                               {...register("to", {required: true})}
                               className="rounded-lg border border-gray-200 text-sm text-gray-800 shadow p-[7px] focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-600 dark:bg-slate-700 dark:text-gray-200"/>
                        <span className="text-xs font-semibold text-red-700 dark:text-red-400">
                        {errors.to?.type === 'required' && "To date is required"}
                        </span>
                    </div>
                </div>
            </div>
        </Modal>
    )
}

