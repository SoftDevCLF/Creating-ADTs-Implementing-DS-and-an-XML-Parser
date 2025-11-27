package manager;

/**
 * Responsible for handling and validating command-line input for the XML
 * Parser application.
 * @version 1.0
 * @author Cintya Lara
 */
public class ParserManager
{
	/** The XML filename supplied via the command line. */
	private String filename;
	
	/**
     * Constructs a new {@code ParserManager} and processes the command-line
     * arguments provided by the user.
     *
     * @param args the command-line arguments passed from the application
     *             entry point. {@code args[0]} is expected to be the name
     *             of the XML document to parse.
     */
	public ParserManager(String[] args)
	{
		// Parsing arguments
		// check there is enough arguments
		if (args.length < 1)
		{
			System.out.println("Error: No XML file supplied.");
			return;
		}

		// Assign argument to variable
		filename = args[0];
		
		//Create a XMLParser object with filename as parameter
		// XMLParser xmlParser = new XMLParser(filename);
		
		// Call the parse method to start parsing
		// xmlParser.parse();
	}

}
