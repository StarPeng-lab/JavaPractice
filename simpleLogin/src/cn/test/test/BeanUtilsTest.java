package cn.test.test;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import cn.test.domain.User;

import java.lang.reflect.InvocationTargetException;

public class BeanUtilsTest {
    @Test
    public void test(){

        User user = new User();

        try {
            //注意此处操作的是username属性值，不是User类中的成员变量username，而是从set/get方法名中提取出的属性值
            //即如果User类中成员变量名为username，而set/get方法为setHehe()/getHehe()，则此时user对象为null
            BeanUtils.setProperty(user,"username","zhangsan");
            System.out.println(user);

            //此处操作的也是属性值username
            String name = BeanUtils.getProperty(user,"username");
            System.out.println(name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
