package com.hanshow.wise.base.common.dao;

import com.hanshow.wise.base.common.model.SysRole;
import com.hanshow.wise.base.common.model.SysRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(SysRoleExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(SysRoleExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(SysRole record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SysRole record);

    /**
     *
     * @mbg.generated
     */
    List<SysRole> selectByExample(SysRoleExample example);

    /**
     *
     * @mbg.generated
     */
    SysRole selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysRole record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysRole record);
}