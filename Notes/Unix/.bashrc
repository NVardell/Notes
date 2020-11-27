# Pure Laziness
alias c="clear"
alias x="exit"
alias ll="ls -lart --color"
alias ns="nslookup "
alias nsa="nslookup -type=any "
alias a="cat ~/.bashrc | grep alias | sort"      # View aliases w/ comments
alias dora="explorer ."                          # Open Windows File Explorer at current directory


# Manage Aliases & User Functions
alias ag="cat ~/.bashrc | grep -i "              # View aliases in bashrc file
alias npmc="start ~/.npmrc"                      # Open npmrc file for editing (Can also run 'npm config edit')
alias bashit="start ~/.bashrc"                   # Open bashrc file for editing
alias load="source ~/.bashrc"                    # Load changes made to bashrc file
alias unload="unalias -a"                        # Remove all bash aliases
alias reload="unload; load;"                     # Remove all bash aliases
alias mine="a | grep -n NVardell"                # Display my GIT repo aliases for cloning


# Navigation
alias i='cd I:/; ll'
alias ar='cd I:/Repos; ll'
alias s='cd I:/Spaces; ll'
alias b="cd I:/Repos/Bits; gs"
alias i="cd I:/Repos/IntelliJ; gs"
alias n="cd I:/Repos/Notes; gs"
alias o="cd I:/Repos/Other; ll"
alias p="cd I:/Repos/Pieces; gs"
alias r="cd I:/Repos/Royally; gs"
alias v="cd I:/Repos/Validate; gs"
alias rw="cd I:/Repos/Royally/royal-web"
alias orw="cd I:/Repos/Other/React/app"


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
alias gco="git checkout "                               # Checkout ....
alias gnb="git checkout -b "                            # Checkout new branch
alias dlb="git branch -D "                              # Delete local branch
alias drb="git push origin --delete "                   # Delete remote branch
alias glb="git branch"                                  # Git List of Local Branches
alias grb="git branch -r"                               # Git List of Remote Branches


# GIT  -  Misc Commands
alias gcf="git clean -f"                         # Force delete untracked files
alias gci="git clean -fX"                        # Remove ignored files (Like other peoples IntelliJ files.... Gr.)
alias gciw="git clean -nX"                       # Remove what ignored files?
alias gcd="git clean -fdx"                       # Force delete untracked & ignored files & directories
alias gcw="git clean -ndx"                       # What goes bye-bye? (n=what, d=directories, x=files(ignored))
alias grt="git rm --cached "                     # Untrack a file & remove it from repo
alias rename="git branch -m "                    # Rename local branch (-m old_branch new_branch OR just new_branch if changing current branch.)   
alias adios="git push origin :"                  # Delete the old branch (:old_branch)
alias gut="git update-index --skip-worktree "    # Untrack a local file & leave in repo


# GIT -  Something went VERY wrong
alias abort="git merge --abort"
alias olord="git reset --hard"


# GIT  -  FAR From Basic Commands (Runs git command on every subdirectory in current directory)
alias sah="for dir in ./*; do( echo "\$dir" && cd "\$dir" && gs); done"       # Status All Here
alias pah="for dir in ./*; do( echo "\$dir" && cd "\$dir" && pull); done"     # Pull All Here
alias sa="c; ar; for dir in ./*; do( echo "\$dir" && cd "\$dir" && gs); done"    # Status All Repositories in Repo Directory
alias pa="c; ar; for dir in ./*; do( echo "\$dir" && cd "\$dir" && pull); done"  # Pull All Repositories in Repo Directory
alias pc="gaa; gc \"Pushing latest changes.\"; push;"                         # Push All Changes in current Directory
alias pn="n; gaa; gc \"Pushing latest notes.\"; push;"                        # Push All changes Notes Repo
alias po="o; gaa; gc \"Pushing latest changes.\"; push;"                      # Push All changes in Other Repo
alias pp="p; gaa; gc \"Pushing latest pieces.\"; push;"                       # Push All changes in Pieces Repo
alias gpe="pc"                                                                # Git Push Everything


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
alias npmu="ncu -u  --packageFile package.json"  # Local Project - Update package.json with new package versions (Requires npm-check-updates.)
alias npmo="npm outdated"                        # Local Project - Display outdated packages
alias npmug="npmu -g"                            # Global - Update all packages
alias npmog="npmo -g --depth=0"                  # Global - Display outdated packages
alias unpm="npm install npm@latest -g"           # Update npm to latest version
alias npmdc="npx depcheck"                       # Check for unused/missing dependencies in package.json



# MISC - Random Shenanigans
alias t="tree"
alias mcp="mvn clean package"                                      # Maven - Clean Package
alias wt="cmd //c tree //a //f"                                    # Windows built-in tree function
alias war="wars | clip"                                            # Runs 'wars' function & adds output to clipboard
alias fact="facts | clip"                                          # Runs 'facts' function & adds output to clipboard
alias cl="wc -l *"                                                 # Count lines for every file in current directory
alias cfpl="cf push -f manifests/manifests-local.yml"              # Cloud Foundry - Push Local
alias car="find . -type d -name 'target' -print -prune -exec rm -r {} +"  # Find and remove all directories named 'target' (Clean & Remove? Don't remember, lol.)
alias clean="c; cpd; cpf;"
alias cw="c; cwpd; cwpf;" # Clean What Project Files & Directories?!?
alias cpd="find . -type d \( -name "*.idea" -o -name "node_mod*" -o -name "target" \) -print -prune -exec rm -r {} +"
alias cwpd="find . -type d \( -name "*.idea" -o -name "node_mod*" -o -name "target" \) -print"
alias cpf="find . -type f -name '*.iml' -print -prune -exec rm -r {} +"
alias cwpf="find . -type f -name '*.iml' -print"
alias currentEncryptedPass="haventSetItYet :/"
alias decryptPass="dp $1 $2"
alias cb="diff ...."    # TODO - Compare repo bash to local bash.


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
   awk -F '>|<|/|"' '/profile/{printf $9 "," $11} /playerLevel/{printf ",_"$5} /cup/{printf ","$5} /_donation/{printf ","$5} /memberRoleInner/{printf ","$5} /lastSeenInner/{print ","$5}'  /i/Repos/Notes/Notes/Unix/Temp.html
}
function wars() {
    awk -F '>|<|/| |"' '/profile/{printf$23} /battleI/{printf","$NF} /winI/{printf ","$NF} /cardsI/{print ","$NF}'  /i/Repos/Notes/Notes/Unix/War.html
}
function snap() {
    DESKTOP_DIR=Desktop/TempPics;
    ACTIVE_DIR=~/$DESKTOP_DIR;
    SPOTLIGHT_DIR=~/AppData/Local/Packages/Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy/LocalState/Assets;

    [ -d ~/OneDrive ] && ACTIVE_DIR=~/OneDrive/$DESKTOP_DIR;

    echo "Creating Temp Directory on Desktop for new images."; mkdir $ACTIVE_DIR;
    echo "Copying files from Windows 10 Snapshot folder to Temp directory on Desktop."; find $SPOTLIGHT_DIR -size +400k -exec cp -nv {} $ACTIVE_DIR \;
    echo "Moving to new Temp directory."; cd $ACTIVE_DIR;
    echo "Renaming all files in new Temp directory."; renameFiles;
    echo "Opening new TempPics directory in File Explorer."; dora;
}
function renameFiles() {
    ls -v | cat -n | while read n f; do mv -n "$f" "Spotlight-$n.png"; done;
}

function ep() {
    echo "Encrypting String =" $1 "& Secret =" $2;
    echo $1 | openssl enc -a -aes256 -nosalt -k $2; # -a=-base64, -k=Secret, -aes256=Encryption Cypher
}
# Decrypts a string
#   @input: String to Decrypt
#   @input: Secret to Decrypt with
function dp() {
    echo $1 | openssl enc -d -a -aes128 -nosalt -k $2;
}
# Cloud Foundry Login
#   @input: Secret
function cfl() {
    #  Couldn't get this to assign to a variable any other way. :/
    DECRYPTED=$(decryptPass currentEncryptedPass $1);
    login UeserID $DECRYPTED;
}
function login() {
    echo "Logging into Cloud Foundry";
    cf login -a https://CloudController.net -u $1 -p $2;
}



# MISC - Export User Functions
export -f p
export -f pc
export -f ep
export -f dp
export -f cfl
export -f facts
export -f wars
export -f snap