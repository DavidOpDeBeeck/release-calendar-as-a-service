import SettingsSideDrawer from "./SettingsSideDrawer.tsx";
import {useState} from "react";
import {IconButton} from "@chakra-ui/react";
import {Settings} from "lucide-react";

export default function SettingsButton() {
    const [showModal, setShowModal] = useState(false);

    return (
        <>
            <IconButton aria-label="Show settings"
                        variant='subtle' size='sm'
                        onClick={() => setShowModal(prev => !prev)}>
                <Settings/>
            </IconButton>
            <SettingsSideDrawer showModal={showModal} closeModal={() => setShowModal(false)}/>
        </>
    );
}
