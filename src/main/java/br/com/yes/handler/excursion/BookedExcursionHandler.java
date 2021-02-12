package br.com.yes.handler.excursion;

import br.com.yes.domain.Excursion;
import br.com.yes.handler.excursion.dto.ExcursionDTO;
import br.com.yes.service.ExcursionService;
import br.com.yes.service.impl.ExcursionServiceImpl;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class BookedExcursionHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final ExcursionService excursionService;
    private final ObjectMapper mapper;

    public BookedExcursionHandler() {
        this.excursionService = new ExcursionServiceImpl();
        this.mapper = new ObjectMapper();
    }

    @SneakyThrows
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        final Excursion excursion = this.mapper.readValue(input.getBody(), ExcursionDTO.class).toDomain();
        final Excursion booked = this.excursionService.bookNewExcursion(excursion);
        final ExcursionDTO dto = ExcursionDTO.fromDomain(booked);

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(201)
                .withBody(this.mapper.writeValueAsString(dto));
    }
}
