package buddd.domain.application.models;

import buddd.domain.users.User;
import lombok.Getter;

/** 一覧用モデル */
@Getter
public class UserSummaryModel {
  private final String id;
  private final String userName;

  public UserSummaryModel(User source) {
    id = source.getId().getValue();
    userName = source.getUserName().getValue();
  }
}
