##
##
##      Material UI SX Properties
##
##          Source
##              - https://mui.com/system/getting-started/the-sx-prop/
####
####
##
##
##  SPACING
##
    + Notes
        - The spacing properties margin, padding, and the longhand 
          properties multiply the values they receive by the 
          theme.spacing value.
            + Default Value = 8px
                - <Box sx={{ margin: 2 }} /> // equivalent to 
                                             //     margin: theme => theme.spacing(2)
        - The following aliases are available for the spacing properties:
            SHORTHAND          CSS PROPERTY
               m       margin
               mt      margin-top
               mr      margin-right
               mb      margin-bottom
               ml      margin-left
               mx      margin-left, margin-right
               my      margin-top, margin-bottom
               p       padding
               pt      padding-top
               pr      padding-right
               pb      padding-bottom
               pl      padding-left
               px      padding-left, padding-right
               py      padding-top, padding-bottom
        - The space utility converts shorthand margin & padding props to margin
          & padding CSS declarations. 
        - The props are named using the format {property}{sides}.
            + Where property is one of:
                SHORTHAND   CSS PROPERTY
                    m       margin
                    p       padding
            + Where sides is one of:
                SHORTHAND   CSS PROPERTY
                    t       margin-top or padding-top
                    b       margin-bottom or padding-bottom
                    l       margin-left or padding-left
                    r       margin-right or padding-right
                    x       both *-left and *-right
                    y       both *-top and *-bottom
                    blank   margin or padding on all 4 sides of the element
        - Additional Spacing Docs
            + https://mui.com/system/spacing/
##
##
##  HORIZONTAL CENTERING
##
    + Notes
        - The CSS flex and grid display properties are often used to align
          elements at the center. However, you can also use margin-left: auto;,
          margin-right: auto;, and a width for horizontally centering:
            + <Box sx={{ mx: 'auto', width: 200 }}>
##
##
##  CALLBACK VALUES
##
    + Notes
        - Each property in the sx prop can receive a function callback as a value. 
        - This is useful when you want to use the theme for calculating a value.
            + <Box sx={{ height: (theme) => theme.spacing(10) }} />
        - The sx prop can also receive a callback when you need to get theme values that are objects:
            + <Box
                  sx={ (theme) => ({
                      ...theme.typography.body,
                      color: theme.palette.primary.main,
                  })}
                />

