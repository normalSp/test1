package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {

    //查询所有
    @Select("SELECT * FROM tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    //添加数据
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    //批量删除
    void deleteByIds(@Param("ids") int[] ids);

    //分页查询
    @Select("SELECT * FROM tb_brand limit #{begin} , #{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);
    //查询总记录数
    @Select("SELECT COUNT(*) FROM tb_brand ")
    int selectTotalCount();


    //分页条件查询
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("brand") Brand brand);
    //根据条件查询总记录数
    int selectTotalCountByCondition(Brand brand);
}
