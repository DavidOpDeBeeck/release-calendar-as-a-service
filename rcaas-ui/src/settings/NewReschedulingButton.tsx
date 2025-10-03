import NewReschedulingSideDrawer from "./NewReschedulingSideDrawer.tsx";
import {useState} from "react";
import {Button} from "@chakra-ui/react";
import {Plus} from "lucide-react";

type Props = {
    specificationIndex: number;
}

export default function NewReschedulingButton({specificationIndex}: Props) {
    const [showModal, setShowModal] = useState(false);

    return (
        <>
            <Button variant="subtle"
                    size="sm"
                    onClick={() => setShowModal(true)}>
                <Plus/>
                Rescheduling
            </Button>
            <NewReschedulingSideDrawer showModal={showModal} closeModal={() => setShowModal(false)} specificationIndex={specificationIndex}/>
        </>
    )
}

