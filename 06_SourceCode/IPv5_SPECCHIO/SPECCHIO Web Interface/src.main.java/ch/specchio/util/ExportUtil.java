package ch.specchio.util;

import java.io.IOException;
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
        
        	double[] wavelength = sdb.getWavelength().getData();
        	for(int i = 0; i < wavelength.length; i++){
        		
        		sb.append(wavelength[i]);
        		for(ChartDataBean cdb : sdb.getVectors()){
        			sb.append(DEFAULT_SEPARATOR).append(cdb.getData()[i]);
        		}
        		sb.append("\n");
        	}
        	
        	byte[] data = sb.toString().getBytes();
    		out.write(data, 0, data.length);
        	out.closeEntry();
        }
        out.close();
        
	}
	

}