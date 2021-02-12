package br.com.yes.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class Agency implements Serializable {

    private UUID id;
    private String name;
    private String cnpj;
    private Representative representative;
    private List<Agency> partners;

    public Agency() {
        this.representative = new Representative();
        this.partners = new ArrayList<>();
    }

    public Agency(UUID id) {
        this();
        this.id = id;
    }

    public Agency addNewPartner(final Collection<Agency> agencies) {
        if (CollectionUtils.isEmpty(this.partners)) this.partners = new ArrayList<>();
        this.partners.addAll(agencies);
        return this;
    }

    public List<UUID> retrievePartnersId() {
        return this.partners.stream()
                .map(Agency::getId)
                .collect(Collectors.toList());
    }
}
