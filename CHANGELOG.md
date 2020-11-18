# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/)
and this project adheres to the following versioning pattern:

Given a version number MAJOR.MINOR.PATCH, increment:

- MAJOR version when the **API** version is incremented. This may include backwards incompatible changes;
- MINOR version when **breaking changes** are introduced OR **new functionalities** are added in a backwards compatible manner;
- PATCH version when backwards compatible bug **fixes** are implemented.


## [Unreleased]
## [2.2.0] - 2020-11-18
### Added
- Invoice resource to load your account with dynamic QR Codes
- DictKey resource to get DICT (PIX) key parameters
- Deposit resource to receive transfers passively
- PIX support in Transfer resource
- BrcodePayment support to pay static and dynamic PIX QR Codes

## [2.1.0] - 2020-10-28
### Added
- BoletoHolmes to investigate boleto status according to CIP

## [2.0.2] - 2020-10-22
### Fixed
- Restrictive Double properties in Boleto resource

## [2.0.1] - 2020-10-21
### Fixed
- Boleto constructor bug when no descriptions or discounts were informed

## [2.0.0] - 2020-10-21
### Added
- ids parameter to Transaction.query
- ids parameter to Transfer.query
- hiddenFields parameter to Boleto.pdf
- ourNumber attribute to Boleto
- PaymentRequest resource to pass payments through manual approval flow
- Boleto.Discount & Boleto.Description parsing on Boleto API responses

### Fixed
- Fixed params alterations on queries

## [0.6.0] - 2020-08-19
### Added
- Travis CI configuration
- Transfer.scheduled parameter to allow Transfer scheduling
- StarkBank.Transfer.delete to cancel scheduled Transfers
- Transaction query by tags

## [0.5.2] - 2020-07-22
### Fixed
- Non-Unix path on Key.create(path) 

## [0.5.1] - 2020-07-22
### Change
- Unix Epoch function replaced from java.time.Instant.now to java.lang.System.currentTimeMillis for better Android compatibility
- Switched HTTP Client from Apache to Retrofit for better Android compatibility
- Changed Error of HTTP status outside 200, 400 and 500 to UnknownError 

## [0.5.0] - 2020-06-05
### Added
- Transfer query taxId parameter
- Global error language setting
- Boleto PDF layout option
### Change
- Test user credentials to environment variable instead of hard-code
- StarkBank.User.defaultUser to StarkBank.Settings.user

## [0.4.0] - 2020-05-12
### Added
- "receiverName" & "receiverTaxId" properties to Boleto entities

## [0.3.0] - 2020-05-08
### Added
- Boleto.Discount & Boleto.Description objects
### Changed
- Package locator to com.starkbank:sdk
### Fixed
- Boleto descriptions amount type

## [0.2.3] - 2020-05-07
### Added
- Remote repository (Maven Central)
### Changed
- Local ECDSA library JAR to remote (Maven Central)
### Fixed
- User check Exception message

## [0.2.2] - 2020-05-07
### Fixed
- build.gradle blocking build

## [0.2.1] - 2020-05-05
### Fixed
- "balance" & "amount" properties overflow

## [0.2.0] - 2020-05-05
### Added
- Support for HashMaps in create methods
- Typo checks for new object parameters
- "balance" in Transaction entities
- Gradle setup
### Changed
- StarkBank.Error.Error to StarkBank.Error.ErrorElement
### Fixed
- Boleto & Utility payments test cases
- "finalize" method warning
- Javadoc notations
- Docstrings

## [0.1.4] - 2020-04-29
### Fixed
- String array as request query parameter

## [0.1.3] - 2020-04-28
### Fixed
- Event.get deserialization

## [0.1.2] - 2020-04-27
### Added
- Ignore nulls in URL encode
- General support for Map implementations instead of HashMaps
- "final" fields 
### Fixed
- Warnings

## [0.1.1] - 2020-04-27
### Fixed
- Exception on invalid query parameter

## [0.1.0] - 2020-04-22
### Added
- Full Stark Bank API v2 compatibility
