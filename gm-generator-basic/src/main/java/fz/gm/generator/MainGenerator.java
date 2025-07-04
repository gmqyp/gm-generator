package fz.gm.generator;

import freemarker.template.TemplateException;
import fz.gm.model.MainTemplateConfig;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        //获取根目录
        String projectPath = System.getProperty("user.dir");
        //输入路径
        String inputPath = projectPath + File.separator + "gm-generator-demo-projects" + File.separator + "acm-template";
        //输出路径
        String outPutPath = projectPath;
        //拷贝文件
        StaticGenerator.copyFilesByReserve(inputPath, outPutPath);

        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("gyh");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("sum");
        String DynamicInputPath = "gm-generator-basic/src/main/resources/templates/MainTemplate.java.ftl";
        String DynamicOutputPath = "acm-template/src/com/yupi/acm/MainTemplate.java";
        DynamicGenerator.doGenerate(DynamicInputPath, DynamicOutputPath, mainTemplateConfig);
    }
}
