package com.liufeng.db.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.liufeng.cgds.user.UserData;


public class UserDataMapper  implements RowMapper<UserData>{

	public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		UserData data = new UserData();
		data.setId(rs.getLong("id"));
		data.setUsername(rs.getString("username"));
		data.setPassword(rs.getString("password"));
		data.setCreatetime(rs.getLong("createtime"));
		data.setEmail(rs.getString("email"));
		data.setQq(rs.getString("qq"));
		
		return data;
	}

}
