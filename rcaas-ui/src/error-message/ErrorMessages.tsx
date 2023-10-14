import {ErrorMessage} from "./ErrorMessage.ts";

type Props = {
    errorMessages: ErrorMessage[] | null
};

export function ErrorMessages({errorMessages}: Props) {
    if (errorMessages === null || errorMessages?.length === 0) {
        return <></>;
    }
    return <div className="mb-2 flex flex-col space-y-1">
        {errorMessages.map(value => (
            <div key={value.message} className="rounded-lg border border-red-600 bg-red-500 p-2 text-xs text-white">
                {value.message}
            </div>
        ))}
    </div>;
}