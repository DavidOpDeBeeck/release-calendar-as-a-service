import {useQuery} from "@tanstack/react-query";

interface QueryInput {
    key: unknown[];
    path: string;
}

export const useHttpQuery = <OUTPUT>({key, path}: QueryInput) => {
    return useQuery<OUTPUT>({
        queryKey: key,
        queryFn: async () => {
            const response = await fetch(`/api/v1${path}`, {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });
            return await response.json()
        }
    });
}
