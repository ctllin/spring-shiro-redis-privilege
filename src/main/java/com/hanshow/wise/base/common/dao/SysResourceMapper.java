package com.hanshow.wise.base.common.dao;

import com.hanshow.wise.base.common.model.SysResource;
import com.hanshow.wise.base.common.model.SysResourceExample;
import java.util.List;

import com.hanshow.wise.base.common.model.dto.SysResourceDto;
import org.apache.ibatis.annotations.Param;

public interface SysResourceMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(SysResourceExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(SysResourceExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(SysResource record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SysResource record);

    /**
     *
     * @mbg.generated
     */
    List<SysResource> selectByExample(SysResourceExample example);

    /**
     *
     * @mbg.generated
     */
    SysResource selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysResource record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysResource record);

    /**
     * 根据用户编号获取资源列表（上下级和按钮，递归）
     * @param userId
     * @return
     */
    List<SysResourceDto> getSysResourceByUserId(Integer userId);
}