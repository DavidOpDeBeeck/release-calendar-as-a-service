import {IconButton, useColorMode} from "@chakra-ui/react";
import {MoonIcon, SunIcon} from "@chakra-ui/icons";

export default function ColorModeButton() {
    const {colorMode, toggleColorMode} = useColorMode()

    return (
        <IconButton aria-label="Toggle color mode"
                    onClick={toggleColorMode}
                    icon={colorMode == "light" ? <SunIcon/> : <MoonIcon/>}>
        </IconButton>
    );
}