package com.megagao.production.ssm.mapper;

import com.megagao.production.ssm.domain.vo.BaseSequence;
import com.megagao.production.ssm.domain.vo.BaseSequenceExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseSequenceMapper {
	/**
	 * 根据序列名称获取下个值
	 * 
	 * @param seqName
	 * @return
	 */
	Integer getSequenceNextval(String seqName);
	Date queryNow();
    int countByExample(BaseSequenceExample example);

    int deleteByExample(BaseSequenceExample example);

    int deleteByPrimaryKey(Integer sequenceId);

    int insert(BaseSequence record);

    int insertSelective(BaseSequence record);

    List<BaseSequence> selectByExample(BaseSequenceExample example);

    BaseSequence selectByPrimaryKey(Integer sequenceId);

    int updateByExampleSelective(@Param("record") BaseSequence record, @Param("example") BaseSequenceExample example);

    int updateByExample(@Param("record") BaseSequence record, @Param("example") BaseSequenceExample example);

    int updateByPrimaryKeySelective(BaseSequence record);

    int updateByPrimaryKey(BaseSequence record);
}