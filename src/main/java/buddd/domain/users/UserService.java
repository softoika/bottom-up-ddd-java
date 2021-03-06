package buddd.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean isDuplicated(User user) {
    UserName name = user.getUserName();
    User searched = userRepository.find(name);

    return searched != null;
  }
}
