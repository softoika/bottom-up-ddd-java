package buddd.domain.application.models;

import buddd.domain.users.FullName;

public class FullNameModel {
  private final String firstName;
  private final String familyName;

  public FullNameModel(FullName source) {
    firstName = source.getFirstName();
    familyName = source.getFamilyName();
  }

  public String getFirstName() {
    return firstName;
  }

  public String getFamilyName() {
    return familyName;
  }
}
