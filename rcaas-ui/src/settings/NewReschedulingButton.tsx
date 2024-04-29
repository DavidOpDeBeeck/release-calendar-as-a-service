import NewReschedulingModal from "./NewReschedulingModal.tsx";
import {useState} from "react";
import {Button} from "@chakra-ui/react";
import {AddIcon} from "@chakra-ui/icons";

type Props = {
    specificationIndex: number;
}

export default function NewReschedulingButton({specificationIndex}: Props) {
    const [showModal, setShowModal] = useState(false);

    return (
        <>
            <Button leftIcon={<AddIcon/>}
                    colorScheme='gray'
                    size="sm"
                    onClick={() => setShowModal(true)}>
                Rescheduling
            </Button>
            <NewReschedulingModal showModal={showModal} closeModal={() => setShowModal(false)} specificationIndex={specificationIndex}/>
        </>
    )
}

