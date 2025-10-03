import {useSetAtom} from "jotai";
import {yearMonthAtom} from "../store.ts";
import {useEffect} from "react";
import {HStack, IconButton, Text} from "@chakra-ui/react";
import {useAtomValue} from "jotai/index";
import {ChevronLeft, ChevronRight} from "lucide-react";

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
        <HStack gap={0}
                bgColor={{base: "blackAlpha.50", _dark: "whiteAlpha.100"}}
                borderRadius={5}>
            <IconButton aria-label='Go to previous month'
                        variant='subtle'
                        onClick={() => setYearMonth(yearMonth => yearMonth.previous())}>
                <ChevronLeft/>
            </IconButton>
            <HStack minWidth='6rem' alignItems='center' justifyContent='center'>
                <Text fontWeight="semibold">{yearMonth.getMonthName()}</Text>
            </HStack>
            <Text fontWeight="semibold" px={2}>{yearMonth.year}</Text>
            <IconButton aria-label='Go to next month'
                        variant='subtle'
                        onClick={() => setYearMonth(yearMonth => yearMonth.next())}>
                <ChevronRight/>
            </IconButton>
        </HStack>
    )
}
