package gov.nih.nci.evs.compare.searchservice.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SemanticTypeFilterTest {
	
	
 @Autowired
 private SemanticTypeFilter filter;

	@Test
	void test() {
	//	SemanticTypeFilter filter = new SemanticTypeFilter();
		assertTrue(filter.isSemanticType("Semantic_Type"));
	}

}
