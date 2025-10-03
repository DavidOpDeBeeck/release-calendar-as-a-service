import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import HeroPage from "./pages/HeroPage.tsx";
import ProjectPage from "./pages/ProjectPage.tsx";
import {Provider} from "jotai";
import {QueryClient, QueryClientProvider,} from '@tanstack/react-query'
import {useHydrateAtoms} from 'jotai/react/utils'
import {queryClientAtom} from "jotai-tanstack-query";
import {ReactElement} from "react";
import {ChakraProvider} from "@chakra-ui/react";
import {system} from './theme.ts'
import '@fontsource/inter'
import {ColorModeProvider} from "./components/ui/color-mode.tsx";

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            refetchOnWindowFocus: false,
            refetchOnReconnect: false,
            retry: false,
            placeholderData: (prev: unknown) => prev
        },
    },
})

const HydrateAtoms = ({children}: { children: ReactElement }) => {
    useHydrateAtoms([[queryClientAtom, queryClient]])
    return children
}

function App() {
    return (
        <ChakraProvider value={system}>
            <ColorModeProvider>
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
            </ColorModeProvider>
        </ChakraProvider>
    )
}

export default App
