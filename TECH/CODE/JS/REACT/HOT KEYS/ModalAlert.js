import React, { createRef, useEffect } from 'react'
import PropTypes from 'prop-types'

import {
    SvgNotificationSuccessLight,
    SvgNotificationInfoLight,
    SvgNotificationWarningLight,
    SvgNotificationErrorLight,
    SvgCloseX
} from '../../icons'

import { Button, ButtonBar } from '../../index'

import './ModalAlert.scss'

/**
 * @public
 * ModalAlert to display alert as a modal
 * @param {Boolean}         params.isOpen            Toogle to open/close the modal
 * @param {String}          params.title            For displaying title of the ModalAlert
 * @param {Node}            params.children         React Component or string to render in the Modal
 * @param {String}          params.type             Render type of Alert to be displayed. It can be 'success', 'info', 'warning'
 * @param {Boolean}         params.allowDismiss     Allowing dismiss of modal on escape key
 * @param {Funtion}         params.handleOK         Callback on Okay button being pressed
 * @param {Funtion}         params.handleCancel     Callback on cancel button being pressed
 * @param {Funtion}         params.handleClose      Callback function for handling close
 */

const getIcon = (type) => {

    switch (type) {
        case 'success':
            return <SvgNotificationSuccessLight className="hc-alert-icon" />
        case 'info':
            return <SvgNotificationInfoLight className="hc-alert-icon" />
        case 'warning':
            return <SvgNotificationWarningLight className="hc-alert-icon" />
        default:
            return <SvgNotificationErrorLight className="hc-alert-icon" />
    }

}

const ModalAlert = ({

    isOpen,
    title = '',
    children = '',
    type = 'error',
    id = 'hc-modal-alert',
    allowDismiss = true,
    handleOK,
    handleCancel,
    handleClose = handleCancel,

     /** this is just the default prop, you can override with any custom footer */
    footer = (
        <ButtonBar>
            { !!handleCancel &&
                <Button display="secondary" onClick={ handleCancel }>
                    Cancel
                </Button>
            }
            { !!handleOK &&
                <Button display="secondary" onClick={ handleOK }>
                    OK
                </Button>
            }
        </ButtonBar>
    )

}) => {

    const modalEscapeKeyHandler = (e) => {
        console.log('### HANDLE ESCAPE')
        if (handleClose) {
            console.log('### HANDLE ESCAPE | HANDLE CLOSE')
            handleClose()
        }
    }

    useEffect(() => {
        console.log('### USE EFFECT')
        function keyListener(e) {
            console.log('### USE EFFECT ~ KEY LISTENER #1 | ' + e.key + '-' + e.keyCode)
            const listener = keyListenersMap.get(e.keyCode);
            console.log('### USE EFFECT ~ KEY LISTENER #2 | ' + e.key + '-' + e.keyCode)
            return listener && listener(e);
        }

        if (isOpen) {
            console.log('### USE EFFECT ~ OPEN & ADDING OPEN CLASS')
            document.body.classList.add('hc-modal-alert-open')
            if (allowDismiss) {
                console.log('### USE EFFECT ~ OPEN & ADDING LISTENER')
                document.addEventListener('keydown', keyListener)
            }
        } else {
            document.body.classList.remove('hc-modal-alert-open')
            if (allowDismiss) {
                console.log('### USE EFFECT ~ CLOSED & REMOVING LISTENER')
                document.removeEventListener('keydown', keyListener)
            }
        }

        return () => {
            console.log('### USE EFFECT ~ RETURNING & REMOVING OPEN CLASS')
            document.body.classList.remove('hc-modal-alert-open')
            if (allowDismiss) {
                console.log('### USE EFFECT ~ RETURNING & REMOVING LISTENER')
                document.removeEventListener('keydown', keyListener)
            }
        }

    }, [isOpen])

    let modalRef;
    const  focusableElements =
        'button, [href], input, select, textarea, [tabindex]:not([tabindex="-1"])';

    const handleTabKey = e => {
        console.log('### HANDLE TAB | ' + e.key + '-' + e.keyCode)

        const modal = document.querySelector('#' + id); // select the modal by it's id
        console.log('### HANDLE TAB | MODAL ELEMENTS=' + modal.length)
        console.log('### HANDLE TAB | MODAL FOCUSABLE =' + modal.querySelectorAll(focusableElements).length)
        const focusable = modalRef.current.querySelectorAll(
            'a[href], button, textarea, input[type="text"], input[type="radio"], input[type="checkbox"], select'
        );

        console.log('###\n### HANDLE TAB | FOCUSABLE ELEMENTS=' + focusable.length)
        console.log('### HANDLE TAB | FOCUSABLE=' + focusable)
        const firstElement = focusable[0];
        console.log('### HANDLE TAB | FIRST ELEMENT=' + firstElement)
        const lastElement = focusable[focusable.length - 1];
        console.log('### HANDLE TAB | LAST ELEMENT=' + lastElement)

        if (!e.shiftKey && document.activeElement !== firstElement) {
            firstElement.focus();
            return e.preventDefault();
        }

        if (e.shiftKey && document.activeElement !== lastElement) {
            lastElement.focus();
            e.preventDefault();
        }
    };
    const keyListenersMap = new Map([[27, modalEscapeKeyHandler], [9, handleTabKey]]);

    if (isOpen) {
        modalRef = createRef();
        return (
            <div
                className="hc-modal-alert-container"
                data-hidden={ !isOpen }
                onClick={ (e) => {
                    if (!allowDismiss) return
                    if (e.target === e.currentTarget) handleClose()
                }}
                role={ 'alertdialog' }
                aria-modal={ 'true' }
                aria-labelledby={ id }
            >

                <div
                    className="hc-modal-alert"
                    id={ id }
                    ref={modalRef}
                >

                    <ul className="alert-layout">

                        <li className="alert-header">
                            <ol className={ type }>
                                <li>
                                    { getIcon(type) }
                                </li>
                                <li>
                                    { title }
                                </li>
                                <li>
                                    { (!!allowDismiss && !!handleClose) &&
                                        <SvgCloseX
                                            className="close-icon"
                                            role="button"
                                            onClick={ handleClose }
                                        />
                                    }
                                </li>
                            </ol>
                        </li>

                        <li className="alert-body">
                            { children }
                        </li>

                        <li className="alert-footer">
                            { footer }
                        </li>

                    </ul>

                </div>

            </div>

        )
    }

    return null

}

ModalAlert.propTypes = {
    /** Toggle to open/close the modal */
    isOpen: PropTypes.bool,
    /** For displaying title of the ModalAlert */
    title: PropTypes.string,
    /** React Component or string to render in the Modal  */
    children: PropTypes.node,
    /** Render type of Alert to be displayed. It can be 'success', 'info', 'warning'  */
    type: PropTypes.string,
    /** Unique String Modal Alert ID for DOM Reference & Aria Identification */
    id: PropTypes.string,
    /** Allowing dismiss of modal on escape key */
    allowDismiss: PropTypes.bool,
    /** Callback on Okay button being pressed */
    handleOK: PropTypes.func,
    /** Callback on cancel button being pressed */
    handleCancel: PropTypes.func,
    /** Callback function for handling close */
    handleClose: PropTypes.func,
}

export default ModalAlert
