package com.liufeng.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.liufeng.cgds.data.KVData;
import com.liufeng.db.row.KVDataMapper;

public class KVDataDAO  extends JdbcDaoSupport{
	private static final String SQL_KVDATA_QUERY = "SELECT * FROM kvdata WHERE appid=? ";
	
	private static final String SQL_KVDATA_UPDATE = "UPDATE kvdata SET "
			+ "value=? WHERE id=?";
	private static final String SQL_KVDATA_INSERT = "INSERT INTO kvdata "
			+ "(appid,uid,`key`,`value`) "
			+ "VALUES"
			+ "(?,?,?,?)";
	
	private KVDataMapper kvDataMapper = new KVDataMapper();
	
	/**
	 * 获取数据
	 * key为""时为获取全部数据
	 * @param appid
	 * @param uid
	 * @param key
	 * @return
	 */
	public List<KVData> getData(String appid,String uid,String key){
		try {
			String[] uidlist = uid.split(",");

			StringBuilder queryString =  new StringBuilder(SQL_KVDATA_QUERY);
			int count = 0;
			for(int i = 0 ; i < uidlist.length ; i ++){
				if("".equals(uidlist[i])){
					continue;
				}
				if(i == 0 ) queryString.append(" AND ( ");
				else  queryString.append(" OR ");
				queryString.append(" uid='").append(uidlist[i]).append("'");
				if(i == uidlist.length - 1) queryString.append(") ");
				count ++;
			}
			
			if(count <= 0){
				return new ArrayList<KVData>();
			}
			
			String[] keylist = key.split(",");

			for(int i = 0 ; i < keylist.length ; i ++){
				if("".equals(keylist[i])){
					continue;
				}
				if(i == 0 ) queryString.append(" AND ( ");
				else  queryString.append(" OR ");
				queryString.append(" `key`='").append(keylist[i]).append("'");
				if(i == keylist.length - 1) queryString.append(")");
			}
			
			return getJdbcTemplate().query(queryString.toString(),kvDataMapper,appid);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public KVData getDataByOne(String appid,String uid,String key){
		try {
			if(uid == null || "".equals(uid)) return null;
			if(key == null || "".equals(key)) return null;
			
			StringBuilder queryString =  new StringBuilder(SQL_KVDATA_QUERY);
			queryString.append(" AND uid='"+uid+"'");
			queryString.append(" AND `key`='"+key+"'");
			logger.info(queryString.toString());
			return getJdbcTemplate().queryForObject(queryString.toString(),kvDataMapper,appid);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return null;
		}
	}
	/**
	 * 更新一个数据
	 * @param data
	 * uname=?,lv=?,head=?,country=?,lvstar=?,item_list=? 
	 */
	public void updateData(final KVData data){
		
		getJdbcTemplate().update(SQL_KVDATA_UPDATE, 
				data.getValue(),
				data.getId());
	}
	
	/**
	 * 添加
	 * @param data
	 * @return
	 */
	public long addData(final KVData data){
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_KVDATA_INSERT,
							Statement.RETURN_GENERATED_KEYS);

					ps.setInt(1, data.getAppid());
					ps.setInt(2,data.getUid());
					ps.setString(3, data.getKey());
					ps.setString(4, data.getValue());
					
					return ps;
				}
			},keyHolder);
			data.setId(keyHolder.getKey().longValue());
			
			return data.getId();
		}catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}
