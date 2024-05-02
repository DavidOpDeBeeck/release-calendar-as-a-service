import NewReleaseSideDrawer from "./NewReleaseSideDrawer.tsx";
import {useState} from "react";
import {Button} from "@chakra-ui/react";
import {AddIcon} from "@chakra-ui/icons";

export default function NewReleaseButton() {
    const [showModal, setShowModal] = useState(false);

    return (
        <>
            <Button leftIcon={<AddIcon/>}
                    colorScheme='blue'
                    size="sm"
                    onClick={() => setShowModal(prev => !prev)}>
                Release
            </Button>
            <NewReleaseSideDrawer showModal={showModal} closeModal={() => setShowModal(false)}/>
        </>
    )
}

