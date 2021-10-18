package gov.nih.nci.evs.compare.searchservice.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gov.nih.nci.evs.compare.searchservice.service.FormattedOutputService;

@RestController
public class FormattedOutputController {

	@Value("${RESULT_TIME_OUT:3600000}")
	public int resultTimeOut;

	@Autowired
	FormattedOutputService service;

	@GetMapping(value = "/get-minfile-for-readCodes/{props}/{terms}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<InputStreamResource> getFileByFormat(@PathVariable String terms, @PathVariable String props) {
		ByteArrayInputStream in;
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", "attachment; filename=" + "output" + ".txt");
		in = (ByteArrayInputStream) service.produceMinTabDelOutputFromList(terms, null, "match", "0", "10");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}
	
	@GetMapping(value = "/get-advancedfile-for-readCodes/{props}/{terms}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<InputStreamResource> getFullFileByFormat(@PathVariable String terms, @PathVariable String props) {
		ByteArrayInputStream in;
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", "attachment; filename=" + "output" + ".txt");
		in = (ByteArrayInputStream) service.produceMinTabDelOutputFromList(terms, null, "match", "0", "10");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}
}
