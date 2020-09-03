package org.tiankafei.jdbc.mybatis.logic;

import org.apache.ibatis.session.SqlSessionFactory;
import org.tiankafei.base.exceptions.BaseException;
import org.tiankafei.jdbc.logic.ICommonLogic;

/**
 * @author 甜咖啡
 */
public interface ICommonMyBatisLogic extends ICommonLogic {

    /**
     * 获取mybatis的session会话工厂类
     *
     * @return mybatis的session会话工厂类
     */
    public SqlSessionFactory getSqlSessionFactory();

    /**
     * 打开mybatis的session会话
     *
     * @param autoCommit 是否自动提交事物
     */
    public void openSession(boolean autoCommit);

    /**
     * 关闭sqlSession
     *
     * @throws BaseException 自定义异常
     */
    public void transactionClose() throws BaseException;

    /**
     * 获取查询结果
     *
     * @param type mapper
     * @return 查询结果
     */
    public Object getMapper(Class<?> type);

    /**
     * 添加映射类
     *
     * @param type 映射类
     */
    public void addMapper(Class<?> type);

}
