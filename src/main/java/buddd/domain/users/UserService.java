package buddd.domain.users;

public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean isDuplicated(User user) {
    var name = user.getUserName();
    var searched = userRepository.find(name);

    return searched != null;
  }
}
