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

        We could also use the -exec parameter of the find command, but then, we’d need to execute our conditional statement with the bash or sh command:
find . -type f -not -name "*.*" -exec bash -c 'if [ $(file --mime-type -b {}) == "image/jpeg" ]; then mv {} {}.jpg; fi;' \;
If we’d like to perform a dry run to make sure our command will do what we intend to, we can simply substitute the mv command with the echo command to print old and new filenames.



##
##  5. Summary
##
In this tutorial, we learned how to use Bash to precisely add extensions to the extensionless files. 
We considered various possible pitfalls like files already having the extension, 
files of the wrong type, or nested directories.


