package cn.edu.hdu.clan;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class FreeMakerGenerator {

    @Test
    public void bootstrap() {
        // 参数值
        GenBen genBen = new GenBen("group", "SysGroup");
        genAll(genBen);
    }

    @Test
    public void batchGen() {
        String beanRoot = "D:\\江俊作案工具\\erp\\src\\main\\java\\cn\\edu\\hdu\\clan\\entity\\sys";
        FilenameFilter fileFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.equals("SysTerm.java");
            }
        };
        String[] files = new File(beanRoot).list(fileFilter);
        assert files != null;
        for (String f : files) {
            String beanName = f.replaceAll(".java", "");
            String name = beanName.substring(0, 1).toUpperCase() + beanName.substring(1);
            GenBen genBen = new GenBen(name, beanName);
            genAll(genBen);
        }
    }

    private static String targetRootPath = "D:\\江俊作案工具\\erp\\src\\main\\java\\cn\\edu\\hdu\\clan\\";

    private static void genAll(GenBen genBen) {
        // controller
        genFile(transBean2Map(genBen), "Controller.ftl", new File(targetRootPath + "controller\\" + genBen.getBeanName() + "Controller.java"));
        genFile(transBean2Map(genBen), "Service.ftl", new File(targetRootPath + "service\\sys\\" + genBen.getBeanName() + "Service.java"));
        genFile(transBean2Map(genBen), "ServiceImpl.ftl", new File(targetRootPath + "service\\sys\\" + genBen.getBeanName() + "ServiceImpl.java"));
    }

    private static Configuration getConfiguration(String templateDirectory) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        try {
            configuration.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
            configuration.setDirectoryForTemplateLoading(new File(templateDirectory));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    // 生成目标文件
    private static void genFile(Map<String, Object> map, String templateFile, File genFile) {
        // 模板目录
        String templateDirectory = "D:\\江俊作案工具\\erp\\src\\main\\resources\\templates";
        try (Writer writer = new FileWriter(genFile)) {
            Template template = getConfiguration(templateDirectory).getTemplate(templateFile, "UTF-8");
            template.process(map, writer);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }

    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static Map<String, Object> transBean2Map(Object obj) {

        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }
}
