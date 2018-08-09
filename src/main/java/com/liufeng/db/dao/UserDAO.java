package com.liufeng.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.liufeng.cgds.user.UserData;
import com.liufeng.db.row.UserDataMapper;


public class UserDAO  extends JdbcDaoSupport{

	private static final String SQL_USER_QUERY = "SELECT * FROM userdata";

	private static final String SQL_USER_INSERT = "INSERT INTO userdata "
			+ "(username,password,createtime,email,qq) "
			+ "VALUES"
			+ "(?,?)";
	
	private UserDataMapper udMapper = new UserDataMapper();
	
	/**
	 * 获取全部数据
	 * @return
	 */
	public List<UserData> getUserList(){
		try {
			return getJdbcTemplate().query(SQL_USER_QUERY,udMapper);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 添加
	 * @param data
	 * @return
	 */
	public long addUser(final UserData data){
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_USER_INSERT,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, data.getUsername());
					ps.setString(2, data.getPassword());
					ps.setLong(3, data.getCreatetime());
					ps.setString(4, data.getEmail());
					ps.setString(5, data.getQq());
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
