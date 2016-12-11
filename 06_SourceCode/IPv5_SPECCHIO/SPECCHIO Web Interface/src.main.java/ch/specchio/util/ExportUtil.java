package ch.specchio.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import ch.specchio.model.ChartDataBean;
import ch.specchio.model.SpaceDetailBean;

public class ExportUtil {

    private static final char DEFAULT_SEPARATOR = ',';
    private static SpecchioUtil util = new SpecchioUtil();
    

	public static void createCsvExportZip(HttpServletResponse resp, List<SpaceDetailBean> sdbList) throws IOException{
		
		resp.setContentType("application/zip");
		resp.setHeader("Content-Disposition", "attachment; filename=\"specchio_csv_export.zip\"");
	    	
		ZipOutputStream out = new ZipOutputStream(resp.getOutputStream());
        for(SpaceDetailBean sdb : sdbList){
        	
        	ZipEntry e = new ZipEntry(sdb.getSpaceTypeName().replace(" ", "_")+".csv");
    		out.putNextEntry(e);
        	
        	StringBuilder sb = new StringBuilder();
        	
        	Map<String, List<String>> attributeValueListMap = util.getAttributeValueListMap(sdb.getSpectrumIdList());
        	
        	for(String attributeName : attributeValueListMap.keySet()){
        		
        		sb.append(attributeName);
        		for(String attributeValue : attributeValueListMap.get(attributeName)){
        			sb.append(DEFAULT_SEPARATOR).append(attributeValue);
        		}
        		sb.append("\n");
        		
        	}
        
        	double[] wavelength = sdb.getWavelength().getValues();
        	for(int i = 0; i < wavelength.length; i++){
        		
        		sb.append(wavelength[i]);
        		for(ChartDataBean cdb : sdb.getVectors()){
        			sb.append(DEFAULT_SEPARATOR).append(cdb.getValues()[i]);
        		}
        		sb.append("\n");
        	}
        	
        	byte[] data = sb.toString().getBytes();
    		out.write(data, 0, data.length);
        	out.closeEntry();
        }
        out.close();
        
	}
	
    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    private static String followCSVformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCSVformat(value));
            } else {
                sb.append(customQuote).append(followCSVformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());


    }

}