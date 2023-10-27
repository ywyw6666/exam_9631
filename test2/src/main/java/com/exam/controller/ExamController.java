package com.exam.controller;

import com.exam.dto.DataDto;
import com.exam.vo.InsertVo;
import com.exam.entity.Crimes;
import com.exam.result.Result;
import com.exam.service.ExamService;
import com.exam.vo.StatVo;
import com.fasterxml.jackson.databind.util.BeanUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/crime")
@Slf4j
public class ExamController {

    @Resource
    private ExamService examService;
    /**
     * 新增犯罪记录接口
     * @param
     * @return
     */
    @PostMapping
    public Result<InsertVo> save(@RequestBody DataDto<Crimes> DataDto , HttpServletRequest request){

        int incidentID= examService.save(DataDto.getData());
        InsertVo dto = new InsertVo();
        dto.setIncidentID(incidentID);
        return Result.success(dto);
    }

    /**
     * 城市风险指数统计接口
     *
     * @return
     */
    @GetMapping("/stat")
    @ApiOperation("城市风险指数统计")
    public Result<List<StatVo>> stat(){
        List<StatVo> statVo = examService.stat();
        return Result.success(statVo);
    }
}
