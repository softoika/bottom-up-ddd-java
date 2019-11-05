package buddd.domain.users;

public interface UserFactory {
  User createUser(UserName userName, FullName fullName);
}
