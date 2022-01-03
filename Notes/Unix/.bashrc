# Pure Laziness
alias c="clear"
alias x="exit"
alias ll="ls -lrta --color --time=birth --time-style='+%m/%d/%y %H:%M'"
alias ns="nslookup "
alias nsa="nslookup -type=any "
alias a="cat ~/.bashrc | grep alias | sort"      # View aliases w/ comments
alias dora="explorer ."                          # Open Windows File Explorer at current directory
alias w11t="w11Themes; dora"                     # Open Windows File Explorer for Windows Themes


# Static File Paths & Values
alias w11Themes="cd ~/AppData/Local/Microsoft/Windows/Themes"


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
alias ar='cd I:/Repo; ll'
alias io='cd I:/Repo/iOS; ll'
alias n="cd I:/Repo/Note; gs"
alias p="cd I:/Repo/Pieces; gs"
alias o="cd I:/Repo/Other; ll"
alias v="cd I:/Repo/Validate; gs"
alias r="cd I:/Repo/Royal; gs"
alias rw="cd I:/Repo/Royal/royal-web"
alias s='cd I:/Space; ll'



# GIT  -  Basic Commands
alias ga="git add "
alias gd="git diff "
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
alias gds="git diff --stat "                            # Add Commit Hash After to get stats on that commit
alias change="git commit --amend"                       # Git Amend to open Vim & Edit last Commit Message (ctrl+i=insert, esc=stop insert, :wq=save & quit.)
alias past="git for-each-ref --sort='-authordate'"      # Display branches with latest commit dates
alias stats="git log --stat --decorate --author='NV'"   # Display the stats for all of my commits
alias gtf="git ls-tree --name-only --full-tree -r HEAD" # Git Tracked Files - List all files being tracked by Git



# GIT - Checkout Branches
alias gco="git checkout "                               # Checkout ....
alias gcod="gco develop"                                # Checkout develop
alias gcom="gco master"                                 # Checkout master
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
alias grau="git remote add upstream "            # Set remote upstream for git to pull changes from (Forking - Original owners repo, not yours.)
                                                 # Ex. $ git remote add upstream https://github.com/ORIGINAL_OWNER/ORIGINAL_REPOSITORY.git


# GIT -  Something went VERY wrong
alias abort="git merge --abort"
alias olord="git reset --hard"



# GIT  -  FAR From Basic Commands (Runs git command on every subdirectory in current directory)
alias sah="for dir in ./*; do( echo "\$dir" && cd "\$dir" && gs); done"          # Status All Here
alias pah="for dir in ./*; do( echo "\$dir" && cd "\$dir" && pull); done"        # Pull All Here
alias sa="c; ar; for dir in ./*; do( echo "\$dir" && cd "\$dir" && gs); done"    # Status All Repositories in Repo Directory
alias pa="c; ar; for dir in ./*; do( echo "\$dir" && cd "\$dir" && pull); done"  # Pull All Repositories in Repo Directory
alias pc="gaa; gc \"Pushing latest changes.\"; push;"                            # Push All Changes in current Directory
alias pn="n; gaa; gc \"Pushing latest notes.\"; push;"                           # Push All changes Notes Repo
alias po="o; gaa; gc \"Pushing latest changes.\"; push;"                         # Push All changes in Other Repo
alias pp="p; gaa; gc \"Pushing latest pieces.\"; push;"                          # Push All changes in Pieces Repo
alias gpe="pc"                                                                   # Git Push Everything



# GIT  -  Repositories
alias ios="clone https://github.com/NVardell/iOS.git"
alias notes="clone https://github.com/NVardell/Notes.git"
alias other="clone https://github.com/NVardell/Other.git"
alias pieces="clone https://github.com/NVardell/Pieces.git"
alias royal="clone https://github.com/NVardell/Royal.git"
alias tut="clone https://github.com/NVardell/Tutorials.git"
alias validate="clone https://github.com/NVardell/Validate.git"



# NPM - Shorthand NPM Commands
alias npms="npm start"                                                                                  # Start npm (Runs 'start' prop in 'scripts' object, otherwise 'node server.js')
alias npmi="npm install "                                                                               # Install packages listed on command line
alias npmu="ncu -u  --packageFile package.json"                                                         # Local Project - Update package.json with new package versions (Requires npm-check-updates.)
alias npmo="npm outdated"                                                                               # Local Project - Display outdated packages
alias npmug="npmu -g"                                                                                   # Global - Update all packages
alias npmog="npmo -g --depth=0"                                                                         # Global - Display outdated packages
alias unpm="npm install npm@latest -g"                                                                  # Update npm to latest version
alias npmdc="npx depcheck"                                                                              # Check for unused/missing dependencies in package.json



# MISC - Project / Code Cleanup
alias mcp="mvn clean package"                                                                           # Maven - Clean Package
alias cw="cwf -print; cwd -print;"                                                                      # Clean What?!
alias cwf="find . -type f -name '*.iml' "                                                               # Clean What Files
alias cwd="find . -maxdepth 5 -type d \( -name "*.idea" -o -name "node_mod*" -o -name "target" \) "     # Clean What Directories
alias cpfd="cwf -prune -exec rm -rv {} \; cwd -prune -exec rm -rv {} \;"                                # Clean Project Files & Directories
alias clean="cpfd;"                                                                                     # Clean Project - Call above functions to find files & then delete them



# MISC - Fancy Shenanigans 😎
alias fcad="face; fade; face;"      # Find, Count, & Delete Everything
alias face="cmf; cef; ced;"         # Count ~ Find & Count Everything
alias fape="pmf; pef; ped;"         # Print ~ Find & Print Everything
alias fade="dmf; def; ded;"         # Delete ~ Find & Delete Everything



# MISC - Aliased Aliases
alias czf="echo -en '\tZip Files = '; find . -type f -name '*.zip'  | wc -l"                            # Count ~ Zip Files
alias cmf="echo -n 'Mac Files = '; find . -type f -name '._*'  | wc -l"                                 # Count ~ Mac Files
alias cef="echo -n 'Empty Files = '; find . -empty -type f | wc -l"                                     # Count ~ Empty Files
alias ced="echo -n 'Empty Directories = '; find . -empty -type d | wc -l"                               # Count ~ Empty Directories
alias pzf="echo -e '\nZip Files = '; find . -type f -name '*.zip' -print"                               # Print ~ Zip Files
alias pmf="echo -e '\nMac Files = '; find . -type f -name '._*' -print"                                 # Print ~ Mac Files
alias pef="echo -e '\nEmpty Files = '; find . -empty -type f -print"                                    # Print ~ Empty Files
alias ped="echo -e '\nEmpty Directories = '; find . -empty -type d -print"                              # Print ~ Empty Directories
alias dmf="find . -type f -name '._*' -delete"                                                          # Delete ~ Mac Files
alias def="find . -empty -type f -delete"                                                               # Delete ~ Empty Files
alias ded="find . -empty -type d -delete"                                                               # Delete ~ Empty Directories



# MISC - Random Shenanigans
alias t="tree"
alias wt="cmd //c tree //f"                                                                             # Windows built-in tree function (c: Command to run.  f: Display Files.)
alias cl="wc -l *"                                                                                      # Count lines for every file in current directory
alias sl="df -h ."                                                                                      # Space Left? (Show free space on current drive.)
alias ld="sl; du -c -Sk -d1 -t2M ./* | sort -hr"                                                        # List Directories & Sort by Size ()
alias ldc="(ld) | clip"                                                                                 # Clip List Directories (ld) Output
alias llc="ll | clip"                                                                                   # Clip Output from List Files Command (ll)
alias cfpl="cf push -f manifests/manifests-local.yml"                                                   # Cloud Foundry - Push Local
alias currentEncryptedPass="haventSetItYet :/"



# MISC - Function Wrappers
alias war="wars | clip"                                                                                 # Runs 'wars' function & adds output to clipboard
alias fact="facts | clip"                                                                               # Runs 'facts' function & adds output to clipboard
alias CfServer="https://CloudController.net"                                                            # CF - Server to login to for deploying latest app changes  
alias decryptPass="dp $1 $2"



# MISC - User Defined Functions
function p() { # Ping url once and print server ip address
    echo "Sending ping to ip = " $1;
    ping -n 1 $1 | grep 'Pinging' | awk -F"[][ .]" '{print $2 " = " $8"."$9"."$10"."$11"}'
}
function pc() { # Pretty curl - Prints response in pretty JSON format
    echo "Sending request to = " $1;
    curl $1 | json_pp
}
function pf() { # Print list of functions defined in this file
    sed -n /\(\)/p ~/.bashrc | awk '{print $2}'
}
function facts() {
   awk -F '>|<|/|"' '/profile/{printf $9 "," $11} /playerLevel/{printf ",_"$5} /cup/{printf ","$5} /_donation/{printf ","$5} /memberRoleInner/{printf ","$5} /lastSeenInner/{print ","$5}'  /i/Repo/Note/Notes/Unix/Temp.html
}
function wars() {
    awk -F '>|<|/| |"' '/profile/{printf$23} /battleI/{printf","$NF} /winI/{printf ","$NF} /cardsI/{print ","$NF}'  /i/Repo/Note/Notes/Unix/War.html
}
function snap() {
    DESKTOP_DIR=Desktop/TempPics;
    ACTIVE_DIR=~/$DESKTOP_DIR;
    SPOTLIGHT_DIR=~/AppData/Local/Packages/Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy/LocalState/Assets;

    [ -d ~/OneDrive ] && ACTIVE_DIR=~/OneDrive/$DESKTOP_DIR;

    echo "Creating Temp Directory on Desktop for new images."; mkdir $ACTIVE_DIR;
    echo "Copying files from Windows 10 Snapshot folder to Temp directory on Desktop."; find $SPOTLIGHT_DIR -size +300k -exec cp -nv {} $ACTIVE_DIR \;
    echo "Moving to new Temp directory."; cd $ACTIVE_DIR;
    echo "Renaming all files in new Temp directory."; renameFiles;
    echo "Opening new TempPics directory in File Explorer."; dora;
}
function renameFiles() {
    ls -v | cat -n | while read n f; do mv -n "$f" "Spotlight-$n.jpg"; done;
}
function rsf() {
    ls -v | cat -n | while read n f; do mv -n "$f" "Span-$n.jpg"; done;
}
function ep() {
    echo "Encrypting String =" $1 "& Secret =" $2;
    echo $1 | openssl enc -a -aes256 -nosalt -k $2; # -a=-base64, -k=Secret, -aes256=Encryption Cypher
}
#######################################
# Decrypts a string
#   @input: String to Decrypt
#   @input: Secret to Decrypt with
function dp() {
    echo $1 | openssl enc -d -a -aes128 -nosalt -k $2;
}
#######################################
# Cloud Foundry Login
#   @input: Secret
#   @Note: Couldn't get this to assign to a variable any other way. :/
function cfl() {
    DECRYPTED=$(decryptPass currentEncryptedPass $1);
    login CfServer UeserID $DECRYPTED;
}
function login() {
    echo "Logging into Cloud Foundry";
    cf login -a $1 -u $2 -p $3;
}




# MISC - Export User Functions
export -f p
export -f pc
export -f pf
export -f ep
export -f dp
export -f cfl
export -f facts
export -f wars
export -f snap
export -f rsf
