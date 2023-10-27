package com.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offences {
    //犯罪代号
    private int offenceCode;
    //罪名1
    private String crimeName1;
    //罪名2
    private String crimeName2;
    //罪名3
    private String crimeName3;
}
