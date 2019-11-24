[![Actions Status](https://github.com/softoika/bottom-up-ddd-java/workflows/Java%20CI/badge.svg)](https://github.com/softoika/bottom-up-ddd-java/actions?query=workflow%3A%22Java+CI%22)
# bottom-up-ddd-java
https://github.com/nrslib/BottomUpDDD のJava実装  
MVCフレームワーク部分のViewは作っていなくてControllerをREST APIにしています。動作はcurlなどで確認してください

## Dependency
- Java 10以降
- MySQL (DB接続する場合)

## 実行方法
```bash
$ ./mvnw install
$ java -jar target/bottom-up-ddd-java-1.0-SNAPSHOT.jar
```

## 実行例
```bash
# 作成
$ curl -v -X POST localhost:8080/users -H "Content-Type:application/json" -d '{"userName":"ttaro", "firstName":"taro", "familyName":"tanaka"}'
# 一覧
$ curl localhost:8080/users
# 詳細
$ curl localhost:8080/users/c1863de4-5075-46b7-800a-dc9e6ef32bf6
# 更新
$ curl -v -X PUT localhost:8080/users/c1863de4-5075-46b7-800a-dc9e6ef32bf6 -H "Content-Type:application/json" -d '{"userName":"tmuxtaro", "firstName":"taro", "familyName":"tanaka"}'
# 削除
$ curl -v -X DELETE localhost:8080/users/c1863de4-5075-46b7-800a-dc9e6ef32bf6
```

## データベースに接続して実行する
1. `setup_db.sql`を流す。もしくは同様のdatabaseとtableを作成する
```bash
$ mysql -uroot -p < setup_db.sql
```
2. `src/main/resources/application.properties`の値を`production`に変える
3. `src/main/resoruces/jdbc.properties`のユーザー名とパスワードを変更し、適宜mysqlでユーザーを作成する
