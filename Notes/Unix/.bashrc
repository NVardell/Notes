# Pure Laziness
alias x="exit"
alias c="clear"
alias ls="ls -lart --color"
alias a="cat ~/.bashrc | grep alias | sort"      # View aliases w/ comments


# Manage Aliases & User Functions
alias ag="cat ~/.bashrc | grep -i "              # View aliases in bashrc file
alias bashit="start ~/.bashrc"                   # Open bashrc file for editing
alias load="source ~/.bashrc"                    # Load changes made to bashrc file
alias mine="a | grep NVardell"                   # Display my GIT repo aliases for cloning


# Navigation
alias s='cd I:/Spaces'
alias ar='cd I:/GIT; ll'
alias b="cd I:/GIT/Bits; gs"
alias i="cd I:/GIT/IntelliJ; gs"
alias n="cd I:/GIT/Notes; gs"
alias o="cd I:/GIT/Other; ll"
alias p="cd I:/GIT/Pieces; gs"
alias r="cd I:/GIT/Royally; gs"
alias v="cd I:/GIT/Validate; gs"


# GIT  -  Basic Commands
alias ga="git add "
alias gs="git status"
alias clone="git clone "
alias stash="git stash"
alias glb="git branch"                           # Git List of Local Branches
alias grb="git branch -r"                        # Git List of Remote Branches
alias pull="git pull"
alias push="git push"
alias gc="git commit -m "
alias gco="git checkout "
alias gnb="git checkout -b "                     # Git New Branch
alias gm="git checkout master"
alias gd="git checkout develop"
alias set=" --set-upstream origin"
alias gr="git remote -v"                         # Git Remote URL
alias grv="git remote show origin"               # Git Remote URL- Verbose


# GIT  -  Misc Commands
alias gcf="git clean -f"                         # Force delete untracked files
alias gca="git clean -fx"                        # Force delete untracked & ignored files
alias gcd="git clean -fdx"                       # Force delete untracked & ignored files & directories
alias gcw="git clean -ndx"                       # What goes bye-bye? (n=what, d=directories, x=files(ignored))
alias rename="push set "                         # Push new branch, set local branch to track (new_branch)
alias rename="git branch -m "                    # Rename local branch (-m old_branch new_branch OR just new_branch if changing current branch.)   
alias adios="git push origin :"                  # Delete the old branch (:old_branch)
alias grt="git rm --cached "                     # Untrack a file & remove it from repo
alias gut="git update-index --skip-worktree "    # Untrack a local file & leave in repo


# GIT -  Something went VERY wrong
alias abort="git merge --abort"
alias olord="git reset --hard"


# GIT  -  FAR From Basic Commands (Runs git command on every subdirectory in current directory)
alias sa="ar; for dir in ./*; do( echo "\$dir" && cd "\$dir" && gs); done"
alias pa="ar; for dir in ./*; do( echo "\$dir" && cd "\$dir" && pull); done"
alias pn="n; ga --all; gc \"Pushing latest notes.\"; push;"
alias pi="i; ga --all; gc \"Manually pushing latest settings.\"; push;"


# GIT  -  Repositories
alias bits="clone https://github.com/NVardell/Bits.git"
alias notes="clone https://github.com/NVardell/Notes.git"
alias pieces="clone https://github.com/NVardell/Pieces.git"
alias validate="clone https://github.com/NVardell/Validate.git"


# MISC - Random Shenanigans
alias t="tree"
alias wt="cmd //c tree //a //f"                  # Windows built-in tree function
alias war="wars | clip"                          # Runs 'wars' function & adds output to clipboard
alias fact="facts | clip"                        # Runs 'facts' function & adds output to clipboard


# MISC - User Defined Functions
function example() {
   echo "Hello, $1!" # $ hi Nate => Hello, Nate!
}
function facts() {
   awk -F '>|<|/|"' '/profile/{printf $9 "," $11} /playerLevel/{printf ",_"$5} /cup/{printf ","$5} /_donation/{printf ","$5} /memberRoleInner/{printf ","$5} /lastSeenInner/{print ","$5}'  /i/GIT/Notes/Notes/Unix/Temp.html
}
function wars() {
    awk -F '>|<|/| |"' '/profile/{printf$23} /battleI/{printf","$NF} /winI/{printf ","$NF} /cardsI/{print ","$NF}'  /i/GIT/Notes/Notes/Unix/War.html
}


# MISC - Export User Functions
export -f example
export -f facts
export -f wars