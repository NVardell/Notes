/**********************************************************************************************************************
***************************************************      LINKS      ***************************************************
***********************************************************************************************************************/
• 


/**********************************************************************************************************************
***************************************************      NOTES      ***************************************************
***********************************************************************************************************************/
• 


/**********************************************************************************************************************
**************************************************      EXAMPLES      *************************************************
***********************************************************************************************************************/
• 


/**********************************************************************************************************************
**************************************************      EXAMPLES      *************************************************
***********************************************      BEFORE & AFTER      **********************************************
***********************************************************************************************************************/
• 'EXAMPLE #1' // https://medium.com/@preethikasireddy/why-use-static-types-in-javascript-part-2-part-3-be699ee7be60#.9ywoivqaz
    - BEFORE
            function calculatePayoutDate(quote, amount, paymentMethod) {
                let payoutDate;
                    /* business logic */
                return payoutDate;
            }
    - AFTER
            function calculatePayoutDate(
                    quote: boolean,
                    amount: number,
                    paymentMethod: string): Date {
                let payoutDate;
                /* business logic */
                return payoutDate;
            }
• 'EXAMPLE #2' ~ https://medium.com/@preethikasireddy/why-use-static-types-in-javascript-part-2-part-3-be699ee7be60#.9ywoivqaz
    - BEFORE
            const calculateAreas = (radii) => {
                // Handle undefined or null input
                if (!radii) { throw new Error("Argument is missing"); }

                // Handle non-array inputs
                if (!Array.isArray(radii)) {
                    throw new Error("Argument must be an array");
                }

                var areas = [];

                for (var i = 0; i < radii.length; i++) {
                    if (typeof radii[i] !== "number") {
                        throw new Error("Array must contain valid numbers only");
                    } else {
                        areas[i] = 3.14 * (radii[i] * radii[i]);
                    }
                }

                return areas;
            };
    - AFTER
            //  Forcing Static Types allows us to remove 65% of the function's code!
            const calculateAreas = (radii: Array < number > ): Array < number > => {
                var areas = [];
                for (var i = 0; i < radii.length; i++) {
                    areas[i] = 3.14 * (radii[i] * radii[i]);
                }

                return areas;
            };
• 'EXAMPLE #XX' ~ 
    - BEFORE
    - AFTER
• 'EXAMPLE #XX' ~ 
    - BEFORE
    - AFTER
• 'EXAMPLE #XX' ~ 
    - BEFORE
    - AFTER