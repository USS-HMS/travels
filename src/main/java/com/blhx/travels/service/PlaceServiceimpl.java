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
    private ProvinceMapper provinceMapper;
    @Override
    public List<Place> findByProvinceIdPage ( Integer page, Integer rows, String provinceId ){
        int start=(page-1)*rows;
         return placeDAO.findByProvinceIdPage(start,rows,provinceId);
    }

    @Override
    public Integer findByProvinceIdCounts ( String provinceId ){
        return placeDAO.findByProvinceIdCounts(provinceId);
    }

    @Override
    public void save ( Place place ){
        //保存景点
        placeDAO.save(place);
        //查询原始省份信息
        Province province=provinceMapper.findOne(place.getId());
        //跟新省份信息的景点信息
        province.setPlacecounts(province.getPlacecounts()+1);
        provinceMapper.update(province);
    }

    @Override
    public void delete ( String id ){

    }

    @Override
    public Place findOne ( String id ){
        return null;
    }

    @Override
    public void update ( Place place ){

    }
}
