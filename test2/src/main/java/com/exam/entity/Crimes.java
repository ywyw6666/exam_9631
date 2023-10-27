package com.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crimes implements Serializable {


    private int incidentId;
    @JsonProperty(value = "offenceCode")
    @NotNull(message = "犯罪代号不能为空")
    private int offenceCode;
    @JsonProperty(value = "dispatchTime")
    @NotNull(message = "派单时间不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dispatchTime;
    @JsonProperty(value = "victims")
    @NotNull(message = "受害者人数不能为空")
    private int victims;
    @JsonProperty(value = "city")
    @NotBlank(message = "城市不能为空")
    private String city;
    @JsonProperty(value = "startDateTime")
    @NotNull(message = "发生时间不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDateTime;
}
