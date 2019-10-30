package buddd.webapp.controllers;

import buddd.domain.application.UserApplicationService;
import buddd.domain.application.models.UserModel;
import buddd.domain.application.models.UserSummaryModel;
import buddd.webapp.models.UserDetailViewModel;
import buddd.webapp.models.UserSummaryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
  private final UserApplicationService userService;

  @Autowired
  UserController(UserApplicationService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  List<UserSummaryViewModel> index() {
    List<UserSummaryModel> users = userService.getUserList();
    return users.stream()
        .map(x -> new UserSummaryViewModel(x.getId(), x.getUserName()))
        .collect(Collectors.toList());
  }

  @PostMapping("/users")
  ResponseEntity<Void> register(
      @RequestParam("userName") String userName,
      @RequestParam("firstName") String firstName,
      @RequestParam("familyName") String familyName) {
    try {
      userService.registerUser(userName, firstName, familyName);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/users/{userId}")
  ResponseEntity<Void> delete(@PathVariable("userId") String userId) {
    try {
      userService.removeUser(userId);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/users/{userId}")
  UserDetailViewModel detail(@PathVariable("userId") String userId) {
    UserModel target = userService.getUserInfo(userId);
    return toUserDetailViewModel(target);
  }

  @PatchMapping("/users/{userId}")
  ResponseEntity<UserDetailViewModel> update(
      @PathVariable("userId") String userId,
      @RequestParam("userName") String userName,
      @RequestParam("firstName") String firstName,
      @RequestParam("familyName") String familyName) {
    try {
      userService.changeUserInfo(userId, userName, firstName, familyName);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    UserModel target = userService.getUserInfo(userId);
    return new ResponseEntity<>(toUserDetailViewModel(target), HttpStatus.OK);
  }

  private UserDetailViewModel toUserDetailViewModel(UserModel target) {
    return new UserDetailViewModel(
        target.getId(),
        target.getUserName(),
        target.getName().getFirstName(),
        target.getName().getFamilyName());
  }
}
