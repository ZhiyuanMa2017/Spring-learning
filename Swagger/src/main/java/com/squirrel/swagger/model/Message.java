package com.squirrel.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

@ApiModel("Message")
public class Message {

    @ApiModelProperty(value = "id")
    private Long id;
    @NotEmpty
    @ApiModelProperty(value = "message body", example = "text")
    private String text;
    @NotEmpty
    @ApiModelProperty(value = "message summary", example = "summary")
    private String summary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Message {" + "id =" + id
                + ", text =" + text + '\''
                + ", summary =" + summary + '\'' + '}';
    }
}
