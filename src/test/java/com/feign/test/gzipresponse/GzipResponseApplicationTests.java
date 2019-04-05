package com.feign.test.gzipresponse;

import com.feign.test.gzipresponse.business.CNAEAdapter;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GzipResponseApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(GzipResponseApplicationTests.class);

	@Autowired
    CNAEAdapter cnaeAdapter;

	@Test
	public void contextLoads() {
        LOGGER.info("{}", StringUtils.join(cnaeAdapter.get(), "\n"));
	}

}
