# Development Setting

## IntelliJ

* IntelliJ & Java8をインストールする
* lombok pluginをインストールする
    * http://siosio.hatenablog.com/entry/2013/12/23/000054
* devtoolsのreloadを有効にする
    * http://rabitarochan.hatenablog.com/entry/2016/01/12/010626
    
### import sources

* git clone https://github.com/uzresk/springboot-doma2-samples.git
* Intellijを開く
* File -> New -> Project From Existing Sourcesを選択し、cloneしたソースを開く
* File -> New -> Module From Existing Sourcesを選択し、appディレクトリを選択
* File -> New -> Module From Existing Sourcesを選択し、frameworkディレクトリを選択

### Run Configuration

* Run Configurationの設定
    * Command Line に spring-boot:runを設定
* JUnit
    * testの時にsrc/main/resources配下をクラスパスに追加する
    * モジュール名を右クリック -> Open Module Settings -> dependenciesにsrc/main/resourcesを追加
    * scopeをtestに変更

## Run

```
cd app
mvnw spring-boot:run
```

or

```
cd app
mvn spring-boot:run
```

---

## Memo

* 画面を構成するのは、html, Controller, Service, Dao
* トランザクション境界はService
* Lombokを利用する
    * formのgetter,setter,toStringなどは@Data
    * slf4jも@slf4jを利用する
* Autowiredは利用せずコンストラクタインジェクションを使う。
    * http://pppurple.hatenablog.com/entry/2016/12/29/233141
* Entityは自動生成する
    * 大量のEntityがある場合はdoma2-genを使う
* Component
     * componentは余計なオブジェクトを生成しないため、Singleton(default)で取り扱う
     * このため、componentとなるオブジェクトにはメンバ変数を記載しないこと。
* validator
    * 必須チェック、属性チェック、相関チェックのタイミングは分割する（必須チェックが通らないと属性チェックは行わない）
    * 日付、電話番号、Emailなどのチェックの場合、チェック内で属性のチェックを行う（日付フィールドに数値チェックと日付チェックをわざわざつけない）
    * その画面でしか利用しない複数項目のチェックは画面固有のValidatorで実装する。複数画面で利用するものは独自のValidatorを作る
    * 参考：http://qiita.com/eiryu/items/95a206d617bd2b956953
    * Validationの仕様はここから
        * http://beanvalidation.org/specification/
        * http://hibernate.org/validator/documentation/
* パッケージ/ディレクトリ構成
    * dao
    * entity
    * web
        * config
        * controller
            * error
            * login
    * service
* テーブル名とEntityのマッピング
    * テーブル名はsnake、Entityはcamelで記載する。
    * マッピングに関してはEntityのアノテーションで解決する（@Entity(naming=NamingType.SNAKE_UPPER_CASE)）
    * EntityクラスBaseEntityクラスを継承する。BaseEntityクラスは共通のフィールドを保持し、BaseEntityListenerでInsert,Update時に共通のカラムがupdateされる
* 排他制御
    * 各テーブルにはバージョンIDカラムを持ち、@Versionを付与しておく。
    * が、そもそも排他制御が必要なのか否か、Immutableなモデルにできないかを検討しておく。
* 例外
    * どんな例外も基本的にはGlobalErrorControllerが動く。
    * GlobalErrorControllerは例外をリクエストスコープに入れて画面側のErrorControllerにforwardする。
    * 一つControllerを間に挟んでいるのはRESTを考慮してのこと。
    * 404エラーは通常ではtomcatのエラーページが表示されてしまう。
* 認証/認可
    * 認証はSecurityConfigで設定
    * 認可はSpringSecurityの機能を使って実現
* コード値
    * プルダウンなどで使うコード値はCodeテーブルに格納しておく。
    * 起動時にCodeManagerが全件キャッシュしている。
    * キャッシュのリロード機能は未実装
    * テンプレート側からキャッシュされたコード値を利用する場合には、CodeUtilityを利用する。(edit.html参照）
* テスト
    * JUnit4を利用。assertはassertjとassertj-dbを利用
    * Excelでのデータ作成は見通し難いので、テストデータの作成はDbsetupを利用
    * test用のデータベースはin-memoryのh2を利用
        * 初期セットアップでsqlを投入
            * http://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html#howto-initialize-a-database-using-spring-jdbc
        * spring.datasource.sql-script-encodingを指定しないと日本語が文字化けする
* DB migration
    * flywayを利用したい
    
