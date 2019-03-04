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
    "test.txt") (echo "File is the testing file."; mv "$name" ./tester/;) ;;
    *) echo "NOT the right file. :/" ;;
esac
done