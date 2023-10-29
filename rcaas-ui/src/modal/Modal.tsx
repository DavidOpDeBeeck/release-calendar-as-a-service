import {Fragment, ReactElement} from 'react'
import {Dialog, Transition} from '@headlessui/react'
import Button from "../components/Button.tsx";

type Props = {
    title: string,
    submitLabel?: string,
    closeLabel?: string,
    open: boolean,
    onSubmit?: () => void,
    onClose: () => void,
    children: ReactElement
}

export default function Modal({title, submitLabel, closeLabel, open, onSubmit, onClose, children}: Props) {
    return (
        <Transition.Root show={open} as={Fragment}>
            <Dialog as="div" className="relative z-10" onClose={onClose}>
                <Transition.Child
                    as={Fragment}
                    enter="ease-out duration-300"
                    enterFrom="opacity-0"
                    enterTo="opacity-100"
                    leave="ease-in duration-200"
                    leaveFrom="opacity-100"
                    leaveTo="opacity-0">
                    <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"/>
                </Transition.Child>
                <div className="fixed inset-0 z-10 overflow-y-auto">
                    <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
                        <Transition.Child
                            as={Fragment}
                            enter="ease-out duration-300"
                            enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
                            enterTo="opacity-100 translate-y-0 sm:scale-100"
                            leave="ease-in duration-200"
                            leaveFrom="opacity-100 translate-y-0 sm:scale-100"
                            leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95">
                            <Dialog.Panel
                                className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:w-full sm:max-w-xl">
                                <div className="bg-white px-4 pt-5 pb-4 dark:bg-slate-700 sm:p-6 sm:pb-4">
                                    <div className="mt-0 text-left">
                                        <Dialog.Title as="h3"
                                                      className="border-b border-gray-200 pb-3 text-2xl font-bold leading-6 text-gray-800 dark:border-slate-600 dark:text-gray-200">
                                            {title}
                                        </Dialog.Title>
                                        <div className="mt-4">
                                            <form>
                                                {children}
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div className="flex-row-reverse bg-gray-50 px-6 py-3 space-x-reverse space-x-1 dark:bg-slate-800 sm:flex">
                                    {submitLabel && <Button
                                        style="primary"
                                        onClick={() => onSubmit && onSubmit()}>
                                        {submitLabel}
                                    </Button>}
                                    {closeLabel && <Button
                                        onClick={() => onClose()}>
                                        {closeLabel}
                                    </Button>}
                                </div>
                            </Dialog.Panel>
                        </Transition.Child>
                    </div>
                </div>
            </Dialog>
        </Transition.Root>
    )
}