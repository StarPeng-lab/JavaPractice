package cn.itcast.service.impl;

import cn.itcast.Jedis.util.JedisPoolUtils;
import cn.itcast.dao.ProvinceDao;
import cn.itcast.dao.impl.ProvinceDaoImpl;
import cn.itcast.domain.Province;
import cn.itcast.service.ProvinceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

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

    /**
     * 从redis查出的数据类型是字符串，将findProvinceServlet中序列化好的数据存入redis
     * 使用redis缓存
     * @return
     */
    @Override
    public String findAllJson(){
        //1、先从redis中查询数据
        //1.1、获取redis客户端连接
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");

        //2、判断province_json数据是否为null
        if(province_json == null || "".equals(province_json)){
            //redis中没有数据
            //2.1、从数据库中查询数据
            List<Province> ps = dao.findAll();
            //2.2、将list序列化为json
            ObjectMapper mapper = new ObjectMapper();
            try{
                //将序列化后的json值赋给province_json , [{},{},...]
                province_json = mapper.writeValueAsString(ps);
            }catch(JsonProcessingException e){
                e.printStackTrace();
            }
            //2.3、将json数据存入redis
            jedis.set("province",province_json);

            //归还连接
            jedis.close();

        }else{
            //redis中有数据
        }

        return province_json;
    }
}
