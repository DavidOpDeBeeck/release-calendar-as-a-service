import {useSetAtom} from "jotai";
import {yearMonthAtom} from "../store.ts";
import {useEffect} from "react";
import {Button, ButtonGroup, HStack, IconButton, Text} from "@chakra-ui/react";
import {ChevronLeftIcon, ChevronRightIcon} from "@chakra-ui/icons";
import {useAtomValue} from "jotai/index";

export default function YearMonthSelector() {
    const setYearMonth = useSetAtom(yearMonthAtom);
    const yearMonth = useAtomValue(yearMonthAtom);

    useEffect(() => {
        const handleKeyDown = (e: KeyboardEvent) => {
            switch (e.code) {
                case "ArrowLeft":
                    setYearMonth(prev => prev.previous());
                    break;
                case "ArrowRight":
                    setYearMonth(prev => prev.next())
                    break;
            }
        }
        document.addEventListener('keydown', handleKeyDown);
        return () => {
            document.removeEventListener('keydown', handleKeyDown);
        }
    }, [setYearMonth]);

    return (
        <ButtonGroup isAttached>
            <IconButton aria-label='Go to previous month'
                        icon={<ChevronLeftIcon/>}
                        onClick={() => setYearMonth(yearMonth => yearMonth.previous())}>
            </IconButton>
            <Button>
                <HStack>
                    <Text fontWeight="bold">{yearMonth.getMonthName()}</Text>
                    <Text fontWeight="bold">{yearMonth.year}</Text>
                </HStack>
            </Button>
            <IconButton aria-label='Go to next month'
                        icon={<ChevronRightIcon/>}
                        onClick={() => setYearMonth(yearMonth => yearMonth.next())}>
            </IconButton>
        </ButtonGroup>
    )
}
