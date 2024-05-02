import NewReleaseButton from "./NewReleaseButton.tsx";
import {useProject} from "../queries/project/useProject.ts";
import SideDrawer from "../common/SideDrawer.tsx";
import SpecificationDetailCard from "./SpecificationDetailCard.tsx";
import {Text, VStack} from "@chakra-ui/react";

type Props = {
    showModal: boolean;
    closeModal: () => void;
}

export default function SettingsSideDrawer({showModal, closeModal}: Props) {
    const {data: project} = useProject();

    return (
        <SideDrawer title={"Settings"}
                    closeLabel={"Close"}
                    open={showModal}
                    onClose={() => closeModal()}>
            <VStack gap={2}>
                {project?.specifications.map((specification, specificationIndex) => (
                    <SpecificationDetailCard
                        key={specificationIndex}
                        specification={specification}
                        specificationIndex={specificationIndex}/>
                ))}
                {project?.specifications.length === 0 && (<Text>No releases available</Text>)}
                <NewReleaseButton/>
            </VStack>
        </SideDrawer>
    )
}

