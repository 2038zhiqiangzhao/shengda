package com.megagao.production.ssm.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.megagao.production.ssm.domain.vo.FileTestExample;
import com.megagao.production.ssm.domain.vo.FileTestVo;

public interface FileTestMapper {
	int countByExample(FileTestExample example);

    int deleteByExample(FileTestExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FileTestVo record);

    int insertSelective(FileTestVo record);

    List<FileTestVo> selectByExample(FileTestExample example);

    FileTestVo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FileTestVo record, @Param("example") FileTestExample example);

    int updateByExample(@Param("record") FileTestVo record, @Param("example") FileTestExample example);

    int updateByPrimaryKeySelective(FileTestVo record);

    int updateByPrimaryKey(FileTestVo record);
}