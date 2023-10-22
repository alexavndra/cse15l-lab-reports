
# Lab report 1
1. <a href="https://alexavndra.github.io/cse15l-lab-reports/lab1#cd-command" style="color:#023e8a;"><code>cd</code> command</a>
2. <a href="https://alexavndra.github.io/cse15l-lab-reports/lab1#ls-command" style="color:#023e8a;"><code>ls</code> command</a>
3. <a href="https://alexavndra.github.io/cse15l-lab-reports/lab1#cat-command" style="color:#023e8a;"><code>cat</code> command</a>

## `cd` command
``` java
[user@sahara ~]$ cd // no args
[user@sahara ~]$ cd lecture1 // directory arg
[user@sahara ~/lecture1]$ cd Hello.java // file arg
bash: cd: Hello.java: Not a directory
```
1. Without any arguments and the working directory being the `/home` folder, the directory didn't change. However, when I am in a different directory (e.g. the `lecture1` directory), it would take me back to the `/home` directory, as it is the 
2. When given the directory, it changed the directory to the given argument (in this case, it was `lecture1`). To get there, you'd be changing the directory from the current working directory, `/home`, and the absolute path to `lecture1` from the working directory would be `/home/lecture1`. 
3. Given the file argument, it gave an error saying that it could not change the directory to `Hello.java`. This is due to `Hello.java` not being a directory, but a file. 

## `ls` command
``` java
[user@sahara ~]$ ls // no arg
lecture1
[user@sahara ~]$ ls ~/lecture1 // directory arg
Hello.class  Hello.java  messages  README
[user@sahara ~]$ ls ./lecture1/Hello.java // file arg
./Hello.java
```
1. With no argument given, `ls` lists the files and any directories that are present in the working directory; in this case, our working directory is `/home`.
2. Using the `/home` as my working directory, I ran the `ls` command with `~\lecture1` as its argument, and this listed the files present in the `lecture1` directory. Running the `ls` command with a given directory shows the contents of said directory, even *if* you are outside of that directory.
3. Using the same working directory as before, giving a file argument for the `ls` command gave the path of `Hello.java`. This is because I also made sure to give the relative path of the file. 

## `cat` command
``` java
[user@sahara ~]$ cat // no arg
[user@sahara ~]$ cat lecture1 // directory arg
cat: lecture1: Is a directory
[user@sahara ~]$ cat Hello.java // file arg
cat: Hello.java: No such file or directory
[user@sahara ~]$ cat ./lecture1/Hello.java // fixed file argument
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Hello {
  public static void main(String[] args) throws IOExce
ption {
    String content = Files.readString(Path.of(args[0])
, StandardCharsets.UTF_8);    
    System.out.println(content);
  }
}
```
1. When given no argument, `cat` in the `/home` working directory did not produce a response, but it kept running, and would not stop until Cmd + ^ was pressed. Since the `cat` command concatenates the content of a given argument, having no argument makes it attempt concatenating every part of whatever working directory it may be in.
2. When given a directory argument of `lecture1`, `cat lecture1` output that `lecture1` was a directory; this is considered an error, as it expects a file to concatenate the contents of. 
3. When given a file argument, `cat` gave the error that `Hello.java` wasn't in the working directory of `\home`; this is because `Hello.java` is located in the `lecture1` directory; however, when put the relative directory of `./lecture1/Hello.java`, it concatenates the contents of `Hello.java` correctly.
