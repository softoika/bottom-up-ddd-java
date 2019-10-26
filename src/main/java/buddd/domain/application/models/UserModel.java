package buddd.domain.application.models;

import buddd.domain.users.User;

/**
 * この例ではドメイン領域の知識が流出しないようにDTO(Data Transfer Object)を用意する
 * DTOを用いるとドメイン領域外部で名前を変更したりするといった操作ができなくなるメリットがある
 */
public class UserModel {
  private final String id;
  private final String userName;
  private final FullNameModel name;

  public UserModel(User source) {
    id = source.getId().getValue();
    userName = source.getUserName().getValue();
    name = new FullNameModel(source.getName());
  }

  public String getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public FullNameModel getName() {
    return name;
  }
}
