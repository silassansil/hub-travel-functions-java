package br.com.yes.handler.agency;

import br.com.yes.domain.Agency;
import br.com.yes.handler.agency.dto.AgencyDataDTO;
import br.com.yes.service.AgencyService;
import br.com.yes.service.impl.AgencyServiceImpl;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class FindAgencyByIdHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final AgencyService agencyService;
    private final ObjectMapper mapper;

    public FindAgencyByIdHandler() {
        this.agencyService = new AgencyServiceImpl();
        this.mapper = new ObjectMapper();
    }

    @Override
    @SneakyThrows
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        final String agencyId = input.getPathParameters().get("agencyId");

        final Agency agency = this.agencyService.findAgencyById(agencyId);

        final AgencyDataDTO dto = AgencyDataDTO.fromDomain(agency);
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody(this.mapper.writeValueAsString(dto));
    }
}
