package buddd.domain.users;

import java.util.Objects;

/**
 * 値オブジェクトのルール
 * - 状態を不変に保つ
 * - 同じ値オブジェクト同士で値が等しいかどうかの確認ができる
 * - 完全に交換可能である
 */
public class FullName {
    private final String firstName;
    private final String familyName;

    public FullName(String firstName, String familyName) {
        // equals(FullName)のときにNPEになってしまうのでnullチェックを追加
        this.firstName = Objects.requireNonNull(firstName);
        this.familyName = Objects.requireNonNull(familyName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public boolean equals(FullName other) {
        if (Objects.equals(other, null)) return false;
        if (Objects.equals(other, this)) return true;
        return firstName.equals(other.familyName) && familyName.equals(other.familyName);
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.equals(obj, null)) return false;
        if (Objects.equals(obj, this)) return true;
        if (!getClass().equals(obj.getClass())) return false;
        return equals((FullName) obj);
    }

    @Override
    public int hashCode() {
        return ((firstName != null ? firstName.hashCode() : 0) * 397) ^ (familyName != null ? familyName.hashCode() : 0);
    }
}
