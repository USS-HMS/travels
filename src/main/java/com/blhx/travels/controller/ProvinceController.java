package com.blhx.travels.controller;

import com.blhx.travels.entity.Province;
import com.blhx.travels.entity.Result;
import com.blhx.travels.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("province")
@CrossOrigin //允许跨域
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;
    @PostMapping("update")
    public Result upDate(@RequestBody Province province){
        Result result=new Result();
        try{
            provinceService.update(province);
            result.setMsg("修改信息采购");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("修改信息错误");
        }
        return result;
    }
    @GetMapping("findOne")
    public Province findOne(String id){
        return  provinceService.findOne(id);
    }
    /**
     * 删除景点
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result delete( String id){
        Result result=new Result();
        try {
            provinceService.delete(id);
            result.setMsg("景点删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("景点删除失败");
        }
        return result;
    }
    /**
     * 添加省份
     * @param province
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody Province province){
        Result result =new Result();
        try {
            provinceService.save(province);
            result.setMsg("省份信息填写成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("省份添加失败");
        }
        return result;
    }
    /**
     * 查询所有
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("findByPage")
    public Map<String, Object> findByPage(Integer page, Integer rows) {
        page = page == null ? 1 : page;
        rows = rows == null ? 4 : rows;
        HashMap<String, Object> map = new HashMap<>();
        //分页处理
        List<Province> provinces = provinceService.findByPage(page, rows);
        //计算总页数
        Integer totals = provinceService.findTotals();
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;
        map.put("provinces", provinces);
        map.put("totals", totals);
        map.put("totalPage", totalPage);
        map.put("page", page);
        return map;
    }
}
