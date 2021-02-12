package br.com.yes.handler.excursion;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.models.dynamodb.AttributeValue;

import java.util.Map;

public class UploadDataSearchEngineHandler implements RequestHandler<DynamodbEvent, String> {

    @Override
    public String handleRequest(DynamodbEvent input, Context context) {
        for (DynamodbEvent.DynamodbStreamRecord record : input.getRecords()) {
            if ("INSERT".equals(record.getEventName()) || "MODIFY".equals(record.getEventName())) {
                final Map<String, AttributeValue> image = record.getDynamodb().getNewImage();
            }
        }
        return "Successfully processed " + input.getRecords().size() + " records.";
    }
}