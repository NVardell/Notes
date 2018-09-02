# Pure Laziness
alias a="alias"
alias c="clear"
alias x="exit"
alias ag="a | grep "
alias ls="ls -lrta --color"
alias load="source ~/.bashrc"
alias mine="a | grep f624966"
alias bashit="start ~/.bashrc"
alias jump="ssh boks-jumphost.jpmchase.net"


# Navigation
alias ar='cd I:/GIT/; ls'
alias all="cd I:/GIT/ALL/"
alias god="cd I:/GIT/GOD/"
alias pub="cd I:/GIT/PUB/"
alias mav1="cd I:/GIT/MAV/dbps/"
alias mav2="cd I:/GIT/MAV2/dbps/"
alias notes="cd I:/GIT/NOTES/"
alias spaces='cd I:/Workspaces/'


# GIT  -  Basic Commands
alias gs="git status"
alias ga="git add "
alias stash="git stash"
alias glb="git branch"
alias grb="git branch -r"
alias pull="git pull"
alias push="git push"
alias gc="git commit -m "
alias gco="git checkout "
alias gnb="git checkout -b "
alias gm="git checkout master"
alias set=" --set-upstream origin"
alias gr="git remote -v"            # Git Remote URL
alias grv="git remote show origin"  # Git Remote URL- Verbose


# GIT  -  Misc Commands
alias gcf="git clean -f"      # Force delete untracked files
alias gca="git clean -fx"     # Force delete untracked & ignored files
alias gcw="git clean -ndx"    # What goes bye-bye? (n=what, d=directories, x=files(ignored))
alias rename="git branch -m "    # Rename local branch (-m old_branch new_branch OR just new_branch if changing current branch.)   
alias adios="git push origin :"  # Delete the old branch (:old_branch)
alias rename="push set "         # Push new branch, set local branch to track (new_branch)


# GIT -  Something went VERY wrong
alias abort="git merge --abort"
alias olord="git reset --hard"


# GIT  -  FAR From Basic Commands (Runs git command on every subdirectory in current directory)
alias sall="for dir in ./*; do( echo "\$dir" && cd "\$dir" && gs); done"
alias pall="for dir in ./*; do( echo "\$dir" && cd "\$dir" && pull); done"
alias pno="notes; ga --all; gc \"Pushing latest notes.\"; push;"
