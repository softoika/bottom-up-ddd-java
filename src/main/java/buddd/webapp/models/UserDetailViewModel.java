package buddd.webapp.models;

public class UserDetailViewModel {
  private String id;
  private String userName;
  private String firstName;
  private String familyName;

  public UserDetailViewModel(String id, String userName, String firstName, String familyName) {
    this.id = id;
    this.userName = userName;
    this.firstName = firstName;
    this.familyName = familyName;
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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }
}
