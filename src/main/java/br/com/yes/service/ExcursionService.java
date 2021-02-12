package br.com.yes.service;

import br.com.yes.domain.Excursion;

import java.util.List;
import java.util.UUID;

public interface ExcursionService {

    Excursion bookNewExcursion(final Excursion excursion);

    List<Excursion> findALlOrdered(final UUID agencyId);
}
