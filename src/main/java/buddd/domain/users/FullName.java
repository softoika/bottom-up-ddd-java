package buddd.domain.users;

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

  /* equalsとhashCodeはlombokの@EqualsAndHashCodeで代用可能 */

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FullName fullName = (FullName) o;
    return firstName.equals(fullName.firstName) &&
            familyName.equals(fullName.familyName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, familyName);
  }
}
