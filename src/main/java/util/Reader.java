package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import exception.ReaderException;

public class Reader {
	
	private static final Logger LOGGER = LogManager.getLogger(Reader.class.getName());
	private static final String ERR_READ = "Error_resource_file_not_found";
	
	public static String readTextFromResourceFile(String fileName) throws ReaderException {
		StringBuilder result = new StringBuilder();
				
		URL url = Reader.class.getClassLoader().getResource(fileName);		
		if (url == null) {
			LOGGER.error(ERR_READ);
			throw new ReaderException(ERR_READ);
		}
		File file = new File(url.getFile());	
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
				result.append("\n");
			}			
		} catch (IOException e) {
			LOGGER.error(ERR_READ);
			throw new ReaderException(ERR_READ, e);
		} 
		
		return result.toString();
	}

}
