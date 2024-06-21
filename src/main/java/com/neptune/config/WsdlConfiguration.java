package com.neptune.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
@SuppressWarnings("unused")
@Configuration
public class WsdlConfiguration {

    @SuppressWarnings("unused")
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.neptune.domain.wdl");
        return marshaller;
    }
    @SuppressWarnings("unused")
    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);
        webServiceTemplate.setDefaultUri("https://www.dataaccess.com/webservicesserver/numberconversion.wso");
        return webServiceTemplate;
    }

}
