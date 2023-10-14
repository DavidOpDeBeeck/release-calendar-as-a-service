import {VersionTO} from "../domain/VersionTO.ts";

interface Props {
    version: VersionTO;
    visible: boolean
}

export default function Version({version, visible}: Props) {
    return (
        <div
            className={`${visible ? 'visible' : 'invisible'} bg-${version.color}-400 border-${version.color}-500 flex px-2 font-bold text-sm text-gray-800 border-2 rounded-md shadow-md duration-150 ease-in opacity-60`}>
            <span className="flex-1">{version.environment}</span>
            <span>{version.value}</span>
        </div>
    )
}
