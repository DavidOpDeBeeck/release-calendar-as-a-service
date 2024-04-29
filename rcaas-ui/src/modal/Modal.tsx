import {ReactElement} from 'react'
import {Button, Drawer, DrawerBody, DrawerCloseButton, DrawerContent, DrawerFooter, DrawerHeader, DrawerOverlay} from "@chakra-ui/react";

type Props = {
    title: string,
    submitLabel?: string,
    closeLabel?: string,
    open: boolean,
    onSubmit?: () => void,
    onClose: () => void,
    children: ReactElement
}

export default function Modal({title, submitLabel, closeLabel, open, onSubmit, onClose, children}: Props) {
    return (
        <Drawer isOpen={open}
                placement='right'
                size="md"
                onClose={onClose}>
            <DrawerOverlay/>
            <DrawerContent>
                <DrawerCloseButton/>
                <DrawerHeader>{title}</DrawerHeader>
                <DrawerBody>
                    <form>{children}</form>
                </DrawerBody>
                <DrawerFooter>
                    <Button variant='outline' mr={3} onClick={onClose}>
                        {closeLabel}
                    </Button>
                    {submitLabel && <Button onClick={onSubmit} colorScheme='blue'>{submitLabel}</Button>}
                </DrawerFooter>
            </DrawerContent>
        </Drawer>
    )
}