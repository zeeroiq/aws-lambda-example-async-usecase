package com.zeero.async.usecase.apis;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.google.gson.Gson;
import com.zeero.async.usecase.apis.event.PatientCheckoutEvent;

public class BillManagementLambda {

    private final Gson gson = new Gson();
    public void handler(SNSEvent snsEvent, Context context) {
        snsEvent.getRecords().parallelStream().forEach(record -> {
            try {

                PatientCheckoutEvent patientCheckoutEvent = gson.fromJson(record.getSNS().getMessage(), PatientCheckoutEvent.class);
                context.getLogger().log("Consumed message from PATIENT_CHECKOUT_EVENT topic");

            } catch (Exception e) {
                context.getLogger().log("Error occurred ");
                context.getLogger().log(e.getLocalizedMessage());
            }
        });
    }
}
