# Stark Bank Java SDK Beta

Welcome to the Stark Bank Java SDK! This tool is made for Java 
developers who want to easily integrate with our API.
This SDK version is compatible with the Stark Bank API v2.

If you have no idea what Stark Bank is, check out our [website](https://starkbank.com/) 
and discover a world where receiving or making payments 
is as easy as sending a text message to your client!

## Help and Feedback

If you have any questions about our SDK, just email us your questions. 
We will respond you quickly, pinky promise. We are here to help you integrate with us ASAP. 
We also love feedback, so don't be shy about sharing your thoughts with us.

Email: developers@starkbank.com

## Supported Java Versions

This library supports the following Java versions:

* Java 8+

If you have specific version demands for your projects, feel free to contact us.

## Stark Bank API Reference

If you want to take a look at our API, follow [this link](https://starkbank.com/docs/api).

## Versioning

This project adheres to the following versioning pattern:

Given a version number MAJOR.MINOR.PATCH, increment:

- MAJOR version when the **API** version is incremented. This may include backwards incompatible changes;
- MINOR version when **breaking changes** are introduced OR **new functionalities** are added in a backwards compatible manner;
- PATCH version when backwards compatible bug **fixes** are implemented.

## Setup

### 1. Install our SDK

Manually download the desired SDK version with the JAR found in our
[GitHub page](https://github.com/starkbank/sdk-java/releases/latest)
and add it to your project.

1.1 If you are using Gradle, add this line to your dependencies in build.gradle:

```sh
implementation files("lib/starkbank-0.1.3.jar")
```

1.2 If you are using Maven, add this dependency to your pom.xml:

```xml
<dependency>
    <groupId>com.starkbank</groupId>
    <artifactId>starkbank</artifactId>
    <version>0.1.3</version>
    <scope>system</scope>
    <systemPath>${basedir}/lib/starkbank-0.1.3.jar</systemPath>
</dependency>
```

### 2. Create your Private and Public Keys

We use ECDSA. That means you need to generate a secp256k1 private
key to sign your requests to our API, and register your public key
with us so we can validate those requests.

You can use one of following methods:

2.1. Check out the options in our [tutorial](https://starkbank.com/faq/how-to-create-ecdsa-keys).

2.2. Use our SDK:

```java
import com.starkbank.*;

Key key = Key.create();

String privatePem = key.privatePem;
String publicPem = key.publicPem;

System.out.print(privatePem);
System.out.print(publicPem);

// or, to also save .pem files in a specific path
Key key = Key.create("file/keys/");

String privatePem = key.privatePem;
String publicPem = key.publicPem;

System.out.print(privatePem);
System.out.print(publicPem);
```

### 3. Create a Project

You need a project for direct API integrations. To create one in Sandbox:

3.1. Log into [Starkbank Sandbox](https://sandbox.web.starkbank.com)

3.2. Go to Menu > Usuários (Users) > Projetos (Projects)

3.3. Create a Project: Give it a name and upload the public key you created in section 2.

3.4. After creating the Project, get its Project ID

3.5. Use the Project ID and private key to create the object below:

```java
import com.starkbank.*;

// Get your private key from an environment variable or an encrypted database.
// This is only an example of a private key content. You should use your own key.
String privateKeyContent = "-----BEGIN EC PARAMETERS-----\nBgUrgQQACg==\n-----END EC PARAMETERS-----\n-----BEGIN EC PRIVATE KEY-----\nMHQCAQEEIMCwW74H6egQkTiz87WDvLNm7fK/cA+ctA2vg/bbHx3woAcGBSuBBAAK\noUQDQgAE0iaeEHEgr3oTbCfh8U2L+r7zoaeOX964xaAnND5jATGpD/tHec6Oe9U1\nIF16ZoTVt1FzZ8WkYQ3XomRD4HS13A==\n-----END EC PRIVATE KEY-----";

Project project = new Project(
    "sandbox",
    "5656565656565656",
    privateKeyContent
);
```

NOTE 1: Never hard-code your private key. Get it from an environment variable or an encrypted database.

NOTE 2: We support `"sandbox"` and `"production"` as environments.

NOTE 3: The project you created in `sandbox` does not exist in `production` and vice versa.


### 4. Setting up the user

There are two kinds of users that can access our API: **Project** and **Member**.

- `Member` is the one you use when you log into our webpage with your e-mail.
- `Project` is designed for integrations and is the one meant for our SDK.

There are two ways to inform the user to the SDK:
 
4.1 Passing the user as argument in all functions:

```java
import com.starkbank.*;

Balance balance = Balance.get(project);
```

4.2 Set it as a default user in the SDK:

```java
import com.starkbank.*;

User.defaultUser = project;

Balance balance = Balance.get();
```

Just select the way of passing the project user that is more convenient to you.
On all following examples we will assume a default user has been set.

## Testing in Sandbox

Your initial balance is zero. For many operations in Stark Bank, you'll need funds
in your account, which can be added to your balance by creating a Boleto. 

In the Sandbox environment, 90% of the created Boletos will be automatically paid,
so there's nothing else you need to do to add funds to your account. Just create
a few and wait around a bit.

In Production, you (or one of your clients) will need to actually pay this Boleto
for the value to be credited to your account.


## Usage

Here are a few examples on how to use the SDK. If you have any doubts, use the built-in
`help()` function to get more info on the desired functionality
(for example: `help(starkbank.boleto.create)`)

### Get balance

To know how much money you have in your workspace, run:

```java
import com.starkbank.*;

Balance balance = Balance.get();

System.out.println(balance);
```

### Create boletos

You can create boletos to charge customers or to receive money from accounts
you have in other banks.

```java
import com.starkbank.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

List<Boleto> boletos = new ArrayList<>();
HashMap<String, Object> data = new HashMap<>();
data.put("amount", 400000);
data.put("name", "Iron Bank S.A.");
data.put("taxId", "20.018.183/0001-80");
data.put("streetLine1", "Av. Faria Lima, 1844");
data.put("streetLine2", "CJ 13");
data.put("district", "Itaim Bibi");
data.put("city", "São Paulo");
data.put("stateCode", "SP");
data.put("zipCode", "01500-000");
data.put("due", "2021-04-20");
data.put("fine", 2.5);
data.put("interest", 1.3);
data.put("overdueLimit", 5);
data.put("tags", new String[]{"War supply", "Invoice #1234"});
boletos.add(new Boleto(data));

boletos = Boleto.create(boletos);

for (Boleto boleto : boletos){
    System.out.println(boleto);
}
```

### Query boletos

You can get a list of created boletos given some filters.

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("after", "2020-04-01");
params.put("before", "2020-04-30");
Generator<Boleto> boletos = Boleto.query(params);

for (Boleto boleto : boletos){
    System.out.println(boleto);
}
```

### Get boleto

After its creation, information on a boleto may be retrieved by passing its id. 
Its status indicates whether it's been paid.

```java
import com.starkbank.*;

Boleto boleto = Boleto.get("5730174175805440");

System.out.println(boleto);
```

### Get boleto PDF

After its creation, a boleto PDF may be retrieved by passing its id. 

```java
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import com.starkbank.*;

InputStream pdf = Boleto.pdf("5915632394567680");

java.nio.file.Files.copy(
    pdf,
    new File("boleto.pdf").toPath(),
    StandardCopyOption.REPLACE_EXISTING
);
```

Be careful not to accidentally enforce any encoding on the raw pdf content,
as it may yield abnormal results in the final file, such as missing images
and strange characters.

### Delete boleto

You can also cancel a boleto by its id.
Note that this is not possible if it has been processed already.

```java
import com.starkbank.*;

Boleto boleto = Boleto.delete("5669456873259008");

System.out.println(boleto);
```

### Query boleto logs

Logs are pretty important to understand the life cycle of a boleto.

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("after", "2020-04-01");
params.put("before", "2020-04-30");
Generator<Boleto.Log> logs = Boleto.Log.query(params);

for (Boleto.Log log : logs){
    System.out.println(log);
}
```

### Get a boleto log

You can get a single log by its id.

```java
import com.starkbank.*;

Boleto.Log log = Boleto.Log.get("6532638269505536");

System.out.println(log);
```

### Create transfers

You can also create transfers in the SDK (TED/DOC).

```java
import com.starkbank.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

List<Transfer> transfers = new ArrayList<>();
HashMap<String, Object> data = new HashMap<>();
data.put("amount", 100000000);
data.put("bankCode", "341");
data.put("branchCode", "2201");
data.put("accountNumber", "76543-8");
data.put("taxId", "594.739.480-42");
data.put("name", "Daenerys Targaryen Stormborn");
data.put("tags", new String[]{"daenerys", "invoice/1234"});
transfers.add(new Transfer(data));

transfers = Transfer.create(transfers);

for (Transfer transfer : transfers){
    System.out.println(transfer);
}
```

### Query transfers

You can query multiple transfers according to filters.

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("after", "2020-04-01");
params.put("before", "2020-04-30");
Generator<Transfer> transfers = Transfer.query(params);

for (Transfer transfer : transfers){
    System.out.println(transfer);
}
```

### Get transfer

To get a single transfer by its id, run:

```java
import com.starkbank.*;

Transfer transfer = Transfer.get("6532638269505536");

System.out.println(transfer);
```

### Get transfer PDF

After its creation, a transfer PDF may also be retrieved by passing its id. 

```java
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import com.starkbank.*;

InputStream pdf = Transfer.pdf("6266195376340992");

java.nio.file.Files.copy(
    pdf,
    new File("transfer.pdf").toPath(),
    StandardCopyOption.REPLACE_EXISTING
);
```

Be careful not to accidentally enforce any encoding on the raw pdf content,
as it may yield abnormal results in the final file, such as missing images
and strange characters.

### Query transfer logs

You can query transfer logs to better understand transfer life cycles.

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("after", "2020-04-01");
params.put("before", "2020-04-30");
Generator<Transfer.Log> logs = Transfer.Log.query(params);

for (Transfer.Log log : logs){
    System.out.println(log);
}
```

### Get a transfer log

You can also get a specific log by its id.

```java
import com.starkbank.*;

Transfer.Log log = Transfer.Log.get("6532638269505536");

System.out.println(log);
```

### Pay a boleto

Paying boletos is also simple.

```java
import com.starkbank.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

List<BoletoPayment> payments = new ArrayList<>();
HashMap<String, Object> data = new HashMap<>();
data.put("line", "34191.09107 05447.947309 71444.640008 8 84660000011631");
data.put("taxId", "38.435.677/0001-25");
data.put("scheduled", "2020-04-11");
data.put("description", "Payment for killing white walkers");
data.put("tags", new String[]{"little girl", "no one"});
payments.add(new BoletoPayment(data));

payments = BoletoPayment.create(payments);

for (BoletoPayment payment : payments){
    System.out.println(payment);
}
```

### Query boleto payments

You can search for boleto payments using filters. 

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("after", "2020-04-01");
params.put("before", "2020-04-30");
Generator<BoletoPayment> payments = BoletoPayment.query(params);

for (BoletoPayment payment : payments){
    System.out.println(payment);
}
```

### Get boleto payment

To get a single boleto payment by its id, run:

```java
import com.starkbank.*;

BoletoPayment payment = BoletoPayment.get("6532638269505536");

System.out.println(payment);
```

### Get boleto payment PDF

After its creation, a boleto payment PDF may be retrieved by passing its id. 

```java
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import com.starkbank.*;

InputStream pdf = BoletoPayment.pdf("6311252829667328");

java.nio.file.Files.copy(
    pdf,
    new File("boleto-payment.pdf").toPath(),
    StandardCopyOption.REPLACE_EXISTING
);
```

Be careful not to accidentally enforce any encoding on the raw pdf content,
as it may yield abnormal results in the final file, such as missing images
and strange characters.

### Delete boleto payment

You can also cancel a boleto payment by its id.
Note that this is not possible if it has been processed already.

```java
import com.starkbank.*;

BoletoPayment payment = BoletoPayment.delete("5669456873259008");

System.out.println(payment);
```

### Query boleto payment logs

Searches are also possible with boleto payment logs:

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("paymentIds", "4785987200745472");
Generator<BoletoPayment.Log> logs = BoletoPayment.Log.query(params);

for (BoletoPayment.Log log : logs){
    System.out.println(log);
}
```


### Get boleto payment log

You can also get a boleto payment log by specifying its id.

```java
import com.starkbank.*;

BoletoPayment.Log log = BoletoPayment.Log.get("6532638269505536");

System.out.println(log);
```

### Pay utility bills

Its also simple to pay utility bills (such electricity and water bills) in the SDK.

```java
import com.starkbank.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

List<UtilityPayment> payments = new ArrayList<>();
HashMap<String, Object> data = new HashMap<>();
data.put("line", "83640000001 1 07540138007 0 61053026111 0 08067159411 9");
data.put("scheduled", "2020-04-11");
data.put("description", "Electricity for the Long Night");
data.put("tags", new String[]{"Energy", "Winterfell"});
payments.add(new UtilityPayment(data));

payments = UtilityPayment.create(payments);

for (UtilityPayment payment : payments){
    System.out.println(payment);
}
```

### Query utility payments

To search for utility payments using filters, run:

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("after", "2020-04-01");
params.put("before", "2020-04-30");
Generator<UtilityPayment> payments = UtilityPayment.query(params);

for (UtilityPayment payment : payments){
    System.out.println(payment);
}
```

### Get utility payment

You can get a specific bill by its id:

```java
import com.starkbank.*;

UtilityPayment payment = UtilityPayment.get("6532638269505536");

System.out.println(payment);
```

### Get utility payment PDF

After its creation, a utility payment PDF may also be retrieved by passing its id. 

```java
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import com.starkbank.*;

InputStream pdf = UtilityPayment.pdf("6565645839761408");

java.nio.file.Files.copy(
    pdf,
    new File("utility-payment.pdf").toPath(),
    StandardCopyOption.REPLACE_EXISTING
);
```

Be careful not to accidentally enforce any encoding on the raw pdf content,
as it may yield abnormal results in the final file, such as missing images
and strange characters.

### Delete utility payment

You can also cancel a utility payment by its id.
Note that this is not possible if it has been processed already.

```java
import com.starkbank.*;

UtilityPayment payment = UtilityPayment.delete("5669456873259008");

System.out.println(payment);
```

### Query utility bill payment logs

You can search for payment logs by specifying filters. Use this to understand the
bills life cycles.

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("paymentIds", "6683343345156096");
Generator<UtilityPayment.Log> logs = UtilityPayment.Log.query(params);

for (UtilityPayment.Log log : logs){
    System.out.println(log);
}
```

### Get utility bill payment log

If you want to get a specific payment log by its id, just run:

```java
import com.starkbank.*;

UtilityPayment.Log log = UtilityPayment.Log.get("6532638269505536");

System.out.println(log);
```

### Create transactions

To send money between Stark Bank accounts, you can create transactions:

```java
import com.starkbank.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

List<Transaction> transactions = new ArrayList<>();
HashMap<String, Object> data = new HashMap<>();
data.put("amount", 10000);
data.put("receiverId", "5651751147405412");
data.put("description", "A Lannister always pays his debts");
data.put("externalId", "my_unique_id");
data.put("tags", new String[]{"lannister", "debts"});
transactions.add(new Transaction(data));

transactions = Transaction.create(transactions);

for (Transaction transaction : transactions){
    System.out.println(transaction);
}
```

### Query transactions

To understand your balance changes (bank statement), you can query
transactions. Note that our system creates transactions for you when
you receive boleto payments, pay a bill or make transfers, for example.

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("after", "2020-04-01");
params.put("before", "2020-04-30");
Generator<Transaction> transactions = Transaction.query(params);

for (Transaction transaction : transactions){
    System.out.println(transaction);
}
```

### Get transaction

You can get a specific transaction by its id:

```java
import com.starkbank.*;

Transaction transaction = Transaction.get("5155966664310784");

System.out.println(transaction);
```

### Create a webhook subscription

To create a webhook subscription and be notified whenever an event occurs, run:

```java
import com.starkbank.*;
import java.util.HashMap;

HashMap<String, Object> data = new HashMap<>();
data.put("url", "https://winterfell.westeros.gov/events-from-stark-bank");
data.put("subscriptions", new String[]{"boleto", "boleto-payment", "transfer", "utility-payment"});
Webhook webhook = Webhook.create(data);

System.out.println(webhook);
```

### Query webhooks

To search for registered webhooks, run:

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;

Generator<Webhook> webhooks = Webhook.query();

for (Webhook webhook : webhooks){
    System.out.println(webhook);
}
```

### Get webhook

You can get a specific webhook by its id.

```java
import com.starkbank.*;

Webhook webhook = Webhook.get("5730174175805440");

System.out.println(webhook);
```

### Delete webhook

You can also delete a specific webhook by its id.

```java
import com.starkbank.*;

Webhook webhook = Webhook.delete("6699417864241152");

System.out.println(webhook);
```

### Process webhook events

Its easy to process events that arrived in your webhook. Remember to pass the
signature header so the SDK can make sure its really StarkBank that sent you
the event.

```java
import com.starkbank.*;

Request request = Listener.listen(); // this is the method you made to get the events posted to your webhook

String content = request.content.toString();
String signature = request.headers.get("Digital-Signature");

Event event = Event.parse(content, signature);
switch (event.subscription) {
    case "transfer": {
        Transfer.Log log = ((Event.TransferEvent) event).log;
        System.out.println(log.transfer);
        break;
    }
    case "boleto": {
        Boleto.Log log = ((Event.BoletoEvent) event).log;
        System.out.println(log.boleto);
        break;
    }
    case "boleto-payment": {
        BoletoPayment.Log log = ((Event.BoletoPaymentEvent) event).log;
        System.out.println(log.payment);
        break;
    }
    case "utility-payment": {
        UtilityPayment.Log log = ((Event.UtilityPaymentEvent) event).log;
        System.out.println(log.payment);
        break;
    }
}
```

### Query webhook events

To search for webhooks events, run:

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("isDelivered", false);
params.put("after", "2020-04-01");
params.put("before", "2020-04-30");
Generator<Event> events = Event.query(params);

for (Event event : events){
    System.out.println(event);
}
```

### Get webhook event

You can get a specific webhook event by its id.

```java
import com.starkbank.*;

Event event = Event.get("5730174175805440");

System.out.println(event);
```

### Delete webhook event

You can also delete a specific webhook event by its id.

```java
import com.starkbank.*;

Event event = Event.delete("6312789471657984");

System.out.println(event);
```

### Set webhook events as delivered

This can be used in case you've lost events.
With this function, you can manually set events retrieved from the API as
"delivered" to help future event queries with `isDelivered=false`.

```java
import com.starkbank.*;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("isDelivered", true);
Event event = Event.update("5824181711142912", params);

System.out.println(event);
```

## Handling errors

The SDK may raise one of four types of errors: __InputErrors__, __InternalServerError__, __UnknownException__, __InvalidSignatureException__

__InputErrors__ will be raised whenever the API detects an error in your request (status code 400).
If you catch such an error, you can get its elements to verify each of the
individual errors that were detected in your request by the API.

For example:

```java
import com.starkbank.*;
import com.starkbank.error.InputErrors;
import com.starkbank.error.ErrorElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

List<Transaction> transactions = new ArrayList<>();
HashMap<String, Object> data = new HashMap<>();
data.put("amount", -200);
data.put("receiverId", "5651751147405412");
data.put("description", ".");
data.put("externalId", "repeated_id");
data.put("tags", new String[]{"Error", "Example"});
transactions.add(new Transaction(data));

try {
    Transaction.create(transactions);
} catch (InputErrors e) {
    for (ErrorElement error : e.errors){
        System.out.println(error.code);
        System.out.println(error.message);
    }
}
```

__InternalServerError__ will be raised if the API runs into an internal error.
If you ever stumble upon this one, rest assured that the development team
is already rushing in to fix the mistake and get you back up to speed.

__UnknownException__ will be raised if a request encounters an error that is
neither __InputErrors__ nor an __InternalServerError__, such as connectivity problems.

__InvalidSignatureException__ will be raised specifically by starkbank.event.parse()
when the provided content and signature do not check out with the Stark Bank public
key.
