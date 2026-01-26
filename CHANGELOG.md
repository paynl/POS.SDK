<img src="https://www.pay.nl/uploads/1/brands/main_logo.png" width="100px"/>

# PayNL POS.SDK changelog

## Android softpos

#### v0.0.97 - 29-11-2025
- fix: Transaction processing timeout increased from 10s to 30s

#### v0.0.96 - 29-11-2025
- BREAKING: Transaction processing timeout decreased from 60s to 10s

#### v0.0.95 - 29-11-2025
- chore: improved logging

#### v0.0.94 - 13-11-2025
- chore: MULTI core is default now

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

#### v0.0.29 - 29-11-2025
- fix: Transaction processing timeout increased from 10s to 30s

#### v0.0.28 - 19-01-2026
- fix: disable reader buzzer

#### v0.0.27 - 19-01-2026
- fix: relaunch trx could not be cancelled

#### v0.0.26 - 29-11-2025
- BREAKING: Transaction processing timeout decreased from 60s to 10s

#### v0.0.25 - 13-11-2025
- chore: MULTI core is default now

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

#### v0.0.19 - 29-11-2025
- fix: Transaction processing timeout increased from 10s to 30s

#### v0.0.17 - 19-01-2026
- fix: Add missing PIN_WAITING event
- fix: PIN cancel did not dismiss the overlay

#### v0.0.15 - 29-11-2025
- BREAKING: Transaction processing timeout decreased from 60s to 10s

#### v0.0.13 - 13-11-2025
- chore: MULTI core is default now

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

### v0.0.32 - 09-01-2026
- fix: show correct tradename during service injection

### v0.0.28 - 22-10-2025
- feat: add refund

### v0.0.24 - 20-08-2025
- fix: improve error reporting during startPayment

## React Native


#### v0.0.68 - 22-10-2025
- fix: updated getOfflineQueue to async function to prevent blocking JS thread

#### v0.0.57 - 22-10-2025
- feat: add missing refund

#### v0.0.55 - 03-10-2025
- feat: add missing setConfiguration function

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
