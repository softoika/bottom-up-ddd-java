package buddd.domain.users;

import buddd.domain.application.UserApplicationService;
import buddd.infrastructure.development.InMemoryUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

  @Test
  void testDuplicateFail() {
    UserRepository repository = new InMemoryUserRepository();
    var userName = new UserName("ttaro");
    var fullName = new FullName("taro", "tanaka");
    repository.save(new User(userName, fullName));
    var app = new UserApplicationService(repository, new UserService(repository));
    Exception e = assertThrows(Exception.class, () -> app.registerUser("ttaro", "taro", "tanaka"));
    assertEquals(e.getMessage(), "重複しています");
  }

  @Test
  void testRegister() throws Exception {
    UserRepository repository = new InMemoryUserRepository();
    var app = new UserApplicationService(repository, new UserService(repository));
    app.registerUser("ttaro", "taro", "tanaka");
    User user = repository.find(new UserName("ttaro"));
    assertNotNull(user);
    assertFalse(user.getId().getValue() == "1");
  }
}
