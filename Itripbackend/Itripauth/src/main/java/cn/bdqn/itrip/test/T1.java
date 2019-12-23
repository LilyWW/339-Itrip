package cn.bdqn.itrip.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T1 {

    @Test
    public void test() throws Exception {

        Map<String,String> map=new HashMap<String,String>();
        //A表示模板中的名称，“默认值”表示替换的内容
        map.put("A","默认值");

        Configuration configuration=new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setDirectoryForTemplateLoading(new File("D:\\workspace\\Itrip\\Itripbackend\\Itripauth\\src\\main\\resources"));

        Template template=configuration.getTemplate("T1.flt");
        //将map值放入模板中，生成a.txt文件
        template.process(map,new FileWriter("d://a.txt"));
    }

    @Test
    public void test2() throws Exception {
        List<User> list=new ArrayList<User>();
        for(int i=0;i<10;i++){
            User user=new User(i,"名字"+i);
            list.add(user);
        }

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("list",list);

        Configuration configuration=new
                Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setDirectoryForTemplateLoading(new File("D:\\workspace\\Itrip\\Itripbackend\\Itripauth\\src\\main\\resources"));
        //将map中的内容放入模板2中，在d盘生成文件
       Template template= configuration.getTemplate("T2.flt");
       template.process(map,new FileWriter("d://b.html"));

    }
}
