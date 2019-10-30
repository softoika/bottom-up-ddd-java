package buddd.domain.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

/* 値オブジェクトのルール
 * - 状態を不変に保つ
 * - 同じ値オブジェクト同士で値が等しいかどうかの確認ができる
 * - 完全に交換可能である
 */
public class FullName {
  @Getter private final String firstName;
  @Getter private final String familyName;

  public FullName(@NonNull String firstName, @NonNull String familyName) {
    this.firstName = firstName;
    this.familyName = familyName;
  }

  public boolean equals(FullName other) {
    if (Objects.equals(other, null)) return false;
    if (Objects.equals(other, this)) return true;
    return firstName.equals(other.firstName) && familyName.equals(other.familyName);
  }
}
