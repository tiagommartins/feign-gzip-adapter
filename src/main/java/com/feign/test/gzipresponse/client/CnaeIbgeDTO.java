package com.feign.test.gzipresponse.client;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class CnaeIbgeDTO {

    private String descricao;
    private String id;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CnaeIbgeDTO cnaeIbgeDTO = (CnaeIbgeDTO) o;

        return new EqualsBuilder()
                .append(descricao, cnaeIbgeDTO.descricao)
                .append(id, cnaeIbgeDTO.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(descricao)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("descricao", descricao)
                .append("id", id)
                .toString();
    }

}
