import SettingsSideDrawer from "./SettingsSideDrawer.tsx";
import {useState} from "react";
import {IconButton} from "@chakra-ui/react";
import {SettingsIcon} from "@chakra-ui/icons";

export default function SettingsButton() {
    const [showModal, setShowModal] = useState(false);

    return (
        <>
            <IconButton aria-label="Show settings"
                        onClick={() => setShowModal(prev => !prev)}
                        icon={<SettingsIcon/>}>
            </IconButton>
            <SettingsSideDrawer showModal={showModal} closeModal={() => setShowModal(false)}/>
        </>
    );
}
