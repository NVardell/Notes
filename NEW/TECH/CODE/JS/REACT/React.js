/**********************************************************************************************************************
************************************************      DEPENDENCY      *************************************************
***********************************************************************************************************************/
* NPM
    + 


/**********************************************************************************************************************
**************************************************      URLS      *****************************************************
***********************************************************************************************************************/
* Next To Take Notes On
    + https://reactjs.org/docs/components-and-props.html
    + https://www.robinwieruch.de/react-function-component
* ReactJS Docs
    + https://reactjs.org/
    + https://reactjs.org/docs/forwarding-refs.html
    + https://reactjs.org/docs/higher-order-components.html
    + https://reactjs.org/docs/components-and-props.html



/**********************************************************************************************************************
**************************************************      NOTES      ****************************************************
***********************************************************************************************************************/



/**********************************************************************************************************************
************************************************      COMPONENTS      *************************************************
***********************************************************************************************************************/
* Component
    + Transforms props into UI.
* Higher Order Components (HOC)
    + Concrete Definition: A function that takes a component & returns a new component.
        const EnhancedComponent = higherOrderComponent(WrappedComponent);
    + Uses
        1. For Cross-Cutting Concerns
        2. Reducing code that might be used many many times in large applications.
           Which is done by abstracting the logic into a single place & use it across many components.
        3. Add features to a component, but shouldn''t drastically alter its contract.
    + Caveats
        1. Don''t use HOCs inside the `render` method
            - If the component return from `render` is identical '===' to the component from the previos render,
              React recursivly updates the subtree by diffing it with the new one.  If they''re !=, the previous 
              subtree is unmounted completely. 
                * Normally, you don''t need to think about this, but it matters for HOCs because
                  it means you can''t apply a HOC to a component within the `render` method of another component.
                * The `unmount / remount` not only impacts performance, it also causes the state of that component
                  and all of its children to be lost.
                    Problem Example:
                        render() { // A new version of EnhancedComponent is created on every render
                            const EnhancedComponent = enhance(MyComponent); // EnhancedComponent1 !== EnhancedComponent2
                            return <EnhancedComponent />; // That causes the entire subtree to unmount/remount each time!
                        }
                    Solution:
                        + Apply the HOCs outside the component definition so that the resulting component is created 
                          only once. Then its identity will be consistent across renders. (Generally, what you want anyways.)
                        + In rare cases where a HOC needs to be applied dynamically, you can do it inside the componets lifecycle 
                          methods or its constructor. 
        2. Static Methods must be copied over
            - Sometimes it is useful to define a static method on a React Component. For example, `Relay` Containers
              expose a static method `getFragment` to facilitate the composition of GraphQL fragments.
                * When you apply a HOC to a component though, the original component is wrapped with a container component.
                  That means the new component does not have any of the static methods of the original component.
                    Example:
                        WrappedComponent.staticMethod = function() {/*...*/}    // Define a static method
                        const EnhancedComponent = enhance(WrappedComponent);    // Now apply a HOC

                        // The enhanced component has no static method
                        typeof EnhancedComponent.staticMethod === 'undefined' // true
                    Solution:
                        // You could..... copy the methods onto the container before returning it?
                            function enhance(WrappedComponent) {
                                class Enhance extends React.Component {/*...*/}
                                // Must know exactly what method(s) to copy :(
                                Enhance.staticMethod = WrappedComponent.staticMethod;
                                return Enhance;
                            }
                        // But... that requires you to `know things` about specific methods of the component (Aka NOT GENERIC)
                        // So instead of that you could.....
                        //      ... use `hoist-non-react-statics` to automatically copy non-React static methods.
                            import hoistNonReactStatic from 'hoist-non-react-statics';
                            function enhance(WrappedComponent) {
                                class Enhance extends React.Component {/*...*/}
                                hoistNonReactStatic(Enhance, WrappedComponent);
                                return Enhance;
                            }
                        // Or.. Without using a random library that you may or may not have access to...
                        //      ... you could export the static method separately from the component itself!
                            // Pretending this is our static component function we want...
                            MyComponent.someFunction = someFunction;
                            
                            export default MyComponent;  // Instead of just exporting the component...
                            export { someFunction };     // Export the method too, separately...

                            // Then... in the consuming module, import both!
                            import MyComponent, { someFunction } from './MyComponent.js';
        3. References (Refs) Are NOT Passed Through
            - While yes, one of the main conventions of HOCs is to 'pass through all props to wrapped component',
              this does not work for `refs`. :(     
                * This is because `ref` is not really a `prop`...
                    + Like `key`, it is handled specially by React.
                    + If you add a `ref` to an element whose component is the result of a HOC, the `ref` refers to 
                      an instance of the outermost container component, NOT the actual wrapped component.
                        Example: (Actually from 'Reacts Forwarding Refs Guide')
                            // HOC: Logs Component `props` to console.
                            //  Passes all `props` through to component it wraps, so rendered output will be same.
                                function logProps(WrappedComponent) {
                                    class LogProps extends React.Component {
                                        componentDidUpdate(prevProps) {
                                            console.log('old props:', prevProps); 
                                            console.log('new props:', this.props);
                                        }
                                        render() { return <WrappedComponent {...this.props} '/'>; }
                                    }
                                    return LogProps;
                                }
                            // Usage Example: We can use this HOC to log all props that get passed to 
                            // our 'fancy button' component.
                                class FancyButton extends React.Component {
                                  focus() { `...` };  // And all the other code here.....
                                }
                            // Rather than exporting FancyButton, we export LogProps, 
                                export default logProps(FancyButton);  // And this will render a FancyButton....
                            // But this is where there will be problems with the current implementation.
                            //      - The `refs` intended for our `FancyButton` Component will actually be
                            //        attached to the LogProps Component.
                        Solution:
                            - Use the `React.forwardRef` API, introduced with React 16.3.
                            - Allows us to explicitily forward `refs` to the inner Component.
                            - `React.forwardRef` accepts a `render` function that recieves `props` & `ref`
                              parameters, and returns a React Node.
                                function logProps(Component) {
                                    class LogProps extends React.Component {
                                        componentDidUpdate(prevProps) {
                                          console.log('old props:', prevProps); console.log('new props:', this.props);
                                        }
                                        render() {
                                            const {forwardedRef, ...rest} = this.props;         // NEW

                                            // Assign the custom prop "forwardedRef" as a ref
                                            return <Component ref={forwardedRef} {...rest} //>;  // NEW
                                        }
                                    }

                                    // Note the second param "ref" provided by React.forwardRef.
                                    // We can pass it along to LogProps as a regular prop, e.g. "forwardedRef"
                                    // And it can then be attached to the Component.
                                    return React.forwardRef((props, ref) => {                   // NEW
                                      return <LogProps {...props} forwardedRef={ref} />; });    // NEW
                                }
                        Building onto that Solution:
                            - We can add additional details, like names for easier debugging & overall clarity.
                            - If you name the render function, DevTools will also include its name.
                                Example: 'ForwardRef(myFunction)'
                                    const WrappedComponent = React.forwardRef(
                                        function myFunction(props, ref) { return <LogProps {...props} forwardedRef={ref} />; }
                                    );
                            - You can even set the function’s displayName property to include 
                              the component you’re wrapping:
                                function logProps(Component) {
                                    class LogProps extends React.Component { ... }

                                    function forwardRef(props, ref) {
                                        return <LogProps {...props} forwardedRef={ref} />;
                                    }

                                    // More helpful Display Name: "ForwardRef(logProps(MyComponent))"
                                    const name = Component.displayName || Component.name;
                                    forwardRef.displayName = `logProps(${name})`;

                                    return React.forwardRef(forwardRef);
                                }
    + What it does NOT do
        - Modify the input component.
        - Use inheritance to copy it''s behavior.
    + Convention
        - Wrap the Display Name for easy debugging.
            * The container components created by HOCs show up in the React Developer Tools like any other component.
              To siplify debugging, choose a display name that communicates that it''s the result of a HOC.
                + Most common technique is to wrap the display name of the wrapped component.
                  If your Higher-Order Component is named 'withSubscription' & the wrapped component''s 
                  DN is 'CommentList', use the DN 'WithSubscription(CommentList)'
                    Example
                        function withSubscription(WrappedComponent) {
                            class WithSubscription extends React.Component {/* ... */}
                            WithSubscription.displayName = `WithSubscription(${getDisplayName(WrappedComponent)})`;
                            return WithSubscription;
                        }

                        function getDisplayName(WrappedComponent) {
                            return WrappedComponent.displayName || WrappedComponent.name || 'Component';
                        }
    + Transforms a component into another component.
    + An advanced technique in React for reusing component logic.
    + Not part of the React API; per se, they are a pattern that emerges from React''s compositional nature.
    + Use Containers as pasrt of their implementation; can think of them as 'parameterized container component' definitions.
    + Instead of mutating the data in the component, the HOC should use composition, by wrapping the input
      component in a container component.
        Mutating Example:
             function logProps(InputComponent) {
                  InputComponent.prototype.componentDidUpdate = function(prevProps) {
                    console.log('Current props: ', this.props); console.log('Previous props: ', prevProps);
                  };
                  // The fact that we're returning the original input is a hint that it has been mutated.
                  return InputComponent;
            }
            // EnhancedComponent will log whenever props are received
            const EnhancedComponent = logProps(InputComponent);
        Wrapper Example: (No Mutation - That''s the point here.)
            function logProps(WrappedComponent) {
              return class extends React.Component {
                componentDidUpdate(prevProps) {
                  console.log('Current props: ', this.props); console.log('Previous props: ', prevProps);
                }
                render() { // Wraps the input component in a container, without mutating it. Good!
                  return <WrappedComponent {...this.props} //>;
                }
              }
            }
    + Not all HOCs look the same: 
        - Sometimes they accept only a single argument, the wrapped componet.
            Example:
                const NavbarWithRouter = withRouter(Navbar);
        - Usually though, HOCs accept additional arguments. 
            Example: 
                // A config object is used to specify a components data dependencies.
                const CommentWithRelay = Relay.createContainer(Comment, config);
        - The most common signature for HOCs looks like this:
            Example: (React Redux''s `connect`)
                const ConnectedComment = connect(commentSelector, commentActions)(CommentList);
            Example: (If you break the above down into pieces, it''s easier to see what is going on.)
                // connect is a function that returns another function
                const enhance = connect(commentListSelector, commentListActions);
                // The returned function is a HOC, which returns a component that''s connected to Redux store
                const ConnectedComment = enhance(CommentList);
                // In other words, `connect` is a higher-order function that returns a higher-order component.
    + Single Argument HOCs
        - Have the signature `Component` => `Component`.
        - Functions whose output type is the same as its input type are really easy to compose together.
            * This same property also allows `connect` & other enhancer-style HOCs to be used as 
              decorators, an experimental JavaScript proposal.
                Example: 
                    // Instead of doing this...
                    const EnhancedComponent = withRouter(connect(commentSelector)(WrappedComponent))

                    // You can do this....  
                    // Use a function composition utility:
                    //      `compose(f, g, h)` is the same as `(...args) => f(g(h(...args)))`
                    const enhance = compose(
                        withRouter,              // These are both single-argument HOCs
                        connect(commentSelector) // These are both single-argument HOCs
                    )
                    const EnhancedComponent = enhance(WrappedComponent)
* Container Components
    + Part of a strategy of seperating responsibility between high-level & low-level concerns.
    + Containers manage things like subscriptions & state, and pass props to components that handle
      things like rendering UI.
* Functional Components
    + If your component''s sole purpose is to just display some data, then it is suggested to write
      the component as a functional component instead of a class component



/**********************************************************************************************************************
************************************************      EXCEPTIONS      *************************************************
***********************************************************************************************************************/
* 




/**********************************************************************************************************************
**************************************************      EXAMPLES      *************************************************
***********************************************************************************************************************/
* 

