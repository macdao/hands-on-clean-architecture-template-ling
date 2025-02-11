# HoCATLing 🐾

HoCATLing，Hands-on Clean Architecture Template Ling，即可落地的整洁架构模板轻量级版本，基于[HoCAT](https://github.com/macdao/hands-on-clean-architecture-template)，适用于小型项目。

- 不拆分多个独立的组件，简化项目结构
- 刻意不使用DIP，而是直接依赖实现，简化依赖关系

![HoCATLing Diagram](https://www.plantuml.com/plantuml/svg/ZPFDQXin4CVlUefvWFe5YbcI6D9BsvG4aaF8OInDR16jHencMvQIToyMMQ-yiajkzlzZvZU8lSgAeaCVJ7xpCQ5W3uvUSGXwor5_OazmwmLXmuw-m0-Ed1DQSbN-dgnYt0MwON4a3pe6MgzlU8WEPUmwS9Zt5nfettPoI6uui9oqdlPUwLpf0DOkYImzXayiWM-2OvNRS9yuKjHbxhlVxVLI-0W1MmxExBqZEE79IaAiOo9ZP4A1AN2Q_Mi0x3GPCANWBQhdM1o0kNyzUmyalxqb0_1kJliEav9y_KFRfh_XgC8Xa2oC9zfUbrungXoiLjtiBckFTqsycyJ7ErYlLbMte48bbDdjIUVFIIJPPwLegJdhdX9dhopZmlWrb-e3MhcWp6FWWxM5Ay6sVvxP1d_usxn2aS-ADMMj5C0FvVzmYq4NSXqyE-xHnvVcHMfd-bbUihrwaLYiVZV6F5kEhtutI5d_KkOVFm8mvegY6_hm3m00)

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
    rectangle VO
    Controller -> VO
    VO <-- WebAdapter
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