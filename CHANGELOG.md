<img src="https://www.pay.nl/uploads/1/brands/main_logo.png" width="100px"/>

# PayNL POS.SDK changelog

## Android softpos

#### v0.0.92 - 08-09-2025
- chore: improve logging

#### v0.0.91 - 08-09-2025
- fix: prevent MainThreadException during sendLogs

#### v0.0.88 - 19-08-2025
- fix: hotfix for refunds

#### v0.0.87 - 18-08-2025
- feat: cancelPayment checks whether it is possible to cancel the payment. It will throw an error if it cannot

#### v0.0.83 - 12-08-2025
- fix: retry init kernel when failure detected

#### v0.0.82 - 12-08-2025

- fix: prevent app crash when scanning MIFARE before starting payment

#### v0.0.81 - 11-08-2025

- fix: dual screen pinprompt fix

#### v0.0.80 - 11-08-2025

- fix: better error handling for sounds

#### v0.0.78 - 07-08-2025

- fix: better handling for NFC reader

#### v0.0.76 - 07-08-2025

- feat: add support for printing tickets using Sunmi printers

#### v0.0.75 - 06-08-2025

- fix: prevent app crash when scanning mifare before/after payment

#### v0.0.72 - 06-08-2025

- feat: added support for Mifare

#### v0.0.70 - 04-08-2025

- feat: allow context to be updated

#### v0.0.66 - 29-07-2025

- fix: rework builder patterns to fix proguard issue

#### v0.0.63 - 28-07-2025

- fix: correctly handle pin prompt cancel

## Android Sunmi hardpos

#### v0.0.23 - 08-09-2025
- fix: prevent MainThreadException during sendLogs

#### v0.0.20 - 18-08-2025
- chore: improve battery consumption
- feat: cancelPayment checks whether it is possible to cancel the payment. It will throw an error if it cannot

#### v0.0.18 - 11-08-2025

- fix: better error handling for sounds

#### v0.0.17 - 07-08-2025

- feat: add support for printing tickets

#### v0.0.15 - 06-08-2025

- feat: added support for Mifare

#### v0.0.11 - 04-08-2025

- feat: allow context to be updated

#### v0.0.8 - 29-07-2025

- fix: rework builder patterns to fix proguard issue

## Android PAX hardpos

#### v0.0.12 - 08-09-2025
- fix: prevent MainThreadException during sendLogs

#### v0.0.10 - 18-08-2025
- feat: cancelPayment checks whether it is possible to cancel the payment. It will throw an error if it cannot

#### v0.0.8 - 11-08-2025

- fix: better error handling for sounds

#### v0.0.7 - 07-08-2025

- feat: add support for printing tickets

#### v0.0.5 - 06-08-2025

- feat: added support for Mifare

#### v0.0.4 - 04-08-2025

- feat: allow context to be updated

#### v0.0.2 - 29-07-2025

- fix: rework builder patterns to fix proguard issue

## iOS - Tap to Pay on iPhone

### v0.0.24 - 20-08-2025
- fix: improve error reporting durign startPayment

## React Native

#### v0.0.54 - 08-09-2025
- chore: update dependency to 0.0.91

#### v0.0.51 - 18-08-2025
- feat: cancelPayment checks whether it is possible to cancel the payment. It will throw an error if it cannot

#### v0.0.49 - 12-08-2025

- chore: update PAY.POS dependencies

#### v0.0.48 - 07-08-2025

- feat: add support for printing tickets

#### v0.0.45 - 04-08-2025

- feat: added support for Mifare

#### v0.0.42 - 04-08-2025

- fix: hotfix to prevent app crash after app back in foreground

#### v0.0.41 - 29-07-2025

- fix: rework builder patterns to fix proguard issue

#### v0.0.38 - 28-07-2025

- fix: correctly cast response models from offline processing
