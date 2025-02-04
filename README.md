# HoCATLing 🐾

HoCATLing，Hands-on Clean Architecture Template Ling，即可落地的整洁架构模板轻量级版本，基于[HoCAT](https://github.com/macdao/hands-on-clean-architecture-template)，适用于小型项目。

- 不拆分多个独立的组件，简化项目结构
- 刻意不使用DIP，而是直接依赖实现，简化依赖关系

![HoCATLing Diagram](https://www.plantuml.com/plantuml/svg/ZPB1QXin48RlUefvWFe5YZ593EcbBIKvv11o64iJhuXM8qQpBKlnkvU8R2rPQzgRcV_xPpxalScAejCOJ7xpCQ7W28vUSGfwpr6_uqXmxmLXpuu-mJUEd1DQSXN_G5On7WFTiJYIHvq3RRTtF4M7Ckzsu30FLnfetuxoJkuvi3Ijfy4hhPNUnVeZaMN4y1z7u8lWN75x7XD7Ydh9VVN7GQ_1VoNWmC6PmJk2nYSh2R7EYOoHHGKMe6L_6m2xR0PCANYBwZcM1E3SlprdZoG_lIK3S39jts5Iai-_wD3rznnLE0IIY-293hUbLuAAWyrcrwOd0zMWvBtMkb8YoJuhHKkTsWCbpbvPvelWnxbKAVRZebAjWG_NNgw0VN_TsGPFKVkNBEFLOMNPJ8hWn-GVSksYAzmEdXsFwEDBzu_KoV8JjflD-cCsH7lwP8nvjXnV_N6ISlwbx5--NdD3qKrZ-0C0)

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
    rectangle WebAdapter
    Controller --> WebAdapter
  }
  WebAdapter --> ApplicationService

  component adapter:persistence {
    rectangle Repository
    rectangle PersistenceAdapter
    PersistenceAdapter --> Repository 
  }
  ApplicationService --> PersistenceAdapter

  component adapter:client {
    rectangle Client
    rectangle ClientAdapter
    ClientAdapter --> Client 
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