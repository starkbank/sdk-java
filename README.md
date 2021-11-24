# Stark Bank Java SDK

Welcome to the Stark Bank Java SDK! This tool is made for Java 
developers who want to easily integrate with our API.
This SDK version is compatible with the Stark Bank API v2.

If you have no idea what Stark Bank is, check out our [website](https://starkbank.com/) 
and discover a world where receiving or making payments 
is as easy as sending a text message to your client!

# Introduction

## Index

- [Introduction](#introduction)
    - [Supported Java Versions](#supported-java-versions)
    - [API documentation](#stark-bank-api-documentation)
    - [Versioning](#versioning)
- [Setup](#setup)
    - [Install our SDK](#1-install-our-sdk)
    - [Create your Private and Public Keys](#2-create-your-private-and-public-keys)
    - [Register your user credentials](#3-register-your-user-credentials)
    - [Setting up the user](#4-setting-up-the-user)
    - [Setting up the error language](#5-setting-up-the-error-language)
    - [Resource listing and manual pagination](#6-resource-listing-and-manual-pagination)
- [Testing in Sandbox](#testing-in-sandbox) 
- [Usage](#usage)
    - [Transactions](#create-transactions): Account statement entries
    - [Balance](#get-balance): Account balance
    - [Transfers](#create-transfers): Wire transfers (TED and manual Pix)
    - [DictKeys](#get-dict-key): Pix Key queries to use with Transfers
    - [Institutions](#query-bacen-institutions): Instutitions recognized by the Central Bank
    - [Invoices](#create-invoices): Reconciled receivables (dynamic PIX QR Codes)
    - [Deposits](#query-deposits): Other cash-ins (static PIX QR Codes, manual PIX, etc)
    - [Boletos](#create-boletos): Boleto receivables
    - [BoletoHolmes](#investigate-a-boleto): Boleto receivables investigator
    - [BrcodePayments](#pay-a-br-code): Pay Pix QR Codes
    - [BoletoPayments](#pay-a-boleto): Pay Boletos
    - [UtilityPayments](#create-utility-payments): Pay Utility bills (water, light, etc.)
    - [TaxPayments](#create-tax-payment): Pay taxes
    - [PaymentPreviews](#preview-payment-information-before-executing-the-payment): Preview all sorts of payments
    - [Webhooks](#create-a-webhook-subscription): Configure your webhook endpoints and subscriptions
    - [WebhookEvents](#process-webhook-events): Manage webhook events
    - [WebhookEventAttempts](#query-failed-webhook-event-delivery-attempts-information): Query failed webhook event deliveries
    - [Workspaces](#create-a-new-workspace): Manage your accounts
- [Handling errors](#handling-errors)
- [Help and Feedback](#help-and-feedback)

## Supported Java Versions

This library supports the following Java versions:

* Java 8+

If you have specific version demands for your projects, feel free to contact us.

## Stark Bank API documentation

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
dependencies {
  implementation 'com.starkbank:sdk:2.8.2'
}
```

1.2 If you are using Maven, add this dependency to your pom.xml:

```xml
<dependency>
  <groupId>com.starkbank</groupId>
  <artifactId>sdk</artifactId>
  <version>2.8.2</version>
</dependency>
```

**Note**: If you are using Android, don't forget to [add the compileOptions to your build.gradle](https://developer.android.com/studio/write/java8-support):

```gradle
compileOptions {
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
}
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

### 3. Register your user credentials

You can interact directly with our API using two types of users: Projects and Organizations.

- **Projects** are workspace-specific users, that is, they are bound to the workspaces they are created in.
One workspace can have multiple Projects.
- **Organizations** are general users that control your entire organization.
They can control all your Workspaces and even create new ones. The Organization is bound to your company's tax ID only.
Since this user is unique in your entire organization, only one credential can be linked to it.

3.1. To create a Project in Sandbox:

3.1.1. Log into [Starkbank Sandbox](https://web.sandbox.starkbank.com)

3.1.2. Go to Menu > Integrations

3.1.3. Click on the "New Project" button

3.1.4. Create a Project: Give it a name and upload the public key you created in section 2

3.1.5. After creating the Project, get its Project ID

3.1.6. Use the Project ID and private key to create the object below:

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

3.2. To create Organization credentials in Sandbox:

3.2.1. Log into [Starkbank Sandbox](https://web.sandbox.starkbank.com)

3.2.2. Go to Menu > Integrations

3.2.3. Click on the "Organization public key" button

3.2.4. Upload the public key you created in section 2 (only a legal representative of the organization can upload the public key)

3.2.5. Click on your profile picture and then on the "Organization" menu to get the Organization ID

3.2.6. Use the Organization ID and private key to create the object below:

```java
import com.starkbank.*;

// Get your private key from an environment variable or an encrypted database.
// This is only an example of a private key content. You should use your own key.
String privateKeyContent = """
-----BEGIN EC PARAMETERS-----
BgUrgQQACg==
-----END EC PARAMETERS-----
-----BEGIN EC PRIVATE KEY-----
MHQCAQEEIMCwW74H6egQkTiz87WDvLNm7fK/cA+ctA2vg/bbHx3woAcGBSuBBAAK
oUQDQgAE0iaeEHEgr3oTbCfh8U2L+r7zoaeOX964xaAnND5jATGpD/tHec6Oe9U1
IF16ZoTVt1FzZ8WkYQ3XomRD4HS13A==
-----END EC PRIVATE KEY-----
""";

Organization organization = new Organization(
    "5656565656565656",
    "sandbox",
    privateKeyContent,
    null, // You only need to set the workspaceId when you are operating a specific workspaceId
);

// To dynamically use your organization credentials in a specific workspaceId,
// you can use the Organization.replace() method:
Balance balance = Balance.get(Organization.replace(organization, "4848484848484848"));
System.out.println(balance);
```

NOTE 1: Never hard-code your private key. Get it from an environment variable or an encrypted database.

NOTE 2: We support `'sandbox'` and `'production'` as environments.

NOTE 3: The credentials you registered in `sandbox` do not exist in `production` and vice versa.


### 4. Setting up the user

There are three kinds of users that can access our API: **Organization**, **Project** and **Member**.

- `Project` and `Organization` are designed for integrations and are the ones meant for our SDKs.
- `Member` is the one you use when you log into our webpage with your e-mail.

There are two ways to inform the user to the SDK:
 
4.1 Passing the user as argument in all functions:

```java
import com.starkbank.*;

Balance balance = Balance.get(project); # or organization
```

4.2 Set it as a default user in the SDK:

```java
import com.starkbank.*;

Settings.user = project; # or organization

Balance balance = Balance.get();
```

Just select the way of passing the user that is more convenient to you.
On all following examples we will assume a default user has been set.

### 5. Setting up the error language

The error language can also be set in the same way as the default user:

```java
import com.starkbank.*;

Settings.language = "en-US";
```

Language options are "en-US" for english and "pt-BR" for brazilian portuguese. English is default.

### 6. Resource listing and manual pagination

Almost all SDK resources provide a `query` and a `page` function.

- The `query` function provides a straight forward way to efficiently iterate through all results that match the filters you inform,
seamlessly retrieving the next batch of elements from the API only when you reach the end of the current batch.
If you are not worried about data volume or processing time, this is the way to go.

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("limit", 200);
Generator<Transaction> transactions = Transaction.query(params);

for (Transaction transaction : transactions){
    System.out.println(transaction);
}
```

- The `page` function gives you full control over the API pagination. With each function call, you receive up to
100 results and the cursor to retrieve the next batch of elements. This allows you to stop your queries and
pick up from where you left off whenever it is convenient. When there are no more elements to be retrieved, the returned cursor will be `None`.


```java
import com.starkbank.*;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("limit", 50);
params.put("cursor", null);

while (true) {
    Transaction.Page page = Transaction.page(params);
    for (Transaction transaction: page.transactions) {
        System.out.println(transaction);
    }
    if (page.cursor == null) {
        break;
    }
    params.put("cursor", page.cursor);
}
```

To simplify the following SDK examples, we will only use the `query` function, but feel free to use `page` instead.

## Testing in Sandbox

Your initial balance is zero. For many operations in Stark Bank, you'll need funds
in your account, which can be added to your balance by creating an Invoice or a Boleto. 

In the Sandbox environment, most of the created Invoices and Boletos will be automatically paid,
so there's nothing else you need to do to add funds to your account. Just create
a few Invoices and wait around a bit.

In Production, you (or one of your clients) will need to actually pay this Invoice or Boleto
for the value to be credited to your account.


# Usage

Here are a few examples on how to use the SDK. If you have any doubts, use the built-in
`help()` function to get more info on the desired functionality
(for example: `help(starkbank.boleto.create)`)

## Create transactions

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

**Note**: Instead of using UtilityPayment objects, you can also pass each payment element in HashMap format

## Query transactions

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

## Get a transaction

You can get a specific transaction by its id:

```java
import com.starkbank.*;

Transaction transaction = Transaction.get("5155966664310784");

System.out.println(transaction);
```

## Get balance

To know how much money you have in your workspace, run:

```java
import com.starkbank.*;

Balance balance = Balance.get();

System.out.println(balance);
```

## Create transfers

You can also create transfers in the SDK (TED/Pix).

```java
import com.starkbank.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

List<Transfer> transfers = new ArrayList<>();
HashMap<String, Object> data1 = new HashMap<>();
data1.put("amount", 100000000);
data1.put("bankCode", "341"); # TED
data1.put("branchCode", "2201");
data1.put("accountNumber", "76543-8");
data1.put("taxId", "594.739.480-42");
data1.put("name", "Daenerys Targaryen Stormborn");
data1.put("scheduled", "2020-12-11");
data1.put("description", "Transaction to dear provider");
data1.put("tags", new String[]{"daenerys", "invoice/1234"});
transfers.add(new Transfer(data1));

HashMap<String, Object> data2 = new HashMap<>();
data2.put("amount", 100000000);
data2.put("bankCode", "20018183"); # Pix
data2.put("branchCode", "2201");
data2.put("accountNumber", "76543-8");
data2.put("accountType", "checking");
data2.put("externalId", "my-internal-id-12345");
data2.put("taxId", "594.739.480-42");
data2.put("name", "Daenerys Targaryen Stormborn");
data2.put("scheduled", "2020-11-11T15:01:39.903667+00:00");
data2.put("tags", new String[]{"daenerys", "invoice/1234"});
transfers.add(new Transfer(data2));

transfers = Transfer.create(transfers);

for (Transfer transfer : transfers){
    System.out.println(transfer);
}
```

**Note**: Instead of using Transfer objects, you can also pass each transfer element in HashMap format

## Query transfers

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

## Get a transfer

To get a single transfer by its id, run:

```java
import com.starkbank.*;

Transfer transfer = Transfer.get("6532638269505536");

System.out.println(transfer);
```

## Cancel a scheduled transfer

To cancel a scheduled transfer by its id, run:

```java
import com.starkbank.*;

Transfer transfer = Transfer.delete("6532638269505536");

System.out.println(transfer);
```

## Get a transfer PDF

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

## Query transfer logs

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

## Get a transfer log

You can also get a specific log by its id.

```java
import com.starkbank.*;

Transfer.Log log = Transfer.Log.get("6532638269505536");

System.out.println(log);
```

## Get DICT key

You can get Pix key's parameters by its id.

```java
import com.starkbank.*;

DictKey dictKey = DictKey.get("tony@starkbank.com");

System.out.println(dictKey);
```

## Query your DICT keys

To take a look at the Pix keys linked to your workspace, just run the following:

```java
import com.starkbank.*;
import java.util.HashMap;
import com.starkbank.utils.Generator;

HashMap<String, Object> params = new HashMap<>();
params.put("status", "registered");
params.put("limit", 1);
params.put("type", "evp");

Generator<DictKey> dictKeys = DictKey.query(params);
for (DictKey dictKey : dictKeys) {
    System.out.println(dictKey);
}
```

## Query Bacen institutions

You can query institutions registered by the Brazilian Central Bank for Pix and TED transactions.

 ```java
import com.starkbank.*;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("search", "stark");

Generator<Institution> institutions = Institution.query(params);

for (Institution institution : institutions) {
    System.out.println(institution);
}
 ```

## Create invoices

You can create dynamic QR Code invoices to charge customers or to receive money from accounts you have in other banks.

Since the banking system only understands value modifiers (discounts, fines and interest) when dealing with **dates** (instead of **datetimes**), these values will only show up in the end user banking interface if you use **dates** in the "due" and "discounts" fields.

If you use **datetimes** instead, our system will apply the value modifiers in the same manner, but the end user will only see the final value to be paid on his interface.

Also, other banks will most likely only allow payment scheduling on invoices defined with **dates** instead of **datetimes**.

```java
import com.starkbank.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

List<Invoice> invoices = new ArrayList<>();

List<HashMap<String, Object>> descriptions = new ArrayList<>();
HashMap<String, Object> description = new HashMap<String, Object>(){{
    put("key", "Some supplies");
    put("value", "100000");
}};
descriptions.add(description);

invoices.add(new Invoice(new HashMap<String, Object>(){{
    put("tags", new String[]{"immediate"});
    put("amount", 23571);
    put("due", "2021-11-28T17:59:26.249976+00:00");
    put("taxId", "012.345.678-90");
    put("name", "Buzz Aldrin");
    put("expiration", 123456789);
    put("fine", 5);
    put("interest", 2.5);
    put("descriptions", descriptions);
}}));

List<HashMap<String, Object>> discounts = new ArrayList<>();
HashMap<String, Object> discount = new HashMap<String, Object>(){{
    put("due", "2021-11-27");
    put("percentage", 2.5);
}};
discounts.add(discount);

invoices.add(new Invoice(new HashMap<String, Object>(){{
    put("tags", new String[]{"scheduled"});
    put("amount", 23571);
    put("due", "2021-11-28");
    put("taxId", "012.345.678-90");
    put("name", "Buzz Aldrin");
    put("expiration", 123456789);
    put("fine", 5);
    put("interest", 2.5);
    put("discounts", discounts);
}}));

invoices = Invoice.create(invoices);

for (Invoice invoice : invoices) {
    System.out.println(invoice);
}
```

**Note**: Instead of using Invoice objects, you can also pass each invoice element in map format

## Get an invoice

After its creation, information on an invoice may be retrieved by its id. 
Its status indicates whether it's been paid.

```java
import com.starkbank.*;

Invoice invoice = Invoice.get("5155165527080960");

System.out.println(invoice);
```

## Get an invoice QR Code

After its creation, an invoice QR Code png file may be retrieved by its id. 

```java
import com.starkbank.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

HashMap<String, Object> options = new HashMap<>();
InputStream png = Invoice.qrcode("5155165527080960");

java.nio.file.Files.copy(
    png,
    new File("qrcode.png").toPath(),
    StandardCopyOption.REPLACE_EXISTING
);
```

## Get an invoice PDF

After its creation, an invoice PDF may be retrieved by its id. 

```java
import com.starkbank.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

HashMap<String, Object> options = new HashMap<>();
options.put("layout", "booklet");
InputStream pdf = Invoice.pdf("5155165527080960", options);

java.nio.file.Files.copy(
    pdf,
    new File("invoice.pdf").toPath(),
    StandardCopyOption.REPLACE_EXISTING
);
```

Be careful not to accidentally enforce any encoding on the raw pdf content,
as it may yield abnormal results in the final file, such as missing images
and strange characters.

## Cancel an invoice

You can also cancel an invoice by its id.
Note that this is not possible if it has been paid already.

```java
import com.starkbank.*;
import java.util.HashMap;

HashMap<String, Object> patchData = new HashMap<>();
patchData.put("status", "canceled");
Invoice invoice = Invoice.update("5155165527080960", patchData);

System.out.println(invoice);
```

## Update an invoice

You can update an invoice's amount, due date and expiration by its id.
Note that this is not possible if it has been paid already.

```java
import com.starkbank.*;
import java.util.HashMap;

HashMap<String, Object> patchData = new HashMap<>();
patchData.put("status", "canceled");
patchData.put("amount", 999999);
patchData.put("due", "2020-11-02T23:06:42.924000+00:00");
patchData.put("expiration", 123456789);
Invoice invoice = Invoice.update("5155165527080960", patchData);

System.out.println(invoice);
```

## Query invoices

You can get a list of created invoices given some filters.

```java
import com.starkbank.*;
import java.util.HashMap;
import com.starkbank.utils.Generator;

HashMap<String, Object> params = new HashMap<>();
params.put("status", "created");
params.put("limit", 1);
params.put("after", "2019-04-01");
params.put("before", "2030-04-30");

Generator<Invoice> invoices = Invoice.query(params);
for (Invoice invoice : invoices) {
    System.out.println(invoice);
}
```

## Query invoice logs

Logs are pretty important to understand the life cycle of an invoice.

```java
import com.starkbank.*;
import java.util.HashMap;
import com.starkbank.utils.Generator;

HashMap<String, Object> params = new HashMap<>();
params.put("limit", 3);
params.put("after", "2019-04-01");
params.put("before", "2030-04-30");
Generator<Invoice.Log> logs = Invoice.Log.query(params);

for (Invoice.Log log : logs) {
    System.out.println(log);
}
```

## Get an invoice log

You can get a single log by its id.

```java
import com.starkbank.*;

Invoice.Log log = Invoice.Log.get("5155165527080960");

System.out.println(log);
```

## Get a reversed invoice log PDF

Whenever an Invoice is successfully reversed, a reversed log will be created.
To retrieve a specific reversal receipt, you can request the corresponding log PDF:

 ```java
import com.starkbank.*;

InputStream pdf = Invoice.Log.pdf("5155165527080960");
java.nio.file.Files.copy(
    pdf,
    new File("invoice.pdf").toPath(),
    StandardCopyOption.REPLACE_EXISTING
);
 ```

Be careful not to accidentally enforce any encoding on the raw pdf content,
as it may yield abnormal results in the final file, such as missing images
and strange characters.

## Get an invoice payment information

Once an invoice has been paid, you can get the payment information using the Invoice.Payment sub-resource:

```java
import com.starkbank.*;

Invoice.Payment payment = Invoice.payment("5155165527080960");

System.out.println(payment);
```

## Query deposits

You can get a list of created deposits given some filters.

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("after", "2020-04-01");
params.put("before", "2020-04-30");
Generator<Deposit> deposits = Deposit.query(params);

for (Deposit deposit : deposits){
    System.out.println(deposit);
}
```

## Get a deposit

After its creation, information on a deposit may be retrieved by its id. 

```java
import com.starkbank.*;

Deposit deposit = Deposit.get("5730174175805440");

System.out.println(deposit);
```

## Query deposit logs

Logs are pretty important to understand the life cycle of a deposit.

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("after", "2020-04-01");
params.put("before", "2020-04-30");
Generator<Deposit.Log> logs = Deposit.Log.query(params);

for (Deposit.Log log : logs){
    System.out.println(log);
}
```

## Get a deposit log

You can get a single log by its id.

```java
import com.starkbank.*;

Deposit.Log log = Deposit.Log.get("6532638269505536");

System.out.println(log);
```


## Create boletos

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
data.put("due", "2021-05-20");
data.put("fine", 2.5);
data.put("interest", 1.3);
data.put("overdueLimit", 5);
data.put("tags", new String[]{"War supply", "Invoice #1234"});

List<Boleto.Description> descriptions = new ArrayList<>();
descriptions.add(new Boleto.Description("taxes", 3000));
descriptions.add(new Boleto.Description("this will be an incredible payment"));
data.put("descriptions", descriptions);

List<Boleto.Discount> discounts = new ArrayList<>();
discounts.add(new Boleto.Discount("2020-05-17", 2.5));
discounts.add(new Boleto.Discount("2020-05-18", 2.0));
data.put("discounts", discounts);

boletos.add(new Boleto(data));

boletos = Boleto.create(boletos);

for (Boleto boleto : boletos){
    System.out.println(boleto);
}
```

**Note**: Instead of using Boleto objects, you can also pass each boleto element in HashMap format

## Query boletos

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

## Get a boleto

After its creation, information on a boleto may be retrieved by passing its id. 
Its status indicates whether it's been paid.

```java
import com.starkbank.*;

Boleto boleto = Boleto.get("5730174175805440");

System.out.println(boleto);
```

## Get a boleto PDF

After its creation, a boleto PDF may be retrieved by passing its id. 

```java
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import com.starkbank.*;

HashMap<String, Object> options = new HashMap<>();
options.put("layout", "booklet");

InputStream pdf = Boleto.pdf("5915632394567680", options);

java.nio.file.Files.copy(
    pdf,
    new File("boleto.pdf").toPath(),
    StandardCopyOption.REPLACE_EXISTING
);
```

Be careful not to accidentally enforce any encoding on the raw pdf content,
as it may yield abnormal results in the final file, such as missing images
and strange characters.

## Delete a boleto

You can also cancel a boleto by its id.
Note that this is not possible if it has been processed already.

```java
import com.starkbank.*;

Boleto boleto = Boleto.delete("5669456873259008");

System.out.println(boleto);
```

## Query boleto logs

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

## Get a boleto log

You can get a single log by its id.

```java
import com.starkbank.*;

Boleto.Log log = Boleto.Log.get("6532638269505536");

System.out.println(log);
```

## Investigate a boleto

You can discover if a StarkBank boleto has been recently paid before we receive the response on the next day.
This can be done by creating a BoletoHolmes object, which fetches the updated status of the corresponding
Boleto object according to CIP to check, for example, whether it is still payable or not. The investigation
happens asynchronously and the most common way to retrieve the results is to register a "boleto-holmes" webhook
subscription, although polling is also possible. 

```java
import com.starkbank;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

List<BoletoHolmes> holmes = new ArrayList<>();
HashMap<String, Object> dataHolmes = new HashMap<>(); 
dataHolmes.put("boletoId", "5976467733217280");
holmes.add(new BoletoHolmes(dataHolmes));

holmes = BoletoHolmes.create(holmes);

for (BoletoHolmes sherlock : holmes){
    System.out.println(sherlock);
}
```

**Note**: Instead of using BoletoHolmes objects, you can also pass each payment element in map format

## Get a boleto holmes

To get a single Holmes by its id, run:

```java
import com.starkbank.*;

sherlock = BoletoHolmes.get("6093880533450752")

System.out.println(sherlock)
```

## Query boleto holmes

You can search for boleto Holmes using filters. 

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("limit", 3);
params.put("after", "2019-04-01");
params.put("before", "2030-04-30");
Generator<BoletoHolmes> holmes = BoletoHolmes.query(params);

for (BoletoHolmes sherlock : holmes){
    System.out.println(sherlock);
}
```

## Query boleto holmes logs

Searches are also possible with boleto holmes logs:

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("limit", 3);
params.put("after", "2019-04-01");
params.put("before", "2030-04-30");
Generator<BoletoHolmes.Log> logs = BoletoHolmes.Log.query(params);

for (BoletoHolmes.Log log : logs){
    System.out.println(log);
}
```


## Get a boleto holmes log

You can also get a boleto holmes log by specifying its id.

```java
import com.starkbank.*;

log = BoletoHolmes.Log.get("5350990148534272")

System.out.println(log);
```

## Pay a BR Code

Paying a BR Code is also simple.

```java
import com.starkbank.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

List<BrcodePayment> payments = new ArrayList<>();
HashMap<String, Object> data = new HashMap<>();
data.put("brcode", "00020126580014br.gov.bcb.pix0136a629532e-7693-4846-852d-1bbff817b5a8520400005303986540510.005802BR5908T'Challa6009Sao Paulo62090505123456304B14A");
data.put("taxId", "012.345.678-90");
data.put("scheduled", "2020-03-13");
data.put("description", "This will be fast");
data.put("tags", new String[]{"pix", "qrcode"});
payments.add(new BrcodePayment(data));

payments = BrcodePayment.create(payments);

for (BrcodePayment payment : payments){
    System.out.println(payment);
}
```

**Note**: Instead of using BrcodePayment objects, you can also pass each payment element in map format

## Query BR Code payments

You can search for brcode payments using filters. 

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("after", "2020-04-01");
params.put("before", "2020-04-30");
Generator<BrcodePayment> payments = BrcodePayment.query(params);

for (BrcodePayment payment : payments){
    System.out.println(payment);
}
```

## Get a BR Code payment

To get a single BR Code payment by its id, run:

```java
import com.starkbank.*;

BrcodePayment payment = BrcodePayment.get("6532638269505536");

System.out.println(payment);
```

## Cancel a BR Code payment

You can cancel a BR Code payment by changing its status to "canceled".
Note that this is not possible if it has been processed already.

```java
import com.starkbank.*;
import java.util.HashMap;

HashMap<String, Object> patchData = new HashMap<>();
patchData.put("status", "canceled");
BrcodePayment payment = BrcodePayment.update("5155165527080960", patchData);

System.out.println(payment);
```

## Get a BR Code payment PDF

After its creation, a boleto payment PDF may be retrieved by its id. 

```java
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import com.starkbank.*;

InputStream pdf = BrcodePayment.pdf("6311252829667328");

java.nio.file.Files.copy(
    pdf,
    new File("brcode-payment.pdf").toPath(),
    StandardCopyOption.REPLACE_EXISTING
);
```

Be careful not to accidentally enforce any encoding on the raw pdf content,
as it may yield abnormal results in the final file, such as missing images
and strange characters.

## Query BR Code payment logs

Searches are also possible with BR Code payment logs:

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("paymentIds", "4785987200745472");
Generator<BrcodePayment.Log> logs = BrcodePayment.Log.query(params);

for (BrcodePayment.Log log : logs){
    System.out.println(log);
}
```

## Get a BR Code payment log

You can also get a BR Code payment log by specifying its id.

```java
import com.starkbank.*;

BrcodePayment.Log log = BrcodePayment.Log.get("6532638269505536");

System.out.println(log);
```

## Pay a boleto

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

**Note**: Instead of using BoletoPayment objects, you can also pass each payment element in HashMap format

## Query boleto payments

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

## Get a boleto payment

To get a single boleto payment by its id, run:

```java
import com.starkbank.*;

BoletoPayment payment = BoletoPayment.get("6532638269505536");

System.out.println(payment);
```

## Get a boleto payment PDF

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

## Delete a boleto payment

You can also cancel a boleto payment by its id.
Note that this is not possible if it has been processed already.

```java
import com.starkbank.*;

BoletoPayment payment = BoletoPayment.delete("5669456873259008");

System.out.println(payment);
```

## Query boleto payment logs

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


## Get a boleto payment log

You can also get a boleto payment log by specifying its id.

```java
import com.starkbank.*;

BoletoPayment.Log log = BoletoPayment.Log.get("6532638269505536");

System.out.println(log);
```

## Create utility payments

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

## Query utility payments

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

## Get a utility payment

You can get a specific bill by its id:

```java
import com.starkbank.*;

UtilityPayment payment = UtilityPayment.get("6532638269505536");

System.out.println(payment);
```

## Get a utility payment PDF

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

## Delete a utility payment

You can also cancel a utility payment by its id.
Note that this is not possible if it has been processed already.

```java
import com.starkbank.*;

UtilityPayment payment = UtilityPayment.delete("5669456873259008");

System.out.println(payment);
```

## Query utility payment logs

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

## Get a utility payment log

If you want to get a specific payment log by its id, just run:

```java
import com.starkbank.*;

UtilityPayment.Log log = UtilityPayment.Log.get("6532638269505536");

System.out.println(log);
```

## Create tax payment

It is also simple to pay taxes (such as ISS and DAS) using this SDK.

```java
import com.starkbank.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

List<TaxPayment> payments = new ArrayList<>();

HashMap<String, Object> data = new HashMap<>();
data.put("barCode", "83660000001084301380074119002551100010601813");
data.put("description", "fix the road");
data.put("tags", new String[]{"take", "my", "money"});

payments.add(new TaxPayment(data));

payments = TaxPayment.create(payments);

for (TaxPayment payment : payments) {
    System.out.println(payment);
}
```

**Note**: Instead of using TaxPayment objects, you can also pass each payment element in dictionary format

## Query tax payments

To search for tax payments using filters, run:

```java
import com.starkbank.*;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("limit", 3);
params.put("after", "2019-04-01");
params.put("before", "2030-04-30");
params.put("status", "success");

Generator<TaxPayment> payments = TaxPayment.query(params);

for (TaxPayment payment : payments) {
    System.out.println(payment);
}
```

## Get tax payment

You can get a specific tax payment by its id:

```java
import com.starkbank.*;

TaxPayment payment = TaxPayment.get("5155165527080960");
System.out.println(payment);
```

## Get tax payment PDF

After its creation, a tax payment PDF may also be retrieved by its id.

```java
import com.starkbank.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;


InputStream pdf = TaxPayment.pdf("5155165527080960");
java.nio.file.Files.copy(
    pdf,
    new File("tax-payment.pdf").toPath(),
    StandardCopyOption.REPLACE_EXISTING
);
```

Be careful not to accidentally enforce any encoding on the raw pdf content,
as it may yield abnormal results in the final file, such as missing images
and strange characters.

## Delete tax payment

You can also cancel a tax payment by its id.
Note that this is not possible if it has been processed already.

```java
import com.starkbank.*;

TaxPayment payment = TaxPayment.delete("5155165527080960");
System.out.println(payment);
```

## Query tax payment logs

You can search for payment logs by specifying filters. Use this to understand each payment life cycle.

```java
import com.starkbank.*;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("limit", 3);
params.put("after", "2019-04-01");
params.put("before", "2030-04-30");

Generator<TaxPayment.Log> logs = TaxPayment.Log.query(params);

for (TaxPayment.Log log : logs) {
    System.out.println(log);
}
```

## Get tax payment log

If you want to get a specific payment log by its id, just run:

```java
import com.starkbank.*;

TaxPayment.Log log = TaxPayment.Log.get("1902837198237992");
System.out.println(log);
```

**Note**: Some taxes can't be payed with bar codes. Since they have specific parameters, each one of them has its own
resource and routes, which are all analogous to the TaxPayment resource. The ones we currently support are:
- DarfPayment, for DARFs

## Preview payment information before executing the payment

You can preview multiple types of payment to confirm any information before actually paying.
If the "scheduled" parameter is not informed, today will be assumed as the intended payment date.
Right now, the "scheduled" parameter only has effect on BrcodePreviews.
This resource is able to preview the following types of payment:
"brcode-payment", "boleto-payment", "utility-payment" and "tax-payment"

```java
import com.starkbank.*;

List<PaymentPreview> previews = new ArrayList<>();
previews.add(new PaymentPreview(new HashMap<String, Object>(){{
    put("id", "00020126580014br.gov.bcb.pix0136a629532e-7693-4846-852d-1bbff817b5a8520400005303986540510.005802BR5908T'Challa6009Sao Paulo62090505123456304B14A");
    put("scheduled", "2021-02-10");
}}));
previews.add(new PaymentPreview(new HashMap<String, Object>(){{
    put("id", "34191.09008 61207.727308 71444.640008 5 81310001234321");
}}));

previews = (List<PaymentPreview>) PaymentPreview.create(previews);

for (PaymentPreview preview : previews) {
    System.out.println(preview);
}
```

**Note**: Instead of using PaymentPreview objects, you can also pass each request element in dictionary format


## Create payment requests to be approved by authorized people in a cost center 

You can also request payments that must pass through a specific cost center approval flow to be executed.
In certain structures, this allows double checks for cash-outs and gives time to load your account
with the required amount before the payments take place.
The approvals can be granted at our web banking and must be performed according to the rules
specified in the cost center.

**Note**: The value of the centerId parameter can be consulted by logging into our web banking and going
to the desired cost center page.

```java
import com.starkbank.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

HashMap<String, Object> paymentData = new HashMap<>();
paymentData.put("amount", 100000000);
paymentData.put("bankCode", "341");
paymentData.put("branchCode", "2201");
paymentData.put("accountNumber", "76543-8");
paymentData.put("taxId", "594.739.480-42");
paymentData.put("name", "Daenerys Targaryen Stormborn");
Transfer payment = new Transfer(paymentData);

List<PaymentRequest> requests = new ArrayList<>();
HashMap<String, Object> data = new HashMap<>();
data.put("centerId", "5967314465849344");
data.put("payment", payment);
data.put("due", "2020-04-11");
data.put("tags", new String[]{"daenerys", "invoice/1234"});
requests.add(new PaymentRequest(data));

requests = PaymentRequest.create(requests);

for (PaymentRequest request : requests){
    System.out.println(request);
}
```

**Note**: Instead of using PaymentRequest objects, you can also pass each request element in HashMap format

## Query payment requests

To search for payment requests, run:

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("centerId", "5967314465849344");
params.put("after", "2020-04-01");
params.put("limit", 10);
Generator<PaymentRequest> requests = PaymentRequest.query(params);

for (PaymentRequest request : requests){
    System.out.println(request);
}
```

## Create a webhook subscription

To create a webhook subscription and be notified whenever an event occurs, run:

```java
import com.starkbank.*;
import java.util.HashMap;

HashMap<String, Object> data = new HashMap<>();
data.put("url", "https://winterfell.westeros.gov/events-from-stark-bank");
data.put("subscriptions", new String[]{"boleto", "boleto-payment", "transfer", "utility-payment", "tax-payment", "boleto-holmes", "brcode-payment", "deposit", "invoice"});
Webhook webhook = Webhook.create(data);

System.out.println(webhook);
```

**Note**: Instead of using Transaction objects, you can also pass each transaction element in HashMap format

## Query webhooks

To search for registered webhooks, run:

```java
import com.starkbank.*;
import com.starkbank.utils.Generator;

Generator<Webhook> webhooks = Webhook.query();

for (Webhook webhook : webhooks){
    System.out.println(webhook);
}
```

## Get a webhook

You can get a specific webhook by its id.

```java
import com.starkbank.*;

Webhook webhook = Webhook.get("5730174175805440");

System.out.println(webhook);
```

## Delete a webhook

You can also delete a specific webhook by its id.

```java
import com.starkbank.*;

Webhook webhook = Webhook.delete("6699417864241152");

System.out.println(webhook);
```

## Process webhook events

It's easy to process events that arrived in your webhook. Remember to pass the
signature header so the SDK can make sure it's really StarkBank that sent you
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
    case "tax-payment": {
        TaxPayment.Log log = ((Event.TaxPaymentEvent) event).log;
        System.out.println(log.payment);
        break;
    }
    case "boleto-holmes": {
        BoletoHolmes.Log log = ((Event.BoletoHolmesEvent) event).log;
        System.out.println(log.payment);
        break;
    }
    case "brcode-payment": {
        BrcodePayment.Log log = ((Event.BrcodePaymentEvent) event).log;
        System.out.println(log.payment);
        break;
    }
    case "deposit": {
        Deposit.Log log = ((Event.DepositEvent) event).log;
        System.out.println(log.payment);
        break;
    }
    case "invoice": {
        Invoice.Log log = ((Event.InvoiceEvent) event).log;
        System.out.println(log.payment);
        break;
    }
}
```

## Query webhook events

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

## Get a webhook event

You can get a specific webhook event by its id.

```java
import com.starkbank.*;

Event event = Event.get("5730174175805440");

System.out.println(event);
```

## Delete a webhook event

You can also delete a specific webhook event by its id.

```java
import com.starkbank.*;

Event event = Event.delete("6312789471657984");

System.out.println(event);
```

## Set webhook events as delivered

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

## Query failed webhook event delivery attempts information

You can also get information on failed webhook event delivery attempts.

```java
import com.starkbank.*;
import java.util.HashMap;

HashMap<String, Object> params = new HashMap<>();
params.put("after", "2020-03-20");
Generator<Event.Attempt> attempts = Event.Attempt.query(params);

for (Event.Attempt attempt: attempts) {
    System.out.println(attempt);
}
```

## Get a failed webhook event delivery attempt information

To retrieve information on a single attempt, use the following function:

```java
import com.starkbank.*;

Event.Attempt attempt = Event.Attempt.get("1616161616161616");

System.out.println(attempt);
```

## Create a new Workspace

The Organization user allows you to create new Workspaces (bank accounts) under your organization.
Workspaces have independent balances, statements, operations and users.
The only link between your Workspaces is the Organization that controls them.

**Note**: This route will only work if the Organization user is used with `workspaceId=null`.

```java
import com.starkbank.*;
import java.util.HashMap;

Workspace workspace = Workspace.create(
    "iron-bank-workspace-1",
    "Iron Bank Workspace 1",
    organization,
);

System.out.println(workspace);
```

## List your Workspaces

This route lists Workspaces. If no parameter is passed, all the workspaces the user has access to will be listed, but
you can also find other Workspaces by searching for their usernames or IDs directly.

```java
import com.starkbank.*;

HashMap<String, Object> params = new HashMap<>();
params.put("limit", 30);
Generator<Workspace> workspaces = Workspace.query(params);

for (Workspace workspace : workspaces) {
    System.out.println(workspace);
}
```

## Get a Workspace

You can get a specific Workspace by its id.

```java
import com.starkbank.*;

Workspace workspace = Workspace.get("10827361982368179")

System.out.println(workspace)
```

## Update a Workspace

You can update a specific Workspace by its id.

```java
import com.starkbank.*;

Map<String, Object> patchData = new HashMap<>();
patchData.put("name", "Updated workspace test");
patchData.put("username", "new-username-test");
patchData.put("allowedTaxIds", new String[]{"35953668082", "88889288043"});

Workspace workspace = Workspace.update(workspace.id, patchData);
System.out.println(workspace);
```

# Handling errors

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

# Help and Feedback

If you have any questions about our SDK, just send us an email. 
We will respond you quickly, pinky promise. We are here to help you integrate with us ASAP. 
We also love feedback, so don't be shy about sharing your thoughts with us.

Email: developers@starkbank.com
