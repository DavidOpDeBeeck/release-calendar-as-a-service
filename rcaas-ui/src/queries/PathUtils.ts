export function resolvePath(path: string) {
    if (window.location.host.indexOf("localhost") > -1) {
        return path;
    }
    return `https://api.releasecalendar.app${path}`;
}