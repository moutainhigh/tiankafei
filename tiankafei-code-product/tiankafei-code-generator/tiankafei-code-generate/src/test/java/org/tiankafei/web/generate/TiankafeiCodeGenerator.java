package org.tiankafei.web.generate;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.Arrays;
import java.util.List;

/**
 * @author tiankafei
 * @since 1.0
 */
public class TiankafeiCodeGenerator {

    private String baseEntityClassPath = "org.tiankafei.web.common.entity.BaseEntity";
    private String baseMapperClassPath = "org.tiankafei.web.common.entity.BaseEntity";
    private String baseServiceClassPath = "org.tiankafei.web.common.entity.BaseEntity";
    private String baseServiceImplClassPath = "org.tiankafei.web.common.entity.BaseEntity";
    private String baseControllerClassPath = "org.tiankafei.web.common.entity.BaseEntity";
    private List<String> tableNameList = Arrays.asList();

    public static void main(String[] args) throws Exception {
        new TiankafeiCodeGenerator();
    }

    public TiankafeiCodeGenerator() throws Exception {
        AutoGenerator autoGenerator = new AutoGenerator();

        // 数据源配置，通过该配置，指定需要生成代码的具体数据库
        autoGenerator.setDataSource(initDataSourceConfig());

        // 数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
        autoGenerator.setStrategy(initStrategyConfig());

        // 包名配置，通过该配置，指定生成代码的包路径
        autoGenerator.setPackageInfo(initPackageConfig());

        // 模板配置，可自定义代码生成的模板，实现个性化操作
        autoGenerator.setTemplate(initTemplateConfig());

        // 全局策略配置
        autoGenerator.setGlobalConfig(initGlobalConfig());

        // 注入配置，通过该配置，可注入自定义参数等操作以实现个性化操作
        autoGenerator.setCfg(initInjectionConfig());

        autoGenerator.execute();
    }

    // 注入配置，通过该配置，可注入自定义参数等操作以实现个性化操作
    private InjectionConfig initInjectionConfig(){
        InjectionConfig injectionConfig = new InjectionConfig(){
            @Override
            public void initMap() {

            }
        };

        return injectionConfig;
    }

    // 全局策略配置
    private GlobalConfig initGlobalConfig(){
        GlobalConfig globalConfig = new GlobalConfig();

        return globalConfig;
    }

    // 模板配置，可自定义代码生成的模板，实现个性化操作
    private TemplateConfig initTemplateConfig(){
        TemplateConfig templateConfig = new TemplateConfig();

        return templateConfig;
    }

    // 包名配置，通过该配置，指定生成代码的包路径
    private PackageConfig initPackageConfig(){
        PackageConfig packageConfig = new PackageConfig();

        return packageConfig;
    }

    // 数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
    private StrategyConfig initStrategyConfig() throws ClassNotFoundException {
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(false);
        strategyConfig.setSkipView(false);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setTablePrefix("sys_");
//        strategyConfig.setFieldPrefix("");
        strategyConfig.setSuperEntityClass(Class.forName(baseEntityClassPath));
        strategyConfig.setSuperEntityColumns("create_user_id", "create_time", "update_user_id", "update_time");
        strategyConfig.setSuperMapperClass(baseMapperClassPath);
        strategyConfig.setSuperServiceClass(baseServiceClassPath);
        strategyConfig.setSuperServiceImplClass(baseServiceImplClassPath);
        strategyConfig.setSuperControllerClass(baseControllerClassPath);
        strategyConfig.setEnableSqlFilter(true);
//        strategyConfig.setInclude();
//        strategyConfig.setLikeTable();
//        strategyConfig.setExclude();
//        strategyConfig.setNotLikeTable();
        strategyConfig.setEntityColumnConstant(false);
        strategyConfig.setChainModel(true);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(false);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setEntityTableFieldAnnotationEnable(true);
        strategyConfig.setVersionFieldName("version");
        strategyConfig.setLogicDeleteFieldName("delete_mark");
        List<TableFill> tableFieldList = Arrays.asList(
                new TableFill("create_user_id", FieldFill.INSERT),
                new TableFill("create_time", FieldFill.INSERT),
                new TableFill("update_user_id", FieldFill.UPDATE),
                new TableFill("update_time", FieldFill.UPDATE));
        strategyConfig.setTableFillList(tableFieldList);
        strategyConfig.setInclude(tableNameList.toArray(new String[]{}));

        return strategyConfig;
    }

    // 数据源配置，通过该配置，指定需要生成代码的具体数据库
    private DataSourceConfig initDataSourceConfig(){
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/master?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("tiankafei");
        // 设置自定义查询
        dataSourceConfig.setDbQuery(new MySqlQuery(){
            @Override
            public String[] fieldCustom() {
                return new String[]{"null", "default"};
            }
        });
        dataSourceConfig.setTypeConvert(new ITypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if (fieldType.equalsIgnoreCase("timestamp")
                        || fieldType.equalsIgnoreCase("datetime")
                        || fieldType.equalsIgnoreCase("date")) {
                    return DbColumnType.TIMESTAMP;
                }
                //其它字段采用默认转换（非mysql数据库可以使用其它默认的数据库转换器）
                return new MySqlTypeConvert().processTypeConvert(globalConfig, fieldType);
            }
        });
        return dataSourceConfig;
    }
}
