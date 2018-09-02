# Sublime Text 3

# Current Settings
Theme - Gravity One
Color Scheme - Flat Dark - My New

# Key Bindings
    // Bookmark Navigation
    { "keys": ["ctrl+alt+b"], "command": "toggle_bookmark" },
    { "keys": ["ctrl+shift+b"], "command": "prev_bookmark" },

    // Multi-Line Select
    { "keys": ["ctrl+alt+q"], "command": "select_lines", "args": {"forward": false} },
    { "keys": ["ctrl+alt+w"], "command": "select_lines", "args": {"forward": true} },
    
    // Put Cursor On Selected Lines (Default Binding)
    { "keys": ["ctrl+shift+l"], "command": "split_selection_into_lines" },

    // Delete Current Line
    { "keys": ["ctrl+d"], "command": "run_macro_file", "args": {"file": "res://Packages/Default/Delete Line.sublime-macro"} },

    // Remove Duplicates
    { "keys": ["ctrl+alt+u"], "command": "permute_lines", "args": {"operation": "unique"} },

    // Go to File Location in Project Sidebar
    { "keys": ["ctrl+alt+f"], "command": "reveal_in_side_bar"},

    // Keymap for Alignment Plugin - Auto Align
    { "keys": ["ctrl+alt+a"], "command": "alignment" },

    // Beautify SQL Code
    { "keys": ["ctrl+alt+s"], "command": "sql_beautifier" },
