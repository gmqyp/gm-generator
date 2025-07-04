package fz.gm.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import fz.gm.model.MainTemplateConfig;
import org.apache.commons.collections4.functors.TruePredicate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("gyh");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("sum");
        String inputPath = "gm-generator-basic/src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = "gm-generator-basic/MainTemplate.java";
        doGenerate(inputPath, outputPath, mainTemplateConfig);
    }

    /**
     * 生成文件
     *
     * @param InputPath 模板文件输入路径
     * @param OutputPath 输出路径
     * @param model 数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String InputPath, String OutputPath, Object model) throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为FreeMarker版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        //指定模板文件所在的路径
        //"gm-generator-basic/src/main/resources/templates"
        configuration.setDirectoryForTemplateLoading(new File(InputPath).getParentFile());

        //设置模板文件使用的字符集
        configuration.setDefaultEncoding("UTF-8");

        //创建模板对象，加载指定模板
        //"MainTemplate.java.ftl"
        Template template = configuration.getTemplate(new File(InputPath).getName());

        //创建数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("gyh");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("sum");

        File file = new File(OutputPath);
        if (file.exists()) {
            file.delete();
        }

        //生成
        //"MainTemplate.java"
        FileWriter out = new FileWriter(OutputPath);
        template.process(model,out);

        //生成文件后关闭
        out.close();
    }
}
