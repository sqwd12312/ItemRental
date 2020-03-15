package com.qny.service;

import java.util.List;

import com.qny.pojo.User;

public interface UserService {

	public List<User> userList() throws Exception;

	public User login(User user) throws Exception;
	
    //qny
    //插入一条新增用户信息
    public void insertUser(User user);
    
    //qny
    //更新一条用户信息
    public void updateUser(User user);
    
    //qny
    //根据id查询用户
    public User getUserById(Integer id);

}
