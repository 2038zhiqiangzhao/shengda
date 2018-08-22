package com.megagao.production.ssm.mapper;

import com.megagao.production.ssm.domain.vo.BaseSystem;
import com.megagao.production.ssm.domain.vo.BaseSystemExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseSystemMapper {
	Date queryNow();
    int countByExample(BaseSystemExample example);

    int deleteByExample(BaseSystemExample example);

    int deleteByPrimaryKey(Integer sysParaId);

    int insert(BaseSystem record);

    int insertSelective(BaseSystem record);

    List<BaseSystem> selectByExample(BaseSystemExample example);

    BaseSystem selectByPrimaryKey(Integer sysParaId);

    int updateByExampleSelective(@Param("record") BaseSystem record, @Param("example") BaseSystemExample example);

    int updateByExample(@Param("record") BaseSystem record, @Param("example") BaseSystemExample example);

    int updateByPrimaryKeySelective(BaseSystem record);

    int updateByPrimaryKey(BaseSystem record);
}