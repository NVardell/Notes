# VIEW CURRENT/SUBDIRECTORY FILES & FOLDERS IN A VISUAL TREE
# RUNS WINDOWS VERSOIN OF TREE PROGRAM
$ tree.com //f //a
# Folder PATH listing
# Volume serial number is 2606-97E5
# C:.
# +---gws
# |       application.properties
# |       config.xml
# |       env.properties
# |       mq-config - 2.xml
# |       mq-config.xml
# |       test.txt
# |
# +---gws-admin
# |       application.properties
# |       config.xml
# |       env.properties
# |       mq-config - 2.xml
# |       mq-config.xml
# |       test.txt
# |
# \---gws_batch
#         application.properties
#         config.xml
#         env.properties
#         mq-config - 2.xml
#         mq-config.xml
#         test.txt

# UNIX TREE | ADDED PROGRAM FILES TO REPO
# SHOULD BE ABLE TO ADD TO PATH AND IT'S A WRAP.
$ tree
# .
# |-- gws
# |   |-- application.properties
# |   |-- config.xml
# |   |-- env.properties
# |   |-- mq-config - 2.xml
# |   |-- mq-config.xml
# |   `-- test.txt
# |-- gws-admin
# |   |-- application.properties
# |   |-- config.xml
# |   |-- env.properties
# |   |-- mq-config - 2.xml
# |   |-- mq-config.xml
# |   `-- test.txt
# `-- gws_batch
#     |-- application.properties
#     |-- config.xml
#     |-- env.properties
#     |-- mq-config - 2.xml
#     |-- mq-config.xml
#     `-- test.txt

# 3 directories, 18 files




# In bash, to use rm -- !(file.txt), you must enable extglob:
shopt -s extglob
# OR
bash -O extglob
# THEN rm should work (Will only run in current directory.)
rm -- !(mq-config.xml)
# Similarly, you can keep two or more particular types of files and remove everything else. 
# Say for example, the following command will keep the files that contains .doc and .mp3 extensions.
rm !(*.doc|*.mp3)


# The print0 and -0 combination is needed if there are spaces in any of the filenames that should be deleted.
find . -type f -not -name '*txt' -print0 | xargs -0 rm --
# Instead of '-print0 | xargs -0 rm --'' you can just do '-delete'
find . -type f -not -name '*txt' -delete

# Remove all regular files (recursively, including hidden ones) except file.txt. 
# To remove directories, change '-type f' to '-type d' and add '-r' option to rm.
find . ! -name 'file.txt' -type f -exec rm -f {} +


# Iff there are no spaces in file names
ls | grep -v file.txt | xargs rm
# If there are spaces in file name
ls | grep -v file.txt | parallel rm


find . -type f ! -name 'file.txt' -delete
# If you want to delete all files of a certain type, but only 1 folder "deep" from the current folder:
# -maxdepth 2 because the current directory "." counts as the first folder.
find . -maxdepth 2 -name "*.log" -type f -delete


# Prints 'Hello 1~30'
for i in {1..30}; do if [ $i != 10 ]; then echo "hello $i"; fi; done


# Move all sub-files to parent directory
# 1. Go to your parent directory and run below
find . -mindepth 2 -type f -print -exec mv {} . \;
# Will find all files in parent & children & executes 'mv' with target dir .

# If you want to just copy, you can use a mere:
# This will copy all files, normal & hidden ones, since /path/subfolder/. expands to "everything in this dir" 
cp -r /path/subfolder/. /path/



# comparison operator -ne is an arithmetic operator, i.e. it compares only integers:
i=7
if [ "$i" -ne 6 ] && [ "$i" -ne 8 ]; then
   echo 'i is neither 6 nor 8'
fi

# To compare strings for inequality, use !=:
if [ "$filename" != 'even' ] && [ "$filename" != 'odd' ]; then
    printf '%s\n' "$filename"
fi
# Or use case:
case "$filename" in
    even|odd) ;;
           *) printf '%s\n' "$filename"
esac

# Also note that * will match any name in the current directory, not just names of regular files. 
# To be sure that you only process regular files in your loop, use
for name in *; do
    test -f "$name" || continue

    # other code here using "$name"
done

# test -f "$name" may be replaced by
if [ ! -f "$name" ]; then
    continue
fi
# Or just:
[ ! -f "$name" ] && continue
# OR
[ -f "$name" ] || continue



# MINE
if [[ "qa01__ws__mq-config.xml" == *"qa"*"ws"* ]]; then echo "They are equal." ; fi
# OUTPUT | They are equal.
case "qa01__ws__mq-config.xml" in
    *"qa"*"ws"*) echo "File is QA & WS" ;;
    *) echo "NOT QA & WS" ;;
esac
# OUTPUT | File is QA & WS


case "qa01__ws__mq-config.xml" in
    *"qa"*"ws"*) (echo "File is QA & WS"; echo "TEST";) ;;
    *) echo "NOT QA & WS" ;;
esac
# OUTPUT |
# File is QA & WS
# TEST





# To move a file from the current directory to another location, enter a path as the third word on the command line.
# This command would remove filename from the current working directory and place it in /dir1/.
mv filename /dir1/

# Alternatively, a path to the location of the file may be entered as the second word and "." as the thrid word. 
# This moves the file from the location specified in word two into your current working directory.
# Moves the file filename from the /tmp/ directory into your current working directory.
mv /tmp/filename .

# Moves the file filename from a directory two levels up to the /tmp/ directory while renaming the file new_name.
mv ../../filename /tmp/new_name



# Mkdir with '-p' causes nothing to happen if directory already exists
# Precautionary.  If/when the directories I want to move the files to don't exist.
mkdir -p /target/directory ; mv file /target/directory




for name in *; do
    case "$name" in
    "test.txt") (echo "File is the testing file."; mkdir -p tester; mv "$name" ./tester/;) ;;
    *) echo "NOT the right file. :(" ;;
esac
done



# Handles Empty Values and Missing Values (As long as tags are present.)
$ awk -F'>|<' '/BILL_NO/{printf $3}/NAME\>/{print (NF==3 || $3=="")?",NA":","$3}' Unix.xml
1234,ABC
5678,BCA
1256,NA
345,NA
8934,PKL




# Want to clean up random crap - Below folder popped up with lots of random crap.
# C:\Users\NV\Google Drive\Personal\Other\Work\Revature\Week 2 - SQL\Genesis_Bonds_Code\Week2\.metadata\.plugins\org.eclipse.jdt.ui\jdt-images

BASH-2
NV@NV MINGW64 ~/Google Drive
$ echo; df -h .; echo; du -c -Sk -d3 -t2M -b ./* | sort -hr
        3949356369      total
        1078905323      ./Personal/Other/Temp
        995882392       ./Tibby/Manuals/Car/Shop Manual I Sections
        444587325       ./Tibby/Manuals/Car/Electric
        282200073       ./Tibby/Manuals/Car/Other
        231089127       ./Personal/Other/Music
        133481998       ./Tibby/Manuals/Car
        81525246        ./Tibby/DIY/Other/Boost & Vacuum
        67568160        ./Personal/Other/Music/Gillie
        34679555        ./Tibby/DIY/Other/ECM Chip
        22245151        ./Tibby
        20712964        ./Tibby/Manuals/Audio
        19874557        ./Images/Color Factory
        17692873        ./Tibby/DIY/Audio
        13400763        ./Personal/Apartment/3 Journal Square
        12051246        ./Tibby/DIY/Cold Air Intake
        11777903        ./Personal/Games/Clash of Clans/Tops
        11363848        ./Personal/Money/Receipts
        8308424 ./Notes/2019 Notes/Print
        6655520 ./Tech/Computer/Fonts
        4442834 ./Notes/OLD
        3455798 ./Personal/Other/Work/Mphasis
        3391166 ./Tibby/Other
        3170304 ./Images/CS Photos
        2875289 ./Notes
        2853313 ./Tech/Builds
        2841109 ./Personal/Other/Work/Revature
        2472944 ./Tech/Builds/2012
        2381079 ./Personal
        2327068 ./Images/Color Factory/Background
        2171283 ./Tech/Builds/Laptop - MSI
        2123355 ./Personal/Projects/Royally Clashing/Ideas
        2118299 ./Tibby/Manuals/Other
        2097153 ./Tibby/DIY


NV@NV MINGW64 ~/Google Drive
$ echo; df -h .; echo; du -c -Sk -d3 -t2M -k ./* | sort -hr
        3863399 total
        1053624 ./Personal/Other/Temp
        972580  ./Tibby/Manuals/Car/Shop Manual I Sections
        434188  ./Tibby/Manuals/Car/Electric
        275596  ./Tibby/Manuals/Car/Other
        225705  ./Personal/Other/Music
        130368  ./Tibby/Manuals/Car
        79616   ./Tibby/DIY/Other/Boost & Vacuum
        66000   ./Personal/Other/Music/Gillie
        33880   ./Tibby/DIY/Other/ECM Chip
        21732   ./Tibby
        20249   ./Tibby/Manuals/Audio
        19456   ./Images/Color Factory
        17296   ./Tibby/DIY/Audio
        13112   ./Personal/Apartment/3 Journal Square
        11776   ./Tibby/DIY/Cold Air Intake
        11544   ./Personal/Games/Clash of Clans/Tops
        11256   ./Personal/Money/Receipts
        8120    ./Notes/2019 Notes/Print
        6596    ./Tech/Computer/Fonts
        4465    ./Notes/OLD
        3424    ./Personal/Other/Work/Mphasis
        3320    ./Tibby/Other
        3100    ./Images/CS Photos
        2812    ./Notes
        2808    ./Personal/Other/Work/Revature
        2804    ./Tech/Builds
        2416    ./Tech/Builds/2012
        2341    ./Personal
        2284    ./Images/Color Factory/Background
        2140    ./Tech/Builds/Laptop - MSI
        2077    ./Personal/Projects/Royally Clashing/Ideas
        2072    ./Tibby/Manuals/Other
        2056    ./Tibby/DIY


NV@NV MINGW64 ~/Google Drive
$ echo; df -h .; echo; du -c -Sk -d3 -t2M -m ./* | sort -hr
        3773    total
        1029    ./Personal/Other/Temp
        950     ./Tibby/Manuals/Car/Shop Manual I Sections
        425     ./Tibby/Manuals/Car/Electric
        270     ./Tibby/Manuals/Car/Other
        221     ./Personal/Other/Music
        128     ./Tibby/Manuals/Car
        78      ./Tibby/DIY/Other/Boost & Vacuum
        65      ./Personal/Other/Music/Gillie
        34      ./Tibby/DIY/Other/ECM Chip
        22      ./Tibby
        20      ./Tibby/Manuals/Audio
        19      ./Images/Color Factory
        17      ./Tibby/DIY/Audio
        13      ./Personal/Apartment/3 Journal Square
        12      ./Tibby/DIY/Cold Air Intake
        12      ./Personal/Games/Clash of Clans/Tops
        11      ./Personal/Money/Receipts
        8       ./Notes/2019 Notes/Print
        7       ./Tech/Computer/Fonts
        5       ./Notes/OLD
        4       ./Tibby/Other
        4       ./Personal/Other/Work/Mphasis
        4       ./Images/CS Photos
        3       ./Tibby/Manuals/Other
        3       ./Tibby/DIY
        3       ./Tech/Builds/Laptop - MSI
        3       ./Tech/Builds/2012
        3       ./Tech/Builds
        3       ./Personal/Projects/Royally Clashing/Ideas
        3       ./Personal/Other/Work/Revature
        3       ./Personal
        3       ./Notes
        3       ./Images/Color Factory/Background




## 
##  Adding an Extension to Files With Bash
## 

##
##  1. Simple Loop
##
        + If we need to add extensions to all the files in a given directory indiscriminately, 
          we can use a simple loop & the mv command to do that.
            - Ex.
                for f in *; do mv "$f" "$f.jpg"; done
        + That solution, while very concise, has a few drawbacks. 
            - What if some files already have the extension? 
            - Or maybe some files aren’t the type we want to fix? 
            - We’ll deal with these problems one after another.

## 
##  2. Selecting Files Without Extensions
## 
        + In real life, we can have various files in one directory, 
          some of which we may like to process and others that we want to leave alone.
        + Let’s say we have two files in the directory: 'picture.jpg' and 'image'. 
          Of course, we don’t want to add a second '.jpg' extension to the first file.
        + We’ll use the find command to select the correct file.
            - Ex.
                find . -not -name "*.*" ./image
        + Then, we can rename the returned files using the -exec parameter & mv command.
            - Ex.
                find . -not -name "*.*" -exec mv {} {}.jpg \;
        + Optionally, we could use a simple while loop:
            - Ex.
                find . -not -name "*.*" | while read FILE; do mv "$FILE" "$FILE".jpg; done



##  
##  4. Discriminate by File Type
##  
        + Our scenario can get more complicated. We could encounter files 
          that don’t have any extension and are not JPEGs. 
        + Mind that directories are also files in Linux, and we could have 
          nested directories that we want to process. 
        + So, we need to get into the directories but not change their names.
            - Let’s imagine that we need to process five files: 
                    1. 'picture.jpg'
                    2. 'image-no-extension'
                    3. 'readme'
                    4. 'nested_directory', and inside that directory, 
                    5.    \--- 'another_image'
            - We want to rename only the 'image' & 'another_image' files, 
              as the remaining files have an extension already or aren’t 
              really JPEG images.  Fortunately, the find command will also
              list the nested files.
##
##      4a. Solve the problems individually
##
            - If we want to filter out directories, we use the –type parameter.
                + Ex.
                    find . -type f -not -name "*.*" ./image ./readme ./nested_directory/another_image
            - To check the actual type of the file, we’ll use the file command,
                + Ex.      
                    ##
                    ## Create readme file with contents of 'test'
                    echo "test" > 'readme'
                    file --mime-type -b 'readme'
                    text/plain
                    ## 
                    ## Find & Print File Type
                    file --mime-type -b 'image-no-extension'
                    image/jpeg
                    ## 
                    ## Find & Print File Type
                    file --mime-type -b 'nested-directory'
                    inode/directory
##
##      4b. Solve problems simultaneously
##
        ##
        ## Option #1
        ##
        ##   Conditional while loop to iterate over the files.
        ##
                find . -type f -not -name "*.*" | while read FILE; 
                do if [ $(file --mime-type -b "$FILE") == "image/jpeg" ]; 
                then mv "$FILE" "$FILE".jpg; fi; done;
        ##
        ## Option #2
        ##
        ##   Using exec flag w/ find command 
        ##      & stringifying conditional bash command
        ##
                find . -type f -not -name "*.*" -exec bash -c \
                'if [ $(file --mime-type -b {}) == "image/jpeg" ]; then mv {} {}.jpg; fi;' \;
        ##
        ## Notes
        ##
        ##   Dry run before 
        ##
        ##      If we’d like to perform a dry run to make sure our command 
        ##      will do what we intend, we can print old/new filenames
        ##      using the `echo` command, in-place of the `mv` command.
        ##          1. Option #1
                        find . -type f -not -name "*.*" -exec bash -c \
                        'if [ $(file --mime-type -b {}) == "image/jpeg" ]; then echo {} {}.jpg; fi;' \;
        ##          2. Option #2
                        find . -type f -not -name "*.*" | while read FILE; 
                        do if [ $(file --mime-type -b "$FILE") == "image/jpeg" ]; 
                        then echo "$FILE" "$FILE".jpg; fi; done;
