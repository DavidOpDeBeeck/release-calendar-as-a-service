import NewReleaseButton from "./NewReleaseButton.tsx";
import {useProject} from "../queries/project/useProject.ts";
import Modal from "../modal/Modal.tsx";
import SpecificationDetail from "./SpecificationDetail.tsx";

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
            <div className="flex flex-col space-y-2">
                {project?.specifications.map((specification, specificationIndex) => (
                    <SpecificationDetail
                        key={specificationIndex}
                        specification={specification}
                        specificationIndex={specificationIndex}/>
                ))}
                {project?.specifications.length === 0 && (<div>
                    <span className="text-xs italic dark:text-gray-200">No releases available</span>
                </div>)}
                <div className="flex flex-row-reverse">
                    <NewReleaseButton/>
                </div>
            </div>
        </Modal>
    )
}

