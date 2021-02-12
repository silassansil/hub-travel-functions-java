package br.com.yes.handler.agency;

import br.com.yes.handler.agency.dto.AgencyDataDTO;
import br.com.yes.service.AgencyService;
import br.com.yes.service.impl.AgencyServiceImpl;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

public class FindAllAgenciesHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final AgencyService agencyService;
    private final ObjectMapper mapper;

    public FindAllAgenciesHandler() {
        this.agencyService = new AgencyServiceImpl();
        this.mapper = new ObjectMapper();
    }

    @Override
    @SneakyThrows
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

        final List<AgencyDataDTO> agencies = this.agencyService.findAll()
                .stream()
                .map(AgencyDataDTO::fromDomain)
                .collect(Collectors.toList());

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody(this.mapper.writeValueAsString(agencies));
    }
}
