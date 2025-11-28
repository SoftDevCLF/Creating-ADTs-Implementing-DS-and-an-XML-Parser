package appDomain;

import java.io.IOException;

import exceptions.EmptyQueueException;
import manager.ParserManager;

/**
 * <p>
 * This application driver code is designed to be used as a basis for the
 * Creating ADTs, Implementing DS and an XML Parser assignment that will 
 * be developed in the CPRG304 F2025 class at SAIT. The implementors of 
 * this applications will be required to add all the correct functionality.
 * </p>
 * 
 * @version 1.0
 * @author Cintya Lara
 * 
 */
public class AppDriver

{
	/**
     * Launches the XML Parser application.
     *
     * @param args the command-line arguments supplied to the program.
     *             The first argument is expected to be the name of the
     *             XML file to be parsed.
	 * @throws EmptyQueueException 
	 * @throws IOException 
     */
	public static void main(String[] args) throws IOException, EmptyQueueException
	{
		new ParserManager(args);

	}

}
