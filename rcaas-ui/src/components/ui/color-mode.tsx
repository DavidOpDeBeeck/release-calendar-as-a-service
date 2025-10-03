"use client"

import type {ThemeProviderProps} from "next-themes"
import {ThemeProvider, useTheme} from "next-themes"

export interface ColorModeProviderProps extends ThemeProviderProps {
}

export function ColorModeProvider(props: ColorModeProviderProps) {
    return (
        <ThemeProvider attribute="class" disableTransitionOnChange {...props} />
    )
}

export type ColorMode = "light" | "dark"

export interface UseColorModeReturn {
    colorMode: ColorMode
    setColorMode: (colorMode: ColorMode) => void
    toggleColorMode: () => void
}

export function useColorMode(): UseColorModeReturn {
    const {resolvedTheme, setTheme, forcedTheme} = useTheme()
    const colorMode = forcedTheme || resolvedTheme
    const toggleColorMode = () => {
        setTheme(resolvedTheme === "dark" ? "light" : "dark")
    }
    return {
        colorMode: colorMode as ColorMode,
        setColorMode: setTheme,
        toggleColorMode,
    }
}

export function useColorModeValue<T>(light: T, dark: T) {
    const {colorMode} = useColorMode()
    return colorMode === "dark" ? dark : light
}