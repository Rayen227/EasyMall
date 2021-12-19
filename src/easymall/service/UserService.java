package easymall.service;

import easymall.po.User;

public interface UserService {
	public User login(String username);

	public int regist(User user);

	public User findByemail(String email);

	public void updateUser(String email);
}
