import {VersionTO} from "../domain/VersionTO.ts";

interface Props {
    version: VersionTO;
    faded: boolean;
}

export default function Version({version, faded}: Props) {
    const bgColor = faded ? `bg-${version.color}-300` : `bg-${version.color}-400`;
    const borderColor = faded ? `border-${version.color}-400` : `border-${version.color}-500`;

    return (
        <div
            className={`${bgColor} ${borderColor} flex px-2 font-bold text-sm text-gray-800 border-2 rounded-md shadow-md duration-150 ease-in`}>
            <span className="flex-1">{version.environment}</span>
            <span>{version.value}</span>
        </div>
    )
}
