## Deploying to maven

To upload this file to maven, you have to reference your GPG key and ossrh tokens in your `gradle.properties` file.

```
signing.keyId=0x00000000
signing.password=yoursecurepassword
signing.secretKeyRingFile=${HOME}/.gnupg/secring.gpg

ossrhUsername=usernametoken
ossrhPassword=usernamepassword
```

Then execute the `uploadArchives` task in Gradle to upload the project to the Maven repository.
