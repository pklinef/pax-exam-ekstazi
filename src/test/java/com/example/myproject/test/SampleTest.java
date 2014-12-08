package com.example.myproject.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.sample9.pde.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.ops4j.pax.exam.CoreOptions.*;

@RunWith(PaxExam.class)
public class SampleTest {

    Logger logger = LoggerFactory.getLogger(SampleTest.class);

    @Rule
    public TestName name = new TestName();

    @Inject
    private HelloService helloService;

    @Configuration
    public Option[] config() {
        return options(
            mavenBundle("org.ops4j.pax.exam.samples", "pax-exam-sample9-pde", "4.3.0"),
            wrappedBundle(mavenBundle().groupId("org.ekstazi").artifactId("org.ekstazi.core").version("4.3.1"))
                .exports("*"),
            junitBundles()
        );
    }

    @Test
    public void getHelloService() {
        logger.info("Starting {}", name.getMethodName());
        assertNotNull(helloService);
        assertEquals("Hello Pax!", helloService.getMessage());
        logger.info("Done {}", name.getMethodName());
    }
}