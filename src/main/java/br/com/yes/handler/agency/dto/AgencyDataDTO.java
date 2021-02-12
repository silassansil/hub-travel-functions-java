package br.com.yes.handler.agency.dto;

import br.com.yes.domain.Agency;
import br.com.yes.domain.Representative;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgencyDataDTO implements Serializable {

    private String id;
    private String agencyName;
    private String cnpj;
    private String representativeName;
    private String cpf;
    private String phone;
    private String email;
    private List<AgencyDataDTO> partners;

    public AgencyDataDTO() {
        this.partners = new ArrayList<>();
    }

    public Agency toDomain() {
        final List<Agency> partners = this.partners.stream()
                .map(AgencyDataDTO::toDomain)
                .collect(Collectors.toList());

        final Representative representative = Representative.builder()
                .name(this.representativeName)
                .cpf(this.cpf)
                .phone(this.phone)
                .email(this.email)
                .build();

        return Agency.builder()
                .name(this.agencyName)
                .cnpj(this.cnpj)
                .representative(representative)
                .partners(partners)
                .build();
    }

    public static AgencyDataDTO fromDomain(final Agency agency) {
        final List<AgencyDataDTO> partners = agency.getPartners()
                .stream()
                .map(AgencyDataDTO::fromDomain)
                .collect(Collectors.toList());

        return AgencyDataDTO.builder()
                .id(agency.getId().toString())
                .agencyName(agency.getName())
                .cnpj(agency.getCnpj())
                .representativeName(agency.getRepresentative().getName())
                .cpf(agency.getRepresentative().getCpf())
                .phone(agency.getRepresentative().getPhone())
                .email(agency.getRepresentative().getEmail())
                .partners(partners.isEmpty() ? null : partners)
                .build();
    }
}
