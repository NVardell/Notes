import React from 'react'

import { ModalAlert } from 'common'

const IdleWarn = ({
    idleWarn,
    handleContinueClick,
    id = 'modal-alert-idle-warning',
    type = 'warning',
    title = 'Inactivity Warning',
    message = 'You are about to be logged out due to inactivity. Select OK to continue session.'
}) => {
    function handleCancel() {
        console.log('###\n### IDLE WARN | HANDLE CANCEL\n###')
        handleContinueClick(true)
    }

    return (
        <ModalAlert
            id={ id }
            type={ type }
            title={ title }
            isOpen={ idleWarn }
            handleOK={ handleContinueClick }
            handleCancel={ () => {
                console.log('###\n### IDLE WARN | HANDLE CANCEL\n###')
                handleCancel()
            } }
        >
            { message }
        </ModalAlert>
    )
}

export default IdleWarn
