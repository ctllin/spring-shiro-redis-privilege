package com.hanshow.wise.base.common.dao;

import com.hanshow.wise.base.common.model.SysUser;
import com.hanshow.wise.base.common.model.SysUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(SysUserExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(SysUserExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(SysUser record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SysUser record);

    /**
     *
     * @mbg.generated
     */
    List<SysUser> selectByExample(SysUserExample example);

    /**
     *
     * @mbg.generated
     */
    SysUser selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysUser record);
}