# Static Variables
export YARN_FORCE_WINPTY=true
export NODE_TLS_REJECT_UNAUTHORIZED=1
export pathToTree="/c/NATE/Apps/Git/bin/tree"



##
##  NEW FOR HOME ~ npm init @eslint/config
##
##
##      GIT CONFIG
##          1. choco install delta
##          2. Update .gitconfig w/ Delta configurations
##
##              Example #1
                    # [core]
                    #     pager = delta

                    # [interactive]
                    #     diffFilter = delta --color-only --features=interactive

                    # [delta]
                    #     features = decorations

                    # [delta "interactive"]
                    #     keep-plus-minus-markers = false

                    # [delta "decorations"]
                    #     commit-decoration-style = blue ol
                    #     commit-style = raw
                    #     file-style = omit
                    #     hunk-header-decoration-style = blue box
                    #     hunk-header-file-style = red
                    #     hunk-header-line-number-style = "#067a00"
                    #     hunk-header-style = file line-number syntax
##
##              Example #2
                    # [delta]
                    #     features = side-by-side line-numbers decorations
                    #     syntax-theme = Dracula
                    #     plus-style = syntax "#003800"
                    #     minus-style = syntax "#3f0001"

                    # [delta "decorations"]
                    #     commit-decoration-style = bold yellow box ul
                    #     file-style = bold yellow ul
                    #     file-decoration-style = none
                    #     hunk-header-decoration-style = cyan box ul

                    # [delta "line-numbers"]
                    #     line-numbers-left-style = cyan
                    #     line-numbers-right-style = cyan
                    #     line-numbers-minus-style = 124
                    #     line-numbers-plus-style = 28
##
##              Example #3
##                  ~ https://dandavison.github.io/delta/features-named-groups-of-settings.html
                    # [delta]
                    #     features = unobtrusive-line-numbers decorations
                    #     whitespace-error-style = 22 reverse

                    # [delta "unobtrusive-line-numbers"]
                    #     line-numbers = true
                    #     line-numbers-minus-style = "#444444"
                    #     line-numbers-zero-style = "#444444"
                    #     line-numbers-plus-style = "#444444"
                    #     line-numbers-left-format = "{nm:>4}┊"
                    #     line-numbers-right-format = "{np:>4}│"
                    #     line-numbers-left-style = blue
                    #     line-numbers-right-style = blue

                    # [delta "decorations"]
                    #     commit-decoration-style = bold yellow box ul
                    #     file-style = bold yellow ul
                    #     file-decoration-style = none
                    #     hunk-header-decoration-style = yellow box
##
##
##      JETBRAINS
##          - KEY MAPPINGS
                # + Add Carets to Ends of Selected Lines = Ctrl+Shift+L
##          - PLUGINS
                # + Foldable
                    # - Folding Rules
                        # .husky deployments dockercopy .dockerignore codeowners dockerfile 
                        # package-lock.json jenkinsfile .npmrc .prettier* .env .gitignore
##
##
##
##      NOTES TO ADD TO NOTES DOC
##
##          - Section
                # + TEMPORARY REPRESENTATIONAL INSANITY
##          - Browser Dev Tools
                # + Display window height & width while developing & testing responsivness
                #     1. Open Console
                #     2. Click eye icon (top left corner)
                #     3. Paste command below & hit enter
                #         window.innerWidth + ' x ' + window.innerHeight
                #               Output:  '1920 x 610'
##
##
##          - Additional Randomness to add something interesting about...
                # + NVM ~ Node Version Manager
                # + Find what peer dependencies a package has so you can add them to dev dependencies
                #     npm info "eslint-config-airbnb@latest" peerDependencies
                # + Spring Boot Prop to output pretty json from rest controller
                #     spring.jackson.serialization.indent_output = true
##          - JavaScript
                # + Pretty Print JSON Objects
                    # JSON.stringify(obj, nil, 4) // Number = Tab size

##
##
##
##      MISC
alias pap="pwd -W"                                      # Print Absolute Path
alias pwp="pap"                                         # Print Whole Path
alias pp="echo '$PATH' | sed 's/:/\\n/g'"
alias gpp="pp | grep -i"
alias hg="history | grep -in "                          # Grep History
alias gh="hg"
alias lll="ll .."                                       # LL Last
alias cba="cp ~/.bashrc ~/Desktop/.bashrc"              # Copy Bash Aliases to Desktop
alias uzt="tar xvf "                                    # Unzip Tar/Tgz
alias uztv="tar -zxvf "                                 # Unzip Tar/Tgz & Verbose Logging
##
##      POWERSHELL
##          + alias trim='Optimize-Volume -DriveLetter C -ReTrim -Verbose'  # Defrag C Drive without admin permissions, HA!
                #   EXAMPLE ~
                #
                # PS C:\WINDOWS\system32> Optimize-Volume -DriveLetter C -ReTrim -Verbose
                # VERBOSE: Invoking retrim on C (C:)...                                                                                                                                                                                                      
                # VERBOSE: Performing pass 1:  
##
##      GIT
alias gi="git update-index --assume-unchanged "         # Git Ignore <filename>
alias gui="git update-index --no-assume-unchanged  "    # Git Un-Ignore <filename> ;|
alias gli="git ls-files -v | grep -e '^[hsmrck'"]       # List Currently Ignored Files (AKA ~ Assumed Unchanged.)
alias gil="gli"
alias gcl="gc 'latest'"
alias gpl="gaa; gcl; push"
alias gpod='git pull origin develop'                    # Git Pull Origin Develop (New Local Branches Pull From Same Name Branch on Remote)
alias gdf="git checkout develop --" # <Filename/path>   # Git Dev File (Overwriting local with version in develop.)
alias gsl="git stash list"                              # Git Stash List
alias gsa="git stash apply "                            # Git Stash Apply 
alias gpo='git pull origin'
##
##      NODE/NPX
alias npc="rm -fdr yarn.lock node_modules/" # Node Project Clean
alias ncp="npc"
alias eli="npx eslint --init"               # Generate EsLint Config & install Peer-Deps
alias apd="npx install-peerdeps --dev "     #  Add Peer-Dependencies (^as package.json's dev dependencies)
alias rdc="npx depcheck"                    # Run Dep Check (Find Unused Dependencies in package.json to remove.)
                                            # eslint-config-airbnb
alias ndc="rdc"                             # Npx Dependency Check
##
##      NPM
##          ~ https://github.com/coreybutler/nvm-windows
alias nvl="nvm list"                # Node Version List
alias nvi="nvm install "            # Node Version Instal
alias nvu="nvm use "                # Node Version Use
alias nvil="nvm install latest"     # Node Version Install Latest
##
##      NPM
alias nup="ncu --color -ui --packageFile package.json"  # NPM Update Packages
alias ngl="npm list -g"                                 # NPM ~ List Global Packages
alias nlg='ngl'
alias niv='npm install --verbose'
alias nuy='npm install -g npm@latest'                   # NPM Update Yourself
##
##      YARN
yarnLoc='/c/Program\ Files/nodejs/yarn.cmd'
alias yarn=$yarnLoc
alias ycc="yarn cache clean"        # Fixes ESLint AirBnb Not Found (NF) Error
alias yccv="ycc --verbose"
alias ys="$yarnLoc start"
alias ysv="yarn start --verbose"
alias yc="start ~/.yarnrc"
alias ya="yarn add"
alias yfi="yarn install --force"    # Yarn Force Install
alias yif="yfi"                     # Yarn Force Install
alias yaf="yfi"                     # Yarn Force Install
alias yfa="yfi"                     # Yarn Force Install
alias yfiv="yfi --verbose"          # Yarn Force Install Verbose
alias yafv="yfiv"                   # Yarn Force Install Verbose
alias yfav="yfiv"                   # Yarn Force Install Verbose
alias yifv="yfiv"                   # Yarn Force Install Verbose
alias ygl="yarn global list"        # Yarn ~ List Global Packages
alias ylg="ygl"                     # Yarn ~ List Global Packages
alias yr="yarn remove "
alias yi="curl --compressed -o- -L https://yarnpkg.com/install.sh | bash"
alias yul="yarn upgrade-interactive --latest"   # Upgrade all Packages to latest version (Run in PowerShell.)





# CNTLM
alias cdc="cd 'c:\Program Files (x86)\cntlm'"
alias ucc="cdc; start cntlm.ini"                               # CD Into CNTLM Directory & Open ini File to Update Password Hash
alias ucp="ucc& cntlm -H "                                     # Update Cntlm Password
alias cdcc="cd 'c:\Program Files (x86)\cntlm'; start cntlm.ini& cntlm -H "


# NAV
repos="/c/NATE/REPO"
alias i="cd C:/NATE; ll"
alias aa="cd C:/NATE/Apps; ll"
alias ar="cd $repos; ll"
alias s="cd C:/NATE/Space; ll"
alias n="cd $repos/NOTE; gs"
alias rr="cd $repos/RPT; ll"
alias rd="cd $repos/RPT/DEMO; ll"
alias show="cd $repos/SHOW; ll"
alias sb="cd $repos/RPT/SHOW-BACK; ll"
alias imc="cd $repos/RPT/IMC; ll"
alias esi="cd $repos/ESI; ll"
alias n8="cd C:/NATE; ll"
alias choc="cd /c/ProgramData/chocolatey/bin"
alias 360="cd $repos/360;"
alias o="cd $repos/OTHER; ll"
alias rdl="rd; c; cd demos/react-js/linting; ll"
## NAV | JIRA
alias jira-show="cd $repos/NOTE/PROJECT/SHOW/JIRA"
alias ad="cd $repos/ADOBE"
alias smart="cd $repos/SMART; ll"


#######################################
# Pivotal Cloud Foundry Shenanigans
alias cfl="cf login -a api.sys.ch3pcf04.express-scripts.com"  # Cloud Foundry - Login
alias cfpl="cf push -f manifest-local.yml"                    # Cloud Foundry - Push Local

### Project Specific
# 2022
#   + Micro
alias imc="cd C:/NATE/REPO/RPT/IMC/"
alias r="cd C:/NATE/REPO/RPT/IMC/router"
alias bk="cd C:/NATE/REPO/RPT/IMC/bk"
alias ms="cd C:/NATE/REPO/RPT/IMC/micro"
alias tm="cd C:/NATE/REPO/RPT/IMC/jwt"
alias sas="c; as; sfa"
alias proto="ssh c7f7q8@genesys@ps2pr1012217@cyberarkpsmp"     # SSH into Kore Prototype Server
##
##  + HC360
alias cc="360 cp /c/NATE/REPO/NOTE/TECH/Code/360/LoginForm.js src/app/Auth/Login/; cp /c/NATE/REPO/NOTE/TECH/Code/360/appConstants.js src/store/appConstants.js; cp /c/NATE/REPO/NOTE/TECH/Code/360/ActivationForm.js src/app/Account/AccountActivation/ActivationForm.js"


# Pure Laziness
alias c="clear"
alias x="exit"
alias X="x"
alias ll="ls -lrta --color --time=birth --time-style='+%m/%d/%y %H:%M'"
alias ns="nslookup "
alias nsa="nslookup -type=any "
alias a="cat ~/.bashrc | grep alias | sort"      # View aliases w/ comments
alias dora="explorer ."                          # Open Windows File Explorer at current directory



# New Found Laziness
alias cn="start https://github.com/NVardell/Notes"              # Check My Notes
alias vn="start https://git.express-scripts.com/NV/Note"        # Validate Notes Pushed
alias nsk="ssh-keygen -t ed25519 -C "                           # Generate New SSH Key <Required - Email>
alias gsk="clip < ~/.ssh/id_ed25519.pub"                        # Copies the contents of the id_ed25519.pub file to your clipboard
alias pow="cd /c/Users/C7F7Q8/AppData/Roaming/PowerBroker; ll"
alias gb='git branch --show-current'
alias gpnb="git push --set-upstream origin $gb"                 # Git Push New Branch



# Find Where My Shenanigans Are At
alias ffn="find . -type f -name "            # Find Files Named ...
alias fif="find . -type f -name index.html"  # Find Index File



# Manage Aliases & User Functions
alias ag="cat ~/.bashrc | grep -i "              # View aliases in bashrc file
alias npmc="start ~/.npmrc"                      # Open npmrc file for editing (Can also run 'npm config edit')
alias bashit="start ~/.bashrc"                   # Open bashrc file for editing
alias load="source ~/.bashrc"                    # Load changes made to bashrc file
alias unload="unalias -a"                        # Remove all bash aliases
alias reload="unload; load;"                     # Remove all bash aliases
alias mine="a | grep -n NVardell"                # Display my GIT repo aliases for cloning



# GIT  -  Basic Commands
alias ga="git add "
alias gd="git diff "
alias gf="git fetch"
alias gs="git status"
alias pull="git pull --verbose"
alias push="git push"
alias stash="git stash --include-untracked"         # Saving Local Changes on a Stash
alias clone="git clone "
alias gca="git -c http.sslVerify=false clone "      # Git Clone Anyway
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
alias gco="git checkout"                         # Checkout ....
alias gcod="gco develop"                         # Checkout develop
alias gcom="gco main"                            # Checkout master
alias gnb="gco -b "                              # Checkout new branch
alias dlb="git branch -D "                       # Delete local branch
alias drb="git push origin --delete "            # Delete remote branch
alias glb="git branch"                           # Git List of Local Branches
alias grb="git branch -r"                        # Git List of Remote Branches


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
alias reset="git restore "



# GIT  -  FAR From Basic Commands (Runs git command on every sub-directory in current directory)
alias sah="for dir in ./*; do( echo "\$dir" && cd "\$dir" && gs); done"          # Status All Here
alias pah="for dir in ./*; do( echo "\$dir" && cd "\$dir" && pull); done"        # Pull All Here
alias pa="c; ar; for dir in ./*; do( echo "\$dir" && cd "\$dir" && pull); done"  # Pull All Repositories in Repo Directory
alias par="c; rr; for dir in ./*; do( echo "\$dir" && cd "\$dir" && pull); done" # Pull All Repositories in RPT Directory
alias sfa="for dir in ./*; do( echo "\$dir" && cd "\$dir" && gs); done"          # Status For All
alias sa="c; ar; for dir in ./*; do( echo "\$dir" && cd "\$dir" && gs); done"    # Status All Repositories in Repo Directory
alias pc="gaa; gc \"Pushing latest changes.\"; push;"                            # Push All Changes in current Directory
alias pn="n; gaa; gc \"Pushing latest notes.\"; push;"                           # Push All changes Notes Repo
alias po="o; gaa; gc \"Pushing latest changes.\"; push;"                         # Push All changes in Other Repo
alias gpe="pc"                                                                   # Git Push Everything



# GIT  -  Repositories
alias note="clone git@git.express-scripts.com:NV/Note.git"
alias micro="clone git@git.express-scripts.com:NV/rpt-micro-site.git"
alias cms="clone git@git.express-scripts.com:ExpressScripts/rpt_microsite.git"
alias ctm="clone git@git.express-scripts.com:ExpressScripts/rpt_tokenmaker.git"
alias cbk="clone git@git.express-scripts.com:ExpressScripts/rpt-admin-sms-botkit.git"
alias cr="clone git@git.express-scripts.com:ExpressScripts/rpt-router.git"
alias c360="clone git@git.express-scripts.com:ExpressScripts/health-connect-360.git"
alias cshow="clone git@git.express-scripts.com:ExpressScripts/showcase-frontend.git"
alias csb="clone git@git.express-scripts.com:ExpressScripts/showcase-backend.git"
alias csh="clone git@git.express-scripts.com:ExpressScripts/smart-data.git"


# NPM - Shorthand NPM Commands
alias nra="npx create-react-app --use-pnp app-name"   # New React App
alias npms="npm start"
alias npmcu="ncu -u --packageFile package.json"       # NPM Check Updates (And.... auto update package.json. :] )
alias npmcon="npm config list"                        # List npmrc Config Files & Configurations
alias npmcons="npm config ls -l"                      # List npmrc Config Files & Configurations



# MISC - Project / Code Cleanup
alias mcp="mvn clean package"                                                                           # Maven - Clean Package
alias cw="cwf -print; cwd -print;"                                                                      # Clean What?!
alias cwf="find . -type f -name '*.iml' "                                                               # Clean What Files
alias cwd="find . -maxdepth 5 -type d \( -name "*.idea" -o -name "node_mod*" -o -name "target" \) "     # Clean What Directories
alias cpf="cwf -delete"                                                                                 # Clean Project Files & Directories
alias cpd="cwd -prune -exec rm -r {} \;"                                                                # Clean Project Files & Directories
alias clean="cpf; cpd;"                                                                                 # Clean Project - Call above functions to find files & then delete them




# MISC - Fancy Shenanigans 😎
alias cmf="echo -en '\tMac Files = '; find . -type f -name '._*'  | wc -l"                              # Count ~ Mac Files
alias ctf="echo -en '\tTemp Files = '; find . -type f -name '~\$*'  | wc -l"                            # Count ~ Windows Temp Files
alias czf="echo -en '\tZip Files = '; find . -type f -name '*.zip'  | wc -l"                            # Count ~ Zip Files
alias cef="echo -en '\tEmpty Files = '; find . -empty -type f | wc -l"                                  # Count ~ Empty Files
alias ced="echo -en '\tEmpty Directories = '; find . -empty -type d | wc -l"                            # Count ~ Empty Directories
alias pmf="echo -e '\nMac Files = '; find . -type f -name '._*' -print"                                 # Print ~ Mac Files
alias ptf="echo -e '\nTemp Files = '; find . -type f -name '~\$*' -print"                               # Print ~ Windows Temp Files
alias pzf="echo -e '\nZip Files = '; find . -type f -name '*.zip' -print"                               # Print ~ Zip Files
alias pef="echo -e '\nEmpty Files = '; find . -empty -type f -print"                                    # Print ~ Empty Files
alias ped="echo -e '\nEmpty Directories = '; find . -empty -type d -print"                              # Print ~ Empty Directories
alias dmf="find . -type f -name '._*' -delete"                                                          # Delete ~ Mac Files
alias dtf="find . -type f -name '~\$*' -delete"                                                         # Delete ~ Windows Temp Files
alias def="find . -empty -type f -delete"                                                               # Delete ~ Empty Files
alias ded="find . -empty -type d -delete"                                                               # Delete ~ Empty Directories



# MISC - Random Shenanigans
alias t="pathToTree"                                                                                    # GnuWin32 Tree Cmd
alias wt="cmd //c tree //f"                                                                             # Windows built-in tree function (c: Command to run.  f: Display Files.)
alias wwai="cmd //c whoami //all"                                                                             # Windows built-in tree function (c: Command to run.  f: Display Files.)
alias cl="wc -l *"                                                                                      # Count lines for every file in current directory
alias sl="df -h ."                                                                                      # Space Left? (Show free space on current drive.)
alias ld="sl; du -c -Sk -d1 -t2M ./* | sort -hr"                                                        # List Directories & Sort by Size ()
alias ldc="(ld) | clip"                                                                                 # Clip List Directories (ld) Output
alias llc="ll | clip"                                                                                   # Clip Output from List Files Command (ll)



##
##      MISC - User Defined Functions
##
##  Ping url once and print server ip address
function p() {
    echo "Sending ping to ip = " $1;
    ping -n 1 $1 | grep 'Pinging' | awk -F"[][ .]" '{print $2 " = " $8"."$9"."$10"."$11"}'
}
##
##  Pretty curl - Prints response in pretty JSON format
function pc() {
    echo "Sending request to = " $1;
    curl $1 | json_pp
}
##
##  Print list of functions defined in this file
function pf() {
    sed -n /\(\)/p ~/.bashrc | awk '{print $2}'
}
#######################################
# Decrypts a string
#   @input: String to Decrypt
#   @input: Secret to Decrypt with
function dp() {
    echo $1 | openssl enc -d -a -aes256 -nosalt -k $2;
}
#######################################
# Cloud Foundry Login
#   @input: Secret
#   @Note: Couldn't get this to assign to a variable any other way. :/
function cfldx() {
    DECRYPTED=$(decryptPass currentEncryptedPass $1);
    echo $DECRYPTED;
    login $CfDev $UserID $DECRYPTED;
}
#######################################
# Cloud Foundry Login
#   @input: Secret
#   @Note: Couldn't get this to assign to a variable any other way. :/
function cflx() {
    DECRYPTED=$(decryptPass currentEncryptedPass $1);
    login CfServer UserID $DECRYPTED;
}
function login() {
    echo "Logging into Cloud Foundry";
    echo $1;
    echo $2;
    echo $3;
    cf login -a $1 -u $2 -p $3;
}


alias chf="cat ~/.ssh/known_hosts"                  # Cat SSHost File
alias ghf="grep -n '-' ~/.ssh/known_hosts"          # Grep Host File
alias fhf="sed -i '/cyber/d' ~/.ssh/known_hosts"    # Fix Host File
function ff() {
    ghf;
    sed -i '/cyber/d' ~/.ssh/known_hosts;
    echo -e "\n\t UPDATED HOST FILE = ";
    ghf;
}



###########################################################
##
##  IMC ~ GET LATEST BOTKIT FILES & PUSH TO RPT REPO
##
##      + Proto ⇰ Local
##          scp c7f7q8@genesys@ps2pr1012217@cyberarkpsmp: \
##          /opt/gcti/kore-app/botkit/config.json \
##          ~/Desktop
## 
## 
##      + Local ⇰ Proto
##          scp /Users/eh7709/Documents/ESIPharmaBot/config.json \
##          eh7709@genesys@ps2pr1012217@cyberarkpsmp:/opt/gcti/kore-app/botkit/
##     
##
##      + Notes
##          - Muli-File Copy Example
##              scp 'user@172.100.100.100:/home/aerokube/selenoid/{browsers.json,run.sh}'
##
##
##
alias sbl="start ~/Desktop/botkit.log";  ##  Start (Open) BotKit Log
alias ssl="start ~/Desktop/smslog.log";  ##  Start (Open) SMS Log
alias ghb="scp c7f7q8@genesys@ps2pr1012217@cyberarkpsmp:/opt/gcti/kore-app/botkit/HurricaneBot.js ~/Desktop"  # Get Hurricane Bot
# function gsl() {
#     echo -e "##\n##\t GETTING LATEST SMS LOGS FROM PROTOTYPE SERVER";
#     scp c7f7q8@genesys@ps2pr1012217@cyberarkpsmp:/opt/gcti/kore-app/botkit/logs/smslog.log ~/Desktop
# }
function gbl() {
    echo -e "##\n##\t GETTING LATEST BOTKIT LOGS FROM PROTOTYPE SERVER";
    scp c7f7q8@genesys@ps2pr1012217@cyberarkpsmp:/opt/gcti/kore-app/logs/botkit.log ~/Desktop
}
function gbc() {
    echo -e "##\n##\t GETTING LATEST BOTKIT CONFIG FROM PROTOTYPE SERVER";
    scp c7f7q8@genesys@ps2pr1012217@cyberarkpsmp:/opt/gcti/kore-app/botkit/ESPharmaBot.js ~/Desktop
}
function gbcj() {
    echo -e "##\n##\t GETTING LATEST BOTKIT CONFIG JSON FROM PROTOTYPE SERVER";
    scp c7f7q8@genesys@ps2pr1012217@cyberarkpsmp:/opt/gcti/kore-app/botkit/config.json ~/Desktop
}

function c1(){
    curl -k -s \
    --request POST https://a-mobile-api.cigna.com//mga/sps/oauth/oauth20/token \
    --data 'client_id=mycigna_mobile_client&grant_type=password&username=dcmtestcgna4&password=Digitalqeintdata' \
    | awk  -F "[,:\"]" '{print "\n", $5}'
}

function c2(){
    curl -k -s \
    --request GET 'https://a-mobile-api.cigna.com/mga/sps/oauth/oauth20/authorize?client_id=mobile_dxs_client&grant_type=implicit&scope=identity&response_type=token&prompt=none'  \
    --header 'Authorization: bearer' $1
}

# MISC - Export User Functions
export -f p
export -f c1
export -f c2
export -f ff
export -f pc
export -f pf
export -f dp
export -f cflx
export -f cfldx
# export -f gsl
export -f gbl

# Trigger Proxy Check When Loading New Bash Terminal
# service="cntlm"
alias prox="curl -kv https://www.google.com"
# alias proxS="WMIC Service WHERE \"Name = '$service'\" GET Started | grep -qo TRUE && echo $service is running!!! || echo Please start $service..."
# proxS
