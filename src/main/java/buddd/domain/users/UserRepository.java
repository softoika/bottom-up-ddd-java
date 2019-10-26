package buddd.domain.users;

import java.util.List;

public interface UserRepository {
  User find(UserId userId);

  User find(UserName userName);

  List<User> findAll();

  void save(User user);

  void remove(User user);
}
