package buddd.infrastructure.production;

import buddd.domain.users.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDUserFactory implements UserFactory {
  @Override
  public User createUser(UserName userName, FullName fullName) {
    return new User(new UserId(UUID.randomUUID().toString()), userName, fullName);
  }
}
