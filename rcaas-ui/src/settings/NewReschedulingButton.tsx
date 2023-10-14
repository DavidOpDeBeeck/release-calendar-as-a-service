import {ClockIcon} from "@heroicons/react/24/outline";
import {showNewReschedulingAtom} from "../store.ts";
import {useSetAtom} from "jotai";
import NewReschedulingModal from "./NewReschedulingModal.tsx";

type Props = {
    index: number;
}

export default function NewReschedulingButton({index}: Props) {
    const setShowNewRescheduling = useSetAtom(showNewReschedulingAtom)

    return (
        <>
            <button onClick={() => setShowNewRescheduling(true)}
                    type="button"
                    className="flex items-center rounded-lg border-2 border-gray-200 p-2 text-sm font-semibold text-gray-800 space-x-1 hover:bg-gray-50 focus:outline focus:outline-2 focus:outline-blue-600 dark:bg-slate-700 dark:border-slate-800 dark:text-gray-200 dark:hover:bg-slate-600">
                <ClockIcon className="h-4 w-4"/>
            </button>
            <NewReschedulingModal index={index}/>
        </>
    )
}

