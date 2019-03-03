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

