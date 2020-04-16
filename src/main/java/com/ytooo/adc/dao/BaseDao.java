package com.ytooo.adc.dao;

import com.ytooo.adc.entity.BaseEntity;
import com.ytooo.adc.page.BasePage;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {
  int insert(T paramT);

  int insertSelective(T paramT);

  int updateByPrimaryKey(T paramT);

  int updateByPrimaryKeySelective(T paramT);

  T selectByPrimaryKey(Object paramObject);

  int deleteByPrimaryKey(Object paramObject);

  List<T> queryByList(BasePage paramBasePage);

  int queryByCount(BasePage paramBasePage);

  List<T> queryByPage(BasePage paramBasePage);
}
