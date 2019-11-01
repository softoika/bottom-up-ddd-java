package buddd.webapp.controllers;

import buddd.domain.application.UserApplicationService;
import buddd.domain.application.models.UserModel;
import buddd.domain.application.models.UserSummaryModel;
import buddd.webapp.models.UserDetailViewModel;
import buddd.webapp.models.UserEditViewModel;
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
  ResponseEntity<Void> register(@RequestBody UserEditViewModel user) {
    try {
      userService.registerUser(user.getUserName(), user.getFirstName(), user.getFamilyName());
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
  }

  @DeleteMapping("/users/{userId}")
  ResponseEntity<Void> delete(@PathVariable("userId") String userId) {
    try {
      userService.removeUser(userId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/users/{userId}")
  ResponseEntity<UserDetailViewModel> detail(@PathVariable("userId") String userId) {
    try {
      UserModel target = userService.getUserInfo(userId);
      return new ResponseEntity<>(toUserDetailViewModel(target), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping(value = "/users/{userId}")
  ResponseEntity<UserDetailViewModel> update(
      @PathVariable("userId") String userId, @RequestBody UserEditViewModel user) {
    try {
      userService.changeUserInfo(
          userId, user.getUserName(), user.getFirstName(), user.getFamilyName());
      UserModel target = userService.getUserInfo(userId);
      return new ResponseEntity<>(toUserDetailViewModel(target), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  private UserDetailViewModel toUserDetailViewModel(UserModel target) {
    return new UserDetailViewModel(
        target.getId(),
        target.getUserName(),
        target.getName().getFirstName(),
        target.getName().getFamilyName());
  }
}
