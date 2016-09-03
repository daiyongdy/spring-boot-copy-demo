/**
 * <b>项目名：</b>spring-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.mybatis<br/>
 * <b>文件名：</b>HbdiySqlSessionDaoSupport.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月3日-下午7:18:57<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.mybatis;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
 * <b>类名称：</b>HbdiySqlSessionDaoSupport <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月3日 下午7:18:57<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

@Repository("hbdiySqlSessionDaoSupport")
public class HbdiySqlSessionDaoSupport{
	
	@Resource(name="primarySqlSessionTemplate")
	private SqlSessionTemplate primarySqlSessionTemplate;
	
	@Resource(name="readSqlSessionTemplate")
	private SqlSessionTemplate readSqlSessionTemplate;
	
	public int insert(String insertId, Object parameter) {
		return this.primarySqlSessionTemplate.insert(insertId, parameter);
	}

	public int update(String updateId, Object parameter) {
		return this.primarySqlSessionTemplate.update(updateId, parameter);
	}
	
	public int delete(String deleteId, Object parameter) {
		return this.primarySqlSessionTemplate.delete(deleteId, parameter);
	}

	public <M> M selectOne(String selectId, Object parameter) {
		return this.readSqlSessionTemplate.selectOne(selectId, parameter);
	}

	public <E> List<E> selectList(String selectId, Object parameter) {
		return this.readSqlSessionTemplate.selectList(selectId, parameter);
	}

	public <E> List<E> selectLimitedList(String selectId,Object parameter, RowBounds rowBounds) {
		return this.readSqlSessionTemplate.selectList(selectId, parameter,rowBounds);
	}
	public int selectCount(String countId, Object parameter) {
		return this.readSqlSessionTemplate.selectOne(countId, parameter);
	}
	
	public <T> List<T> selectByPage(String selectId, Object params, int page, int rows) {
		RowBounds rowBounds = new RowBounds(page, rows);
		List<T> result = this.readSqlSessionTemplate.selectList(selectId, params, rowBounds);
		return result;
	}
	
}
