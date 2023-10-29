import {useSetAtom} from "jotai";
import {ChevronLeftIcon, ChevronRightIcon} from "@heroicons/react/24/outline";
import {yearMonthAtom} from "../store.ts";
import {useEffect} from "react";
import Button from "../components/Button.tsx";

export default function YearMonthSelector() {
    const setYearMonth = useSetAtom(yearMonthAtom);

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
        <div className="flex space-x-2">
            <Button onClick={() => setYearMonth(yearMonth => yearMonth.previous())}>
                <ChevronLeftIcon className="h-4 w-4"/>
            </Button>
            <Button onClick={() => setYearMonth(yearMonth => yearMonth.next())}>
                <ChevronRightIcon className="h-4 w-4"/>
            </Button>
        </div>
    )
}
