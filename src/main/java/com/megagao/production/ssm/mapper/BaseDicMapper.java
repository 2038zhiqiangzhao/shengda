package com.megagao.production.ssm.mapper;

import com.megagao.production.ssm.domain.vo.BaseDic;
import com.megagao.production.ssm.domain.vo.BaseDicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseDicMapper {
	//查询所有
	 List<BaseDic> selectAll();
    int countByExample(BaseDicExample example);

    int deleteByExample(BaseDicExample example);

    int insert(BaseDic record);

    int insertSelective(BaseDic record);

    List<BaseDic> selectByExample(BaseDicExample example);

    int updateByExampleSelective(@Param("record") BaseDic record, @Param("example") BaseDicExample example);

    int updateByExample(@Param("record") BaseDic record, @Param("example") BaseDicExample example);
}