import {ReactElement} from 'react'
import {Button, CloseButton, Drawer} from "@chakra-ui/react";

type Props = {
    title: string,
    submitLabel?: string,
    closeLabel?: string,
    open: boolean,
    onSubmit?: () => void,
    onClose: () => void,
    children: ReactElement | ReactElement[]
}

export default function SideDrawer({title, submitLabel, closeLabel, open, onSubmit, onClose, children}: Props) {
    return (
        <Drawer.Root open={open}
                     placement='end'
                     size="md"
                     onOpenChange={onClose}>
            <Drawer.Backdrop/>
            <Drawer.Trigger/>
            <Drawer.Positioner p={4}>
                <Drawer.Content rounded="md">
                    <Drawer.CloseTrigger/>
                    <Drawer.Header>
                        <Drawer.Title>{title}</Drawer.Title>
                    </Drawer.Header>
                    <Drawer.Body>{children}</Drawer.Body>
                    <Drawer.Footer gap={0}>
                        <Button variant="subtle" mr={3} onClick={onClose}>
                            {closeLabel}
                        </Button>
                        {submitLabel && <Button onClick={onSubmit} colorPalette='blue' variant="subtle">{submitLabel}</Button>}
                    </Drawer.Footer>
                    <Drawer.CloseTrigger asChild>
                        <CloseButton size="sm"/>
                    </Drawer.CloseTrigger>
                </Drawer.Content>
            </Drawer.Positioner>
        </Drawer.Root>
    )
}