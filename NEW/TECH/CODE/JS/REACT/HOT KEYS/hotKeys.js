const hotKeys = () => {

    // document.body.addEventListener('keydown', event => {
    //     if (event.key === 'Tab') { document.body.setAttribute('data-input', 'keyboard') }
    //
    //     if (event.ctrlKey && event.shiftKey) {
    //         const value = parseInt(getComputedStyle(document.body).getPropertyValue('--brand'), 0)
    //         const valueDown = value - 2 >= 0 ? value - 2 : 358
    //         const valueUp = value + 2 <= 360 ? value + 2 : 2
    //
    //         switch (event.key) {
    //             case 'ArrowUp':
    //                 document.body.setAttribute('data-theme', 'light')
    //                 break
    //             case 'ArrowDown':
    //                 document.body.setAttribute('data-theme', 'dark')
    //                 break
    //             case 'ArrowLeft':
    //                 document.documentElement.style.setProperty('--brand', valueDown)
    //                 break
    //             case 'ArrowRight':
    //                 document.documentElement.style.setProperty('--brand', valueUp)
    //                 break
    //             default:
    //         }
    //     }
    // })

    document.body.addEventListener('mousedown', () => {
        document.body.setAttribute('data-input', 'mouse')
    })

}

export default hotKeys
