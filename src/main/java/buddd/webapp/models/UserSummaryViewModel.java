package buddd.webapp.models;

public class UserSummaryViewModel {
  private String id;
  private String userName;

  public UserSummaryViewModel(String id, String userName) {
    this.id = id;
    this.userName = userName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
