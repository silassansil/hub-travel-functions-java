package br.com.yes.handler.excursion;

import br.com.yes.handler.excursion.dto.ExcursionDTO;
import br.com.yes.service.ExcursionService;
import br.com.yes.service.impl.ExcursionServiceImpl;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FindAllExcursionOrderedByPartners implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final ExcursionService excursionService;
    private final ObjectMapper mapper;

    public FindAllExcursionOrderedByPartners() {
        this.excursionService = new ExcursionServiceImpl();
        this.mapper = new ObjectMapper();
    }

    @Override
    @SneakyThrows
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        final UUID agencyId = UUID.fromString(input.getPathParameters().get("agencyId"));
        final List<ExcursionDTO> excursions = this.excursionService.findALlOrdered(agencyId)
                .stream()
                .map(ExcursionDTO::fromDomain)
                .collect(Collectors.toList());

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody(this.mapper.writeValueAsString(excursions));
    }
}
