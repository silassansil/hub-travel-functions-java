package br.com.yes.repository;

import br.com.yes.domain.Excursion;

import java.util.List;

public interface ExcursionRepository {

    Excursion saveOrUpdateExcursion(final Excursion excursion);

    List<Excursion> findAll();
}
