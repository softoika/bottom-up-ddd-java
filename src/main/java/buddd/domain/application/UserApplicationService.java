package buddd.domain.application;

import buddd.domain.application.models.UserModel;
import buddd.domain.application.models.UserSummaryModel;
import buddd.domain.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserApplicationService {
  private final UserRepository userRepository;
  private final UserService userService;

  @Autowired
  public UserApplicationService(UserRepository userRepository, UserService userService) {
    this.userRepository = userRepository;
    this.userService = userService;
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
    User target = userRepository.find(targetId);
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
    User target = userRepository.find(targetId);
    if (target == null) {
      throw new Exception("not found. target id:" + id);
    }
    userRepository.remove(target);
  }

  public UserModel getUserInfo(String id) throws Exception {
    var userId = new UserId(id);
    User target = userRepository.find(userId);
    if (target == null) {
      throw new Exception("not found. target id:" + id);
    }
    return new UserModel(target);
  }

  public List<UserSummaryModel> getUserList() {
    List<User> users = userRepository.findAll();
    return users.stream().map(x -> new UserSummaryModel(x)).collect(Collectors.toList());
  }
}
