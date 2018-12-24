package com.hanshow.wise.base.common.dao;

import com.hanshow.wise.base.common.model.SysUserRoleExample;
import com.hanshow.wise.base.common.model.SysUserRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(SysUserRoleExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(SysUserRoleExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(SysUserRoleKey key);

    /**
     *
     * @mbg.generated
     */
    int insert(SysUserRoleKey record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SysUserRoleKey record);

    /**
     *
     * @mbg.generated
     */
    List<SysUserRoleKey> selectByExample(SysUserRoleExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysUserRoleKey record, @Param("example") SysUserRoleExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysUserRoleKey record, @Param("example") SysUserRoleExample example);
}