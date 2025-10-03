import NewReleaseSideDrawer from "./NewReleaseSideDrawer.tsx";
import {useState} from "react";
import {Button} from "@chakra-ui/react";
import {Plus} from "lucide-react";

export default function NewReleaseButton() {
    const [showModal, setShowModal] = useState(false);

    return (
        <>
            <Button colorPalette='blue'
                    size="sm"
                    variant="subtle"
                    onClick={() => setShowModal(prev => !prev)}>
                <Plus/>
                Release
            </Button>
            <NewReleaseSideDrawer showModal={showModal} closeModal={() => setShowModal(false)}/>
        </>
    )
}

