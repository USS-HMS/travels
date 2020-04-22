package com.blhx.travels.service;


import com.blhx.travels.dao.ProvinceDAO;
import com.blhx.travels.entity.Province;
import com.blhx.travels.mapper.ProvinceMapper;
import com.blhx.travels.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProvinceServiceimpl implements ProvinceService {
    @Autowired
    private ProvinceMapper provinceMapper;
    @Override
    public List<Province> findByPage ( Integer page, Integer rows ){
        int start =(page-1)*rows;
        return provinceMapper.findByPage(start,rows);
    }

    @Override
    public Integer findTotals ( ){
        return provinceMapper.findTotals();
    }

    @Override
    public void save ( Province province ){
        province.setPlacecounts(0);
        provinceMapper.save(province);
    }

    @Override
    public void delete ( String id ){
        provinceMapper.delete(id);
    }

    @Override
    public Province findOne ( String id ){
       return provinceMapper.findOne(id);
    }

    @Override
    public void update ( Province province ){
        provinceMapper.update(province);
    }
}
