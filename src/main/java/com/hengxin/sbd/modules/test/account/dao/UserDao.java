package com.hengxin.sbd.modules.test.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hengxin.sbd.modules.test.account.entity.User;

@Mapper
public interface UserDao {

	@Insert("insert into user (user_name,password,create_date) "
			+ "values (#{userName},#{password},#{createDate})")
	Integer insertUser(User user);

	@Delete("delete from user where user_id = #{userId}")
	Integer deleteUserByUserId(Integer userId);

	@Update("update user set user_name = #{userName}, password = #{password}"
			+ "where user_id = #{userId}")
	Integer updateUserByUserId(User user);

	@Select("select * from user")
	List<User> selectAllUser();

	@Select("select * from user where user_name = #{userName}")
	User selectUserByName(String userName);

}
