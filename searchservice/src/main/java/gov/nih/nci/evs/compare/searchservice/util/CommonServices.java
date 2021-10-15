package gov.nih.nci.evs.compare.searchservice.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gov.nih.nci.evs.compare.searchservice.model.Association;
import gov.nih.nci.evs.compare.searchservice.model.Format;
import gov.nih.nci.evs.compare.searchservice.model.Property;
import gov.nih.nci.evs.compare.searchservice.model.PropertyPrime;
import gov.nih.nci.evs.compare.searchservice.model.Rel;
import gov.nih.nci.evs.compare.searchservice.model.Role;
import gov.nih.nci.evs.compare.searchservice.model.Synonym;
import gov.nih.nci.evs.compare.searchservice.model.TypeListAndPositionTuple;

public class CommonServices {
	
	
	public String adjustTextForContainedComma(String text) {
	 if(text.contains(",")) {
		 return "\"" + text + "\"";
	 }
	 else {return text;}
	}
	
	public static String cleanListOutPut(String list){
		if (list == null)  return null;
		return list.replace("[", "").replace("]", "");
	}
	
	
	public boolean existsCheck(List<? extends PropertyPrime> x) {
		return x == null || x.size() == 0;
	}
	
	public List<Property> validateAndOrCreateEmptyPropertyList(TypeListAndPositionTuple tuple){
		if(tuple.getPos() == null) {return new ArrayList<Property>();}
		else {return tuple.getProperties();}
	}
	
	public List<String> getHeadersByPosition(ConcurrentMap<String, TypeListAndPositionTuple> map) {
		return map.values()
				.stream()
				.sorted(Comparator
				.comparing(TypeListAndPositionTuple::getPos))
				.map(x -> x.getType())
				.collect(Collectors.toList());
	}
	
	public static List<String> splitInput(String codes){
		if(codes == null) {
			System.out.println("Input for code, property, or source cannot be null");
			return null;
		}
		return Arrays.asList(codes.split(","));
	}
	
	public static Gson getGsonForPrettyPrint() {
		return new GsonBuilder().setPrettyPrinting().create();
	}
	
	public static RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		builder.additionalMessageConverters(messageConverters);
		return builder.build();
	}
	
	public static RestTemplate getRestTemplate() {
		RestTemplateBuilder builder = new RestTemplateBuilder();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		builder.additionalMessageConverters(messageConverters);
		return builder.build();
	}
	
	  public static List<String> getCodesListForCode(String code){
		  List<String> list = new ArrayList<String>();
		  list.add(code);
		  return list;
	  }
	  
	  
	 public static void saveOrUpdateWeightedRels(Rel role, Hashtable<String, Rel> wRoles) {
			Rel rStored = wRoles.get(role.getType());
			role.setWeight(1);
			if(rStored == null){wRoles.put(role.getType(), role);
			}else {rStored.setWeight(rStored.getWeight() + 1);
			}
		}
	 
		
		public static List<Rel> getSortedRels(List<? extends Rel> rels){
			return CommonServices.sortRelListByWeight(getDistinctWeightedRelsForEntityCodes(rels));
		}
		
		public static List<Rel> getDistinctWeightedRelsForEntityCodes(List<? extends Rel> rawRels){
			Hashtable<String,Rel> distinctRels = new Hashtable<String,Rel>();	
			rawRels.stream().forEach(x -> CommonServices.saveOrUpdateWeightedRels(x, distinctRels));
			return distinctRels.values().stream().collect(Collectors.toList());
		}
	 
		public static List<Rel> sortRelListByWeight(List<Rel> rels){
			Collections.sort(rels, new Comparator<Rel>() {            @Override
	            public int compare(Rel r1, Rel r2) {
	            return r2.getWeight() - r1.getWeight();
	        }});
			return rels;
		}


}
