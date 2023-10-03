# Lab report 1
## `cd` command
``` java
[user@sahara ~]$ cd // no args
[user@sahara ~]$ cd lecture1 // directory arg
[user@sahara ~/lecture1]$ cd Hello.java // file arg
bash: cd: Hello.java: Not a directory
```
1. Without any arguments and the working directory being the `\home` folder, there was nowhere to change directory. However, this was *not* an error.
2. When given the directory, it changed the directory to the given argument (in this case, it was `lecture1`)
3. Given the file argument, it gave an error saying that it could not change the directory to `Hello.java`. This is due to `Hello.java` not being a directory, but a file. 

## `ls` command
``` java
[user@sahara ~]$ ls // no arg
lecture1
[user@sahara ~/lecture1]$ ls ~/lecture1 // directory arg
Hello.class  Hello.java  messages  README
[user@sahara ~/lecture1]$ ls ./Hello.java // file arg
./Hello.java
```
1. With no argument given, ls just lists the files that are present in the current directory.
2. To make sure that more files were listed, the working directory was changed to `lecture1`. From there, I ran the `ls` command with `~\lecture1`, and this listed the files present in the `lecture1` directory.
3. Using the same working directory as before, giving a file argument for the `ls` command gave the path of `Hello.java`. This is because I also made sure to give the relative path of the file. 

## `cat` command
``` java
[user@sahara ~]$ cat // no arg
[user@sahara ~]$ cat lecture1 // directory arg
cat: lecture1: Is a directory
[user@sahara ~]$ cat Hello.java // file arg
cat: Hello.java: No such file or directory
```
1. When given no argument, `cat` in the `\home` working directory did not produce a response.
2. When given a directory argument of `lecture1`, `cat lecture1` output that `lecture1` was a directory.
3. When given a file argument, `cat` gave the error that `Hello.java` wasn't in the working directory of `\home`; this is because `Hello.java` is located in the `lecture1` directory.
