package com.blhx.travels.mapper;

import com.blhx.travels.entity.Place;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlaceMapper {
    @Select("select\n" +
            "          id,\tname,\tpicpath,\thottime,\thotticket,\tdimticket,\tplacedes,\tprovinceid\n" +
            "        from t_place\n" +
            "        where provinceid = #{provinceId}\n" +
            "        order by hotticket\n" +
            "        limit #{start},#{rows}")
    List<Place> findByProvinceIdPage ( Integer page, Integer rows, String provinceId );

    @Select("select count(id) from  t_place where provinceid=#{provinceId}")
    Integer findByProvinceIdCounts ( String provinceId );

    @Insert("insert into t_place values (#{id},#{name},#{picpath},#{hottime},#{hotticket},#{dimticket},#{placedes},#{provinceid})")
    void save ( Place place );
}
