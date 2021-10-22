package gov.nih.nci.evs.compare.searchservice.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import gov.nih.nci.evs.compare.searchservice.model.ConceptWrapper;
import gov.nih.nci.evs.compare.searchservice.model.RestEntityWrapper;
import gov.nih.nci.evs.compare.searchservice.model.RestPropertyMetadata;
import gov.nih.nci.evs.compare.searchservice.util.CommonServices;

@Service
public class EVSAPIBaseService {

	private Logger log = LoggerFactory.getLogger(EVSAPIBaseService.class);

	@Value("${evs.api.url.baseurl}")
	private String baseURL;
	
	@Value("${evs.api.url.basesourceurl}")
	private String baseSourceURL;

	@Value("${evs.api.url.metadataurl}")
	private String baseMetaURL;

	@Value("${SUMMARY}")
	private String summary;

	@Value("${MAPS}")
	private String maps;

	@Value("${REST_PROP_URL}")
	private String propURL;

	@Value("${REST_SYN_URL}")
	private String synURL;

	@Value("${REST_DEF_URL}")
	private String defURL;

	@Value("${REST_STATUS_URL}")
	private String statusURL;

	@Value("${REST_DEFSOURCE_URL}")
	private String defSourceURL;

	@Value("${REST_DEFTYPE_URL}")
	private String defTypeURL;

	@Value("${REST_SYNSOURCE_URL}")
	private String synSourceURL;

	@Value("${REST_SYNTYPE_URL}")
	private String synTypeURL;

	@Value("${REST_QUERYTYPE}")
	private String queryType;
	
	@Value("${REST_SCOPETYPE}")
	private String scopeType;
	
	@Value("${REST_TERMINOLOGIES}")
	private String terminologies;

	public ConceptWrapper getConcepts(String term) {

		WebClient client = getNewWebClientWithBuffer();
		try {
			return client.get().uri(new URI(baseURL + "?term=" + term)).retrieve().bodyToMono(ConceptWrapper.class)
					.block();
		} catch (URISyntaxException e) {
			log.info("Bad Resource Request, check the URL for special characters: ", e);
			return null;
		}
	}

	public RestEntityWrapper getConceptsByInclusion(String term, String include) {
		WebClient client = getNewWebClientWithBuffer();
		try {
			return client.get().uri(new URI(baseURL + "?include=" + include + "&term=" + term)).retrieve()
					.bodyToMono(RestEntityWrapper.class).block();
		} catch (URISyntaxException e) {
			log.info("Bad Resource Request, check the URL for special characters: ", e);
			return null;
		}
	}

	public RestEntityWrapper getConceptsByInclusionAndType(String term, String include, String type) {
		WebClient client = getNewWebClientWithBuffer();
		term = term.trim();
		term = term.replace(" ", "%20");
		try {
			return client.get().uri(new URI(baseURL + "?include=" + include + "&type=" + type + "&term=" + term))
					.retrieve().bodyToMono(RestEntityWrapper.class).block();
		} catch (URISyntaxException e) {
			log.info("Bad Resource Request, check the URL for special characters: ", e);
			return null;
		}
	}
	
	public RestEntityWrapper getConceptsBySourcePropsInclusionType(String source, String props, String includes, String querytype, String terms, String start, String size) {
		WebClient client = getNewWebClientWithBuffer();
		terms = terms.trim();
		terms = terms.replace(" ", "%20");
		try {
			return client.get().uri(new URI(
					baseURL + "/" + source + "/search" + "?props=" + props 
					+ "&includes=" + includes + "&type=" + querytype 
					+ "&term=" + terms + "&fromRecord=" + start + "&pageSize=" + size))
					.retrieve().bodyToMono(RestEntityWrapper.class).block();
		} catch (URISyntaxException e) {
			log.info("Bad Resource Request, check the URL for special characters: ", e);
			return null;
		}
	}

	public RestPropertyMetadata[] getRestProperties(RestTemplate template) {
		return template.getForObject(baseMetaURL + propURL, RestPropertyMetadata[].class);

	}

	public RestPropertyMetadata[] getRestSynonyms(RestTemplate template) {
		return template.getForObject(baseMetaURL + synURL, RestPropertyMetadata[].class);

	}

	public RestPropertyMetadata[] getRestDefinitions(RestTemplate template) {
		return template.getForObject(baseMetaURL + defURL, RestPropertyMetadata[].class);

	}

	public List<String> getRestConceptStatus() {
		WebClient client = getNewWebClientWithBuffer();
		return Stream.of(client.get().uri(baseMetaURL + statusURL).retrieve().bodyToMono(String[].class).block())
				.collect(Collectors.toList());
	}

	public List<RestPropertyMetadata> getRestDefSource() {
		WebClient client = getNewWebClientWithBuffer();
		return Stream.of(client.get().uri(baseMetaURL + defSourceURL).retrieve()
				.bodyToMono(RestPropertyMetadata[].class).block()).collect(Collectors.toList());
	}

	public List<RestPropertyMetadata> getRestDefType() {
		WebClient client = getNewWebClientWithBuffer();
		return Stream.of(
				client.get().uri(baseMetaURL + defTypeURL).retrieve().bodyToMono(RestPropertyMetadata[].class).block())
				.collect(Collectors.toList());
	}

	public List<RestPropertyMetadata> getRestProperties() {
		WebClient client = getNewWebClientWithBuffer();
		return Stream
				.of(client.get().uri(baseMetaURL + propURL).retrieve().bodyToMono(RestPropertyMetadata[].class).block())
				.collect(Collectors.toList());
	}

	public List<RestPropertyMetadata> getRestSynSource() {
		WebClient client = getNewWebClientWithBuffer();
		return Stream.of(client.get().uri(baseMetaURL + synSourceURL).retrieve()
				.bodyToMono(RestPropertyMetadata[].class).block()).collect(Collectors.toList());
	}

	public List<RestPropertyMetadata> getRestSynType() {
		WebClient client = getNewWebClientWithBuffer();
		return Stream.of(
				client.get().uri(baseMetaURL + synTypeURL).retrieve().bodyToMono(RestPropertyMetadata[].class).block())
				.collect(Collectors.toList());
	}

//	public String getCuratedTopNodeList() {
//		return curatedTopNodeList;
//	}
//
//	public String getBaseURL() {
//		return baseURL;
//	}
//
//	public String getChildren() {
//		return children;
//	}
//
//	public String getDescendants() {
//		return descendants;
//	}
//
//	public String getSummary() {
//		return summary;
//	}
//
//	public String getMaps() {
//		return maps;
//	}
//
//	public String getParents() {
//		return parents;
//	}
//
//	public String getPropURL() {
//		return propURL;
//	}
//
//	public String getFilterList() {
//		return filterList;
//	}
//
//	public String getRootFilterList() {
//		return rootFilterList;
//	}
//	
//	public String getEmailList() {
//		return emailList;
//	}
//
//	public void setEmailList(String emailList) {
//		this.emailList = emailList;
//	}
//
//	public String getSmtpServer() {
//		return smtpServer;
//	}
//
//	public void setSmtpServer(String smtpServer) {
//		this.smtpServer = smtpServer;
//	}
//
//
//	public String getDefaultEmail() {
//		return defaultEmail;
//	}
//
//	public void setDefaultEmail(String defaultEmail) {
//		this.defaultEmail = defaultEmail;
//	}
//
	public WebClient getNewWebClientWithBuffer() {

		return WebClient.builder()
				.exchangeStrategies(ExchangeStrategies.builder()
						.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(76 * 256 * 1024)).build())
				.build();
	}

	public List<String> getRestQueryType() {
		return Stream.of(queryType.split(",")).collect(Collectors.toList());
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getBaseMetaURL() {
		return baseMetaURL;
	}

	public void setBaseMetaURL(String baseMetaURL) {
		this.baseMetaURL = baseMetaURL;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getMaps() {
		return maps;
	}

	public void setMaps(String maps) {
		this.maps = maps;
	}

	public String getPropURL() {
		return propURL;
	}

	public void setPropURL(String propURL) {
		this.propURL = propURL;
	}

	public String getSynURL() {
		return synURL;
	}

	public void setSynURL(String synURL) {
		this.synURL = synURL;
	}

	public String getDefURL() {
		return defURL;
	}

	public void setDefURL(String defURL) {
		this.defURL = defURL;
	}

	public String getStatusURL() {
		return statusURL;
	}

	public void setStatusURL(String statusURL) {
		this.statusURL = statusURL;
	}

	public String getDefSourceURL() {
		return defSourceURL;
	}

	public void setDefSourceURL(String defSourceURL) {
		this.defSourceURL = defSourceURL;
	}

	public String getDefTypeURL() {
		return defTypeURL;
	}

	public void setDefTypeURL(String defTypeURL) {
		this.defTypeURL = defTypeURL;
	}

	public String getSynSourceURL() {
		return synSourceURL;
	}

	public void setSynSourceURL(String synSourceURL) {
		this.synSourceURL = synSourceURL;
	}

	public String getSynTypeURL() {
		return synTypeURL;
	}

	public void setSynTypeURL(String synTypeURL) {
		this.synTypeURL = synTypeURL;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public List<String> getScopeType() {
		return CommonServices.splitInput(scopeType);
	}

	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}

	public List<String> getTerminologies() {
		return CommonServices.splitInput(terminologies);
	}


}
