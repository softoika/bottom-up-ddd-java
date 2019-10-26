package buddd.webapp;

import buddd.domain.users.*;
import buddd.infrastructure.inmemory.InMemoryUserRepository;
import buddd.infrastructure.product.ProductUserRepository;

public class App {
  public void createUser(
      String userName, String firstName, String familyName, UserRepository userRepository)
      throws Exception {
    var user = new User(new UserName(userName), new FullName(firstName, familyName));
    var userService = new UserService(userRepository);
    if (userService.isDuplicated(user)) {
      throw new Exception("重複しています");
    } else {
      userRepository.save(user);
    }
  }

  public static void main(String[] args) {
    var app = new App();
    var repository = new InMemoryUserRepository();
    var testData = new User(new User("ttaro"), new FullName("taro", "tanaka"));
    repository.save(testData);
    try {
      app.createUser("ttaro", "taro", "tanaka", repository);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
