<img src="https://www.pay.nl/uploads/1/brands/main_logo.png" width="100px"/>

# PAY.POS SDK - React Native

### Requirements

- React native using Old and New Architecture (>= 0.75.0)
- Make sure you have contacted PayNL support for an `integrationId` (required for initSDK)
- Make sure you have the requirements for [iOS](./sdk-ios.md#requirements)
- Make sure you have the requirements for [Android softpos](./sdk-android-softpos.md#requirements)

### Getting started

#### React native

Install the package via:

```bash
npm i paynl-pos-sdk-react-native
```

#### iOS Setup

After installing the NPM package, make sure to update your Cocoapods via:

```bash
cd ios
pod install --repo-update
cd ..
```

#### Android setup

> [!NOTE]
> If you are not planning to support Android softpos, you can skip this

First make sure you have the PayNL gradle repositories in your global gradle properties:

- MacOS: `~/.gradle/gradle.properties`
- Windows: `USER_HOME/.gradle/gradle.properties`

```
# Pay.POS SDK registry credentials
PAYNL_REGISTRY_LOGIN=...
PAYNL_REGISTRY_TOKEN=...

# Github personal access token with read:packages scope
GITHUB_USERNAME=...
GITHUB_PERSONAL_TOKEN=...
```

Next, go to your `android/build.gradle` and add the following repositories:

```groovy
allprojects {
    repositories {
       google()
       mavenCentral()
       
       // ---- These two need to be added ----
       maven {
        name = "PayNLRegistry"
        url = uri("https://maven.pkg.github.com/paynl/pos-sdk")
        credentials {
          username = project.GITHUB_USERNAME
          password = project.GITHUB_PERSONAL_TOKEN
        }
      }
       maven {
        name = "PayNLMavenClientRegistry"
        url = uri("https://maven.pkg.github.com/theminesec/ms-registry-client")
        credentials {
          username = project.PAYNL_REGISTRY_LOGIN
          password = project.PAYNL_REGISTRY_TOKEN
        }
      }
      // ---- END ----
    }
}
```

Last, let's make sure you do not get build errors.
Go to your `android/app/build.gradle`-file and add the following:

```groovy
android {
  ...
  
    packaging {
        resources {
            excludes += "/META-INF/DEPENDENCIES"
            excludes += "/META-INF/LICENSE"
            excludes += "/META-INF/LICENSE.txt"
            excludes += "/META-INF/license.txt"
            excludes += "/META-INF/NOTICE"
            excludes += "/META-INF/NOTICE.txt"
            excludes += "/META-INF/notice.txt"
            excludes += "/META-INF/ASL2.0"
            excludes += "/META-INF/*.kotlin_module"
        }
    }
}
```

##### Expo note

If you get build errors while using expo, please update your `android/app/build.gradle`-file to the following:

```groovy
android {
  ...
  
  // Prevent BouncyCastle conflicts
  configurations {
        all*.exclude module: 'bcprov-jdk15to18'
        all*.exclude module: 'bcutil-jdk18on'
        all*.exclude module: 'bcprov-jdk15on'
        all*.exclude module: 'bcutil-jdk15on'
    }

    packaging {
        resources {
            excludes += "/META-INF/DEPENDENCIES"
            excludes += "/META-INF/LICENSE"
            excludes += "/META-INF/LICENSE.txt"
            excludes += "/META-INF/license.txt"
            excludes += "/META-INF/NOTICE"
            excludes += "/META-INF/NOTICE.txt"
            excludes += "/META-INF/notice.txt"
            excludes += "/META-INF/ASL2.0"
            excludes += "/META-INF/*.kotlin_module"
        }
    }
}
```

##### Advanced

It is also possible to pin the SDK version used by the React Native package.
Go to your `android/build.gradle` and add the default configuration:

```groovy
buildscript {
    ext {
      buildToolsVersion = "35.0.0"
      minSdkVersion = 26
      compileSdkVersion = 36
      targetSdkVersion = 36
      ndkVersion = "27.1.12297006"
      kotlinVersion = "2.0.21"
      androidXBrowser = "1.8.0"

      paynlVersion = "<LATEST_VERSION_HERE>" // <-- The SDK version being used. Check the Paynl docs to see the latest version
    }
}
```

### SDK flow

```mermaid
flowchart LR;
  subgraph Starting point
  A[initSDK]
  end

  subgraph Transaction flow
  A --> B{readyForPayments}
  B --> F[startPayment]
  F --> H{Payment result}
  H --> |PAID: Show Ticket & payerMessage to customer| N
  N[sendTicket] --> F
  H --> |FAILED: Show payerMessage to customer| F
  H --> |CANCELLED: Show paymentCancelled to customer| F
  end

  subgraph Get terminal information
  B --> I[getTerminalInfo]
  B --> J[getAllowedCurrencies]
  end

  subgraph Auth flow
  A --> M{needsLogin}
  B --> K[logout]
  K --> L{Terminal:delete api}
  L --> A
  M --> C[getActivationCode]
  C --> D{Terminal:Create API}
  D --> E[loginViaCode]
  E --> A
  M --> G[loginViaCredentials]
  G --> A

  end

  subgraph Offline processing
  H -->|OFFLINE: Add to queue| O{Offline queue}
  B --> P[getOfflineQueue]
  P --> O
  B --> Q[triggerFullOfflineProcessing]
  Q -->|Process all items from queue| O
  B --> R[triggerSingleOfflineProcessing]
  R -->|Fetch single item with ID for processing| O
  B --> S[clearOfflineItem]
  S -->|Remove single item from queue| O
  end
```

### API Spec

#### Init sdk

This function will initialize the SDK. It will return `PayNlInitResult` enum type.

| **Name**                              | **Type**             | **Description**                                                                                                                                                                                                                                                                                              |
|---------------------------------------|----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| integrationId                         | String               | The UUID received from PayNL support in order to process payments                                                                                                                                                                                                                                            |
| licenseName                           | String               | The name of the license file in your assets folder                                                                                                                                                                                                                                                           |
| overlayParams                         | PaymentOverlayParams | Using these params you can configure the overlay during a payment (Opt-out feature)                                                                                                                                                                                                                          |
| overlayParams.enabled                 | boolean              | The enables/disables the overlay (default: `true`)                                                                                                                                                                                                                                                           |
| overlayParams.closeDelayInMs          | long                 | Configures an auto close delay on the overlay (default: 0 -> Keep open)                                                                                                                                                                                                                                      |
| overlayParams.logoImage               | int                  | The reference id for your logo (default: `R.drawable.paynl` -> The PayNL logo)                                                                                                                                                                                                                               |
| overlayParams.waitingCardAnimation    | int                  | The reference id for a lottie json animation shown while waiting for NFC detection. Make sure your [lottie json](http://airbnb.io/lottie/#/android?id=from-resraw-lottie_rawres-or-assets-lottie_filename) is in the raw folder (default: `R.raw.reader_animation`)                                          |
| overlayParams.buttonShape             | int                  | The reference id for a custom background shape for the buttons in the overlay. (Default: R.drawable.pay_btn)                                                                                                                                                                                                 |
| overlayParams.progressBarColor        | String               | The color of the loading spinner during processing of Payment. Hex-only (default: `#FF585FFF`)                                                                                                                                                                                                               |
| overlayParams.successColor            | String               | The color of the success check when payment is success. Hex-only (default: `#FF00D388`)                                                                                                                                                                                                                      |
| overlayParams.errorColor              | String               | The color of error during payment. Hex-only (default: `#FFC5362C`)                                                                                                                                                                                                                                           |
| overlayParams.backgroundColor         | String               | The background color of the overlay & ticket viewer. Hex-only (default: `#FFFFFFFF`)                                                                                                                                                                                                                         |
| overlayParams.amountTextColor         | String               | The text color of the amount. Hex-only (default: `#FF444444`)                                                                                                                                                                                                                                                |
| overlayParams.payerMessageTextColor   | String               | The text color of the payerMessage. Hex-only (default: `#FF888888`)                                                                                                                                                                                                                                          |
| overlayParams.buttonTextColor         | String               | The text color of the buttons. Hex-only (default: `#FF000000`)                                                                                                                                                                                                                                               |
| overlayParams.cancelButtonLabel       | String               | The label text on the cancel button (default: `Annuleren`)                                                                                                                                                                                                                                                   |
| overlayParams.closeButtonLabel        | String               | The label text on the close button (default: `Sluiten`)                                                                                                                                                                                                                                                      |
| overlayParams.waitingCardLabel        | String               | The label text while waiting for NFC detection (default: `Bied uw kaart aan`)                                                                                                                                                                                                                                |
| overlayParams.waitingPincode          | String               | The label text while waiting for the pincode (default: `Voer uw pincode in`)                                                                                                                                                                                                                                 |
| overlayParams.processingCardLabel     | String               | The label text while processing payment (default: `Betaling verwerken...`)                                                                                                                                                                                                                                   |
| overlayParams.paymentCancelled        | String               | The label text for payment cancelled (default: `Betaling afgebroken`)                                                                                                                                                                                                                                        |
| overlayParams.ticketHeaderLabel       | String               | The label text for the ticket viewer header (default: `Betaling succesvol!`)                                                                                                                                                                                                                                 |
| overlayParams.emailHeaderLabel        | String               | The label text for the email ticket header (default: `Voer email adres in`)                                                                                                                                                                                                                                  |
| overlayParams.emailButtonLabel        | String               | The label text for the send ticket button (default: `Mailen`)                                                                                                                                                                                                                                                |
| pinPadLayoutParams.hideShadow         | boolean              | The upper part of the screen shows a shadow during the WAITING_PINCODE. You can disable this and show your own content in the top part via this property (default: false)                                                                                                                                    |
| pinPadLayoutParams.waitingPincode     | String               | The hint text during WAITING_PINCODE (default: `Voer uw pincode in`)                                                                                                                                                                                                                                         |
| pinPadLayoutParams.backgroundColor    | String               | The background color of the overlay (default: `#FFFFFFFFFF`)                                                                                                                                                                                                                                                 |
| pinPadLayoutParams.numTextColor       | String               | The text color of the numbers in the pin pad (default: `#FF444444`)                                                                                                                                                                                                                                          |
| pinPadLayoutParams.okButtonColor      | String               | The background color of the OK button in the pin pad (default: `#FF585FFF`)                                                                                                                                                                                                                                  |
| pinPadLayoutParams.okButtonTextColor  | String               | The text color of the OK button in the pin pad (default: `#FFFFFFFF`)                                                                                                                                                                                                                                        |
| pinPadLayoutParams.clearButtonColor   | String               | The button color of the Clear button in the pin pad (default: `#FF888888`)                                                                                                                                                                                                                                   |
| pinPadLayoutParams.deleteButtonColor  | String               | The button color of the Delete button in the pin pad (default: `#FFC5362C`)                                                                                                                                                                                                                                  |
| useExternalDisplayIfAvailable         | String               | This will make sure the overlay and PIN prompt is show on the secondary screen, if a secondary screen is available (default: `true`)                                                                                                                                                                         |
| enableSound                           | boolean              | During a transaction, some user feedback is required to improve the User Experience. Example are: NFC scan beep or payment success beep. The SDK has a build-in tone generator which uses the phone's volume to generate the correct sounds (default: `true`)                                                |
| enableOfflineProcessing               | boolean              | ANDROID-ONLY Just like the PAY.POS app, PayNL is able to store your transaction when the mobile phone does not have internet. NOTE: It is not guaranteed that the payment will be approved. This is a major risk while using offline processing (default: `false`)                                           |
| enforcePinCodeDuringOfflineProcessing | boolean              | ANDROID-ONLY If a payment will be queued for Offline processing, you can enforce a pin prompt for lower risk. NOTE: this will only trigger a pin prompt for supported card (virtual card do not have a pin code). Extra note: This feature still does not guaranteed a successful payment (default: `false`) |
| enableLogging                         | boolean              | If problems occure, PayNL support needs logs from the SDK to help you out. This feature can be disabled for minor performance improvements, BUT NO SUPPORT CAN BE GIVEN IF THIS FEATURE IS DISABLED (default: `true`)                                                                                        |

##### Example

```ts
import {Platform} from 'react-native';
import {PayNlSdk} from 'paynl-pos-sdk-react-native';

class PayNLService {
    async initSdk() {
        const result = await PayNlSdk.initSdk({
            integrationId: Platform.select({
                ios: '',
                default: !__DEV__ ? 'PRODUCTION_ID' : 'DEVELOPMENT_ID',
            }),
            licenseName: '', // Required for Android softpos
        });

        switch (result) {
            case 'needs_login':
                // Start login flow and reinitialized the SDK
                return;

            case 'ready_for_payment':
                // The SDK is ready to start payment
                return;

            case 'failed':
                // Failed to init SDK. Please consult logs or contact PayNL support
                return;
        }
    }
}
```

#### Get activation code

> [!WARNING]
> Only use this if the `initSdk` method returned `needs_login`.
> Otherwise this method will log out this device and you will be forced to activate this activation code

This function will register this device and get an activation code to be activated
via [Terminals:create](https://developer.pay.nl/reference/post_terminals).
This function does not take parameters and has the following return type: `PayNlActivationResponse`:

| **Name**             | **Type** | **Description**                                               |
|----------------------|----------|---------------------------------------------------------------|
| `response`           | object   |                                                               |
| `response.code`      | string   | The activation code to be used in the `Terminals:Create` call |
| `response.expiresAt` | string   | This activation response is valid till this ISO8601 timestamp |

##### Example

```ts
import {PayNlSdk} from 'paynl-pos-sdk-react-native';

class PayNLService {
    async getActivationCode() {
        try {
            const response = await PayNlSdk.getActivationCode();
            // Call Terminal:Create API
        } catch (e) {
            console.error(`Error from PAY.POS sdk: ${error}`)
        }
    }
}
```

#### loginViaCode

> [!NOTE]
> This method requires the usage of `getActivationCode`

> [!NOTE]
> After completing this method, you need to re-invoke the `initSdk` function

After using the `getActivationCode` and [Terminals:create](https://developer.pay.nl/reference/post_terminals), you can
use this `loginViaCode`.
It does not have a return type, but you need to provide the code from the `getActivationCode` to complete the login.

##### Example

```ts
import {PayNlSdk} from 'paynl-pos-sdk-react-native';

class PayNLService {
    async loginViaCode(code: string) {
        try {
            await PayNlSdk.loginViaCode(code);
        } catch (e) {
            console.error(`Error from PAY.POS sdk: ${error}`)
        }
    }
}
```

#### loginViaCode

> [!WARNING]
> Only use this if the `initSdk` method returned `.needsLogin`.
> Otherwise this method will log out this device and reactivate using your account

> [!NOTE]
> After completing this method, you need to re-invoke the `initSdk` function

An alternative way to activate your device is via your PayNL account.
For this you need your a-code, service code, and service secret.

These codes can be found in the PayNL dashboard or using the
API: [Account:Me](https://developer.pay.nl/reference/accounts_me_get)
and [Merchant:info](https://developer.pay.nl/reference/merchants_info).

##### Example

```ts
import {PayNlSdk} from 'paynl-pos-sdk-react-native';

class PayNLService {
    async loginViaCredentials(aCode: string, serviceCode: string, serviceSecret: string) {
        try {
            await PayNlSdk.loginViaCredentials(aCode, serviceCode, serviceSecret);
        } catch (e) {
            console.error(`Error from PAY.POS sdk: ${error}`)
        }
    }
}
```

#### Get terminal info

With an activated terminal, you can fetch some basic information for reporting or other use cases.

The available information is the following:

| **Name**                      | **Type** | **Description**                                                                                         |
|-------------------------------|----------|---------------------------------------------------------------------------------------------------------|
| `terminalInfo`                | object?  |                                                                                                         |
| `terminalInfo.terminal`       | object   |                                                                                                         |
| `terminalInfo.terminal.code`  | string   | The terminal code known at PayNL                                                                        |
| `terminalInfo.terminal.name`  | string   | The terminal name giving during activation                                                              |
| `terminalInfo.merchant`       | object   |                                                                                                         |
| `terminalInfo.merchant.code`  | string   | Your M-code known at PayNL                                                                              |
| `terminalInfo.merchant.name`  | string   | Your merchant's name                                                                                    |
| `terminalInfo.service`        | object   | If no service data is provided during payment, this will be the service the payment will be recorded to |
| `terminalInfo.service.code`   | string   | The SL-code known at PayNL                                                                              |
| `terminalInfo.service.name`   | string   | The service's name                                                                                      |
| `terminalInfo.tradeName`      | object   |                                                                                                         |
| `terminalInfo.tradeName.code` | string   | The TM-code for this terminal                                                                           |
| `terminalInfo.tradeName.name` | string   | The tradeName's name                                                                                    |

##### Example

```ts
import {PayNlSdk, type TerminalInfo} from 'paynl-pos-sdk-react-native';

class PayNLService {
    getTerminalInfo(): TerminalInfo | undefined {
        try {
            const info = PayNlSdk.getTerminalInfo();
            if (!info) {
                console.error('This terminal is not activated...')
                return undefined;
            }

            return info;
        } catch (e) {
            console.error(`Error from PAY.POS sdk: ${error}`);
            return undefined;
        }
    }
}
```

#### Get allowed currencies

With an activated terminal, you can fetch the allowed currencies this SDK supports:

| **Name**                   | **Type** | **Description**                                               |
|----------------------------|----------|---------------------------------------------------------------|
| `allowedCurrencies`        | array?   |                                                               |
| `allowedCurrencies[]`      | object   |                                                               |
| `allowedCurrencies[].id`   | string   | The ISO 4217 number of this currency (example: Euro -> "978") |
| `allowedCurrencies[].code` | string   | The ISO 4217 code of this currency (example: Euro -> "EUR")   |
| `allowedCurrencies[].sign` | string   | The sign of this currency (example Euro -> "€")               |

##### Example

```ts
import {PayNlSdk, type AllowedCurrency} from 'paynl-pos-sdk-react-native';

class PayNLService {
    getTerminalInfo(): AllowedCurrency[] | undefined {
        try {
            const currencies = PayNlSdk.getAllowedCurrencies();
            if (!currencies || currencies.length === 0) {
                console.error('This terminal is not activated...')
                return undefined;
            }

            return currencies;
        } catch (e) {
            console.error(`Error from PAY.POS sdk: ${error}`)
            return undefined;
        }
    }
}
```

#### Start payment

With a fully activated terminal, you can start accepting and processing payments.
This function has 2 parameters:

| **Name**            | **Type** | **Description**                                                                                                                                                                   |
|---------------------|----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `transaction`       | object   | The transaction that needs to be use for this payment. For more details about this object, please check out [order:create](https://developer.pay.nl/reference/api_create_order-1) |
| `service`           | object?  | A transaction can be re-routed to another service (within the same Merchant). NOTE: this is optional                                                                              |
| `service.serviceId` | string   | The service code (example: SL-1234-1234). A list of services can be requested via: [Merchant:info](https://developer.pay.nl/reference/merchants_info)                             |
| `service.secret`    | string   | The secret belonging to this service                                                                                                                                              |

This function returns the `PayNlTransactionResult` type:

| **Name**              | **Type**               | **Description**                                                                                                                                                                  |
|-----------------------|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `result`              | object                 |                                                                                                                                                                                  |
| `result.statusAction` | PayNlTransactionStatus | The endresult of the transaction. Example: paid, failed                                                                                                                          |
| `result.payerMessage` | string                 | The message required to show on the UI. Example: `Betaling geslaagd`. Note: the language is determined by the user's card                                                        |
| `result.orderId`      | string                 | The orderId belonging to this transaction. Can be used to query the transaction in the [Transaction:info api](https://developer.pay.nl/reference/get_transactions-transactionid) |
| `result.reference`    | string?                | If provided, the SDK will echo back the provided reference in the transaction request                                                                                            |
| `result.ticket`       | string                 | A base64 encoded ticket. Only provided with a successful payment                                                                                                                 |

##### Example

```ts
import {PayNlSdk, type Transaction, type Service} from 'paynl-pos-sdk-react-native';
// React Native does not have a Base64 decoder build-in
import {Buffer} from 'buffer';

class PayNLService {
    async startPayment(transaction: Transaction, service?: Service) {
        try {
            const result = await PayNlSdk.startTransaction({transaction, service});
            if (result.statusAction !== 'PAID') {
                console.error(`Failed to process payment. Reason: ${result.payerMessage}`);
                return;
            }

            let ticket = '';
            if (result.ticket !== '') {
                const buff = new Buffer(result.ticket, 'base64');
                ticket = buff.tostring('ascii');
            }

            console.log(JSON.stringify(result));
            console.log('Ticket:')
            console.log(ticket)
        } catch (e) {
            console.error(`Error from PAY.POS sdk: ${error}`)
        }
    }
}
```

#### Payment Events

During a transaction, it is possible to receive events.
These events could be used to render/animate your on view.

| **Event**            | **Data**                                                                                                  | **Description**                                                                                                                                                                    |
|----------------------|-----------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| PAYMENT_WAITING_CARD | null                                                                                                      | The SDK is ready to scan payment card                                                                                                                                              |
| PAYMENT_PROCESSING   | {"schemeBrand":"MASTERCARD, MEASTRO, VISA, V_PAY, AMEX, BANCONTACT, UNKNOWN"}                             | The SDK has successfully scanned the card and has send the payment for processing. NOTE: You are required to show [the image](../brands) of that specific scheme during this event |
| PAYMENT_COMPLETED    | {"statusAction:"PAID\|DECLINED","transactionId":"MV-xxxx-xxxx-xxxx",orderId":"",reference:"","ticket":""} | The SDK has successfully processed the payment. NOTE: The transaction might not be paid, due to insufficient balance for example                                                   |
| PAYMENT_FAILED       | {"code": "SV-xxxx", "description": ""}                                                                    | The SDK has failed to process the payment. Please review the data to see why                                                                                                       |
| PIN_WAITING          | {"usingSecondaryScreen":"true\|false"}                                                                    | The SDK is waiting for the pincode of the customer. Please note that `usingSecondaryScreen` is of type String                                                                      |
| PIN_CANCELLED        | null                                                                                                      | The pincode input has been cancelled. The transaction itself is also cancelled                                                                                                     |

##### Example

```js
import {NativeEventEmitter, NativeModules} from 'react-native';
import {PayNlSdk} from 'paynl-pos-sdk-react-native';

class PayNLService {
    setPaymentListener() {
        // Old architecture
        const eventEmitter = new NativeEventEmitter(NativeModules.PayNlSdk);
        eventEmitter.addListener('onPaymentEvent', (data) => {
            console.log('paymentEvent', JSON.stringify(data));
        });

        // New architecture
        PayNlSdk.onPaymentEvent((data) => {
            console.log('paymentEvent', JSON.stringify(data));
        });
    }
}
```

#### Send ticket via E-mail

After a succesfull transaction, it is possible to send the ticket via e-mail to someone else.
For this, you need the transactionId (MV-code), the ticket self (both received after startTransaction), and the user's
E-mail address

##### Example

```js
import {PayNlSdk, type Transaction} from 'paynl-pos-sdk-react-native';

class PayNLService {
    async sendTicket(email: string, transaction: Transaction) {
        try {
            await PayNlSdk.sendTicket(email, transaction.transactionId, transaction.ticket);
        } catch (e) {
            console.error(`Error from PAY.POS sdk: ${error}`)
        }
    }
}
```

#### Logout

> [!NOTE]
> This function will not de-activate your device in the PayNL portal
> You still need to call the [Terminals:delete api](https://developer.pay.nl/reference/delete_terminals-terminalcode)

Sometimes, it might be needed to log out/de-activate this terminal.
Reasons can be: A switch between merchants or this device will not be used for a while.

##### Example

```ts
import {PayNlSdk} from 'paynl-pos-sdk-react-native';

class PayNLService {
    logout() {
        PayNlSdk.logout();
    }
}
```

#### Send logs

> [!NOTE]
> ANDROID ONLY: Pressing 5x on the logo in the overlay will also trigger this function (if `enabledLogging = true`)

When encountering problems with the SDK, PayNL support needs the logs stored in the SDK.
To provide these logs, you can invoke the `sendLogs()` function

##### Example

```ts
import {PayNlSdk} from 'paynl-pos-sdk-react-native';

class PayNLService {
    async sendLogs() {
        await PayNlSdk.sendLogs();
    }
}
```

#### Get Offline queue - ANDROID ONLY

If `configuration.setEnableOfflineProcessing` is set to true, you need to regularly fetch the content of the Offline
Processing queue.
Every transaction that has queued, need to be manually triggered for processing (or you can use the triggerFull
function).
To know if a trigger is needed, you can use this method.

##### Response

| **Property**         | **Type/value**     | **Description**                                                                                            |
|----------------------|--------------------|------------------------------------------------------------------------------------------------------------|
| `size`               | number             | The size of the queued transactions                                                                        |
| `queue`              | OfflineQueueItem[] |                                                                                                            |
| `queue[].id`         | string             | A short identifier of the queued transaction. Can be used for manual trigger or to clear it from the queue |
| `queue[].reference`  | string?            | The reference provided during the `startTransaction`                                                       |
| `queue[].enqueuedAt` | string (ISO 8601)  | The datetime of the moment this transaction was enqueued                                                   |

##### Example

```ts
import {PayNlSdk, type OfflineQueueModel} from 'paynl-pos-sdk-react-native';

class PayNLService {
    getOfflineQueue(): Promise<OfflineQueueModel> {
        return PayNlSdk.getOfflineQueue();
    }
}
```

#### Trigger full offline sync - ANDROID ONLY

If `configuration.setEnableOfflineProcessing` is set to true, you need to manually trigger a sync.
You can choose to do a full sync or sync per queued item.
Here is an example for full sync.

##### Response

| **Name**                | **Type**               | **Description**                                                                                                                                                                  |
|-------------------------|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `result`                | object[]               | A list of all the processed transactions. Might not contain all items due to server errors                                                                                       |
| `result[].statusAction` | PayNlTransactionStatus | The endresult of the transaction. Example: paid, cancelled, failed                                                                                                               |
| `result[].payerMessage` | string                 | The message required to show on the UI. Example: `Betaling geslaagd`. Note: the language is determined by the user's card                                                        |
| `result[].orderId`      | string                 | The orderId belonging to this transaction. Can be used to query the transaction in the [Transaction:info api](https://developer.pay.nl/reference/get_transactions-transactionid) | 
| `result[].reference`    | string?                | If provided, the SDK will echo back the provided reference in the transaction request                                                                                            |
| `result[].ticket`       | string                 | A base64 encoded ticket. Only provided with a successful payment                                                                                                                 |

##### Example

```ts
import {PayNlSdk} from 'paynl-pos-sdk-react-native';

class PayNLService {
    async triggerFullOfflineProcessing() {
        try {
            await PayNlSdk.triggerFullOfflineProcessing();
        } catch (e) {
            console.error(`Error from PAY.POS sdk: ${error}`)
        }
    }
}
```

#### Trigger single offline sync - ANDROID ONLY

If `configuration.setEnableOfflineProcessing` is set to true, you need to manually trigger a sync.
Here is an example for single sync.

##### Request

| **Property** | **Type/value** | **Description**                                 |
|--------------|----------------|-------------------------------------------------|
| `id`         | string         | The identifier fetched from `getOfflineQueue()` |

##### Response

| **Name**              | **Type**               | **Description**                                                                                                                                                                  |
|-----------------------|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `result`              | object                 | A list of all the processed transactions. Might not contain all items due to server errors                                                                                       |
| `result.statusAction` | PayNlTransactionStatus | The endresult of the transaction. Example: paid, cancelled, failed                                                                                                               |
| `result.payerMessage` | string                 | The message required to show on the UI. Example: `Betaling geslaagd`. Note: the language is determined by the user's card                                                        |
| `result.orderId`      | string                 | The orderId belonging to this transaction. Can be used to query the transaction in the [Transaction:info api](https://developer.pay.nl/reference/get_transactions-transactionid) | 
| `result.reference`    | string?                | If provided, the SDK will echo back the provided reference in the transaction request                                                                                            |
| `result.ticket`       | string                 | A base64 encoded ticket. Only provided with a successful payment                                                                                                                 |

##### Example

```ts
import {PayNlSdk} from 'paynl-pos-sdk-react-native';

class PayNLService {
    async triggerSingleOfflineProcessing(id: string) {
        try {
            await PayNlSdk.triggerSingleOfflineProcessing(id);
        } catch (e) {
            console.error(`Error from PAY.POS sdk: ${error}`)
        }
    }
}
```

#### Clear item from offline queue - ANDROID ONLY

An offline transaction is valid for 7days, after that the transaction cannot be processed.
That being said, the SDK does not remove those transaction, you are responsible for maintaining the offline queue.
To remove a single offline transaction, you can use this method.

##### Request

| **Property** | **Type/value** | **Description**                                 |
|--------------|----------------|-------------------------------------------------|
| `id`         | String         | The identifier fetched from `getOfflineQueue()` |

##### Example

```ts
import {PayNlSdk} from 'paynl-pos-sdk-react-native';

class PayNLService {
    async clearOfflineItem(id: string) {
        if (!PayNlSdk.clearOfflineItem(id)) {
            console.warn(`Could not remove item from queue -> Item is not found...`)
        }
    }
}
```
