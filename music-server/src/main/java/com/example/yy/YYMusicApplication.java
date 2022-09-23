package com.example.yy;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.example.yy.mapper")
public class YYMusicApplication {
    public static void main(String[] args){
        //docConfig();
        SpringApplication.run(YYMusicApplication.class,args);
    }
    public static void docConfig(){ DocsConfig config = new DocsConfig();
        config.setProjectPath("D:\\Projects\\music\\YY-Music\\music-server"); // 项目根目录
        config.setProjectName("YYMusic"); // 项目名称
        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath("src/static/apiDoc"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
        }

}
