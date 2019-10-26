package buddd.domain.application;

import buddd.domain.application.models.UserModel;
import buddd.domain.application.models.UserSummaryModel;
import buddd.domain.users.*;

import java.util.List;
import java.util.stream.Collectors;

public class UserApplicationService {
  private final UserRepository userRepository;
  private final UserService userService;

  public UserApplicationService(UserRepository userRepository) {
    this.userRepository = userRepository;
    userService = new UserService(userRepository);
  }

  public void registerUser(String useName, String firstName, String familyName) throws Exception {
    var user = new User(new UserName(useName), new FullName(firstName, familyName));
    if (userService.isDuplicated(user)) {
      throw new Exception("重複しています");
    } else {
      userRepository.save(user);
    }
  }

  public void changeUserInfo(String id, String userName, String firstName, String familyName)
      throws Exception {
    var targetId = new UserId(id);
    var target = userRepository.find(targetId);
    if (target == null) {
      throw new Exception("not found. target id:" + id);
    }
    var newUserName = new UserName(userName);
    target.changeUserName(newUserName);
    var newName = new FullName(firstName, familyName);
    target.changeName(newName);
    userRepository.save(target);
  }

  public void removeUser(String id) throws Exception {
    var targetId = new UserId(id);
    var target = userRepository.find(targetId);
    if (target == null) {
      throw new Exception("not found. target id:" + id);
    }
    userRepository.remove(target);
  }

  public UserModel getUserInfo(String id) {
    var userId = new UserId(id);
    var target = userRepository.find(userId);
    if (target == null) {
      return null;
    }
    return new UserModel(target);
  }

  public List<UserSummaryModel> getUserList() {
    var users = userRepository.findAll();
    return users.stream().map(x -> new UserSummaryModel(x)).collect(Collectors.toList());
  }
}
