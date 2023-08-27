package ru.vanjo.qa.uiautotestsstarter.helpers.testrail.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("test_id")
    public Integer testId;
    @JsonProperty("status_id")
    public Integer statusId;
    @JsonProperty("created_by")
    public Integer createdBy;
    @JsonProperty("created_on")
    public Integer createdOn;
    @JsonProperty("assignedto_id")
    public Integer assignedtoId;
    @JsonProperty("comment")
    public String comment;
    @JsonProperty("version")
    public String version;
    @JsonProperty("elapsed")
    public String elapsed;
    @JsonProperty("defects")
    public String defects;
}
