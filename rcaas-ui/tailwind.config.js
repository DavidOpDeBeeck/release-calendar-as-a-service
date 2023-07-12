const colors = require('tailwindcss/colors')

const toRgba = (hexCode, opacity = 50) => {
    let hex = hexCode.replace('#', '');

    if (hex.length === 3) {
        hex = `${hex[0]}${hex[0]}${hex[1]}${hex[1]}${hex[2]}${hex[2]}`;
    }

    const r = parseInt(hex.substring(0, 2), 16);
    const g = parseInt(hex.substring(2, 4), 16);
    const b = parseInt(hex.substring(4, 6), 16);

    return `rgba(${r},${g},${b},${opacity / 100})`;
};

const flattenColorPalette = (obj, sep = '-') => Object.assign(
    {},
    ...function _flatten(o, p = '') {
        return [].concat(...Object.keys(o)
            .map(k =>
                typeof o[k] === 'object' ?
                    _flatten(o[k], k + sep) :
                    ({[p + k]: o[k]})
            )
        );
    }(obj)
);

module.exports = {
    content: [
        "./index.html",
        "./src/**/*.{js,ts,jsx,tsx}",
    ],
    theme: {
        extend: {
            colors: {
                current: 'currentColor',
                sky: colors.sky,
                cyan: colors.cyan,
            },
        },
    },
    safelist: [
        'bg-red-300',
        'bg-red-400',
        'border-red-400',
        'border-red-500',
        'bg-orange-300',
        'bg-orange-400',
        'border-orange-400',
        'border-orange-500',
        'bg-amber-300',
        'bg-amber-400',
        'border-amber-400',
        'border-amber-500',
        'bg-yellow-300',
        'bg-yellow-400',
        'border-yellow-400',
        'border-yellow-500',
        'bg-lime-300',
        'bg-lime-400',
        'border-lime-400',
        'border-lime-500',
        'bg-green-300',
        'bg-green-400',
        'border-green-400',
        'border-green-500',
        'bg-emerald-300',
        'bg-emerald-400',
        'border-emerald-400',
        'border-emerald-500',
        'bg-teal-300',
        'bg-teal-400',
        'border-teal-400',
        'border-teal-500',
        'bg-cyan-300',
        'bg-cyan-400',
        'border-cyan-400',
        'border-cyan-500',
        'bg-sky-300',
        'bg-sky-400',
        'border-sky-400',
        'border-sky-500',
        'bg-blue-300',
        'bg-blue-400',
        'border-blue-400',
        'border-blue-500',
        'bg-indigo-300',
        'bg-indigo-400',
        'border-indigo-400',
        'border-indigo-500',
        'bg-violet-300',
        'bg-violet-400',
        'border-violet-400',
        'border-violet-500',
        'bg-purple-300',
        'bg-purple-400',
        'border-purple-400',
        'border-purple-500',
        'bg-fuchsia-300',
        'bg-fuchsia-400',
        'border-fuchsia-400',
        'border-fuchsia-500',
        'bg-pink-300',
        'bg-pink-400',
        'border-pink-400',
        'border-pink-500',
        'bg-rose-300',
        'bg-rose-400',
        'border-rose-400',
        'border-rose-500',
    ],
    variants: {},
    plugins: [
        function ({addUtilities, theme}) {
            const utilities = {
                '.bg-stripes': {
                    backgroundImage:
                        'linear-gradient(45deg, var(--stripes-color) 12.50%, transparent 12.50%, transparent 50%, var(--stripes-color) 50%, var(--stripes-color) 62.50%, transparent 62.50%, transparent 100%)',
                    backgroundSize: '5.66px 5.66px',
                },
            }

            const addColor = (name, color) =>
                (utilities[`.bg-stripes-${name}`] = {'--stripes-color': color})

            const colors = flattenColorPalette(theme('backgroundColor'))
            for (let name in colors) {
                try {
                    const [r, g, b, a] = toRgba(colors[name])
                    if (a !== undefined) {
                        addColor(name, colors[name])
                    } else {
                        addColor(name, `rgba(${r}, ${g}, ${b}, 0.4)`)
                    }
                } catch (_) {
                    addColor(name, colors[name])
                }
            }

            addUtilities(utilities)
        },
    ],
}
