package com.cleardewy.aoki.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
@RefreshScope
@ConfigurationProperties
@Data
public class AokiConfigProperties {

    private Spring spring=new Spring();

    @Data
    public static class Spring{
        private Mail mail=new Mail();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Mail{
        private String username;
        private String password;
        private String host;
        private String port;
        private String protocol;
    }


    public String toYamlString() {

        // 将绑定后的对象转换为YAML格式的字符串
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK); // 设置为块风格，保持换行缩进
        options.setPrettyFlow(true); // 设置为漂亮打印，即格式化输出
        String yaml = new Yaml(options).dump(this);
        return yaml.substring(yaml.indexOf('\n')+1);
    }

}
