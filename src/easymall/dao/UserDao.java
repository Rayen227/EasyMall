package easymall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.User;

@Repository
@Mapper
public interface UserDao {
	public User login(String username);

	public int regist(User user);

	public User findByemail(String email);

	public void updateUser(String user); //名字可以考虑改 不太合适
}
