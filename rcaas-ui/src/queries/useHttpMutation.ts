import {QueryClient, useMutation, useQueryClient} from "@tanstack/react-query";

type MutationInput = {
    key: unknown[];
    path: string;
    method?: string;
    onSuccess?: (queryClient: QueryClient) => void;
}

export const useHttpMutation = <INPUT, OUTPUT>({key, path, method, onSuccess}: MutationInput) => {
    const queryClient = useQueryClient()

    return useMutation<OUTPUT, string[], INPUT>({
        mutationKey: key,
        mutationFn: async (input: INPUT) => {
            const response = await fetch(`/api/v1${path}`, {
                method: method || 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(input)
            });

            const body = await response.json();

            if (response.ok) {
                return body;
            }

            throw translateErrorMessages(body);
        },
        onSuccess: () => onSuccess && onSuccess(queryClient)
    });
}

const translations = new Map([
    ["PROJECT_NAME_SHOULD_NOT_BE_NULL", "Project name is required"],
    ["RESCHEDULING_FROM_DATE_SHOULD_BE_A_RELEASE_DATE", "From date should be a release date"],
    ["FROM_DATE_SHOULD_BE_BEFORE_TO_DATE", "From date should be before To date"],
])

export function translateErrorMessages(errorMessages: { message: string }[]): string[] {
    return errorMessages.map(value => translations.get(value.message) || value.message)
}