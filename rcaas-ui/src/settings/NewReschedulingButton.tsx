import {ClockIcon} from "@heroicons/react/24/outline";
import NewReschedulingModal from "./NewReschedulingModal.tsx";
import {useState} from "react";
import Button from "../components/Button.tsx";

type Props = {
    specificationIndex: number;
}

export default function NewReschedulingButton({specificationIndex}: Props) {
    const [showModal, setShowModal] = useState(false);

    return (
        <>
            <Button onClick={() => setShowModal(true)}>
                <ClockIcon className="h-4 w-4"/>
            </Button>
            <NewReschedulingModal showModal={showModal} closeModal={() => setShowModal(false)} specificationIndex={specificationIndex}/>
        </>
    )
}

