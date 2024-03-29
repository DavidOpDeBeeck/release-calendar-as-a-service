import {QueryClient, useMutation, useQueryClient} from "@tanstack/react-query";
import {ErrorMessage, translateErrorMessages} from "../error-message/ErrorMessage.ts";

type MutationInput = {
    key: unknown[];
    path: string;
    method?: string;
    onSuccess?: (queryClient: QueryClient) => void;
}

export const useHttpMutation = <INPUT, OUTPUT>({key, path, method, onSuccess}: MutationInput) => {
    const queryClient = useQueryClient()

    return useMutation<OUTPUT, ErrorMessage[], INPUT>(key, async (input: INPUT) => {
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
    }, {onSuccess: () => onSuccess && onSuccess(queryClient)});
}
