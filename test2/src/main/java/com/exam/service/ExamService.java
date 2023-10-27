package com.exam.service;

import com.exam.entity.Crimes;
import com.exam.vo.StatVo;

import java.util.List;

public interface ExamService {
    /**
     * 新增
     * @param crime
     */
    int save(Crimes crime);

    /**
     * 城市风险指数统计
     */
    List<StatVo> stat();
}
