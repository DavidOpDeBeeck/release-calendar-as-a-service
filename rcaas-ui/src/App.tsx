import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import HeroPage from "./pages/HeroPage.tsx";
import ProjectPage from "./pages/ProjectPage.tsx";
import {Provider} from "jotai";
import {QueryClient, QueryClientProvider,} from '@tanstack/react-query'
import {useHydrateAtoms} from 'jotai/react/utils'
import {queryClientAtom} from "jotai-tanstack-query";
import {ReactElement} from "react";

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            refetchOnWindowFocus: false,
            refetchOnReconnect: false,
            retry: false,
            keepPreviousData: true
        },
    },
})

const HydrateAtoms = ({children}: { children: ReactElement }) => {
    useHydrateAtoms([[queryClientAtom, queryClient]])
    return children
}

function App() {
    return (
        <QueryClientProvider client={queryClient}>
            <Provider>
                <HydrateAtoms>
                    <Router>
                        <Routes>
                            <Route path="/" element={<HeroPage/>}/>
                            <Route path="/:id" element={<ProjectPage/>}/>
                        </Routes>
                    </Router>
                </HydrateAtoms>
            </Provider>
        </QueryClientProvider>
    )
}

export default App
