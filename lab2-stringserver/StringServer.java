import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
   // Creating new list for query?
   ArrayList<String> messageQuery = new ArrayList<String>();
   String returnQuery = "";
   
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            if (messageQuery.size() == 0) {
                return "No messages yet!";
            }
            return String.format("%s", returnQuery);
        } else if (url.getPath().equals("/add-message")) {
            // Add message to list
            String[] parameters = url.getQuery().split("=");
            messageQuery.add(parameters[1]);

            // adds the new query into the list and adds increment
            String params = Integer.toString(messageQuery.indexOf(parameters[1]) + 1);
            String newMessage = params + ". " + parameters[1];
            messageQuery.set(messageQuery.indexOf(parameters[1]), newMessage);
            returnQuery += newMessage + "\n";

            // returns it to print on the page
            return String.format("%s", returnQuery);
        } else {
            return "404 Not Found!";
        }
    }
}

public class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
