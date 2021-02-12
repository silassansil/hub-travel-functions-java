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

public class RegisterAgencyHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final AgencyService agencyService;
    private final ObjectMapper mapper;

    public RegisterAgencyHandler() {
        this.agencyService = new AgencyServiceImpl();
        this.mapper = new ObjectMapper();
    }

    @SneakyThrows
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        final Agency agency = this.mapper.readValue(input.getBody(), AgencyDataDTO.class).toDomain();
        final Agency registered = this.agencyService.registerAgency(agency);
        final AgencyDataDTO dto = AgencyDataDTO.fromDomain(registered);
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(201)
                .withBody(this.mapper.writeValueAsString(dto));
    }
}
