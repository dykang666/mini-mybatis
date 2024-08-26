package com.example.minimybatis.session.defaults;

import com.example.minimybatis.constants.Constant;
import com.example.minimybatis.session.Configuration;
import com.example.minimybatis.session.SqlSession;
import com.example.minimybatis.session.SqlSessionFactory;
import com.example.minimybatis.utils.CommonUtis;
import com.example.minimybatis.utils.XmlUtil;


import java.io.File;
import java.net.URL;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:  默认SQL会话工厂实现类
 * @date 2024/8/25 15:53
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    /** the configuration */
    private final Configuration configuration;
    /**
     * @param configuration
     */
    public DefaultSqlSessionFactory(Configuration configuration)
    {

        this.configuration = configuration;
        loadMappersInfo(Configuration.getProperty(Constant.MAPPER_LOCATION).replaceAll("\\.", "/"));
    }


    public SqlSession openSession() {
        SqlSession session = new DefaultSqlSession(this.configuration);

        return session;
    }

    private void loadMappersInfo(String dirName){
        //只获取文件加下 不获取classpath下的文件
        URL resources = DefaultSqlSessionFactory.class.getClassLoader().getResource(dirName);
        File mappersDir = new File(resources.getFile());
        if (mappersDir.isDirectory())
        {  // 显示包下所有文件
            File[] mappers = mappersDir.listFiles();
            if (CommonUtis.isNotEmpty(mappers))
            {
                for (File file : mappers)
                {
                    // 对文件夹继续递归
                    if (file.isDirectory())
                    {
                        loadMappersInfo(dirName + "/" + file.getName());
                    }
                    else if (file.getName().endsWith(Constant.MAPPER_FILE_SUFFIX))
                    {
                        // 只对XML文件解析
                        XmlUtil.readMapperXml(file, this.configuration);
                    }

                }
            }
        }

    }
}
