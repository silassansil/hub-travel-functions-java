package br.com.yes.repository.impl;

import br.com.yes.domain.Excursion;
import br.com.yes.repository.ExcursionRepository;
import br.com.yes.repository.entity.AgencyEntity;
import br.com.yes.repository.entity.ExcursionEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ExcursionRepositoryImpl extends AbstractRepository implements ExcursionRepository {

    @Override
    public Excursion saveOrUpdateExcursion(Excursion excursion) {
        final ExcursionEntity toSave = ExcursionEntity.fromDomain(excursion);
        return super.save(toSave)
                .toDomain();
    }

    @Override
    public List<Excursion> findAll() {
        return super.findAll(ExcursionEntity.class)
                .stream()
                .map(ExcursionEntity::toDomain)
                .collect(Collectors.toList());
    }
}
