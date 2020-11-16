package cn.itcast.service.impl;

import cn.itcast.dao.ProvinceDao;
import cn.itcast.dao.impl.ProvinceDaoImpl;
import cn.itcast.domain.Province;
import cn.itcast.service.ProvinceService;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    //声明dao
    private ProvinceDao dao = new ProvinceDaoImpl();

    /**
     * 查找所有的省份-jdbc方式
     * @return
     */
    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }
}
