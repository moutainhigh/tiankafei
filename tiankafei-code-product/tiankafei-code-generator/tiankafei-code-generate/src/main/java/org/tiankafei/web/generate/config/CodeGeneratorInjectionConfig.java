package org.tiankafei.web.generate.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.tiankafei.web.generate.properties.CfgProperties;
import org.tiankafei.web.generate.properties.CodeProperties;

/**
 * @author 魏双双
 * @since 1.0
 **/
public class CodeGeneratorInjectionConfig {

    // 注入配置，通过该配置，可注入自定义参数等操作以实现个性化操作
    public static InjectionConfig initInjectionConfig(CodeProperties codePropertie) {
        // 转换器
        ObjectMapper objectMapper = new ObjectMapper();

        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                ConfigBuilder config = getConfig();

                Map<String, Object> map = new HashMap<>();
                map.put("shiroAuthority", codePropertie.isShiroAuthority());
                map.put("voPackage", codePropertie.getProjectPath() + "." + codePropertie.getModuleName() + ".vo");
                map.put("paramPackage", codePropertie.getProjectPath() + "." + codePropertie.getModuleName() + ".param");

                String superVoClassPath = codePropertie.getSuperVoClassPath();
                String superVoPackage = superVoClassPath.substring(0, superVoClassPath.lastIndexOf("."));
                String superVoName = superVoClassPath.substring(superVoClassPath.lastIndexOf(".") + 1);
                map.put("superVoPackage", superVoPackage);
                map.put("superVoClassName", superVoName);

                String superPageParamClassPath = codePropertie.getSuperPageParamClassPath();
                String superPageParamPackage = superPageParamClassPath.substring(0, superPageParamClassPath.lastIndexOf("."));
                String superPageParamName = superPageParamClassPath.substring(superPageParamClassPath.lastIndexOf(".") + 1);
                map.put("superParamPackage", superPageParamPackage);
                map.put("superPageParamClassName", superPageParamName);

                List<TableInfo> tableInfoList = config.getTableInfoList();
                tableInfoList.stream().forEach(tableInfo -> {
                    CfgProperties cfgProperties = new CfgProperties();
                    String name = tableInfo.getName();

                    Set<String> importPackages = tableInfo.getImportPackages();
                    List<String> importPackageList = importPackages.stream().filter(importPackage -> {
                        if (importPackage.endsWith("TableName")
                                || importPackage.endsWith("IdType")
                                || importPackage.endsWith("Model")
                                || importPackage.endsWith("Version")
                                || importPackage.endsWith("TableId")
                                || importPackage.endsWith("FieldFill")
                                || importPackage.endsWith("TableLogic")
                                || importPackage.endsWith("TableField")) {
                            return false;
                        }
                        return true;
                    }).collect(Collectors.toList());
                    cfgProperties.setImportPackageList(importPackageList);

                    String entityName = tableInfo.getEntityName();
                    String entityConstName = firstToLowerCase(entityName);
                    cfgProperties.setEntityConstName(entityConstName);

                    String voName = entityName.replace("Entity", "Vo");
                    cfgProperties.setVoClassName(voName);
                    cfgProperties.setVoConstName(firstToLowerCase(voName));

                    String listParamName = entityName.replace("Entity", "ListParam");
                    cfgProperties.setListParamClassName(listParamName);
                    cfgProperties.setListParamConstName(firstToLowerCase(listParamName));

                    String pageParamName = entityName.replace("Entity", "PageParam");
                    cfgProperties.setPageParamClassName(pageParamName);
                    cfgProperties.setPageParamConstName(firstToLowerCase(pageParamName));

                    String permission = underlineToColon(name);
                    cfgProperties.setAuthority(permission);

                    String serviceName = tableInfo.getServiceName();
                    String serviceConstName = firstToLowerCase(serviceName);
                    cfgProperties.setServiceConstName(serviceConstName);

                    String mapperName = tableInfo.getMapperName();
                    String mapperConstName = firstToLowerCase(mapperName);
                    cfgProperties.setMapperConstName(mapperConstName);

                    String controllerName = tableInfo.getControllerName();
                    String controllerConstName = firstToLowerCase(controllerName);
                    cfgProperties.setControllerConstName(controllerConstName);

                    try {
                        String json = objectMapper.writeValueAsString(cfgProperties);
                        Map tmpMap = objectMapper.readValue(json, Map.class);
                        map.put(name, tmpMap);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
                setMap(map);
            }
        };

        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        fileOutConfigList.add(new FileOutConfig("/myself/vo.java.vm") {
            @SneakyThrows
            @Override
            public String outputFile(TableInfo tableInfo) {
                String name = tableInfo.getName();
                Map<String, Object> map = injectionConfig.getMap();
                Map tmpMap = (Map) map.get(name);
                String json = objectMapper.writeValueAsString(tmpMap);
                CfgProperties cfgProperties = objectMapper.readValue(json, CfgProperties.class);
                String voClassName = cfgProperties.getVoClassName();

                String path = codePropertie.getOutputDir() + File.separator
                        + codePropertie.getProjectPath().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + File.separator
                        + codePropertie.getModuleName() + File.separator + "vo" + File.separator + voClassName + StringPool.DOT_JAVA;
                System.out.println(path);
                return path;
            }
        });
        fileOutConfigList.add(new FileOutConfig("/myself/listParam.java.vm") {
            @SneakyThrows
            @Override
            public String outputFile(TableInfo tableInfo) {
                String name = tableInfo.getName();
                Map<String, Object> map = injectionConfig.getMap();
                Map tmpMap = (Map) map.get(name);
                String json = objectMapper.writeValueAsString(tmpMap);
                CfgProperties cfgProperties = objectMapper.readValue(json, CfgProperties.class);
                String listParamClassName = cfgProperties.getListParamClassName();

                String path = codePropertie.getOutputDir() + File.separator
                        + codePropertie.getProjectPath().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + File.separator
                        + codePropertie.getModuleName() + File.separator + "param" + File.separator + listParamClassName + StringPool.DOT_JAVA;
                return path;
            }
        });
        fileOutConfigList.add(new FileOutConfig("/myself/pageParam.java.vm") {
            @SneakyThrows
            @Override
            public String outputFile(TableInfo tableInfo) {
                String name = tableInfo.getName();
                Map<String, Object> map = injectionConfig.getMap();
                Map tmpMap = (Map) map.get(name);
                String json = objectMapper.writeValueAsString(tmpMap);
                CfgProperties cfgProperties = objectMapper.readValue(json, CfgProperties.class);
                String pageParamClassName = cfgProperties.getPageParamClassName();

                String path = codePropertie.getOutputDir() + File.separator
                        + codePropertie.getProjectPath().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + File.separator
                        + codePropertie.getModuleName() + File.separator + "param" + File.separator + pageParamClassName + StringPool.DOT_JAVA;
                return path;
            }
        });

        injectionConfig.setFileOutConfigList(fileOutConfigList);
        return injectionConfig;
    }

    /**
     * 下划线转换成冒号连接命名
     * sys_user --> sys:user
     *
     * @param underline
     * @return
     */
    public static String firstToLowerCase(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            return underline.substring(0, 1).toLowerCase() + underline.substring(1);
        }
        return null;
    }

    /**
     * 下划线转换成冒号连接命名
     * sys_user --> sys:user
     *
     * @param underline
     * @return
     */
    public static String underlineToColon(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            String string = underline.toLowerCase();
            return string.replaceAll("_", ":");
        }
        return null;
    }

}
