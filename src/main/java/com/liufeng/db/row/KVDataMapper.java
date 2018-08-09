package com.liufeng.db.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.liufeng.cgds.data.KVData;

public class KVDataMapper implements RowMapper<KVData> {
	public KVData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		KVData data = new KVData();
		data.setId(rs.getLong("id"));
		data.setAppid(rs.getInt("appid"));
		data.setUid(rs.getInt("uid"));
		data.setKey(rs.getString("key"));
		data.setValue(rs.getString("value"));
		
		return data;
	}

}
