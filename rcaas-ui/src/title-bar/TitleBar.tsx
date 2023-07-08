import SettingsButton from "../settings/SettingsButton.tsx";
import ProjectName from "../project/ProjectName.tsx";
import Settings from "../settings/Settings.tsx";

export default function TitleBar() {
    return (
        <>
            <div
                className="mb-2 flex flex-none items-center justify-between rounded-lg border border-gray-200 bg-white px-3 py-2 space-x-2 dark:border-slate-800 dark:bg-slate-700">
                <ProjectName/>
                <SettingsButton/>
            </div>
            <Settings/>
        </>
    )
}
