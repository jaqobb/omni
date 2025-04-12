# omni

Modular utilities designed to speed up Java development. omni offers a collection of independent modules, allowing you to include only what you need—saving time by avoiding writing boilerplate code and adding unnecessary dependencies.

## Available modules

* omni-common: General utilities that are small and do not yet warrant a separate module, such as helpers for converting strings to booleans or numbers.
* omni-math: Math utilities, including classes for working with 2D and 3D bounding boxes.
* omni-random: Random utilities, including helpers for randomizing booleans, numbers, and elements in arrays and collections, as well as support for chance testing and weighted randomness.

## Adding to your project

Replace `{module}` with one of the modules listed in the [Available modules](#available-modules) section. You can use multiple modules simultaneously as needed, without any issues.

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

### Gradle (Groovy)

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

### Gradle (Kotlin)

```kotlin
repositories {
  maven {
    name = "jaqobbRepositorySnapshots"
    url = uri("https://repository.jaqobb.dev/snapshots")
  }
}

dependencies {
  implementation("dev.jaqobb:{module}:1.0.0-SNAPSHOT")   
}
```
