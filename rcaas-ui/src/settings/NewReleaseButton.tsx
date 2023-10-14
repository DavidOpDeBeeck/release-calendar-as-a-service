import {PlusIcon} from "@heroicons/react/24/outline";
import NewReleaseModal from "./NewReleaseModal.tsx";
import {showNewReleaseAtom} from "../store.ts";
import {useSetAtom} from "jotai";

export default function NewReleaseButton() {
    const setShowNewRelease = useSetAtom(showNewReleaseAtom)

    return (
        <>
            <button onClick={() => setShowNewRelease(true)}
                    type="button"
                    className="flex items-center rounded-lg border-2 border-gray-200 p-2 text-sm font-semibold text-gray-800 space-x-1 hover:bg-gray-50 focus:outline focus:outline-2 focus:outline-blue-600 dark:border-slate-800 dark:text-gray-200 dark:hover:bg-slate-600">
                <PlusIcon className="h-4 w-4"/>
                <span>Release</span>
            </button>
            <NewReleaseModal/>
        </>
    )
}

