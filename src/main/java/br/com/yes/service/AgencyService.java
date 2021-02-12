package br.com.yes.service;

import br.com.yes.domain.Agency;

import java.util.List;

public interface AgencyService {

    Agency registerAgency(final Agency agency);

    Agency updatePartnersList(final String agencyId, final List<String> agencies);

    Agency findAgencyById(final String agencyId);

    List<Agency> findAll();
}
