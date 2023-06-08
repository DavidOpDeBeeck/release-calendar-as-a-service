import {useQuery} from "@tanstack/react-query";
import {resolvePath} from "./PathUtils";

interface QueryInput {
    key: unknown[];
    path: string;
}

export const useHttpQuery = <OUTPUT>({key, path}: QueryInput) => {
    return useQuery<OUTPUT>(key, async () => {
        const response = await fetch(resolvePath(`/api/v1${path}`), {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
        return await response.json()
    });
}
