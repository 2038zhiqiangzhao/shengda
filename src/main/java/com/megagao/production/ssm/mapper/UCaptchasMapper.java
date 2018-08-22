package com.megagao.production.ssm.mapper;

import com.megagao.production.ssm.domain.vo.UCaptchas;
import com.megagao.production.ssm.domain.vo.UCaptchasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UCaptchasMapper {
    int countByExample(UCaptchasExample example);

    int deleteByExample(UCaptchasExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UCaptchas record);

    int insertSelective(UCaptchas record);

    List<UCaptchas> selectByExample(UCaptchasExample example);

    UCaptchas selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UCaptchas record, @Param("example") UCaptchasExample example);

    int updateByExample(@Param("record") UCaptchas record, @Param("example") UCaptchasExample example);

    int updateByPrimaryKeySelective(UCaptchas record);

    int updateByPrimaryKey(UCaptchas record);
}