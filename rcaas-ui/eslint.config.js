import globals from "globals";
import js from "@eslint/js";
import ts from "typescript-eslint";
import reactHooks from "eslint-plugin-react-hooks";
import reactRefresh from "eslint-plugin-react-refresh";
import parser from "@typescript-eslint/parser";

export default ts.config(
    js.configs.recommended,
    ts.configs.recommended,
    reactHooks.configs.recommended,
    {
        languageOptions: {
            ecmaVersion: 2022,
            sourceType: "module",
            globals: {
                ...globals.browser,
                ...globals.node
            },
            parser: parser,
        },
        plugins: {
            'react-refresh': reactRefresh,
        },
        rules: {
            'react-refresh/only-export-components': 'warn',
        },
    },
);