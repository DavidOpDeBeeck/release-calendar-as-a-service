import {Cog6ToothIcon} from "@heroicons/react/24/outline";
import SettingsModal from "./SettingsModal.tsx";
import {useState} from "react";
import Button from "../components/Button.tsx";

export default function SettingsButton() {
    const [showModal, setShowModal] = useState(false);

    return (
        <>
            <Button onClick={() => setShowModal(prev => !prev)}>
                <Cog6ToothIcon className="h-4 w-4"/>
            </Button>
            <SettingsModal showModal={showModal} closeModal={() => setShowModal(false)}/>
        </>
    );
}
