package buddd.domain.application.models;

import buddd.domain.users.User;

/** 一覧用モデル */
public class UserSummaryModel {
  private final String id;
  private final String userName;

  public UserSummaryModel(User source) {
    id = source.getId().getValue();
    userName = source.getUserName().getValue();
  }

  public String getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }
}
