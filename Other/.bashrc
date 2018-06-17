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