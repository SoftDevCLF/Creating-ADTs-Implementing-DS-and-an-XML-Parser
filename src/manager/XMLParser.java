package manager;

//import java.io.BufferReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.NoSuchElementException;


import implementations.MyStack;
import implementations.MyQueue;

/**
 * Responsible for parsing an XML file and reporting syntax errors 
 * based on matching start and end tags. It will use the customized
 * MyStack and MyQueue classes created for this assignment. 
 * 
 * @version 1.0
 * @author Temi Bankole
 */

public class XMLParser
{
	/** To Input the name of the XML file being parsed*/
	private String filename;
	
	/**Stack to hold opening tags during parsing*/
//	private MyStack<String> tagStack;
	
	/**Queue to store error tags in the order they occur*/
 	private MyQueue<String> errorQueue;
	
	/**Queue to store extra unmatched closing tags */
 	private MyQueue<String> extrasQueue;
 	
 	
 	//Constructor
// 	public XMLParser(String filename) {
// 		this.filename = filename;
// 		tagStack = new MyStack<>();
// 		errorQueue = new MyQueue<>();
// 		extrasQueue = new MyQueue<>();
// 	}
 	
 	
 	//Reads the XML file line by line and checks for parsing errors.
 	public void parse() {
 		// TODO: implement XML parsing logic
 		
 		// TODO: While (loop) there are lines to read 
 		//   - Check if line is a self-closing tag → ignore
        //   - If start tag → push onto tagStack
        //   - If end tag:
        //        - If matches top of tagStack → pop
        //        - Else if matches head of errorQueue → dequeue and ignore
        //        - Else if stack is empty → add to errorQueue
        //        - Else → search stack for matching start tag
        //             - If found → pop each into errorQueue until match
        //             - Else → add to extrasQueue
 		
 		// TODO: After EOF, pop remaining tagStack elements into errorQueue

        // TODO: Process errorQueue and extrasQueue:
        //   - If either queue is empty (not both) → report all as errors
        //   - Else → peek both queues
        //       - If mismatch → dequeue from errorQueue and report
        //       - Else → dequeue from both
        //   - Repeat until both queues empty

        // TODO: Output errors to console
 	}
 	
 	// TODO: Helper methods
    // private boolean isSelfClosingTag(String tag)
    // private boolean isStartTag(String tag)
    // private boolean isEndTag(String tag)
    // private boolean lineMatchesTop(String tag)
    // private boolean lineMatchesHeadErrorQueue(String tag)
    // private boolean stackHasMatch(String tag)
    // private void popStackUntilMatch(String tag)
    // private void processQueues()
    // private String convertToStartTag(String endTag)
 	
 	
	
	
	
	

}
