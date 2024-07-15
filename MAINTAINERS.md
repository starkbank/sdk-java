# Deploying to Maven Central Repository

## Maven Steps

### Install maven :
```sh
brew install maven
```

### Create a Settings.xml file at `<your-user>/.m2`
```sh
cd <your-user>/.m2
vi settings.xml
```

### Paste your credentials
```sh
<settings>
  <servers>
    <server>
      <id>central</id>
      <username><!-- your token username --></username>
      <password><!-- your token password --></password>
    </server>
  </servers>
</settings>
```

## Register gpg keys

### Install gpg :
https://gnupg.org/download/

### Generate gpg keys:
```sh
gpg --gen-key
```

### Register key to online server:
```sh
gpg --keyserver keys.openpgp.org --send-keys YOUR-KEY-ID
gpg --keyserver keyserver.ubuntu.com --send-keys YOUR-KEY-ID
```


## Modify the pom.xml file

Add the following to `<executions></executions>` 
```sh
<executions>
    <execution>
        <id>sign-artifacts</id>
        <phase>verify</phase>
        <goals>
            <goal>sign</goal>
        </goals>
    </execution>
    <configuration>
        <keyname>YOUR-KEY-ID</keyname>
        <gpgArguments>
            <arg>--pinentry-mode</arg>
            <arg>loopback</arg>
        </gpgArguments>
    </configuration>
</executions>
```


## Deploy and release

### Publish to Maven Central:
```sh
mvn clean deploy
```

### Login to Maven Central Repository Manager and release:
https://central.sonatype.com/artifact/com.starkinfra.core/starkcore