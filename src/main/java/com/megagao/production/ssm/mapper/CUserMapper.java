package com.megagao.production.ssm.mapper;

import com.megagao.production.ssm.domain.vo.CUser;
import com.megagao.production.ssm.domain.vo.CUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CUserMapper {
    int countByExample(CUserExample example);

    int deleteByExample(CUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CUser record);

    int insertSelective(CUser record);

    List<CUser> selectByExample(CUserExample example);

    CUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CUser record, @Param("example") CUserExample example);

    int updateByExample(@Param("record") CUser record, @Param("example") CUserExample example);

    int updateByPrimaryKeySelective(CUser record);

    int updateByPrimaryKey(CUser record);
}