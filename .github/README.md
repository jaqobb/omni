# omni

Modular utilities designed to speed up Java development by reducing boilerplate code. omni offers a collection of independent modules, allowing you to include only what you need - saving time by avoiding repetitive code and unnecessary dependencies.

## Adding to your project

Replace `{module}` with one of the available modules: `omn-common`, `omni-math`, `omni-random`. You can also include multiple modules as needed, without any issues.

### Maven

```xml
<repositories>
  <repository>
    <id>jaqobb-repository-snapshots</id>
    <name>jaqobb Repository</name>
    <url>https://repository.jaqobb.dev/snapshots</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>dev.jaqobb</groupId>
    <artifactId>{module}</artifactId>
    <version>1.0.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```

### Gradle Groovy

```groovy
repositories {
  maven {
    name "jaqobbRepositorySnapshots"
    url "https://repository.jaqobb.dev/snapshots"
  }
}

dependencies {
  implementation "dev.jaqobb:{module}:1.0.0-SNAPSHOT"
}
```
