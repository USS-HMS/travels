package com.blhx.travels.service;


import com.blhx.travels.dao.ProvinceDAO;
import com.blhx.travels.entity.Province;
import com.blhx.travels.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProvinceServiceimpl implements ProvinceService{
    @Autowired
    private ProvinceDAO provinceDAO;
    @Override
    public List<Province> findByPage ( Integer page, Integer rows ){
        int start =(page-1)*rows;
        return provinceDAO.findByPage(start,rows);
    }

    @Override
    public Integer findTotals ( ){
        return provinceDAO.findTotals();
    }

    @Override
    public void save ( Province province ){

    }

    @Override
    public void delete ( String id ){

    }

    @Override
    public Province findOne ( String id ){
        return null;
    }

    @Override
    public void update ( Province province ){

    }
}
