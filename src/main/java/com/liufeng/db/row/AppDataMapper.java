package com.liufeng.db.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.liufeng.cgds.app.AppData;



public class AppDataMapper  implements RowMapper<AppData>{

	public AppData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub

		AppData pd = new AppData();
		pd.setId(rs.getLong("id"));
		pd.setAppname(rs.getString("appname"));
		pd.setCreatetime(rs.getLong("createtime"));
		pd.setKey(rs.getString("appkey"));
		pd.setUserid(rs.getString("userid"));
		return pd;
	}

}
