import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import HeroPage from "./pages/HeroPage.tsx";
import ProjectPage from "./pages/ProjectPage.tsx";
import {Provider} from "jotai";
import {QueryClient, QueryClientProvider,} from '@tanstack/react-query'
import {useHydrateAtoms} from 'jotai/react/utils'
import {queryClientAtom} from "jotai-tanstack-query";
import {ReactElement} from "react";
import {ChakraProvider, extendTheme, ThemeConfig} from "@chakra-ui/react";
import '@fontsource/open-sans'

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

const HydrateAtoms = ({children}: {children: ReactElement}) => {
    useHydrateAtoms([[queryClientAtom, queryClient]])
    return children
}

const config: ThemeConfig = {
    initialColorMode: 'system',
    useSystemColorMode: false,
}

const theme = extendTheme(config, {
    fonts: {
        heading: `'Open Sans', sans-serif`,
        body: `'Open Sans', sans-serif`,
    },
})

function App() {
    return (
        <ChakraProvider theme={theme}>
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
        </ChakraProvider>
    )
}

export default App
