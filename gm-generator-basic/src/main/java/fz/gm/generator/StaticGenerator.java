package fz.gm.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

/**
 * 静态文件生成器
 */
public class StaticGenerator {
    public static void main(String[] args) {
        //获取根目录
        String projectPath = System.getProperty("user.dir");
        //输入路径
        String inputPath = projectPath + File.separator + "gm-generator-demo-projects" + File.separator + "acm-template";
        //输出路径
        String outPutPath = projectPath;
        //拷贝文件
//        copyFileByHutool(inputPath, outPutPath);
        copyFilesByReserve(inputPath, outPutPath);
    }

    /**
     * 拷贝文件，（hutool工具会将输入文件拷贝到输出文件）
     * @param inputFile 输入文件
     * @param outputFile 输出文件
     */
    public static void copyFileByHutool(String inputFile, String outputFile){
        FileUtil.copy(inputFile,outputFile,false);
    }

    /**
     * 递归拷贝文件，将目录下的文件完整的拷贝
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByReserve(String inputPath, String outputPath){
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByReserve(inputFile,outputFile);
        } catch (IOException e) {
            System.err.println("文件复制失败");
            throw new RuntimeException(e);
        }
    }

    public static void copyFileByReserve(File inputFile, File outputFile) throws IOException {
        //区分是目录还是文件
        if(inputFile.isDirectory()){
            System.out.println(inputFile.getName());
            //这行代码的作用是 构造目标文件的完整路径，将 inputFile 的文件名附加到 outputFile 指定的目录路径下，生成一个表示目标文件的 File 对象。
            File destOutFile = new File(outputFile, inputFile.getName());
            //如果是目录，首先先创建目录
            if(!destOutFile.exists()){
                destOutFile.mkdir();
            }
            //获取目录下面的子目录
            File[] files = inputFile.listFiles();
            if(ArrayUtil.isEmpty(files)){
                return;
            }
            for(File file : files){
                //递归拷贝下一级目录
                copyFileByReserve(file,destOutFile);
            }
        }else{
            //是文件直接复制到目标文件
            //生成目标文件的路径
            //resolve（xxx）将括号中的内容与前者进行拼接
            //最后copy参数表示直接覆盖本文件
            System.out.println(inputFile.getName() + "\t" + inputFile.length());
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(),destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
