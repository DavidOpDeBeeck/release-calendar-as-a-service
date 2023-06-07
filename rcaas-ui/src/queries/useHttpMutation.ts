import {QueryClient, useMutation, useQueryClient} from "@tanstack/react-query";

interface MutationInput {
    key: unknown[];
    path: string;
    method?: string;
    onSuccess?: (queryClient: QueryClient) => void;
}

export const useHttpMutation = <INPUT, OUTPUT>({key, path, method, onSuccess}: MutationInput) => {
    const queryClient = useQueryClient()

    return useMutation<OUTPUT, unknown, INPUT>(key, async (input: INPUT) => {
        const response = await fetch(`/api/v1${path}`, {
            method: method || 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(input)
        });
        return await response.json()
    }, {onSuccess: () => onSuccess && onSuccess(queryClient)});
}
