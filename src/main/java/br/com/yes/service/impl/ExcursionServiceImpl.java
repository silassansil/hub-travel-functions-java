package br.com.yes.service.impl;

import br.com.yes.domain.Excursion;
import br.com.yes.repository.ExcursionRepository;
import br.com.yes.repository.impl.ExcursionRepositoryImpl;
import br.com.yes.service.AgencyService;
import br.com.yes.service.ExcursionService;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExcursionServiceImpl implements ExcursionService {

    private final ExcursionRepository excursionRepository;
    private final AgencyService agencyService;

    public ExcursionServiceImpl() {
        this.excursionRepository = new ExcursionRepositoryImpl();
        this.agencyService = new AgencyServiceImpl();
    }

    @Override
    public Excursion bookNewExcursion(final Excursion excursion) {
        return this.excursionRepository.saveOrUpdateExcursion(excursion);
    }

    @Override
    public List<Excursion> findALlOrdered(final UUID agencyId) {
        final List<UUID> partners = this.agencyService.findAgencyById(agencyId.toString())
                .retrievePartnersId();

        return this.excursionRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(p -> partners.indexOf(p.getOwnerId())))
                .collect(Collectors.toList());
    }
}
