import {IconButton} from "@chakra-ui/react";
import {Moon, Sun} from "lucide-react";
import {useColorMode} from "../components/ui/color-mode.tsx";

export default function ColorModeButton() {
    const {colorMode, toggleColorMode} = useColorMode()

    return (
        <IconButton aria-label="Toggle color mode"
                    variant='subtle'
                    onClick={toggleColorMode}>
            {colorMode == "light" ? <Sun/> : <Moon/>}
        </IconButton>
    );
}