package com.hanshow.wise.base.common.dao;

import com.hanshow.wise.base.common.model.SysRoleResourceExample;
import com.hanshow.wise.base.common.model.SysRoleResourceKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleResourceMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(SysRoleResourceExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(SysRoleResourceExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(SysRoleResourceKey key);

    /**
     *
     * @mbg.generated
     */
    int insert(SysRoleResourceKey record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SysRoleResourceKey record);

    /**
     *
     * @mbg.generated
     */
    List<SysRoleResourceKey> selectByExample(SysRoleResourceExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysRoleResourceKey record, @Param("example") SysRoleResourceExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysRoleResourceKey record, @Param("example") SysRoleResourceExample example);
}