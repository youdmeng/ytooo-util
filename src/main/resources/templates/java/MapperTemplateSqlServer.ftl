<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${bussPackage}.${entityPackage}.dao.${className}Dao" >
  <!-- Result Map-->
  <resultMap id="BaseResultMap" type="${bussPackage}.${entityPackage}.entity.$!{className}" >
#foreach($po in ${pkColumnDatas})
    <id column="$po.columnName" property="$po.dataName" />
#end
#foreach($po in ${notPkColumnDatas})
    <result column="$po.columnName" property="$po.dataName" />
#end
  </resultMap>

  <!-- $!{tableName} table all fields -->
  <sql id="Base_Column_List" >
     $!{SQL.columnFields}
  </sql>

  <!-- 查询条件 -->
  <sql id="Base_Where_Clause">
    where 1=1
    <trim suffixOverrides="," >
#foreach($po in $!{columnDatas})
      <if test="$!{po.dataName} != null" >
        and $!{po.columnName} ${$!{po.dataName}Operator} #{$!{po.dataName}}
      </if>
#if(${po.shortDataType} == 'Date')
      <if test="$!{po.dataName}1 != null" >
        and $!{po.columnName} &gt;= #{$!{po.dataName}1}
      </if>
      <if test="$!{po.dataName}2 != null" >
        and $!{po.columnName} &lt;= #{$!{po.dataName}2}
      </if>
#end
#end
    </trim>
  </sql>

  <!-- 插入记录 -->
  <insert id="insert" parameterType="${bussPackage}.${entityPackage}.entity.${className}" >
#if($pkColumnCount == 1)
    <!-- <selectKey resultType="${pkColumn.dataType}" order="BEFORE" keyProperty="${pkColumn.columnName}">
        SELECT SEQ_${tableName}.NEXTVAL FROM DUAL
    </selectKey> -->
#end
    insert into ${tableName}(<include refid="Base_Column_List" />)
    values (#foreach($po in ${columnDatas})#{${po.dataName}, jdbcType=${po.jdbcType}}#if($velocityCount < ${columnDatas.size()}), #end#end)
  </insert>

  <!-- 动态插入记录 主键是序列 -->
  <insert id="insertSelective" parameterType="${bussPackage}.${entityPackage}.entity.${className}" >
    #if($pkColumnCount == 1)
    <!-- <selectKey resultType="${pkColumn.dataType}" order="BEFORE" keyProperty="${pkColumn.columnName}">
        SELECT SEQ_${tableName}.NEXTVAL FROM DUAL
    </selectKey> -->
    #end
    insert into ${tableName}
    <trim prefix="(" suffix=")" suffixOverrides="," >
        #foreach($po in $!{columnDatas})
        <if test="$!{po.dataName} != null" >$!{po.columnName},</if>
        #end
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides="," >
        #foreach($po in $!{columnDatas})
        <if test="$!{po.dataName} != null" >#{${po.dataName}, jdbcType=${po.jdbcType}},</if>
        #end
    </trim>
  </insert>

#if($pkColumnCount > 0)
  <!-- 根据pk，修改记录-->
  <update id="updateByPrimaryKey" parameterType="${bussPackage}.${entityPackage}.entity.${className}" >
    update ${tableName}
#foreach($po in ${notPkColumnDatas})
       #if($velocityCount == 1)set #else    #end ${po.columnName} = #{${po.dataName}}#if($velocityCount < ${notPkColumnDatas.size()}),
#end
#end

#foreach($po in ${pkColumnDatas})
     #if($velocityCount == 1)where #else  and #end ${po.columnName} = #{${po.dataName}}#if($velocityCount < ${pkColumnDatas.size()}),
#end
#end

  </update>

  <!-- 修改记录，只修改只不为空的字段 -->
  <update id="updateByPrimaryKeySelective" parameterType="${bussPackage}.${entityPackage}.entity.${className}" >
    update ${tableName}
    <set >
#foreach($po in ${notPkColumnDatas})
      <if test="${po.dataName} != null" >
        ${po.columnName} = #{${po.dataName}},
      </if>
#end
    </set>
#foreach($po in ${pkColumnDatas})
     #if($velocityCount == 1)where #else  and #end${po.columnName} = #{${po.dataName}}#if($velocityCount < ${pkColumnDatas.size()}),
#end
#end

  </update>

  <!-- 根据id查询 ${codeName} -->
  <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="#if($pkColumnCount == 1)${pkColumn.dataType}#else${bussPackage}.entity.${entityPackage}.${className}Key#end">
    select <include refid="Base_Column_List" /> 
      from ${tableName}
#if($pkColumnCount == 1)
     where ${pkColumn.columnName} = #{value}
#else
#foreach($po in ${pkColumnDatas})
     #if($velocityCount == 1)where #else  and #end`${po.columnName}` = #{${po.dataName}}#if($velocityCount < ${pkColumnDatas.size()}),
#end
#end
#end

  </select>

  <!-- 删除记录 -->
  <delete id="deleteByPrimaryKey" parameterType="#if($pkColumnCount == 1)${pkColumn.dataType}#else${bussPackage}.entity.${entityPackage}.${className}Key#end">
    delete from ${tableName}
#if($pkColumnCount == 1)
     where ${pkColumn.columnName} = #{value}
#else
#foreach($po in ${pkColumnDatas})
     #if($velocityCount == 1)where #else  and #end`${po.columnName}` = #{${po.dataName}}#if($velocityCount < ${pkColumnDatas.size()}),
#end
#end
#end

  </delete>

#end
  <!-- ${codeName} 列表总数-->
  <select id="queryByCount" resultType="java.lang.Integer" parameterType="com.adc.da.base.page.BasePage">
    select count(1) from ${tableName}
    <include refid="Base_Where_Clause"/>
  </select>

  <!-- 查询${codeName}列表 -->
  <select id="queryByPage" resultMap="BaseResultMap" parameterType="com.adc.da.base.page.BasePage">
		select <include refid="Base_Column_List" /> from ${tableName}
			<include refid="Base_Where_Clause"/>
			<if test="pager.orderCondition != null and pager.orderCondition != ''" >
				${pager.orderCondition}
			</if>
			offset ${pager.pageOffset} rows fetch next ${pager.pageSize} rows only
  </select>

  <select id="queryByList" resultMap="BaseResultMap" parameterType="com.adc.da.base.page.BasePage">
    select <include refid="Base_Column_List"/> from ${tableName}
    <include refid="Base_Where_Clause"/>
    <if test="pager.orderCondition != null and pager.orderCondition != ''" >
		${pager.orderCondition}
    </if>
  </select>

</mapper>
