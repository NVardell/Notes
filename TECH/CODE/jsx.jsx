/**********************************************************************************************************************
***************************************************      NOTES      ***************************************************
***********************************************************************************************************************/
• 



/**********************************************************************************************************************
***************************************************      NOTES      ***************************************************
***********************************************************************************************************************/
• URL IMAGE FILE PATH EXAMPLES TO TRY
    - style={{ backgroundImage: "url(/image.png)" }}
    - style={{ backgroundImage: `url(${process.env.PUBLIC_URL + '/image.png'})` }}






/**********************************************************************************************************************
**************************************************      EXAMPLES      *************************************************
***********************************************************************************************************************/
• 'EXAMPLE #1' ~ (HC360) event.js 
    - Function w/ callback (cb)
        //
        //  Example #1
        export const curryWithPressed = cb => e => {
            const el = e.target
            el.classList.add('pressed')
            
            const x = (el) => el.classList.remove('pressed')
            setTimeout(() => x(el), 200)
            
            if (!cb) return
            cb(e)
        }
        //
        //  Example #2
        export const curryWithPreventDefault = cb => e => {
            e.preventDefault()
            if (typeof cb === 'function') cb(e)
        }
        //
        //  Example #3
        export const curryWithEnterKeyPress = (onClick) => ({ key }) => {
            if (key === 'Enter') onClick()
        }
//
//  12 Required ESLint Rules for React | https://blog.logrocket.com/12-essential-eslint-rules-react/
//
// • defaultArguments
//     - This strategy expects the defaults to be specified in the function
//     declaration, using JavaScript’s inbuilt default values syntax.

        const MyComponent = ({ action = 'init' }) => { ... }

        MyComponent.propTypes = {
          Action: PropTypes.string;
        };
• EsLint react/jsx-key ~ https://github.com/jsx-eslint/eslint-plugin-react/blob/master/docs/rules/jsx-key.md
        Examples of correct code for this rule:

        [<Hello key="first" />, <Hello key="second" />, <Hello key="third" />];
        data.map((x) => <Hello key={x.id}>{x}</Hello>);
        Array.from([1, 2, 3], (x) => <Hello key={x.id}>{x}</Hello>);
        <Hello key={id} {...{ id, caption }} />
• 
• 
• 
• 
• 