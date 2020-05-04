## Credentials for tests

In order to run tests, set the credentials by creating the file `projectCredentials.json`
in the root of the project with the following format:

```
{
  "environment": "sandbox",
  "id": "0000000000000000",
  "privateKey": "-----BEGIN EC PARAMETERS-----00000000000000\n-----END EC PARAMETERS-----\n-----BEGIN EC PRIVATE KEY-----\n0000000000000000000000000000000000000000000000000000000000000000\n00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\n-----END EC PRIVATE KEY-----\n"
}

```

The tests will get the credentials in that file by default.

## Deploying to maven

To upload this file to maven, you have to reference your GPG key and ossrh tokens in your `gradle.properties` file.

```
signing.keyId=0x00000000
signing.password=yoursecurepassword
signing.secretKeyRingFile=${HOME}/.gnupg/secring.gpg

ossrhUsername=usernametoken
ossrhPassword=usernamepassword
```
