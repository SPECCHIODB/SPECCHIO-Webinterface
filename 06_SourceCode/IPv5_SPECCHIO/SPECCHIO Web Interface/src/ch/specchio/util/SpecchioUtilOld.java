package ch.specchio.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ch.specchio.client.SPECCHIOClient;
import ch.specchio.client.SPECCHIOClientException;
import ch.specchio.model.MetaDataBean;
import ch.specchio.types.MatlabAdaptedArrayList;

public class SpecchioUtilOld {
	
	private SPECCHIOClient specchio_client;
	private List<MetaDataBean> metaDataObjectList;
	private ArrayList<Integer> ids;
	private Map<String, String> keywordToAttributeMap;
	
	public SpecchioUtilOld() throws SPECCHIOClientException, FileNotFoundException, IOException {
		initKeyWordToAttributeMap();
	}
	
	private void initMetaDataObjectList(){
		metaDataObjectList = new LinkedList<MetaDataBean>();
		for(int i = 0; i < ids.size(); i++){
			metaDataObjectList.add(new MetaDataBean());
		}
	}
	
	private void initKeyWordToAttributeMap(){
		keywordToAttributeMap = new HashMap<>();
		keywordToAttributeMap.put("at", "Acquisition Time");
		keywordToAttributeMap.put("cn", "Campaign Name");
		keywordToAttributeMap.put("in", "Investigator");
		keywordToAttributeMap.put("fn", "File Name");
	}
	
	public List<MetaDataBean> fillMetaparameterValues(SPECCHIOClient specchio_client, ArrayList<Integer> ids){
		this.specchio_client = specchio_client;
		this.ids = ids;
		initMetaDataObjectList();
		
		fillFileNames();
		fillAcquisitionTime();
		fillCampaignName();
		fillInvestigator();
		
		return metaDataObjectList;
	}
	
	
	private void fillFileNames(){
		MatlabAdaptedArrayList<Object> resultList = getMetaparameterValues(ids, "File Name");
		for(int i = 0; i < resultList.size(); i++){
			MetaDataBean metaDO = metaDataObjectList.get(i);
			Object o = resultList.get(i);
			metaDO.setFileName(o != null ? o.toString() : "no data");
		}
	}
	
	private void fillAcquisitionTime(){
		MatlabAdaptedArrayList<Object> resultList = getMetaparameterValues(ids, "Acquisition Time");
		for(int i = 0; i < resultList.size(); i++){
			MetaDataBean metaDO = metaDataObjectList.get(i);
			Object o = resultList.get(i);
			metaDO.setAcquisitionTime(o != null ? o.toString() : "no data");
		}
	}
	
	private void fillCampaignName(){
		MatlabAdaptedArrayList<Object> resultList = getMetaparameterValues(ids, "Campaign Name");
		for(int i = 0; i < resultList.size(); i++){
			MetaDataBean metaDO = metaDataObjectList.get(i);
			Object o = resultList.get(i);
			metaDO.setCampaignName(o != null ? o.toString() : "no data");
		}
	}
	
	private void fillInvestigator(){
		MatlabAdaptedArrayList<Object> resultList = getMetaparameterValues(ids, "Investigator");
		for(int i = 0; i < resultList.size(); i++){
			MetaDataBean metaDO = metaDataObjectList.get(i);
			Object o = resultList.get(i);
			metaDO.setInvestigator(o != null ? o.toString() : "no data");
		}
	}
	
	private MatlabAdaptedArrayList<Object> getMetaparameterValues(ArrayList<Integer> ids, String metaparameter){
		return specchio_client.getMetaparameterValues(ids, metaparameter);
		
	}
	
	public String getAttributeForKeyword(String keyword){
		return keywordToAttributeMap.get(keyword);
	}

}
