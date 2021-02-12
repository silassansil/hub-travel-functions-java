package br.com.yes.service.impl;

import br.com.yes.domain.Agency;
import br.com.yes.repository.AgencyRepository;
import br.com.yes.repository.impl.AgencyRepositoryImpl;
import br.com.yes.service.AgencyService;

import java.util.List;
import java.util.stream.Collectors;

public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;

    public AgencyServiceImpl() {
        this.agencyRepository = new AgencyRepositoryImpl();
    }

    @Override
    public Agency registerAgency(final Agency agency) {
        return this.agencyRepository.saveOrUpdateAgency(agency);
    }

    @Override
    public Agency updatePartnersList(final String agencyId, final List<String> agencies) {
        final List<Agency> partnersToSave = agencies.stream()
                .map(this.agencyRepository::findAgencyById)
                .collect(Collectors.toList());

        final Agency toSave = this.agencyRepository.findAgencyById(agencyId)
                .addNewPartner(partnersToSave);

        return this.agencyRepository.saveOrUpdateAgency(toSave);
    }

    @Override
    public Agency findAgencyById(String agencyId) {
        return this.agencyRepository.findAgencyById(agencyId);
    }

    @Override
    public List<Agency> findAll() {
        return this.agencyRepository.findAll();
    }
}
