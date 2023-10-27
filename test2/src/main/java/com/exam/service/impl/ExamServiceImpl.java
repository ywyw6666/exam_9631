package com.exam.service.impl;

import com.exam.entity.Crimes;
import com.exam.entity.RiskTop3;
import com.exam.entity.StatEntity;
import com.exam.mapper.ExamMapper;
import com.exam.service.ExamService;
import com.exam.vo.StatVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类业务层
 */
@Service
@Slf4j
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;
    /**
     * 新增
     * @param crimes
     */
    public int save(Crimes crimes) {
        examMapper.insert(crimes);
        int incidentID =  examMapper.queryMaxId();
        return incidentID;
    }

    public List<StatVo> stat(){
        List<StatVo> statVos = new ArrayList<>();

        List<StatEntity> entityList = examMapper.queryStatVo();
        String lastYear = entityList.get(entityList.size()-1).getYear();
        for (int i = 0; i < entityList.size(); i++) {
            StatVo statVo = new StatVo();
            List<RiskTop3> riskTop3s = new ArrayList<>();
            StatEntity entity = entityList.get(i);
            statVo.setYear(entity.getYear());
            RiskTop3 top30 = new RiskTop3();
            top30.setCity(entity.getCity());
            top30.setRank(entity.getRn());
            top30.setRiskIndex(entity.getRiskIndex());
            riskTop3s.add(top30);
            for (int j = i; j < entityList.size()-1; j++) {
                StatEntity entity2 = entityList.get(j+1);
                if (!entity2.getYear().equals(entity.getYear())||i<j-3){
                    i=j;
                    break;
                }else{
                    RiskTop3 top3 = new RiskTop3();
                    top3.setCity(entity2.getCity());
                    top3.setRank(entity2.getRn());
                    top3.setRiskIndex(entity2.getRiskIndex());
                    riskTop3s.add(top3);
                }
            }
            statVo.setRiskTop3(riskTop3s);
            statVos.add(statVo);
            if (lastYear.equals(entity.getYear())){
                break;
            }
        }
        return statVos;
    }
}
