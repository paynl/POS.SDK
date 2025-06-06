<img src="https://www.pay.nl/uploads/1/brands/main_logo.png" width="100px" />

# PAY.POS SDK - Android Softpos

### Requirements:

- Android v8.0 or higher (minSDK 26)
    - Android security patch is not older than 2 years
- Device support NFC (and is enabled)
    - A hardPOS device (like the Sunmi P series or PAX) is not supported via this SDK
- Your project supports Gradle (default for Android apps)
- Make sure you have contacted PayNL support for an `integrationId` (required for initSDK)
- Make sure you have the gradle credentials from PayNL support
- Make sure you have a personal access token on Github with `read:packages` scope

> [!WARNING]
> Note that offline mode, as featured in the PAY.POS app, is not supported as of now

### Getting started

To get started, create a `gradle.properties` file in your global gradle file:

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

Now that you have set your credentials, go to the root `build.gradle` file and make sure the following is in this file:

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

Next, let's make sure you do not get build errors.
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

Last but not least, let's add the SDK to your Android app.
Go to `app/build.gradle` and go to the `dependencies` section.
In there you can add the PayNL POS SDK via:

```groovy
def PAYNL_VERSION = "<LATEST_VERSION_HERE>"
dependencies {
  debugImplementation "com.paynl.pos:sdk.softpos.staging:$PAYNL_VERSION"
  releaseImplementation "com.paynl.pos:sdk.softpos:$PAYNL_VERSION"
}
```

#### Why is there a staging and production version?

A softpos application has strict security policies.
One being the Android security patch, but also other things like ADB or timestamp mismatch and much more.

During development, you do not want to be bothered with these security policies, thus we allow to disable those BUT ONLY
DURING DEVELOPMENT.
You are not able to use the staging version of the SDK in a production app!

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

This function will initialize the SDK. It will return `PayNlInitResult` enum type

| **Name**                                            | **Type**             | **Description**                                                                                                                                                                                                                                                                                 |
|-----------------------------------------------------|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| context                                             | Context              | The current android activity/context                                                                                                                                                                                                                                                            |
| configuration                                       | PayNLConfiguration   | The configuration of the SDK, use the PayNlConfiguration.Builder() for a quick and easy setup                                                                                                                                                                                                   | 
| configuration.integrationId                         | String               | The UUID received from PayNL support in order to process payments                                                                                                                                                                                                                               |
| configuration.licenseName                           | String               | The name of the license file in your assets folder                                                                                                                                                                                                                                              |
| configuration.overlayParams                         | PaymentOverlayParams | Using these params you can configure the overlay during a payment (Opt-out feature)                                                                                                                                                                                                             |
| configuration.overlayParams.enabled                 | boolean              | The enables/disables the overlay (default: `true`)                                                                                                                                                                                                                                              |
| configuration.overlayParams.closeDelayInMs          | long                 | Configures an auto close delay on the overlay (default: 0 -> Keep open)                                                                                                                                                                                                                         |
| configuration.overlayParams.logoImage               | int                  | The reference id for your logo (default: `R.drawable.paynl` -> The PayNL logo)                                                                                                                                                                                                                  |
| configuration.overlayParams.waitingCardAnimation    | int                  | The reference id for a lottie json animation shown while waiting for NFC detection. Make sure your [lottie json](http://airbnb.io/lottie/#/android?id=from-resraw-lottie_rawres-or-assets-lottie_filename) is in the raw folder (default: `R.raw.reader_animation`)                             |
| configuration.overlayParams.buttonShape             | int                  | The reference id for a custom background shape for the buttons in the overlay. (Default: R.drawable.pay_btn)                                                                                                                                                                                    |
| configuration.overlayParams.progressBarColor        | String               | The color of the loading spinner during processing of Payment. Hex-only (default: `#FF585FFF`)                                                                                                                                                                                                  |
| configuration.overlayParams.successColor            | String               | The color of the success check when payment is success. Hex-only (default: `#FF00D388`)                                                                                                                                                                                                         |
| configuration.overlayParams.errorColor              | String               | The color of error during payment. Hex-only (default: `#FFC5362C`)                                                                                                                                                                                                                              |
| configuration.overlayParams.backgroundColor         | String               | The background color of the overlay & ticket viewer. Hex-only (default: `#FFFFFFFF`)                                                                                                                                                                                                            |
| configuration.overlayParams.amountTextColor         | String               | The text color of the amount. Hex-only (default: `#FF444444`)                                                                                                                                                                                                                                   |
| configuration.overlayParams.payerMessageTextColor   | String               | The text color of the payerMessage. Hex-only (default: `#FF888888`)                                                                                                                                                                                                                             |
| configuration.overlayParams.buttonTextColor         | String               | The text color of the buttons. Hex-only (default: `#FF000000`)                                                                                                                                                                                                                                  |
| configuration.overlayParams.cancelButtonLabel       | String               | The label text on the cancel button (default: `Annuleren`)                                                                                                                                                                                                                                      |
| configuration.overlayParams.closeButtonLabel        | String               | The label text on the close button (default: `Sluiten`)                                                                                                                                                                                                                                         |
| configuration.overlayParams.waitingCardLabel        | String               | The label text while waiting for NFC detection (default: `Bied uw kaart aan`)                                                                                                                                                                                                                   |
| configuration.overlayParams.waitingPincode          | String               | The label text while waiting for the pincode (default: `Voer uw pincode in`)                                                                                                                                                                                                                    |
| configuration.overlayParams.processingCardLabel     | String               | The label text while processing payment (default: `Betaling verwerken...`)                                                                                                                                                                                                                      |
| configuration.overlayParams.paymentCancelled        | String               | The label text for payment cancelled (default: `Betaling afgebroken`)                                                                                                                                                                                                                           |
| configuration.overlayParams.ticketHeaderLabel       | String               | The label text for the ticket viewer header (default: `Betaling succesvol!`)                                                                                                                                                                                                                    |
| configuration.overlayParams.emailHeaderLabel        | String               | The label text for the email ticket header (default: `Voer email adres in`)                                                                                                                                                                                                                     |
| configuration.overlayParams.emailButtonLabel        | String               | The label text for the send ticket button (default: `Mailen`)                                                                                                                                                                                                                                   |
| configuration.pinPadLayoutParams.hideShadow         | boolean              | The upper part of the screen shows a shadow during the WAITING_PINCODE. You can disable this and show your own content in the top part via this property (default: false)                                                                                                                       |
| configuration.pinPadLayoutParams.waitingPincode     | String               | The hint text during WAITING_PINCODE (default: `Voer uw pincode in`)                                                                                                                                                                                                                            |
| configuration.pinPadLayoutParams.backgroundColor    | String               | The background color of the overlay (default: `#FFFFFFFFFF`)                                                                                                                                                                                                                                    |
| configuration.pinPadLayoutParams.numTextColor       | String               | The text color of the numbers in the pin pad (default: `#FF444444`)                                                                                                                                                                                                                             |
| configuration.pinPadLayoutParams.okButtonColor      | String               | The background color of the OK button in the pin pad (default: `#FF585FFF`)                                                                                                                                                                                                                     |
| configuration.pinPadLayoutParams.okButtonTextColor  | String               | The text color of the OK button in the pin pad (default: `#FFFFFFFF`)                                                                                                                                                                                                                           |
| configuration.pinPadLayoutParams.clearButtonColor   | String               | The button color of the Clear button in the pin pad (default: `#FF888888`)                                                                                                                                                                                                                      |
| configuration.pinPadLayoutParams.deleteButtonColor  | String               | The button color of the Delete button in the pin pad (default: `#FFC5362C`)                                                                                                                                                                                                                     |
| configuration.useExternalDisplayIfAvailable         | String               | This will make sure the overlay and PIN prompt is show on the secondary screen, if a secondary screen is available (default: `true`)                                                                                                                                                            |
| configuration.enableSound                           | boolean              | During a transaction, some user feedback is required to improve the User Experience. Example are: NFC scan beep or payment success beep. The SDK has a build-in tone generator which uses the phone's volume to generate the correct sounds (default: `true`)                                   |
| configuration.enableOfflineProcessing               | boolean              | Just like the PAY.POS app, PayNL is able to store your transaction when the mobile phone does not have internet. NOTE: It is not guaranteed that the payment will be approved. This is a major risk while using offline processing (default: `false`)                                           |
| configuration.enforcePinCodeDuringOfflineProcessing | boolean              | If a payment will be queued for Offline processing, you can enforce a pin prompt for lower risk. NOTE: this will only trigger a pin prompt for supported card (virtual card do not have a pin code). Extra note: This feature still does not guaranteed a successful payment (default: `false`) |
| configuration.enableLogging                         | boolean              | If problems occure, PayNL support needs logs from the SDK to help you out. This feature can be disabled for minor performance improvements, BUT NO SUPPORT CAN BE GIVEN IF THIS FEATURE IS DISABLED (default: `true`)                                                                           |

##### Example

```java
package com.paynl.example;

import android.content.Context;
import android.util.Log;

import com.paynl.pos.sdk.PosService;
import com.paynl.pos.sdk.shared.exceptions.SVErrorBaseException;
import com.paynl.pos.sdk.shared.models.paynl.PayNlInitResult;
import com.paynl.pos.sdk.shared.payment.PaymentOverlayParams;

class PayNLService {
  private static final String integrationId = "";
  private static final String licenseName = "";
  
  private PayNlConfiguration configuration = PayNlConfiguration.Builder()
            .setIntegrationId(integrationId) // Received via PayNL support
            .setLicenseName(licenseName) // License for testing ONLY
            .setEnableSound(true)
            .setEnableLogging(true) // If set to false, PayNL cannot provide support
            .setUseExternalDisplayIfAvailable(true) // Disable if you want the pinpad to always be shown on the primary screen
            .setOverlayParams(new PaymentOverlayParams())
            .setPinPadLayoutParams(new PinPadLayoutParams())
            .build();
  
  private final PosService posService;
  
  public Example(Context context) {
    this.posService = new PosService(context, configuration);
    // ... OR ...
    // this.posService = new PosService(context);
    // this.posService.setConfiguration(configuration);
  }
    
  public void initSdk() {
    try {
      PayNlInitResult initResult = this.posService.initSdk();
      switch (initResult) {
        case needsLogin:
          // Start login flow
          break;
        case readyForPayments:
          // SDK is ready to start payments
          break;
      }
    } catch (SVErrorBaseException exception) {
      Log.e("PayNLExample", String.format("Failed to initialize SDK - code: %s, description: %s", e.code, e.description));
    }
  }
}
```

#### Get activation code

> [!WARNING]
> Only use this if the `initSdk` method returned `needsLogin`.
> Otherwise this method will log out this device, and you will be forced to activate this activation code

This function will register this device and get an activation code to be activated
via [Terminals:create](https://developer.pay.nl/reference/post_terminals).
This function does not take parameters and has the following return type: `PayNlActivationResponse`:

| **Name**             | **Type** | **Description**                                               |
|----------------------|----------|---------------------------------------------------------------|
| `response`           | object   |                                                               |
| `response.code`      | String   | The activation code to be used in the `Terminals:create` call |
| `response.expiresAt` | Date     | This activation response is valid till this date              |

##### Example

```java
import android.util.Log;

import com.paynl.pos.sdk.shared.models.paynl.PayNlActivationCode;

class PayNLService {

  // ...
  
  public PayNlActivationCode getActivationCode() {
    try {
      return this.posService.getActivationCode();
    } catch (SVErrorBaseException e) {
      Log.e("PayNLExample", String.format("Failed to fetch activation code - code: %s, description: %s", e.code, e.description));
      return null;
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

```java
import android.util.Log;

class PayNLService {

  // ...
  
  public boolean loginViaCode(String code) {
    try {
      this.posService.loginViaCode(code);
      return true;
    } catch (SVErrorBaseException e) {
      Log.e("PayNLExample", String.format("Failed to activate SDK with activation code - code: %s, description: %s", e.code, e.description));
      return false;
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

```java
import android.util.Log;

class PayNLService {

  // ...
  
  public boolean loginViaCredentials(String aCode, String serviceCode, String serviceSecret) {
      try {
          this.posService.loginViaCredentials(aCode, serviceCode, serviceSecret);
          return true;
      } catch (SVErrorBaseException e) {
          Log.e("PayNLExample", String.format("Failed to activate SDK with credentials - code: %s, description: %s", e.code, e.description));
          return false;
      }
  }
}
```

#### Get terminal info

With an activated terminal, you can fetch some basic information for reporting or other use cases.

The available information is the following:

| **Name**                      | **Type**                   | **Description**                                                                                         |
|-------------------------------|----------------------------|---------------------------------------------------------------------------------------------------------|
| `terminalInfo`                | PayNlTerminalInfo          |                                                                                                         |
| `terminalInfo.terminal`       | PayNlTerminalInfoTerminal  |                                                                                                         |
| `terminalInfo.terminal.code`  | String                     | The terminal code known at PayNL                                                                        |
| `terminalInfo.terminal.name`  | String                     | The terminal name giving during activation                                                              |
| `terminalInfo.merchant`       | PayNlTerminalInfoMerchant  |                                                                                                         |
| `terminalInfo.merchant.code`  | String                     | Your M-code known at PayNL                                                                              |
| `terminalInfo.merchant.name`  | String                     | Your merchant's name                                                                                    |
| `terminalInfo.service`        | PayNlTerminalInfoService   | If no service data is provided during payment, this will be the service the payment will be recorded to |
| `terminalInfo.service.code`   | String                     | The SL-code known at PayNL                                                                              |
| `terminalInfo.service.name`   | String                     | The service's name                                                                                      |
| `terminalInfo.tradeName`      | PayNlTerminalInfoTradeName |                                                                                                         |
| `terminalInfo.tradeName.code` | String                     | The TM-code for this terminal                                                                           |
| `terminalInfo.tradeName.name` | String                     | The tradeName's name                                                                                    |

##### Example

```java
import android.util.Log;

import com.paynl.pos.sdk.shared.models.paynl.info.PayNlTerminalInfo;

class PayNLService {

  // ...
  
  public PayNlTerminalInfo getTerminalInfo() {
    PayNlTerminalInfo terminalInfo = this.posService.getTerminalInfo();
    if (terminalInfo == null) {
      Log.e("PayNLExample", "Failed to get terminal info - TERMINAL_NOT_ACTIVATED");
      return null;
    }

    return terminalInfo;
  }
}
```

#### Get allowed currencies

With an activated terminal, you can fetch the allowed currencies this SDK supports:

| **Name**                   | **Type**                   | **Description**                                               |
|----------------------------|----------------------------|---------------------------------------------------------------|
| `allowedCurrencies`        | List<PayNlAllowedCurrency> |                                                               |
| `allowedCurrencies[].id`   | String                     | The ISO 4217 number of this currency (example: Euro -> "978") |
| `allowedCurrencies[].code` | String                     | The ISO 4217 code of this currency (example: Euro -> "EUR")   |
| `allowedCurrencies[].sign` | String                     | The sign of this currency (example Euro -> "€")               |

##### Example

```java
import android.util.Log;

import com.paynl.pos.sdk.shared.models.paynl.info.PayNlAllowedCurrency;

class PayNLService {

  // ...
  
  public List<PayNlAllowedCurrency> getAllowedCurrencies() {
      List<PayNlAllowedCurrency> currencies = this.posService.getAllowedCurrencies();
      if (currencies.isEmpty()) {
          Log.e("PayNLExample", "Failed to get allowed currencies - TERMINAL_NOT_ACTIVATED");
          return null;
      }

      return currencies;
  }
}
```

#### Start payment

With a fully activated terminal, you can start accepting and processing payments.
This function has 2 parameters:

| **Name**            | **Type**             | **Description**                                                                                                                                                                   |
|---------------------|----------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `transaction`       | PayNlTransaction     | The transaction that needs to be use for this payment. For more details about this object, please check out [order:create](https://developer.pay.nl/reference/api_create_order-1) |
| `service`           | PayNlService or null | A transaction can be re-routed to another service (within the same Merchant). NOTE: this is optional                                                                              |
| `service.serviceId` | String               | The service code (example: SL-1234-1234). A list of services can be requested via: [Merchant:info](https://developer.pay.nl/reference/merchants_info)                             |
| `service.secret`    | String               | The secret belonging to this service                                                                                                                                              |

This function returns the `PayNlTransactionResult` type:

| **Name**              | **Type**               | **Description**                                                                                                                                                                  |
|-----------------------|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `result`              | PayNlTransactionResult |                                                                                                                                                                                  |
| `result.statusAction` | PayNlTransactionStatus | The endresult of the transaction. Example: paid, cancelled, failed                                                                                                               |
| `result.payerMessage` | String                 | The message required to show on the UI. Example: `Betaling geslaagd`. Note: the language is determined by the user's card                                                        |
| `result.orderId`      | String                 | The orderId belonging to this transaction. Can be used to query the transaction in the [Transaction:info api](https://developer.pay.nl/reference/get_transactions-transactionid) |
| `result.reference`    | String?                | If provided, the SDK will echo back the provided reference in the transaction request                                                                                            |
| `result.ticket`       | String                 | A base64 encoded ticket. Only provided with a successful payment                                                                                                                 |

##### Example

```java
import android.util.Log;

import com.paynl.pos.sdk.shared.models.paynl.transaction.PayNlTransaction;
import com.paynl.pos.sdk.shared.models.paynl.transaction.PayNlTransactionAmount;
import com.paynl.pos.sdk.shared.models.paynl.transaction.PayNlTransactionResult;
import com.paynl.pos.sdk.shared.models.paynl.transaction.PayNlTransactionStatus;

class PayNLService {

  // ...
  
  public void startTransaction() {
    try {
      PayNlTransaction transaction = new PayNlTransaction.Builder()
              .setAmount(new PayNlTransactionAmount(100, "EUR"))
              .build();

      PayNlTransactionResult result = this.posService.startTransaction(transaction, null);

      if (result.statusAction != PayNlTransactionStatus.PAID) {
        Log.w("PayNlExample", "Payment failed or cancelled...");
        return;
      }

      Log.i("PayNLExample", "Payment processed!");
      Log.i("PayNLExample", String.format("OrderId: %s\nReference: %s\nPayerMessage: %s", result.orderId, result.reference, result.payerMessage));

      byte[] ticketBytes = Base64.getDecoder().decode(result.ticket);
      Log.i("PayNLExample", String.format("Ticket data:\n\n%s", new String(ticketBytes)));

    } catch (SVErrorBaseException e) {
      Log.e("PayNLExample", String.format("Failed to process payment - code: %s, description: %s", e.code, e.description));
    }
  }
}
```

#### Payment Events

During a transaction, it is possible to receive events.
These events could be used to render/animate yourown view.

| **Event**            | **Data**                                                                                                  | **Description**                                                                                                                                                                    |
|----------------------|-----------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| PAYMENT_WAITING_CARD | null                                                                                                      | The SDK is ready to scan payment card                                                                                                                                              |
| PAYMENT_PROCESSING   | {"schemeBrand":"MASTERCARD\|MEASTRO\|VISA\|V_PAY\|AMEX\|BANCONTACT"}                                      | The SDK has successfully scanned the card and has send the payment for processing. NOTE: You are required to show [the image](../brands) of that specific scheme during this event |
| PAYMENT_COMPLETED    | {"statusAction:"PAID\|DECLINED","transactionId":"MV-xxxx-xxxx-xxxx",orderId":"",reference:"","ticket":""} | The SDK has successfully processed the payment. NOTE: The transaction might not be paid, due to insufficient balance for example                                                   |
| PAYMENT_FAILED       | {"code": "SV-xxxx", "description": ""}                                                                    | The SDK has failed to process the payment. Please review the data to see why                                                                                                       |
| PIN_WAITING          | {"usingSecondaryScreen":"true\|false"}                                                                    | The SDK is waiting for the pincode of the customer. Please note that `usingSecondaryScreen` is of type String                                                                      |
| PIN_CANCELLED        | null                                                                                                      | The pincode input has been cancelled. The transaction itself is also cancelled                                                                                                     |

##### Example

```java
import android.util.Log;

class PayNLService {

  // ...
  
  public void setPaymentListener() {
    this.posService.setOnPaymentEventListener((event, data) -> {
      Log.i("OnPaymentEvent", String.format("Got event %s", event.type));
    });
  }
}
```

#### Send ticket via E-mail

After a succesfull transaction, it is possible to send the ticket via e-mail to someone else.
For this, you need the transactionId (MV-code), the ticket self (both received after startTransaction), and the user's
E-mail address

##### Example

```java
import android.util.Log;

import com.paynl.pos.sdk.shared.models.paynl.transaction.PayNlTransactionResult;

class PayNLService {

  // ...
  
  public void sendTicket(PayNlTransactionResult transaction, String email) {
    try {
      this.posService.sendTicket(email, transaction.transactionId, transaction.ticket);
    } catch (SVErrorBaseException e) {
      Log.e("PayNLExample", String.format("Failed to send ticket - code: %s, description: %s", e.code, e.description));
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

```java
class PayNLService {

  // ...
  
  public void logout() {
      this.posService.logout();
  }
}
```

#### Send logs

> [!NOTE]
> This only works if the SDK configuration contains `enabledLogging = true`

> [!NOTE]
> Pressing 5x on the logo in the overlay will also trigger this function (if `enabledLogging = true`)

When encountering problems with the SDK, PayNL support needs the logs stored in the SDK.
To provide these logs, you can invoke the `sendLogs()` function

##### Example

```java
import android.util.Log;

class PayNLService {

  // ...
  
  public void sendLogs() {
    try {
      this.posService.sendLogs();
    } catch (SVErrorBaseException e) {
      Log.e("PayNLExample", String.format("Failed to send logs - code: %s, description: %s", e.code, e.description));
    }
  }
}
```

#### Get Offline queue

If `configuration.setEnableOfflineProcessing` is set to true, you need to regularly fetch the content of the Offline
Processing queue.
Every transaction that has queued, need to be manually triggered for processing (or you can use the triggerFull
function).
To know if a trigger is needed, you can use this method.

##### Response

| **Property**         | **Type/value**         | **Description**                                                                                            |
|----------------------|------------------------|------------------------------------------------------------------------------------------------------------|
| `size`               | int                    | The size of the queued transactions                                                                        |
| `queue`              | List<OfflineQueueItem> |                                                                                                            |
| `queue[].id`         | String                 | A short identifier of the queued transaction. Can be used for manual trigger or to clear it from the queue |
| `queue[].reference`  | String or null         | The reference provided during the `startTransaction`                                                       |
| `queue[].enqueuedAt` | Date                   | The datetime of the moment this transaction was enqueued                                                   |

##### Example

```java
import com.paynl.pos.sdk.shared.models.offline.OfflineQueueModel;

class PayNLService {

  // ...
  
  public OfflineQueueModel getOfflineQueue() {
    return this.posService.getOfflineQueue();
  }
}
```

#### Trigger full offline sync

If `configuration.setEnableOfflineProcessing` is set to true, you need to manually trigger a sync.
You can choose to do a full sync or sync per queued item.
Here is an example for full sync.

##### Response

| **Name**                | **Type**                     | **Description**                                                                                                                                                                  |
|-------------------------|------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `result`                | List<PayNlTransactionResult> | A list of all the processed transactions. Might not contain all items due to server errors                                                                                       |
| `result[].statusAction` | PayNlTransactionStatus       | The endresult of the transaction. Example: paid, cancelled, failed                                                                                                               |
| `result[].payerMessage` | String                       | The message required to show on the UI. Example: `Betaling geslaagd`. Note: the language is determined by the user's card                                                        |
| `result[].orderId`      | String                       | The orderId belonging to this transaction. Can be used to query the transaction in the [Transaction:info api](https://developer.pay.nl/reference/get_transactions-transactionid) | 
| `result[].reference`    | String?                      | If provided, the SDK will echo back the provided reference in the transaction request                                                                                            |
| `result[].ticket`       | String                       | A base64 encoded ticket. Only provided with a successful payment                                                                                                                 |

##### Example

```java
import android.util.Log;

class PayNLService {

  // ...
  
  public void triggerFullOfflineProcessing() {
    try {
      this.posService.triggerFullOfflineProcessing();
    } catch (SVErrorBaseException e) {
       Log.e("PayNLExample", String.format("Failed to trigger full offline sync - code: %s, description: %s", e.code, e.description));
    }
  }
}
```

#### Trigger single offline sync

If `configuration.setEnableOfflineProcessing` is set to true, you need to manually trigger a sync.
Here is an example for single sync.

##### Request

| **Property** | **Type/value** | **Description**                                 |
|--------------|----------------|-------------------------------------------------|
| `id`         | String         | The identifier fetched from `getOfflineQueue()` |

##### Response

| **Name**              | **Type**               | **Description**                                                                                                                                                                  |
|-----------------------|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `result`              | PayNlTransactionResult |                                                                                                                                                                                  |
| `result.statusAction` | PayNlTransactionStatus | The endresult of the transaction. Example: paid, cancelled, failed                                                                                                               |
| `result.payerMessage` | String                 | The message required to show on the UI. Example: `Betaling geslaagd`. Note: the language is determined by the user's card                                                        |
| `result.orderId`      | String                 | The orderId belonging to this transaction. Can be used to query the transaction in the [Transaction:info api](https://developer.pay.nl/reference/get_transactions-transactionid) | 
| `result.reference`    | String?                | If provided, the SDK will echo back the provided reference in the transaction request                                                                                            |
| `result.ticket`       | String                 | A base64 encoded ticket. Only provided with a successful payment                                                                                                                 |

##### Example

```java
import android.util.Log;

class PayNLService {

  // ...
  
  public void triggerSingleOfflineProcessing(String id) {
    try {
      this.posService.triggerSingleOfflineProcessing(id);
    } catch (SVErrorBaseException e) {
       Log.e("PayNLExample", String.format("Failed to trigger single offline sync - code: %s, description: %s", e.code, e.description));
    }
  }
}
```

#### Clear item from offline queue

An offline transaction is valid for 7days, after that the transaction cannot be processed.
That being said, the SDK does not remove those transaction, you are responsible for maintaining the offline queue.
To remove a single offline transaction, you can use this method.

##### Request

| **Property** | **Type/value** | **Description**                                 |
|--------------|----------------|-------------------------------------------------|
| `id`         | String         | The identifier fetched from `getOfflineQueue()` |

##### Example

```java
import android.util.Log;

class PayNLService {

  // ...
  
  public void clearOfflineItem(String id) {
    if (!this.posService.clearOfflineItem(id)) {
      Log.w("PayNLExample", "Failed to clear single item from queue - Id is not found in queue");
    }
  }
}
```
