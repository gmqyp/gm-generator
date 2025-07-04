package fz.gm.model;

import lombok.Data;

/**
 * 动态模板配置
 */
@Data
public class MainTemplateConfig {
    /*要求：
        1.修改作者
        2.修改输出信息
        3.修改循环次数
     */

    /**
     * 是否生成循环
     */
    private boolean loop;

    /**
     * 添加作者
     */
    private String author = "gyh";

    /**
     * 修改输出信息
     */
    private String outputText = "sum";
}
