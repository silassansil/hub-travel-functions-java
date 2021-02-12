package br.com.yes.repository;

import br.com.yes.domain.Agency;

import java.util.List;

public interface AgencyRepository {

    Agency saveOrUpdateAgency(final Agency agency);

    Agency findAgencyById(final String id);

    List<Agency> findAll();
}
