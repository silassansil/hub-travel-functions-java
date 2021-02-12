package br.com.yes.repository.impl;

import br.com.yes.domain.Agency;
import br.com.yes.repository.AgencyRepository;
import br.com.yes.repository.entity.AgencyEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AgencyRepositoryImpl extends AbstractRepository implements AgencyRepository {

    @Override
    public Agency saveOrUpdateAgency(Agency agency) {
        final AgencyEntity toSave = AgencyEntity.fromDomain(agency);
        return super.save(toSave)
                .toDomain();
    }

    @Override
    public Agency findAgencyById(String id) {
        return super.findById(id, AgencyEntity.class)
                .map(AgencyEntity::toDomain)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Agency> findAll() {
        return super.findAll(AgencyEntity.class)
                .stream()
                .map(AgencyEntity::toDomain)
                .collect(Collectors.toList());
    }
}
