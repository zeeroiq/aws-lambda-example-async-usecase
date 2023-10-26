package com.zeero.async.usecase.apis;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.google.gson.Gson;
import com.zeero.async.usecase.apis.event.PatientCheckoutEvent;

import java.util.Arrays;
import java.util.List;

/**
 * Handler for requests to Lambda function.
 */
public class PatientCheckoutLambda {

    private final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
    private final Gson gson = new Gson();

    public void handleRequest(S3Event s3Event, final Context context) {
        try {
        s3Event.getRecords().parallelStream().forEach(record -> {
            S3ObjectInputStream content = s3.getObject(record.getS3().getBucket().getName(),
                            record.getS3().getObject().getKey())
                    .getObjectContent();
            List<PatientCheckoutEvent> checkoutEventsPatients = Arrays.asList(gson.fromJson(gson.toJson(content), PatientCheckoutEvent[].class));
            context.getLogger().log(checkoutEventsPatients.toString());
        });
        } catch (Exception e) {
            context.getLogger().log("Error occurred ");
            context.getLogger().log(e.getLocalizedMessage());
        }
    }
}
