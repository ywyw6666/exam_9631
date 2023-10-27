package com.exam.vo;

import com.exam.entity.RiskTop3;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatVo {

    String year;

    List<RiskTop3> riskTop3;



}
