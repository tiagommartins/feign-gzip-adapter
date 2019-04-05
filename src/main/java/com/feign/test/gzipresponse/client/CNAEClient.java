package com.feign.test.gzipresponse.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value = "cnae-client",
        url = "https://servicodados.ibge.gov.br/api/v2/cnae/subclasses/3511502")
public interface CNAEClient {

    @GetMapping(headers = {"Accept-Encoding=gzip, deflate"})
    ResponseEntity<byte[]> get();

}
