package com.nnniu.shiro.ch2.service.impl;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

import com.nnniu.shiro.ch2.dao.UserDao;
import com.nnniu.shiro.ch2.dao.impl.UserDaoImpl;
import com.nnniu.shiro.ch2.entity.User;
import com.nnniu.shiro.ch2.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	public User createUser(User user) {
		byte[] priSaltBytes = "mm".getBytes();
		ByteSource pubByteSource = randomNumberGenerator.nextBytes();
		byte[] pubSaltBytes = pubByteSource.getBytes();
		byte[] total = new byte[priSaltBytes.length + pubSaltBytes.length];
		System.arraycopy(priSaltBytes, 0, total, 0, priSaltBytes.length);
		System.arraycopy(pubSaltBytes, 0, total, priSaltBytes.length, pubSaltBytes.length);
		String secPwd = new Md5Hash(user.getPassword(), ByteSource.Util.bytes(total)).toHex();
		user.setSalt(pubByteSource.toHex());
		user.setPassword(secPwd);
		return userDao.createUser(user);
	}
	
	public void changePassword(Long id, String newPassword) {
		User user = userDao.findOne(id);
		if (user == null) {
			return;
		}
		
		byte[] priSaltBytes = "mm".getBytes();
		ByteSource pubByteSource = randomNumberGenerator.nextBytes();
		byte[] pubSaltBytes = pubByteSource.getBytes();
		byte[] total = new byte[priSaltBytes.length + pubSaltBytes.length];
		System.arraycopy(priSaltBytes, 0, total, 0, priSaltBytes.length);
		System.arraycopy(pubSaltBytes, 0, total, priSaltBytes.length, pubSaltBytes.length);
		String secPwd = new Md5Hash(newPassword, ByteSource.Util.bytes(total)).toHex();
		user.setSalt(pubByteSource.toHex());
		user.setPassword(secPwd);
		userDao.updateUser(user);
	}
	
	public void correlationRoles(Long userId, Long... roleIds) {
		userDao.correlationRoles(userId, roleIds);
	}
	
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		userDao.uncorrelationRoles(userId, roleIds);
	}
	
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	// 测试
	public void testCascade() {
		userDao.testCascade();
	}
	
}
