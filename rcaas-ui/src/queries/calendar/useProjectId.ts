import {useParams} from "react-router-dom";

export const useProjectId = () => {
    const {id} = useParams();
    if (id === undefined) {
        throw Error("CalendarId should be present in url")
    }
    return id;
};