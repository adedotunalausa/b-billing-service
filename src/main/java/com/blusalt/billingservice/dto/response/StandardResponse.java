package com.blusalt.billingservice.dto.response;

import com.blusalt.billingservice.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponse implements Serializable {

    @JsonProperty
    protected Status status;

}
