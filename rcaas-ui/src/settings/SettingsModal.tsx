import NewReleaseButton from "./NewReleaseButton.tsx";
import {useProject} from "../queries/project/useProject.ts";
import Modal from "../modal/Modal.tsx";
import SpecificationDetail from "./SpecificationDetail.tsx";
import {Text, VStack} from "@chakra-ui/react";

type Props = {
    showModal: boolean;
    closeModal: () => void;
}

export default function SettingsModal({showModal, closeModal}: Props) {
    const {data: project} = useProject();

    return (
        <Modal title={"Settings"}
               closeLabel={"Close"}
               open={showModal}
               onClose={() => closeModal()}>
            <VStack gap={2}>
                {project?.specifications.map((specification, specificationIndex) => (
                    <SpecificationDetail
                        key={specificationIndex}
                        specification={specification}
                        specificationIndex={specificationIndex}/>
                ))}
                {project?.specifications.length === 0 && (<Text>No releases available</Text>)}
                <NewReleaseButton/>
            </VStack>
        </Modal>
    )
}

