/**********************************************************************************************************************
***************************************************      LINKS      ***************************************************
***********************************************************************************************************************/
* EsLint Official
    - [Config Docs]
        + [NEW] Configuration Files 
            ~ https://eslint.org/docs/latest/use/configure/configuration-files-new
    - [Blog Post]
        + [NEW] Linting Config File
            ~ https://eslint.org/blog/2022/08/new-config-system-part-2/


/**********************************************************************************************************************
***************************************************      NOTES      ***************************************************
***********************************************************************************************************************/
* RULE SEVERITIES
    - There are three possible severities you can specify for a rule
        [0] "off"   - the rule should be turned off completely. 
        [1] "warn"  - the reported problem should be treated as a warning. 
            - When using the ESLint CLI, warnings are reported but do not change the exit code. 
            - If only warnings are reported, the exit code is 0.
        [2] "error" - the reported problem should be treated as an error. 
            - When using the ESLint CLI, errors cause the CLI to exit with a nonzero code.
* CONFIGURATION
    - [NEW] eslint.config.js (aka flat config)
        + Defaults in this new Flat Config setup
            - ecmaVersion: "latest" 
            - sourceType: "module" for all .js and .mjs files
            - ESLint searches for .js, .mjs, and .cjs files
                + With eslintrc, ESLint only ever searched for .js files 
                  when you passed a directory name on the command line, 
                  and you would need to use the --ext flag to define more. 
                + With flat config, all three of the most common JavaScript 
                  filename extensions are automatically searched.
        + The new (Posted Aug./2022) config file: eslint.config.js
            - An eslint.config.js file exports an array of config objects.
        + In contrast to eslintrc, which allowed multiple config files in multiple locations,
          multiple config file formats, and even package.json-based configs, flat config has just 
          one location for all of your project’s configuration: the eslint.config.js file. 
        + By limiting configuration to one location and one format, we can take advantage of the 
          JavaScript runtime’s loading mechanism directly and avoid the need for custom parsing
          of config files.
        + When the ESLint CLI is used, it searches for eslint.config.js from the current working 
          directory and if not found will continue the search up the directory’s ancestors until 
          the file is found or the root directory is hit. 
        + That one eslint.config.js file contains all of the configuration information for that
          run of ESLint so it dramatically reduces the disk access required as compared to eslintrc, 
          which had to check each directory from the linted file location up to the root for 
          any additional config files.
        + Additionally, using a JavaScript file allowed us to rely on users to load additional 
          information that their config file might need. 
              - Instead of extends & plugins loading things by name, you can now just use 
                import and require as necessary to bring in those additional resources. 
                  + Here’s an example of what an eslint.config.js file looks like:
                        //
                        //    Example eslint.config.js
                        //
                        export default [
                            {
                                files: ["**/*.js"],
                                rules: {
                                    "semi": "error",
                                    "no-unused-vars": "error"
                                }  
                            }
                        ];
        + PLUGINS
            - Plugins are used to share rules, processors, configurations, parsers, and more across ESLint projects.
            - On the surface, using a plugin in flat config looks very similar to using a plugin in eslintrc. 
                + The big difference is...
                    ~ eslintrc uses strings
                    ~ flat configs uses objects
                + Example
                    ~ You can import the plugin directly and place it into the `plugins` key.
                        - The config uses the plugin by importing it as a local variable
                        - After added to plugins, rules inside the plugin are referenced using that namespace
                            + Namespace in example below is `jsdoc`
                            // 
                            //    Plugin Example
                            //
                            import jsdoc from "eslint-plugin-jsdoc";

                            export default [
                                {
                                    files: ["**/*.js"],
                                    plugins: {
                                        jsdoc
                                    }
                                    rules: {
                                        "jsdoc/require-description": "error",
                                        "jsdoc/check-values": "error"
                                    }  
                                }
                            ];
            - Using PlugIn Rules (https://eslint.org/docs/latest/use/configure/configuration-files-new)
                + To do this, specify the plugin in a config obj using the 'plugins' key.
                    - 'plugin' key = {`Object`}
                        + key prop name = name of the plugin
                        + key prop value = plugin object itself
                                //
                                //   Using Plugin Rules
                                //
                                import jsdoc from "eslint-plugin-jsdoc";

                                export default [
                                    {
                                        files: ["**/*.js"],
                                        plugins: {
                                            // jsdoc: jsdoc  // Because the name of the plugin & plugin object are both 'jsdoc', 
                                            jsdoc,           // you can shorten the configuration to simply, 'jsdoc'
                                            jsd: jsdoc
                                                             //  They don't HAVE to be the same....
                                                             //      If ya really wanted, you could do something like...
                                                             //             plugins: { jsd: jsdoc },
                                                             //             rules:  { 'jsd/check-values' 'error' },
                                        },
                                        rules: {
                                            "jsdoc/require-description": "error",
                                            "jsdoc/check-values": "error"
                                            // OR
                                            "jsd/require-description": "error",
                                            "jsd/check-values": "error"
                                            // In this configuration, the JSDoc plugin is defined to have the name jsdoc. 
                                            // The prefix 'jsdoc/' in each rule name indicates that the rule is coming from 
                                            // the plugin with that name rather than from ESLint itself.
                                        }
                                    }
                                ];
        + EXTEND PREDEFINED CONFIGS
            - ESLint has two predefined configs
                1. eslint:recommended 
                    - enables the rules that ESLint recommends everyone use to avoid potential errors
                2. eslint:all
                    - enables all of the rules shipped with ESLint
            - To include these predefined configs, you can insert the string values into the exported array,
              then make any modifications to other properties in subsequent configuration objects.  Example below.
                    // 
                    //    Predefined Configs
                    //          Here, the eslint:recommended predefined configuration is applied first
                    //          then another configuration object adds the desired configuration for semi.
                    //
                    export default [
                        "eslint:recommended",
                        {
                            rules: {
                                semi: ["warn", "always"]
                            }
                        }
                    ];
            - Using configurations included in plugins
                + Simply, add the config directly to the eslint.config.js config array.
                    - Often this is done for a plugins 'recommended' configuration.
                        //
                        //   Use Plugins Recommended Config
                        //
                        import jsdoc from "eslint-plugin-jsdoc";
                        export default [
                            // configuration included in plugin
                            jsdoc.configs.recommended,
                            // other configuration objects...
                            {
                                files: ["**/*.js"],
                                plugins: {
                                    jsdoc: jsdoc
                                },
                                rules: {
                                    "jsdoc/require-description": "warn",
                                }
                            }
                        ];
        + IGNORING THINGS
            - By default
                + EsLint LINTS '*.js' files (unless speccified in configs 'files' prop.)
                + EsLint IGNORES ["**/node_modules/**", ".git/**"] (AND appends additional patterns if 'ignores' prop is defined)
                    //
                    //    Ignore Example 
                    //       1.      Ignore Docker directory
                    //       2 & 3.  Ignore files ending with '.config.js' EXCEPT 'eslint.config.js'
                    //
                    export default [
                        {
                            ignores: [".config/*", "**/*.config.js", "!**/eslint.config.js"]
                        }
                    ];
    - [OLD] .eslintrc
        + Checks each directory from the linted file location up to the root for 
          any additional config files, causing drastic performance issues.
        + 
* PLUGINS
    - ESLint plugins allow you to add custom rules according to the needs of your project. 
    - Plugins are published as npm modules with names in the format of eslint-plugin-<plugin-name>.
    - To use the plugin, you need to first install it via npm, and then you can add it to your eslintrc.
        //  Example .eslintrc
        {
            "plugins": ["my-awesome-plugin"] // The "eslint-plugin" suffix can be ommited
        }
    - Adding a plugin does not mean that all the rules for the plugins will be applied automatically, 
      you still need to individually apply each and every rule you would want to use with that plugin
        "rules": {
            "eqeqeq": "off",
            "curly": "error",
        }
    - Similar to plugins shareable configs are also published with names in the 
      format of eslint-config-<config-name>.



