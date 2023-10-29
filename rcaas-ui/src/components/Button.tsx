import React from "react";

type ButtonStyle = "default" | "primary" | "error";

type Props = React.ButtonHTMLAttributes<HTMLButtonElement> & {
    style?: ButtonStyle;
};

export default function Button({style = "default", type, ...props}: Props) {
    return (<button {...props}
                    type={type || "button"}
                    className={"rounded-lg border-2 p-2 text-sm font-semibold focus:outline focus:outline-2 focus:outline-blue-600 dark:text-gray-200"
                        + (style === "default" ? " text-gray-800 border-gray-200 bg-white hover:bg-gray-50 dark:border-slate-800 dark:bg-slate-700 dark:hover:bg-slate-600" : "")
                        + (style === "primary" ? " text-white border-blue-700 bg-blue-600 hover:bg-blue-500" : "")
                        + (style === "error" ? " text-gray-200 border-red-700 bg-red-600 hover:bg-red-500" : "")
                    }>
        {props.children}
    </button>)
}