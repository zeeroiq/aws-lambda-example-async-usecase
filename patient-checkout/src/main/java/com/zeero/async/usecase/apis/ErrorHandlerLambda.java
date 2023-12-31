package com.zeero.async.usecase.apis;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorHandlerLambda {

    public void handler(SQSEvent event) {
        event
                .getRecords()
                .parallelStream()
                .forEach(snsRecord -> log.info("Dead Later Queue Event - " + snsRecord.toString()));
    }
}
