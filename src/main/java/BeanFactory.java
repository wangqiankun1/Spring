import com.wangqiankun.Dao.StudentDao;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class BeanFactory {
    private static Properties props ;
    private static Map<String,Object> beans;
    static {
        props = new Properties();
        beans = new HashMap<String, Object>();
        InputStream inputStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.properties");
        try {
            props.load(inputStream);
            Set keySet = props.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()){
                String beanName = iterator.next();
                String beamPath = props.get(beanName).toString();
                Object bean = Class.forName(beamPath).newInstance();
                beans.put(beanName,bean);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    static Object getBean(String beanName){
        return beans.get(beanName);
    }
    public static void main(String[] args) {
        System.out.println("开始：");
        for(int i=0;i<5;i++){
            StudentDao studentDao = (StudentDao) BeanFactory.getBean("studentDao");
            System.out.println(studentDao);
        }
    }
}
