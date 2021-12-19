package easymall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.UserDao;
import easymall.po.User;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public User login(String username) {
		// TODO Auto-generated method stub
		return userDao.login(username);
	}
	@Override
	public int regist(User user) {
		return userDao.regist(user);
	}
	@Override
	public User findByemail(String email) {
		return userDao.findByemail(email);
	}
	@Override
	public void updateUser(String user) {
		userDao.updateUser(user);
	}

}
