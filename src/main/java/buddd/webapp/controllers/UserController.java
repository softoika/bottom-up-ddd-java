package buddd.webapp.controllers;

import buddd.domain.application.UserApplicationService;
import buddd.domain.application.models.UserModel;
import buddd.domain.application.models.UserSummaryModel;
import buddd.webapp.models.UserDetailViewModel;
import buddd.webapp.models.UserSummaryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
  private final UserApplicationService userService;

  @Autowired
  public UserController(UserApplicationService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<UserSummaryViewModel> index() {
    List<UserSummaryModel> users = userService.getUserList();
    return users.stream()
        .map(x -> new UserSummaryViewModel(x.getId(), x.getUserName()))
        .collect(Collectors.toList());
  }

  @PostMapping("/users")
  public void register(
      @RequestParam("userName") String userName,
      @RequestParam("firstName") String firstName,
      @RequestParam("familyName") String familyName)
      throws Exception {
    userService.registerUser(userName, firstName, familyName);
  }

  @DeleteMapping("/users/{userId}")
  public void delete(@PathVariable("userId") String userId) throws Exception {
    userService.removeUser(userId);
  }

  @GetMapping("/users/{userId}")
  public UserDetailViewModel detail(@PathVariable("userId") String userId) {
    UserModel target = userService.getUserInfo(userId);
    return toUserDetailViewModel(target);
  }

  @PatchMapping("/users/{userId}")
  public UserDetailViewModel update(
      @PathVariable("userId") String userId,
      @RequestParam("userName") String userName,
      @RequestParam("firstName") String firstName,
      @RequestParam("familyName") String familyName)
      throws Exception {
    userService.changeUserInfo(userId, userName, firstName, familyName);
    UserModel target = userService.getUserInfo(userId);
    return toUserDetailViewModel(target);
  }

  private UserDetailViewModel toUserDetailViewModel(UserModel target) {
    return new UserDetailViewModel(
        target.getId(),
        target.getUserName(),
        target.getName().getFirstName(),
        target.getName().getFamilyName());
  }
}
