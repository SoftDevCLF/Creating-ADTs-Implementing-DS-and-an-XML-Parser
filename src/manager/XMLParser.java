package manager;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import exceptions.EmptyQueueException;
import implementations.MyQueue;
import implementations.MyStack;

public class XMLParser {

    private MyStack stack = new MyStack();
    private MyQueue errorQueue = new MyQueue();
    private MyQueue extrasQueue = new MyQueue();

    //Parser method
    public void parse(String filePath) throws FileNotFoundException, EmptyQueueException {

        Scanner reader = new Scanner(new File(filePath));
        System.out.println("Parsing Errors Include:");

        while (reader.hasNextLine()) {
        	
        	String line = reader.nextLine();

            int startParsing = 0;
            int open = line.indexOf("<", startParsing);
            //Find ALL tags in this line
            while (open != -1) {
                int close = line.indexOf(">", open);
                if (close == -1)
                    break; //malformed tag
                
                //Extract and process the tag
                String fullTag = line.substring(open, close + 1);
                processTags(fullTag);
                startParsing = close + 1;
                open = line.indexOf("<", startParsing);
            }
        }
        reader.close();
        //Leftover unclosed tags
        while (!stack.isEmpty())
            errorQueue.enqueue(stack.pop());
        cleanQueues();
        //If there are no errors print success message.
        if (errorQueue.isEmpty() && extrasQueue.isEmpty()) {
        	System.out.print("No parsing errors found. The XML file is constructed correctly!");
        }
    }

    //Process Tag Method
    private void processTags(String fullTag) throws EmptyQueueException {

        //Ignore the XML attributes
        if (fullTag.startsWith("<?") && fullTag.endsWith("?>"))
            return;

        if (fullTag.endsWith("/>"))
            return;

        boolean isClosing = fullTag.startsWith("</");

        //Extract tag name only
        String name = fullTag.replace("<", "")
                             .replace(">", "")
                             .replace("/", "")
                             .split(" ")[0];

        if (!isClosing) {
            stack.push(name); 
        } else {
            handleClosingTag(name);
        }
    }

    private void handleClosingTag(String tag) throws EmptyQueueException {

        //If matches top of stack, pop stack and all is well
        if (!stack.isEmpty() && stack.peek().equals(tag)) {
            stack.pop();
            return;
        }
        //If matches head of errorQ, dequeue and ignore
        if (!extrasQueue.isEmpty() && errorQueue.peek().equals(tag)) {
            errorQueue.dequeue();
            return;
        }
        //If stack is empty, add to errorQ
        if (stack.isEmpty()) {
            extrasQueue.enqueue(tag);
            System.out.println("Error: </" + tag + "> has no opening tag");
            return;
        }
        //Search stack for matching Start_Tag
        MyStack temp = new MyStack();
        boolean found = false;

        while (!stack.isEmpty()) {
            String top = (String) stack.pop();
            if (top.equals(tag)) {
                found = true;
                break;
            }
            temp.push(top);
        }

        //If stack has match
        if (found) {
            while (!temp.isEmpty()) {
                // Pop each E from stack into errorQ until match, report as error
                String misplaced = (String) temp.pop();
                errorQueue.enqueue(misplaced);
                System.out.println("Error: <" + misplaced + "> is not constructed correctly");
            }
        } else {
            //Add E to extrasQueue
            extrasQueue.enqueue(tag);
            System.out.println("Error: </" + tag + "> does not match opening tag");
        }

        while (!temp.isEmpty())
            stack.push(temp.pop());
    }
    private void cleanQueues() throws EmptyQueueException {
        //If both queues are not empty, peek both queues
        while (!errorQueue.isEmpty() && !extrasQueue.isEmpty()) {
            String openTag = (String) errorQueue.peek();
            String closedTag = (String) extrasQueue.peek();

            if (openTag.equals(closedTag)) {
                errorQueue.dequeue();
                extrasQueue.dequeue();
            } else {
                //Don’t match, dequeue from errorQ and report as error
                System.out.println("Error: <" + errorQueue.dequeue() + "> not closed");
            }

            while (!errorQueue.isEmpty())
                System.out.println("Error: <" + errorQueue.dequeue() + "> not closed");

            while (!extrasQueue.isEmpty())
                System.out.println("Error: </" + extrasQueue.dequeue() + "> has no opening tag");
        }
    }
}