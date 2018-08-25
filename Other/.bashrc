# Pure Laziness
alias a='alias'
alias c='clear'
alias load='source ~/.bashrc'
alias ls='ls -F --color --show-control-chars'
alias ll='ls -F --color --show-control-chars -lrta'


# Workspace Navigation
alias spaces="cd /c/Workspaces/"


# Repository Navigation
alias ar='cd /c/GIT'
alias set='cd /c/GIT/Settings'
alias val='cd /c/GIT/Validate'


# GIT - Basics
alias gs='git status '
alias stash="git stash"
alias pull='git pull'
alias push='git push'
alias glb='git branch'
alias grb='git branch -r'
alias ga='git add .'
alias gc='git commit -m '
alias gco='git checkout '
alias gnb='git checkout -b '
alias gmb='git checkout master'

# Auto-Start ssh-agent when Git Bash opens
env=~/.ssh/agent.env

agent_load_env () { test -f "$env" && . "$env" >| /dev/null ; }

agent_start () {
    (umask 077; ssh-agent >| "$env")
    . "$env" >| /dev/null ; }

agent_load_env

# agent_run_state: 0=agent running w/ key; 1=agent w/o key; 2= agent not running
agent_run_state=$(ssh-add -l >| /dev/null 2>&1; echo $?)

if [ ! "$SSH_AUTH_SOCK" ] || [ $agent_run_state = 2 ]; then
    agent_start
    ssh-add
elif [ "$SSH_AUTH_SOCK" ] && [ $agent_run_state = 1 ]; then
    ssh-add
fi

unset env



# ADDITIONAL ONES THAT I USE
# Pure Laziness
alias a="alias"
alias c="clear"
alias x="exit"
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
alias gr="git remote -v" # Display Repo URL
alias grv="git remote show origin" # (Git Remote Verbose)


# GIT  -  Misc Commands
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


# GIT  -  Checkout Specific Release Branch
alias 118="git checkout release/2018.03.11"
alias 218="git checkout release/2018.05.20"
alias jdr="git checkout release/2018.06.24"
alias 318="git checkout release/2018.08.26"
alias sdr="git checkout release/2018.09.23"
alias 418="git checkout release/2018.11.11"
alias ddr="git checkout release/2018.12.09"


# GIT  -  Pull Specific Release Branch
alias p118="git pull origin release/2018.03.11"
alias p218="git pull origin release/2018.05.20"
alias pjdr="git pull origin release/2018.06.24"
alias p318="git pull origin release/2018.08.26"
alias psdr="git pull origin release/2018.09.23"
alias p418="git pull origin release/2018.11.11"
alias pddr="git pull origin release/2018.12.09"
