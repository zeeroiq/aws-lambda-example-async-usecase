package com.zeero.async.usecase.apis.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientCheckoutEvent {
    private String firstname;
    private String lastname;
    private String ssn;
}
