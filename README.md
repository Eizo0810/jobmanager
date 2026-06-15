# JobManager

求人管理システム（Spring Boot + PostgreSQL）

## 概要

求職者支援訓練向けの求人管理システムです。  
求人情報の登録・検索・編集・削除を行えます。

## 使用技術

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Thymeleaf
- Bootstrap
- Git / GitHub

## 主な機能

- 求人一覧表示
- 求人詳細表示
- 求人登録
- 求人編集
- 求人削除
- バリデーション
- 企業名・職種・勤務地による複合検索
- ユーザー登録
- ログイン / ログアウト
- ログインユーザーごとの求人管理
- 他ユーザー求人へのアクセス制限

## 画面イメージ

### 求人一覧

![求人一覧](screenshots/job-list.png)

### 求人登録

![求人登録](screenshots/job-create.png)

### 求人詳細

![求人詳細](screenshots/job-detail.png)

### 求人検索

![求人検索](screenshots/job-search.png)

## ER図

```mermaid
erDiagram
    APP_USER ||--o{ JOB : owns

    APP_USER {
        BIGINT id PK
        VARCHAR username
        VARCHAR password
        VARCHAR role
        BOOLEAN enabled
    }

    JOB {
        BIGINT id PK
        VARCHAR company_name
        VARCHAR job_title
        VARCHAR location
        VARCHAR employment_type
        VARCHAR salary
        TEXT description
        TEXT required_skills
        DATE posted_date
        BIGINT user_id FK
    }
```

## システム構成図

```mermaid
flowchart LR
    A[ユーザー]
    B[Web Browser]
    C[Spring Security]
    D[Spring Boot Controller]
    E[Service]
    F[Spring Data JPA Repository]
    G[(PostgreSQL)]

    A --> B
    B --> C
    C --> D
    D --> E
    E --> F
    F --> G
```

## Docker起動方法

### 起動

```bash
docker compose up --build
```

### アクセスURL

```text
http://localhost:8080/login
```

### 初期ユーザー

```text
ユーザー名：admin
パスワード：password
```

### Docker版で確認できること

- ユーザー登録
- ログイン / ログアウト
- 求人登録
- 求人一覧表示
- 求人検索
- 求人編集
- 求人削除
- PostgreSQLコンテナへのデータ保存

### 停止

```bash
docker compose down
```
