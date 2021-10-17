/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.providers.models.ModulesResponse;

/**
 *
 * @author juan barraza
 */
// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import io.quicktype.Converter;
//
// Then you can deserialize a JSON string with
//
//     ModelResponse data = Converter.fromJsonString(jsonString);

import com.fasterxml.jackson.annotation.*;

public class ModelResponse {
    private boolean success;
    private ModulesObject[] data;
    private String message;

    @JsonProperty("success")
    public boolean getSuccess() { return success; }
    @JsonProperty("success")
    public void setSuccess(boolean value) { this.success = value; }

    @JsonProperty("data")
    public ModulesObject[] getData() { return data; }
    @JsonProperty("data")
    public void setData(ModulesObject[] value) { this.data = value; }

    @JsonProperty("message")
    public String getMessage() { return message; }
    @JsonProperty("message")
    public void setMessage(String value) { this.message = value; }
}

