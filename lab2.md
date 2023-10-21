# Part 1
This is my code for `StringServer.java`. ![Code for `StringServer.java`](lab2-assets/string-server-entire-code.png)
In this screenshot, the code method used are `handleRequest`, which takes a parameter called `URI url`, which is what handles the query requests put into the URL. From there, it determines whether the given query is in the command list provided (either `/` or `/add-message`). If it just the `/`, then it will return the current query list; if the given command is `add-message`, it will examine the query provided after (e.g. the query is "Hello"), and then add it to the query list. After adding it to the query list, it will display the updated list on the screen. If incorrect values (e.g. an unknown query command, a new URI outside of the `localhost`), are provided, it will print a "404" error to the user.

This is my output from the `StringSearch.java` web server. ![Output from `StringServer.java`](lab2-assets/string-server-output.png)
In the `StringServer.java` web server, the primary method called is `add-message`, which takes the given query and adds it to the generated query list; after adding it, it displays the updated list on the web page. The relevant arguments for this include anything provided, except a new URI. The values of the query and its query list constantly change as you add more and modify what's in the list. If no values are changed, the list remains the same.

# Part 2
This is me going into the `.ssh` folder on my CSE15L profile. ![Location of SSH keys on my CSE15L profile](lab2-assets/on-remote.png)

Now this is me going into the `.ssh` folder on my own computer (not logged onto the `ieng6` server). ![Location of SSH keys on my own computer](lab2-assets/on-desktop.png)

Lastly, I logged into the `ieng6` server using the passphrase I created; though I can't put the passphrase nor the whole output, it successfully logged me in. ![Logging into the SSH tunnel with passphrase](lab2-assets/going-into-ssh.png)

# Part 3
Something I learned in Week 3's lab was successfully inputting SSH public keys into my profile. Though I've worked several times with SSH keys and tokens prior to CSE 15L, I didn't use them other than "completion" purposes (e.g. work to get into GitLab). But learning that I can `scp` my computer's SSH keys into a remote profile actually shorthands how I log into my job's remote server. I find this extremely helpful, as my password is extremely long; with using the short passphrase I created, it saves me so much time–and slow key typing–in logging in.