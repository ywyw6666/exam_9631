package com.exam.mapper;
import com.exam.entity.Crimes;
import com.exam.entity.StatEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface  ExamMapper {
    /**
     * 插入数据
     * @param crimes
     */
    void insert(Crimes crimes);

    Integer queryMaxId();

    List<StatEntity> queryStatVo();
}
