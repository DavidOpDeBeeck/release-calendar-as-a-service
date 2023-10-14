export type ErrorMessage = {
    message: string;
}

const translations = new Map([
    ["PROJECT_NAME_SHOULD_NOT_BE_NULL", "Project name is required"],
    ["RESCHEDULING_FROM_DATE_SHOULD_BE_A_RELEASE_DATE", "From date should be a release date"],
    ["FROM_DATE_SHOULD_BE_BEFORE_TO_DATE", "From date should be before To date"],
])

export function translateErrorMessages(errorMessages: ErrorMessage[]) {
    return errorMessages.map(value => ({message: translations.get(value.message)}))
}