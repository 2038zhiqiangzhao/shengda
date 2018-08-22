package com.megagao.production.ssm.mapper;

import com.megagao.production.ssm.domain.vo.Testdata;
import com.megagao.production.ssm.domain.vo.TestdataExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TestdataMapper {
	//扩展的mapper接口方法
	List<Testdata> find();
	
	//逆向工程生成的mapper接口
    int countByExample(TestdataExample example);

    int deleteByExample(TestdataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Testdata record);

    int insertSelective(Testdata record);

    List<Testdata> selectByExample(TestdataExample example);

    Testdata selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Testdata record, @Param("example") TestdataExample example);

    int updateByExample(@Param("record") Testdata record, @Param("example") TestdataExample example);

    int updateByPrimaryKeySelective(Testdata record);

    int updateByPrimaryKey(Testdata record);
}