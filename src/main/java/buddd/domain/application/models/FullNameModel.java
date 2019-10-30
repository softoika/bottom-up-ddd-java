package buddd.domain.application.models;

import buddd.domain.users.FullName;
import lombok.Getter;

@Getter
public class FullNameModel {
  private final String firstName;
  private final String familyName;

  public FullNameModel(FullName source) {
    firstName = source.getFirstName();
    familyName = source.getFamilyName();
  }
}
