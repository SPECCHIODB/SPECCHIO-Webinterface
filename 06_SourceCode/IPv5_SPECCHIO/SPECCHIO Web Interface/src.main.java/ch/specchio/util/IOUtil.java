package ch.specchio.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import ch.specchio.model.ChartDataBean;
import ch.specchio.model.SpaceDetailBean;
import ch.specchio.types.MetaFile;

public class IOUtil {
	
	/**
	 * This method creates a ZIP File containing a CSV File for each SpaceDetailBean from the given sdbList.
	 * The file will be written to the OutputStream of the given HttpServletResponse.
	 * 
	 * @param resp - the HttpServletResponse
	 * @param sdbList - the list of SpaceDetailBeans that should be exported
	 * @param separator - separators between values in csv file
	 * @throws Exception 
	 */
	public static void createCsvExportZip(HttpServletResponse resp, List<SpaceDetailBean> sdbList, String separator) throws Exception {
		SpecchioUtil util = new SpecchioUtil();
		
		ZipOutputStream out = new ZipOutputStream(resp.getOutputStream());
        for(SpaceDetailBean sdb : sdbList){
        	
        	ZipEntry e = new ZipEntry(sdb.getSpaceTypeName().replace(" ", "_")+".csv");
    		out.putNextEntry(e);
        	
        	StringBuilder sb = new StringBuilder();
        	
        	Map<String, List<String>> attributeValueListMap = util.getAttributeValueListMap(sdb.getSpectrumIdList());
        	
        	// attribute names and values for each spectrum
        	for(String attributeName : attributeValueListMap.keySet()){
        		
        		sb.append(attributeName);
        		for(String attributeValue : attributeValueListMap.get(attributeName)){
        			sb.append(separator).append(attributeValue);
        		}
        		sb.append("\n");
        		
        	}
        
        	// wavelength and vectors of each spectrum
        	double[] wavelength = sdb.getWavelength().getData();
        	for(int i = 0; i < wavelength.length; i++){
        		
        		sb.append(wavelength[i]);
        		for(ChartDataBean cdb : sdb.getVectors()){
        			sb.append(separator).append(cdb.getData()[i]);
        		}
        		sb.append("\n");
        	}
        	
        	byte[] data = sb.toString().getBytes();
    		out.write(data, 0, data.length);
        	out.closeEntry();
        }
        out.close();
        
	}
	
	/**
	 * Creates a File with the given filename and the values from the given mp_file.
	 * Used for PDFs and Images on Detail view.
	 * @return the filename
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static String createTempFile(String filename, MetaFile mp_file) throws IOException, URISyntaxException{
		
		File class_dir = new File(IOUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		File conf_file = new File(class_dir.getParent());

		// Get path of current folder as string.
		String pathCurrentClass = conf_file.getPath();
		
		String directory = "WEB-INF";
		int endIndex = pathCurrentClass.indexOf(directory);

		// Get path from root to "WEB-INF" as String (without WEB-INF!).
		String pathPart1 = pathCurrentClass.substring(0, endIndex);

		String pathPart2 = "/" + filename;
		

		// Concatenate first and second part of path.
		String path = new StringBuilder().append(pathPart1).append(pathPart2).toString();
		// Write Value of mp_file
		FileOutputStream fos = new FileOutputStream(path);
		mp_file.writeValue(fos);
		fos.close();
		
		return filename;
	}
	
	/**
	 * Load db_config.txt
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void loadDbConfig() throws IOException, URISyntaxException {

		File class_dir = new File(IOUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		File conf_file = new File(class_dir.getParent());

		// Get path of current folder as string.
		String pathCurrentClass = conf_file.getPath();
		
		// Read connection string
		String connectionString = readDbConnectionString(pathCurrentClass);
		
		// Write connection string
		writeDbConnectionString(pathCurrentClass, connectionString);

	}
	
	/**
	 * This method reads the database connection settings and returns it as string.
	 * @param pathString
	 * @return connectionString
	 * @throws IOException 
	 */
	private static String readDbConnectionString(String pathString) throws IOException {

		String pathCurrentClass = pathString;
		String connectionString = null;

		// Look for "domain1" substring within path.
		String directory = "domain1";
		int endIndex = pathCurrentClass.indexOf(directory) + directory.length();

		// Get path from root to "domain1" as String.
		String pathPart1 = pathCurrentClass.substring(0, endIndex);

		// Path from "domain1" to "db_config.txt".
		String pathPart2 = "/lib/ext/db_config.txt";

		// Concatenate first and second part of path.
		// Database connection string will be read from source file .../domain1/lib/ext/db_config.txt.
		String path = new StringBuilder().append(pathPart1).append(pathPart2).toString();

		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
	
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			connectionString = sb.toString();
		} finally {
			br.close();
		}
			
		return connectionString;
	}
	
	/**
	 * This method writes the database connection settings to SPECCHIO Web Interface. 
	 * @param pathString
	 * @param cs (connection string)
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	private static void writeDbConnectionString(String pathString, String cs) throws FileNotFoundException, UnsupportedEncodingException {

		String pathCurrentClass = pathString;
		String connectionString = cs;

		String directory = "WEB-INF";
		int endIndex = pathCurrentClass.indexOf(directory) + directory.length();

		// Get path from root to "WEB-INF" as String.
		String pathPart1 = pathCurrentClass.substring(0, endIndex);

		// Path from "WEB-INF" to "db_config.txt".
		String pathPart2 = "/lib/db_config.txt";

		// Concatenate first and second part of path.
		// Database connection string will be written to target file .../WEB-INF/lib/db_config.txt.
		String path = new StringBuilder().append(pathPart1).append(pathPart2).toString();
		PrintWriter writer = new PrintWriter(path, "UTF-8");
		try {
			writer.println(connectionString);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
}
