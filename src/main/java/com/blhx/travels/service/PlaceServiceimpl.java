package com.blhx.travels.service;

import com.blhx.travels.dao.PlaceDAO;
import com.blhx.travels.entity.Place;
import com.blhx.travels.entity.Province;
import com.blhx.travels.mapper.ProvinceMapper;
import com.blhx.travels.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceServiceimpl implements PlaceService {
    @Autowired
    private PlaceDAO placeDAO;
    @Autowired
    private ProvinceService provinceService;
    @Override
    public void save(Place place) {
        //保存景点
        placeDAO.save(place);
        //查询原始省份信息
        Province province = provinceService.findOne(place.getProvinceid());
        //更新省份信息的景点个数
        province.setPlacecounts(province.getPlacecounts()+1);
        provinceService.update(province);

    }

    @Override
    public List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId) {
        int start = (page-1)*rows;
        return placeDAO.findByProvinceIdPage(start,rows,provinceId);
    }

    @Override
    public Integer findByProvinceIdCounts(String provinceId) {
        return placeDAO.findByProvinceIdCounts(provinceId);
    }

    @Override
    public void delete ( String id ){
        //获取原始省份信息
        Place place=placeDAO.findOne(id);
        Province province=provinceService.findOne(place.getProvinceid());
        province.setPlacecounts(province.getPlacecounts()-1);
        provinceService.update(province);
        //删除景点
        placeDAO.delete(id);


    }

    @Override
    public Place findOne ( String id ){
        return placeDAO.findOne(id);
    }

    @Override
    public void update ( Place place ){
        placeDAO.update(place);
    }
}
