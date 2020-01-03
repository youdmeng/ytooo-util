package com.ytooo.repository.dao;

import com.ytooo.repository.QuartzInfo;
import com.ytooo.repository.QuartzInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuartzInfoMapper {
    long countByExample(QuartzInfoExample example);

    int deleteByExample(QuartzInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QuartzInfo record);

    int insertSelective(QuartzInfo record);

    List<QuartzInfo> selectByExample(QuartzInfoExample example);

    QuartzInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QuartzInfo record, @Param("example") QuartzInfoExample example);

    int updateByExample(@Param("record") QuartzInfo record, @Param("example") QuartzInfoExample example);

    int updateByPrimaryKeySelective(QuartzInfo record);

    int updateByPrimaryKey(QuartzInfo record);
}