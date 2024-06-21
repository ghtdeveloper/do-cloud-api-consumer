package com.neptune.service;

import com.neptune.domain.wdl.NumberToWords;
import com.neptune.domain.wdl.NumberToWordsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import java.math.BigInteger;
@RequiredArgsConstructor
@Service
@Slf4j
public class NumberConversionService  extends WebServiceGatewaySupport {

    private final WebServiceTemplate webServiceTemplate;

    public String generateNumberToWords(Long number) {
        NumberToWords request = new NumberToWords();
        request.setUbiNum(BigInteger.valueOf(number));
        NumberToWordsResponse response = (NumberToWordsResponse) webServiceTemplate
                .marshalSendAndReceive(request);
        return response.getNumberToWordsResult().trim();
    }
}
