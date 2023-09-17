import CreateProject from "../project/CreateProject.tsx";
import {useEffect} from "react";

export default function HeroPage() {
    useEffect(() => {
        document.title = 'Release Calendar';
    }, []);

    return (
        <div className="flex h-full w-full flex-col items-center justify-center bg-gray-100 p-8 dark:bg-slate-800">
            <div className="mt-4 mb-8 flex flex-none flex-col text-center">
                <h1 className="text-5xl font-semibold text-gray-800 dark:text-slate-200">Release Calendar As a Service</h1>
                <p className="mt-3 text-xl text-gray-800 dark:text-slate-300">
                    Create, manage and share your release calendar with your team and your clients.
                </p>
                <div className="mt-8">
                    <CreateProject/>
                </div>
            </div>
        </div>
    )
}
