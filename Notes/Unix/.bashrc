# Pure Laziness
alias c="clear"
alias x="exit"
alias ll="ls -lart --color"
alias a="cat ~/.bashrc | grep alias | sort"                             # View aliases w/ comments
alias dora="explorer ."                                                 # Open Windows File Explorer at current directory


# Manage Aliases & User Functions
alias ag="cat ~/.bashrc | grep -i "              # View aliases in bashrc file
alias npmc="start ~/.npmrc"                      # Open npmrc file for editing (Can also run 'npm config edit')
alias bashit="start ~/.bashrc"                   # Open bashrc file for editing
alias load="source ~/.bashrc"                    # Load changes made to bashrc file
alias mine="a | grep NVardell"                   # Display my GIT repo aliases for cloning
alias unload="unalias -a"                        # Remove all bash aliases


# Navigation
alias i='cd I:/; ll'
alias ar='cd I:/GIT; ll'
alias s='cd I:/Spaces; ll'
alias b="cd I:/GIT/Bits; gs"
alias i="cd I:/GIT/IntelliJ; gs"
alias n="cd I:/GIT/Notes; gs"
alias o="cd I:/GIT/Other; ll"
alias p="cd I:/GIT/Pieces; gs"
alias r="cd I:/GIT/Royally; gs"
alias v="cd I:/GIT/Validate; gs"
alias rw="cd I:/GIT/Royally/royal-web"
alias orw="cd I:/GIT/Other/React/app"


# GIT  -  Basic Commands
alias ga="git add "
alias gs="git status"
alias pull="git pull"
alias push="git push"
alias stash="git stash"
alias clone="git clone "
alias gc="git commit -m "
alias gaa="git add --all"
alias gr="git remote -v"                                # Git Remote URL
alias grv="git remote show origin"                      # Git Remote URL- Verbose
alias set="git remote set-url origin "                  # Update repository url to a new uri for pushing changes if/when the repo is renamed/moved/deleted
alias difflc="git diff --stat "                         # Add Commit Hash After to get stats on that commit
alias change="git commit --amend"                       # Git Amend to open Vim & Edit last Commit Message (ctrl+i=insert, esc=stop insert, :wq=save & quit.)
alias past="git for-each-ref --sort='-authordate'"      # Display branches with latest commit dates
alias stats="git log --stat --decorate --author='NV'"   # Display the stats for all of my commits


# GIT - Checkout Branches
alias gd="git checkout develop"                         # Checkout develop
alias gm="git checkout master"                          # Checkout master
alias gco="stash; git checkout "                        # Checkout ....
alias gnb="stash; git checkout -b "                     # Checkout new branch
alias dlb="git branch -D "                              # Delete local branch
alias drb="git push origin --delete "                   # Delete remote branch
alias glb="git branch"                                  # Git List of Local Branches
alias grb="git branch -r"                               # Git List of Remote Branches


# GIT  -  Misc Commands
alias gcf="git clean -f"                         # Force delete untracked files
alias gci="git clean -fX"                              # Remove ignored files (Like other peoples IntelliJ files.... Gr.)
alias gciw="git clean -nX"                             # Remove what ignored files?
alias gcd="git clean -fdx"                       # Force delete untracked & ignored files & directories
alias gcw="git clean -ndx"                              # What goes bye-bye? (n=what, d=directories, x=files(ignored))
alias grt="git rm --cached "                     # Untrack a file & remove it from repo
alias rename="git branch -m "                    # Rename local branch (-m old_branch new_branch OR just new_branch if changing current branch.)   
alias adios="git push origin :"                  # Delete the old branch (:old_branch)
alias gut="git update-index --skip-worktree "    # Untrack a local file & leave in repo


# GIT -  Something went VERY wrong
alias abort="git merge --abort"
alias olord="git reset --hard"


# GIT  -  FAR From Basic Commands (Runs git command on every subdirectory in current directory)
alias gpe="gs; gaa; gc \"Pushing latest changes.\"; push;"  # Push everything
alias sa="ar; for dir in ./*; do( echo "\$dir" && cd "\$dir" && gs); done"
alias pa="ar; for dir in ./*; do( echo "\$dir" && cd "\$dir" && pull); done"
alias pc="ga --all; gc \"Pushing all latest changes.\"; push;"
alias pn="n; ga --all; gc \"Pushing latest notes.\"; push;"
alias po="o; ga --all; gc \"Pushing latest changes.\"; push;"
alias pp="p; ga --all; gc \"Pushing latest pieces.\"; push;"


# GIT  -  Repositories
alias bits="clone https://github.com/NVardell/Bits.git"
alias notes="clone https://github.com/NVardell/Notes.git"
alias other="clone https://github.com/NVardell/Other.git"
alias pieces="clone https://github.com/NVardell/Pieces.git"
alias royal="clone https://github.com/NVardell/Royally.git"
alias validate="clone https://github.com/NVardell/Validate.git"


# NPM - Shorthand NPM Commands
alias npms="npm start"                           # Start npm (Runs 'start' prop in 'scripts' object, otherwise 'node server.js')
alias npmi="npm install "                        # Install packages listed on command line
alias npmu="npm update"                          # Local Project - Update all packages
alias npmo="npm outdated"                        # Local Project - Display outdated packages
alias npmug="npmu -g"                            # Global - Update all packages
alias npmog="npmo -g --depth=0"                  # Global - Display outdated packages
alias unpm="npm install npm@latest -g"           # Update npm to latest version


# MISC - Random Shenanigans
alias t="tree"
alias wt="cmd //c tree //a //f"                  # Windows built-in tree function
alias war="wars | clip"                          # Runs 'wars' function & adds output to clipboard
alias fact="facts | clip"                        # Runs 'facts' function & adds output to clipboard
alias cl="wc -l *"                               # Count lines for every file in current directory


# MISC - User Defined Functions
function p() { # Ping url once and print server ip address
    echo "Sending ping to ip = " $1;
    ping -n 1 $1 | grep 'Pinging' | awk -F"[][ .]" '{print $2 " = " $8"."$9"."$10"."$11"}'
}
function pc() { # Pretty curl - Prints response in pretty JSON format
    echo "Sending request to = " $1;
    curl $1 | json_pp
}
function facts() {
   awk -F '>|<|/|"' '/profile/{printf $9 "," $11} /playerLevel/{printf ",_"$5} /cup/{printf ","$5} /_donation/{printf ","$5} /memberRoleInner/{printf ","$5} /lastSeenInner/{print ","$5}'  /i/GIT/Notes/Notes/Unix/Temp.html
}
function wars() {
    awk -F '>|<|/| |"' '/profile/{printf$23} /battleI/{printf","$NF} /winI/{printf ","$NF} /cardsI/{print ","$NF}'  /i/GIT/Notes/Notes/Unix/War.html
}


# MISC - Export User Functions
export -f p
export -f pc
export -f facts
export -f wars