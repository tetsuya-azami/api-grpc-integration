# redocの表示手順

1. bootRunタスクを実行
2. openApiGenerateタスクを実行
3. generateOpenApiDocsタスクを実行
4. プロジェクト直下のディレクトリで下記コマンドを実行
    1. ```bash
       docker compose -f tools/redoc/docker-compose.yml up -d
       ```
5. http://127.0.0.1:8081/ にアクセス

# redocの更新手順

1. openApiGenerateタスクを実行
2. tools/redoc/openapi.jsonを消す
3. generateOpenApiDocsタスクを実行