import NewReschedulingModal from "./NewReschedulingModal.tsx";
import {useState} from "react";
import {IconButton} from "@chakra-ui/react";
import {TimeIcon} from "@chakra-ui/icons";

type Props = {
    specificationIndex: number;
}

export default function NewReschedulingButton({specificationIndex}: Props) {
    const [showModal, setShowModal] = useState(false);

    return (
        <>
            <IconButton aria-label="Add rescheduling"
                        icon={<TimeIcon/>}
                        size="sm"
                        onClick={() => setShowModal(true)}>
            </IconButton>
            <NewReschedulingModal showModal={showModal} closeModal={() => setShowModal(false)} specificationIndex={specificationIndex}/>
        </>
    )
}

