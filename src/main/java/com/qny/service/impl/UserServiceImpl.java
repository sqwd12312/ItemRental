package com.qny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qny.dao.UserMapper;
import com.qny.pojo.User;
import com.qny.pojo.UserExample;
import com.qny.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> userList() throws Exception {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		List<User> list = userMapper.selectByExample(example);
		System.out.println("123" + list);
		return list;
	}

	@Override
	public User login(User user) throws Exception {
		User user1 = userMapper.selectByUser(user);
		return user1;
	}

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
		
	}

    //qny
    //更新一条用户信息
	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
		
	}

	/**   
	 * @title: getUserById
	 * @description: 根据id查询用户
	 * @param id:  用户id
	 * @return User: 查询到的用户信息 
	 * @see com.qny.service.UserService#getUserById(java.lang.Integer)     
	 */ 
	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(id);
	}

}
