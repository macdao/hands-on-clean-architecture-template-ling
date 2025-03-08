# HoCATLing 🐾

HoCATLing，Hands-on Clean Architecture Template Ling，即可落地的整洁架构模板轻量级版本，基于[HoCAT](https://github.com/macdao/hands-on-clean-architecture-template)，适用于小型项目。

- 不拆分多个独立的组件，简化项目结构
- 刻意不使用DIP，而是直接依赖实现，简化依赖关系

![HoCATLing Diagram](https://www.plantuml.com/plantuml/svg/ZPJFYXin3CRlVWezGFC29OJT3TrUsbAoXpqiIt76siJ48xcofcKeUVSsBidyyHZsbA3lztsIXPYxf5QqQuD99q_HYct1uPljWZuowJVR8ZnwiR1bXn_WAnEdQ1jq8tw7ZLew17nWSIXFsWTShn-u8sUbtsp0sNIiE6npEiY5t79WcRYUZrvnoNGPh6n2BAqDxsW2dyNs8sxBRMH2qZdtnH-EMeDlGy2UWpD7xn0cyoH5GTO-eZ5o7GMkm9JzOm2QQpBO68Dlh7gch00C_lj1UqBvvHiq06SpzJiR5UNZRzmN_YsJ2eU0CXEUSVzZXpyxJFhlDqMcX1aP7B3QB69bqlt_2Gf_3XYhYtcmNgsctDd0g91poaKiBo6Y99yKgS5Y6JkCdBooz3FX4wdNN1mnb-TdOKv_7rgCdrQMy-13O9qQ6kMdbs_DJ6bTNa4JF9AMfv3BYyNA9MJjieOwLMQDLpAfmEgML1ShRUfRcNMjS1juSTnQJw_bPGtAxM--o7BTSsBQ-coCUMcOtloXazp-DUVVFW5Wp1sIwzhm5m00)

```plantuml
@startuml
skinparam defaultFontName Fira Code, Monospaced
skinparam RectangleBorderStyle<<Boundary>> dashed
skinparam RectangleBackgroundColor<<Boundary>> White
skinparam RectangleFontStyle<<Boundary>> normal
skinparam RectangleBackgroundColor Gray
skinparam ComponentBackgroundColor LightGray
skinparam ComponentFontStyle bold
hide <<Boundary>> stereotype

rectangle Boundary <<Boundary>> {
  component application {
    rectangle ApplicationService
  }

  component adapter:web {
    rectangle Controller
    rectangle Web_Request_Response
    Controller -> Web_Request_Response
    rectangle WebAdapter
    Controller --> WebAdapter
    Web_Request_Response <-- WebAdapter
  }
  WebAdapter --> ApplicationService

  component adapter:persistence {
    rectangle Repository
    rectangle Entity
    Repository -> Entity
    rectangle PersistenceAdapter
    PersistenceAdapter --> Repository
    PersistenceAdapter --> Entity
  }
  ApplicationService --> PersistenceAdapter

  component adapter:client {
    rectangle Client
    rectangle Client_Request_Response
    Client -> Client_Request_Response
    rectangle ClientAdapter
    ClientAdapter --> Client
    ClientAdapter --> Client_Request_Response
  }
  ApplicationService --> ClientAdapter

  ApplicationService -> [domain]
  WebAdapter --> [domain]
  [domain] <-- PersistenceAdapter
  [domain] <-- ClientAdapter
}


[configuration] --> Boundary  

@enduml
```

## 项目说明

查看文档[EXPLANATION.md](docs/EXPLANATION.md)。

## 项目使用

- 前置条件
    - 安装Java 21。
    - 安装Docker和Docker Compose。

- 测试构建

  使用`./gradlew build`构建项目。

- 本地运行

  使用`./gradlew bootRun`运行本地环境。

  如果需要启动本地三方服务，运行`scripts/run-stub-runner-server src/test/resources/contracts/client 16581`

- 打包

  使用`./gradlew bootBuildImage`构建Docker镜像。这基于Spring Boot的Gradle插件。

## 其他说明

查看[HoCAT README](https://github.com/macdao/hands-on-clean-architecture-template/blob/main/README.md)