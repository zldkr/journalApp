# journalApp

基于 Spring Boot、Spring Security 和 MongoDB 的日记 REST API 学习项目。
支持注册用户、HTTP Basic 认证、个人日记增删改查，以及管理员查询和创建用户。

## 环境

- 推荐 JDK 17；`pom.xml` 的 Java 编译目标为 1.8。
- Maven Wrapper 已包含在仓库中，无需另行安装 Maven。
- MongoDB Atlas 或支持事务的 MongoDB 副本集。日记服务使用 MongoDB 事务。

## Windows PowerShell 启动

在克隆后的项目根目录执行：

```powershell
Copy-Item src/main/resources/application-dev.example.yml src/main/resources/application-dev.yml
$env:MONGODB_URI = 'mongodb+srv://<username>:<password>@<cluster-host>/?appName=journalApp'
$env:MONGODB_DATABASE = 'journaldb'
./mvnw.cmd -DskipTests package
java -jar target/journalApp-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

把连接串中的占位符换成自己的数据库信息，并按 MongoDB URI 规则编码用户名和密码。
Atlas 需要允许运行机器的 IP 访问。`JAVA_HOME` 和 `Path` 应指向 JDK 17。
上面的打包命令跳过测试；完整测试命令为 `./mvnw.cmd test`，其中
`JournalAppApplicationTests` 会启动 Spring 上下文并连接配置的 MongoDB。

生产配置可以从 `application-prod.example.yml` 复制为 `application-prod.yml`，
使用 `--spring.profiles.active=prod` 启动，默认端口为 8081。

## 主要接口

开发配置默认地址为 `http://localhost:8080`。

| 方法 | 路径 | 功能 |
| --- | --- | --- |
| GET | `/public/health-check` | 健康检查，返回 `OK!` |
| POST | `/public/create-user` | 注册用户 |
| GET / POST | `/journal` | 查询当前用户日记 / 创建日记 |
| GET / PUT / DELETE | `/journal/id/{id}` | 查询 / 更新 / 删除日记 |
| PUT | `/user/modify` | 修改当前用户资料 |
| DELETE | `/user` | 删除当前用户 |
| GET | `/admin/all-users` | 管理员查询用户 |
| POST | `/admin/create-admin-user` | 创建管理员用户 |

需要登录的接口使用 HTTP Basic 认证，管理员接口需要 `ADMIN` 角色。

## 配置与仓库内容

真实 `application-dev.yml`、`application-prod.yml`、环境变量文件、日志、IDE 配置、
构建产物以及本地 Postman 请求不会提交。仓库提供的配置示例只读取环境变量。
本地已有配置无需覆盖；克隆仓库后再复制示例。此项目提供后端 API，未包含前端。
