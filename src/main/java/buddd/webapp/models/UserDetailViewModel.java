package buddd.webapp.models;

import lombok.Value;

@Value
public class UserDetailViewModel {
  private String id;
  private String userName;
  private String firstName;
  private String familyName;
}
