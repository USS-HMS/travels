package com.blhx.travels.mapper;

import com.blhx.travels.entity.Province;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ProvinceMapper {
    //分页查询所有
    @Select("select id,name,tags,placecounts " +
            "from t_province " +
            "order by placecounts" +
            " limit #{start},#{rows}")
    List<Province> findByPage (@Param("start") Integer start, @Param("rows") Integer rows);
    //查询总条数
    @Select("select count(id) from t_province")
    Integer findTotals ( );

    //省份添加
    @Insert("insert into t_province values(#{id},#{name},#{tags},#{placecounts}")
    void save ( Province province );

    //省份删除
    @Delete("DELETE from t_province where id = #{id}")
    void delete ( String id );

    //查询一个
    @Select("  select id,name,tags,placecounts from t_province where id =#{id}")
    Province findOne ( String id );

    //修改省份信息
    @Update(" update t_province set name=#{name},tags=#{tags},placecounts=#{placecounts} where id=#{id}")
    void update ( Province province );

}
