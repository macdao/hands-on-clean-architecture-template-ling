不拆分多个独立的组件。实现层面不使用gradle subprojects。

优点：

- 不使用gradle subprojects
- 不使用convention plugin
- 简化了mavenBom的使用
- SpringBoot测试不需要空的SpringBootApplication
- application service的测试不需要mock所有的依赖

缺点：

- 各组件没有独立的依赖声明
- 没有构建工具级别的组件间依赖管理