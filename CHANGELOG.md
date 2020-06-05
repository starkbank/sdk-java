# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/)
and this project adheres to the following versioning pattern:

Given a version number MAJOR.MINOR.PATCH, increment:

- MAJOR version when the **API** version is incremented. This may include backwards incompatible changes;
- MINOR version when **breaking changes** are introduced OR **new functionalities** are added in a backwards compatible manner;
- PATCH version when backwards compatible bug **fixes** are implemented.


## [Unreleased]
### Added
- Transfer query taxId parameter
### Change
- Test user credentials to environment variable instead of hard-code

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
