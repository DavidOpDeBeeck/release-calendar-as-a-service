import {PlusIcon} from "@heroicons/react/24/outline";
import NewReleaseModal from "./NewReleaseModal.tsx";
import {useState} from "react";
import Button from "../components/Button.tsx";

export default function NewReleaseButton() {
    const [showModal, setShowModal] = useState(false);

    return (
        <>
            <Button onClick={() => setShowModal(prev => !prev)}>
                <div className="flex items-center space-x-1">
                    <PlusIcon className="h-4 w-4"/>
                    <span>Release</span>
                </div>
            </Button>
            <NewReleaseModal showModal={showModal} closeModal={() => setShowModal(false)}/>
        </>
    )
}

