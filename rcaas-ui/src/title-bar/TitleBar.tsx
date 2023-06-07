import SettingsButton from "../settings/SettingsButton.tsx";
import ProjectName from "../project/ProjectName.tsx";
import Settings from "../settings/Settings.tsx";

export default function TitleBar() {
    return (
        <div className="mb-2 flex flex-none flex-col rounded-lg border border-gray-200 bg-white px-4 py-2 shadow dark:border-slate-800 dark:bg-slate-700">
            <div className="flex items-center justify-between">
                <ProjectName/>
                <SettingsButton/>
            </div>
            <Settings/>
        </div>
    )
}
