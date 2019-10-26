package buddd.infrastructure.inmemory;

import buddd.domain.users.User;
import buddd.domain.users.UserId;
import buddd.domain.users.UserName;
import buddd.domain.users.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
  private final Map<UserId, User> data = new HashMap<>();

  @Override
  public User find(UserName userName) {
    Optional<User> target =
        data.entrySet().stream()
            .map(x -> x.getValue())
            .filter(x -> x.getUserName().equals(userName))
            .findFirst();
    if (target.isPresent()) {
      return cloneUser(target.get());
    } else {
      return null;
    }
  }

  @Override
  public void save(User user) {
    data.put(user.getId(), user);
  }

  private User cloneUser(User user) {
    return new User(user.getId(), user.getUserName(), user.getName());
  }
}
