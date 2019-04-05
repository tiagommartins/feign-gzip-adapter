package com.feign.test.gzipresponse.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feign.test.gzipresponse.client.CNAEClient;
import com.feign.test.gzipresponse.client.CnaeIbgeDTO;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.feign.test.gzipresponse.utils.GzipUtil.unzip;
import static java.lang.String.format;

@Component
public class CNAEAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CNAEAdapter.class);

    private final CNAEClient cnaeClient;
    private final ObjectMapper mapper;

    @Autowired
    public CNAEAdapter(final CNAEClient cnaeClient, final ObjectMapper mapper) {
        this.cnaeClient = cnaeClient;
        this.mapper = mapper;
    }

    public List<CnaeIbgeDTO> get() {
        return Optional.ofNullable(cnaeClient.get())
                .filter(HttpEntity::hasBody)
                .map(r -> unzip(r.getBody()))
                .map(this::convertToList)
                .orElseGet(Lists::newArrayList);
    }

    private List<CnaeIbgeDTO> convertToList(String j) {
        try {
            return mapper.readValue(j, mapper.getTypeFactory().constructCollectionType(List.class, CnaeIbgeDTO.class));
        } catch (IOException e) {
            LOGGER.warn(format("Erro ao converter json: %s", j), e);
            return null;
        }
    }

}
