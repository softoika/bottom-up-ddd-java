package buddd.domain.users;

import java.util.Objects;
import java.util.UUID;

/**
 * エンティティの特徴
 * - 可変
 * - 同じ属性でも区別される
 * - 同一性を持つ
 *
 * Userオブジェクトは識別子(UserId)を持つ
 */
public class User {
    private final UserId id;
    private FullName name;

    /**
     * データベース等から読み取ったときに利用するコンストラクタ
     */
    public User(UserId id, FullName name) {
        // idにnullが入るのはおかしいし、nameがchangeNameでnullを例外としているのでコンストラクタでもnullを弾くべき
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
    }

    /**
     * 生成するときのコンストラクタ (guid(uuid)が設定される)
     */
    public User(FullName name) {
        this(new UserId(UUID.randomUUID().toString()), name);
    }

    public void changeName(FullName newName) {
        name = Objects.requireNonNull(newName);
    }

    public boolean equals(User other) {
        if (Objects.equals(other, null)) return false;
        if (Objects.equals(other, this)) return true;
        return id.equals(other.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.equals(obj, null)) return false;
        if (Objects.equals(obj, this)) return true;
        if (!getClass().equals(obj.getClass())) return false;
        return equals((User) obj);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
