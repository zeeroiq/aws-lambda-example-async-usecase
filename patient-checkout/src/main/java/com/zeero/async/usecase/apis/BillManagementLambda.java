package com.zeero.async.usecase.apis;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.google.gson.Gson;
import com.zeero.async.usecase.apis.event.PatientCheckoutEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BillManagementLambda {

    private final Gson gson = new Gson();
    public void handler(SNSEvent snsEvent) {
        snsEvent.getRecords().parallelStream().forEach(record -> {
            try {

                PatientCheckoutEvent patientCheckoutEvent = gson.fromJson(record.getSNS().getMessage(), PatientCheckoutEvent.class);
                log.info("Consumed message from PATIENT_CHECKOUT_EVENT topic");
                log.info(gson.toJson(patientCheckoutEvent));
            } catch (Exception e) {
                log.error("Error occurred \n", e);
            }
        });
    }
}
