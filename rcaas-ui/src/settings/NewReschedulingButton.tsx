import {ClockIcon} from "@heroicons/react/24/outline";
import NewReschedulingModal from "./NewReschedulingModal.tsx";
import {useState} from "react";

type Props = {
    specificationIndex: number;
}

export default function NewReschedulingButton({specificationIndex}: Props) {
    const [showModal, setShowModal] = useState(false);

    return (
        <>
            <button onClick={() => setShowModal(true)}
                    type="button"
                    className="flex items-center rounded-lg border-2 border-gray-200 bg-white p-2 text-sm font-semibold text-gray-800 space-x-1 hover:bg-gray-50 focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-800 dark:bg-slate-700 dark:text-gray-200 dark:hover:bg-slate-600">
                <ClockIcon className="h-4 w-4"/>
            </button>
            <NewReschedulingModal showModal={showModal} closeModal={() => setShowModal(false)} specificationIndex={specificationIndex}/>
        </>
    )
}

