package buddd.domain.users;

import java.util.Objects;

/**
 * 値オブジェクトを作るモチベーション
 * - 存在しない値を存在させない
 * - 間違った代入を防ぐ
 */
public class UserName {
    private final String name;

    public UserName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("It cannot be null or empty");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("It must be 50 characters or less");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equals(UserName other) {
        if (Objects.equals(other, null)) return false;
        if (Objects.equals(other, this)) return true;
        return name.equals(other.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.equals(obj, null)) return false;
        if (Objects.equals(obj, this)) return true;
        if (!getClass().equals(obj.getClass())) return false;
        return equals((UserName) obj);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
