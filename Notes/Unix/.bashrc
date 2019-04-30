# Pure Laziness
alias a="alias"
alias c="clear"
alias x="exit"
alias ag="a | grep "
alias ls="ls -lrta --color"
alias load="source ~/.bashrc"
alias mine="a | grep NVardell"
alias bashit="start ~/.bashrc"


# Navigation
alias ar='cd I:/GIT; ll'
alias all="cd I:/GIT/All"
alias note="cd I:/GIT/Notes"
alias piece="cd I:/GIT/Pieces"
alias spaces='cd I:/Spaces'


# GIT  -  Basic Commands
alias ga="git add "
alias gs="git status"
alias clone="git clone "
alias stash="git stash"
alias glb="git branch"              # Git List of Local Branches
alias grb="git branch -r"           # Git List of Remote Branches
alias pull="git pull"
alias push="git push"
alias gc="git commit -m "
alias gco="git checkout "
alias gnb="git checkout -b "        # Git New Branch
alias gm="git checkout master"
alias set=" --set-upstream origin"
alias gr="git remote -v"            # Git Remote URL
alias grv="git remote show origin"  # Git Remote URL- Verbose


# GIT  -  Misc Commands
alias gcf="git clean -f"         # Force delete untracked files
alias gca="git clean -fx"        # Force delete untracked & ignored files
alias gcw="git clean -ndx"       # What goes bye-bye? (n=what, d=directories, x=files(ignored))
alias rename="git branch -m "    # Rename local branch (-m old_branch new_branch OR just new_branch if changing current branch.)   
alias adios="git push origin :"  # Delete the old branch (:old_branch)
alias rename="push set "         # Push new branch, set local branch to track (new_branch)


# GIT -  Something went VERY wrong
alias abort="git merge --abort"
alias olord="git reset --hard"


# GIT  -  FAR From Basic Commands (Runs git command on every subdirectory in current directory)
alias sall="for dir in ./*; do( echo "\$dir" && cd "\$dir" && gs); done"
alias pall="for dir in ./*; do( echo "\$dir" && cd "\$dir" && pull); done"
alias pno="note; ga --all; gc \"Pushing latest notes.\"; push;"


# GIT  -  Repositories
alias bits="clone https://github.com/NVardell/Bits.git"
alias notes="clone https://github.com/NVardell/Notes.git"
alias pieces="clone https://github.com/NVardell/Pieces.git"
alias validate="clone https://github.com/NVardell/Validate.git"


# MISC - Random Shenanigans
alias t="tree"
alias wt="cmd //c tree //a //f"


# MISC - User Defined Functions
function facts() {
   awk -F '>|<|/|"' '/profile/{printf $9 "," $11} /playerLevel/{printf ",_"$5} /cup/{printf ","$5} /_donation/{printf ","$5} /memberRoleInner/{printf ","$5} /lastSeenInner/{print ","$5}'  /i/GIT/Notes/Notes/Unix/Temp.html
}
function hi() {
   echo "Hello, $1!" # $ hi Nate => Hello, Nate!
}


# MISC - Export User Functions
export -f facts
export -f hi