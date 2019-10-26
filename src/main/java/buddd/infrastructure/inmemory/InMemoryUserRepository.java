package buddd.infrastructure.inmemory;

import buddd.domain.users.User;
import buddd.domain.users.UserId;
import buddd.domain.users.UserName;
import buddd.domain.users.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository {
  private final Map<UserId, User> data = new HashMap<>();

  @Override
  public User find(UserId id) {
    User user = data.get(id);
    if (user != null) {
      return cloneUser(user);
    } else {
      return null;
    }
  }

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
  public List<User> findAll() {
    return data.entrySet().stream().map(x -> cloneUser(x.getValue())).collect(Collectors.toList());
  }

  @Override
  public void save(User user) {
    data.put(user.getId(), user);
  }

  @Override
  public void remove(User user) {
    data.remove(user.getId());
  }

  private User cloneUser(User user) {
    return new User(user.getId(), user.getUserName(), user.getName());
  }
}
