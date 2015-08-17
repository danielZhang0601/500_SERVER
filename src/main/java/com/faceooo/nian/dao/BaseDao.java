package com.faceooo.nian.dao;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BaseDao {

	private String route = null;

	private static Map<String, SqlMapClient> cache;

	private static SqlMapClient sqlMapClient;

	static {
		try {
			Reader reader = Resources
					.getResourceAsReader("../SqlMapConfig.xml");
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置数据库
	 * 
	 * @param route
	 *            jdbc:mysql://192.168.0.10/数据库名?user=USR&password=PWD
	 */
	protected void setRoute(String route) {
		this.route = route;
	}

	protected String getRoute() {
		return this.route;
	}

	/**
	 * 查询对象类表
	 * 
	 * @param statementName
	 *            sql 语句的名称，这个要和SqlMap中配置的一样
	 * @param parameterObject
	 *            执行sql语句的参数
	 * @return
	 * @throws SQLException
	 */
	protected List executeQueryForList(String statementName,
			Object parameterObject) throws SQLException {
		checkSqlMap();
		return sqlMapClient.queryForList(statementName, parameterObject);
	}

	/**
	 * 查询对象
	 * 
	 * @param statementName
	 *            sql 语句的名称，这个要和SqlMap中配置的一样
	 * @param parameterObject
	 *            执行sql语句的参数
	 * @return
	 * @throws SQLException
	 */
	protected Object executeQueryForObject(String statementName,
			Object parameterObject) throws SQLException {
		checkSqlMap();
		return sqlMapClient.queryForObject(statementName, parameterObject);
	}

	/**
	 * 插入对象
	 * 
	 * @param statementName
	 *            sql 语句的名称，这个要和SqlMap中配置的一样
	 * @param parameterObject
	 *            执行sql语句的参数
	 * @return
	 * @throws SQLException
	 */
	protected long executeInsert(String statementName, Object parameterObject)
			throws SQLException {
		checkSqlMap();
		Object o = sqlMapClient.insert(statementName, parameterObject);
		if (null == o) {
			return 0L;
		} else {
			return (Long) o;
		}
	}

	/**
	 * 更新对象
	 * 
	 * @param statementName
	 *            sql 语句的名称，这个要和SqlMap中配置的一样
	 * @param parameterObject
	 *            执行sql语句的参数
	 * @throws SQLException
	 */
	protected int executeQueryForUpdate(String statementName,
			Object parameterObject) throws SQLException {
		checkSqlMap();
		return sqlMapClient.update(statementName, parameterObject);
	}

	/**
	 * 删除对象
	 * 
	 * @param statementName
	 *            sql 语句的名称，这个要和SqlMap中配置的一样
	 * @param parameterObject
	 *            执行sql语句的参数
	 * @throws SQLException
	 */
	protected int executeDelete(String statementName, Object parameterObject)
			throws SQLException {
		checkSqlMap();
		return sqlMapClient.delete(statementName, parameterObject);
	}

	private void checkSqlMap() throws SQLException {
		if (null == sqlMapClient) {
			SqlMapClient smc = cache.get(route);
			if (null == smc) {
				throw new SQLException("can not find route named" + route);
			} else {
				sqlMapClient = smc;
			}
		}
	}

	public Map<String, SqlMapClient> getCache() {
		return cache;
	}

	public void setCache(Map<String, SqlMapClient> cache) {
		this.cache = cache;
	}

}
