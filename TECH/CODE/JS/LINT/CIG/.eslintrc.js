// 
// 
//      LOG ROCKET
// 
//          ~ 12 ESSENTIAL ESLINT RULES FOR REACT
//              = https://blog.logrocket.com/12-essential-eslint-rules-react/
{
    // ...other config stuff
    settings: {
        react: {
            version: 'detect'
        }
    },
    extends: [
        'eslint:recommended',
        'plugin:react/recommended',
        'plugin:react/jsx-runtime',
        'plugin:react-hooks/recommended',
        'plugin:react/prop-types', // Maybe. Making this up, lol.
    ]
}
module.exports = {
    parser: '@babel/eslint-parser',
    parserOptions: {
        sourceType: 'module',
        requireConfigFile: false
    },
    env: {
        browser: true,
        node: true,
        es6: true,
        jest: true
    },
    settings: {
        react: {
            version: 'detect'
        }
    },
    extends: [
        'eslint-config-semistandard',
        'plugin:react/recommended',
        'plugin:import/recommended'
    ],
    rules: {
        // allow scripts that can be run via command line to ship with console logs
        'no-console': ['off'],
        // can help with integrating 3rd party libs such as d3
        'react/no-find-dom-node': ['off'],
        // this rule can falsely report errors for jsx arrays that are passed where they are mapped with a key
        // with this rule off, please check the console for warnings related to this. there should be none
        'react/jsx-key': ['off']
    }
};

// 
// 
//  ESLINT DOC EXAMPLE
//          ~ https://eslint.org/docs/latest/use/configure/configuration-files
module.exports = {
    "extends": "eslint:all",
    "rules": {
        // override default options
        "comma-dangle": ["error", "always"],
        "indent": ["error", 2],
        "no-cond-assign": ["error", "always"],

        // disable now, but enable in the future
        "one-var": "off", // ["error", "never"]

        // disable
        "init-declarations": "off",
        "no-console": "off",
        "no-inline-comments": "off",
    },
    processors: {
        // This processor will be applied to `*.md` files automatically.
        // Also, people can use this processor as "plugin-id/.md" explicitly.
        ".md": {
            preprocess(text, filename) { /* ... */ },
            postprocess(messageLists, filename) { /* ... */ }
        }
    }
}
module.exports = {
    "extends": "eslint:recommended",
    "rules": {
        // enable additional rules
        "indent": ["error", 4],
        "linebreak-style": ["error", "unix"],
        "quotes": ["error", "double"],
        "semi": ["error", "always"],

        // override configuration set by extending "eslint:recommended"
        "no-empty": "warn",
        "no-cond-assign": ["error", "always"],

        // disable rules from base configurations
         "for-direction": "off",
    }
}
// 
// EsLint Custom Plugin
//          ~ https://eslint.org/docs/latest/extend/plugins#configs-in-plugins
module.exports = {  // eslint-plugin-myPlugin
    configs: {
        myConfig: {
            plugins: ["myPlugin"],
            env: ["browser"],
            rules: {
                semi: "error",
                "myPlugin/my-rule": "error",
                "eslint-plugin-myPlugin/another-rule": "error"
            }
        },
        myOtherConfig: {
            plugins: ["myPlugin"],
            env: ["node"],
            rules: {
                "myPlugin/my-rule": "off",
                "eslint-plugin-myPlugin/another-rule": "off",
                "eslint-plugin-myPlugin/yet-another-rule": "error"
            }
        }
    }
};
// 
//  ESLINT - https://eslint.org/docs/latest/extend/plugins#configs-in-plugins
//          ~ Processors & Plugins
module.exports = {
    processors: {
        "processor-name": {
            // takes text of the file and filename
            preprocess: function(text, filename) {
                // here, you can strip out any non-JS content
                // and split into multiple strings to lint

                return [ // return an array of code blocks to lint
                    { text: code1, filename: "0.js" },
                    { text: code2, filename: "1.js" },
                ];
            },

            // takes a Message[][] and filename
            postprocess: function(messages, filename) {
                // `messages` argument contains two-dimensional array of Message objects
                // where each top-level array item contains array of lint messages related
                // to the text that was returned in array from preprocess() method

                // you need to return a one-dimensional array of the messages you want to keep
                return [].concat(...messages);
            },

            supportsAutofix: true // (optional, defaults to false)
        }
    }
};
// 
//  ESLINT DOCS ~ https://eslint.org/docs/latest/extend/plugins#configs-in-plugins
//      ~ Environments in Plugins
module.exports = {
    environments: {
        jquery: {
            globals: {
                $: false
            }
        }
    }
};
//      ~ Rules in Plugins
module.exports = {
    rules: {
        "dollar-sign": {
            create: function (context) {
                // rule implementation ...
            }
        }
    }
};
