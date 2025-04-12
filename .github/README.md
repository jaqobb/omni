# omni

Set of modular utilities meant to speed up Java development.

## Adding to your project

### Maven

Replace `{module}` with one of the following: `omn-common`, `omni-math`, `omni-random`.

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
