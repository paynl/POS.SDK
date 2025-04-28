<img src="https://www.pay.nl/uploads/1/brands/main_logo.png" width="100px" />

# PAY.POS SDK - Error codes

### General error codes

| **Code** | **Description**              | **What to do?**                                                                                        |
|----------|------------------------------|--------------------------------------------------------------------------------------------------------|
| SV-0000  | Unexpected error             | Please consult the logging to see what's wrong, or contact PayNL support                               |
| SV-0001  | No integrationId is provided | Contact PayNL support to get an integrationId                                                          |
| SV-0007  | Terminal not activated       | The terminal is not activated, please use loginViaCode or loginViaCredentials to activate the terminal |
| SV-0008  | API error                    | Please consult the logging to see what's wrong, or contact PayNL support                               |
| SV-0009  | Transaction cancelled        | This is not really an error, the transaction is cancelled by the user                                  |
| SV-0010  | NFC disabled                 | The kernel detected that the NFC adapter is disabled. Please activate it in the phone's settings       |

### Android softpos specific codes

| **Code** | **Description**                | **What to do?**                                                                                                                              |
|----------|--------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| SV-1000  | Failed to load CAPK            | Contact PayNL support and share your logs                                                                                                    |
| SV-1001  | Failed to load terminal params | Contact PayNL support and share your logs                                                                                                    |
| SV-1002  | Failed to load payment params  | Contact PayNL support and share your logs                                                                                                    |
| SV-1003  | Failed to load keys            | Contact PayNL support and share your logs                                                                                                    |
| SV-1004  | Failed to derive keys          | Contact PayNL support and share your logs                                                                                                    |
| SV-1005  | Failed to encrypt data         | Contact PayNL support and share your logs                                                                                                    |
| SV-1006  | Failed to scan card            | The provided card is not supported. Please ask the consumer to pay using a different card                                                    |
| SV-1007  | Failed to capture PIN          | The user cancelled the PIN prompt or something went wrong during PIN capture. If the later, please contact PayNL Support and share your logs |

### iOS specific codes

| **Code** | **Description**                 | **What to do?**                                                                                                                        |
|----------|---------------------------------|----------------------------------------------------------------------------------------------------------------------------------------|
| SV-2002  | Failed to load token            | Make sure the SDK is initialized. Otherwise, contact PayNL support and share your logs                                                 |
| SV-2003  | Reader not initialized          | Make sure the SDK is initialized                                                                                                       |
| SV-2004  | Passcode required               | Prompt the user to setup a Passcode on the iPhone [docs](https://support.apple.com/nl-nl/guide/iphone/iph14a867ae/ios)                 |
| SV-2101  | Account not linked              | Make sure the SDK is initialized. Otherwise, contact PayNL support and share your logs                                                 |
| SV-2102  | Prepare failed                  | Contact PayNL support and share your logs                                                                                              |
| SV-2103  | Invalid token                   | Contact PayNL support and share your logs                                                                                              |
| SV-2104  | OS not supported                | Prompt the user to update their iPhone to the latest iOS version [docs](https://support.apple.com/nl-nl/guide/iphone/iph3e504502/ios)  |
| SV-2105  | Device banned                   | Prompt to user to use a different iPhone, since this device has been banned by Apple. Check logs to see how long this device is banned |
| SV-2106  | Not ready                       | Make sure the SDK is initialized                                                                                                       |
| SV-2107  | Reader busy                     | Make sure you use one instance of the PAY.POS SDK                                                                                      |
| SV-2108  | Prepare expired                 | Re-initialize the SDK                                                                                                                  |
| SV-2109  | Account already linked          | Contact PayNL support and share your logs                                                                                              |
| SV-2110  | Account linking failed          | Contact PayNL support and share your logs                                                                                              |
| SV-2111  | Account linking cancelled       | Re-initialize the SDK. User cancelled the Terms & Conditions from Apple                                                                |
| SV-2112  | Token expired                   | Re-initialize the SDK                                                                                                                  |
| SV-2113  | Unsupported action              | Contact PayNL support and share your logs                                                                                              |
| SV-2114  | Not allowed                     | Contact PayNL support and share your logs                                                                                              |
| SV-2115  | Background requests not allowed | You cannot use the SDK, while your app is in the background. Make sure it is on the foreground and try again                           |
| SV-2116  | Prepare in progress             | Please wait until SDK is initialized. If this is the case, contact PayNL support and share your logs                                   |
| SV-2117  | Prepare failed                  | Contact PayNL support and share your logs                                                                                              |
| SV-2118  | Pin not allowed                 | The device is unable to capture the PIN from a user. Consider using another device                                                     |
| SV-2119  | PIN Cancelled                   | The user cancelled the PIN capture -> Transaction is cancelled                                                                         |
