package com.hanshow.wise.base.common.dao;

import com.hanshow.wise.base.common.model.SysOrganization;
import com.hanshow.wise.base.common.model.SysOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOrganizationMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(SysOrganizationExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(SysOrganizationExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(SysOrganization record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SysOrganization record);

    /**
     *
     * @mbg.generated
     */
    List<SysOrganization> selectByExample(SysOrganizationExample example);

    /**
     *
     * @mbg.generated
     */
    SysOrganization selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysOrganization record, @Param("example") SysOrganizationExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysOrganization record, @Param("example") SysOrganizationExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysOrganization record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysOrganization record);
}