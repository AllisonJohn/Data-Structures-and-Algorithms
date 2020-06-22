import java.io.*;
import java.util.*;
/**
 * FileUtil class performs actions involving files with methods loadFile and saveFile
 * @author Allison John
 * @version 1-10-18
 */
public class FileUtil
{
    /**
     * loadFile takes the fileName to get the contents of the file
     * @param fileName to the name the file in the same directory
     * @return a string iterator for each line in the file
     */
	public static Iterator<String> loadFile(String fileName)
	{
		try
		{
			Scanner in = new Scanner(new File(fileName));
			List<String> list = new ArrayList<String>();
			while (in.hasNextLine())
				list.add(in.nextLine());
			in.close();
			return list.iterator();
		}
		catch(FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * saveFile takes an iterator and writes the contents into a file
	 * @param fileName the name of the file to use
	 * @param data the data to be put into the file
	 */
	public static void saveFile(String fileName, Iterator<String> data)
	{
		try
		{
			PrintWriter out = new PrintWriter(
				new FileWriter(fileName), true);
			while (data.hasNext())
				out.println(data.next());
			out.close();
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}