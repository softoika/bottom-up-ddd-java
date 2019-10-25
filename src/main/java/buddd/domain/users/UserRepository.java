package buddd.domain.users;

public interface UserRepository {
  User find(UserName userName);

  void save(User user);
}
