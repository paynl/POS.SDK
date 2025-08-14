<img src="https://www.pay.nl/uploads/1/brands/main_logo.png" width="100px" />

# PAY.POS SDK - Error codes NEW

> [!NOTICE]
> These are the new error codes. For the older SV-error, please review the bottom of this document

### Android softpos errors

| **PAY-error** | **Title**                                      | **Description**       |
|---------------|------------------------------------------------|-----------------------|
| PAY-700000    | UNKNOWN                                        | Contact PayNL support |
| PAY-700001    | FAILED_TO_LOAD_PUBLIC_KEY                      | Contact PayNL support |
| PAY-700002    | FAILED_TO_LOAD_AID_PARAM                       | Contact PayNL support |
| PAY-700003    | FAILED_TO_START_PAYMENT                        | Contact PayNL support |
| PAY-700004    | FAILED_TO_ENCRYPT_PAYLOAD                      | Contact PayNL support |
| PAY-700005    | FAILED_TO_PARSE_PAYMENT_START                  | Contact PayNL support |
| PAY-700006    | INVALID_PAYMENT_STATE                          | Contact PayNL support |
| PAY-700100    | UNKNOWN                                        | Contact PayNL support |
| PAY-700101    | MHD_SDK_NOT_AVAILABLE                          | Contact PayNL support |
| PAY-700102    | MHD_SDK_LICENSE_INVALID                        | Contact PayNL support |
| PAY-700103    | MHD_SDK_LICENSE_PATH_INVALID                   | Contact PayNL support |
| PAY-700104    | MHD_SDK_LICENSE_EXPIRED                        | Contact PayNL support |
| PAY-700105    | MHD_SDK_INIT_EMVFAIL                           | Contact PayNL support |
| PAY-700106    | MHD_SDK_INIT_STORAGEFAIL                       | Contact PayNL support |
| PAY-700107    | MHD_SDK_NOT_INITIALIZED                        | Contact PayNL support |
| PAY-700108    | MHD_SDK_JNI_OBJECT_NULL                        | Contact PayNL support |
| PAY-700109    | MHD_SDK_JNI_ARRAY_NULL                         | Contact PayNL support |
| PAY-700110    | MHD_SDK_JNI_METHOD_NOTEXIST                    | Contact PayNL support |
| PAY-700111    | MHD_SDK_JNI_CLASS_NOTEXIST                     | Contact PayNL support |
| PAY-700112    | MHD_SDK_JNI_CLASS_EXCEPTION                    | Contact PayNL support |
| PAY-700113    | MHD_GENERAL_ERROR                              | Contact PayNL support |
| PAY-700114    | MHD_SDK_INVALID_PARAMETER                      | Contact PayNL support |
| PAY-700115    | MHD_SDK_LICENSE_FAILEDOPEN                     | Contact PayNL support |
| PAY-700116    | MHD_STORAGE_ERR_KEY_NOTFOUND                   | Contact PayNL support |
| PAY-700117    | MHD_STORAGE_ERR_DEIVCE_UNMATCH                 | Contact PayNL support |
| PAY-700200    | UNKNOWN                                        | Contact PayNL support |
| PAY-700201    | SERVER_INTERNAL_ERROR                          | Contact PayNL support |
| PAY-700202    | SERVER_INTERNAL_ERROR_WITH_DETAILS             | Contact PayNL support |
| PAY-700203    | PARAMETER_ERROR                                | Contact PayNL support |
| PAY-700204    | TOKEN_SDK_ID_NOT_DETECTED                      | Contact PayNL support |
| PAY-700205    | TOKEN_SDK_ID_NOT_MATCH                         | Contact PayNL support |
| PAY-700206    | SDK_ID_NOT_DETECTED                            | Contact PayNL support |
| PAY-700207    | LICENSE_ID_NOT_DETECTED                        | Contact PayNL support |
| PAY-700208    | CUSTOMER_ID_NOT_DETECTED                       | Contact PayNL support |
| PAY-700209    | CHALLENGE_ID_NOT_DETECTED                      | Contact PayNL support |
| PAY-700210    | REGISTRATION_SIGN_NOT_MATCH                    | Contact PayNL support |
| PAY-700211    | ATTESTATION_DATA_ENCRYPTION_DECRYPTION_FAILED  | Contact PayNL support |
| PAY-700212    | ATTESTATION_DATA_ENCRYPTION_RESULT_FAILED      | Contact PayNL support |
| PAY-700213    | ATTESTATION_DATA_CHECK_FAILED                  | Contact PayNL support |
| PAY-700214    | ADD_OR_UPDATE_USER_NOT_DETECTED                | Contact PayNL support |
| PAY-700215    | APPLICATION_PACKAGE_NAME_NOT_DETECTED          | Contact PayNL support |
| PAY-700216    | AUTH_INCORRECT                                 | Contact PayNL support |
| PAY-700217    | TOKEN_UNAUTHORIZED                             | Contact PayNL support |
| PAY-700218    | ATTESTATION_ADB_ENABLED                        | Contact PayNL support |
| PAY-700219    | ATTESTATION_ANDROID_ID_NOT_MATCH               | Contact PayNL support |
| PAY-700220    | ATTESTATION_MANDATORY_PERMISSIONS_NOT_GRANTED  | Contact PayNL support |
| PAY-700221    | ATTESTATION_APP_VERSION_CODE_TOO_LOW           | Contact PayNL support |
| PAY-700222    | ATTESTATION_MAGISK_HIDE_DETECTED               | Contact PayNL support |
| PAY-700223    | ATTESTATION_DEVICE_MODEL_BLACKLISTED           | Contact PayNL support |
| PAY-700224    | ATTESTATION_DEBUG_BUILD                        | Contact PayNL support |
| PAY-700225    | ATTESTATION_UNDER_HOOKING                      | Contact PayNL support |
| PAY-700226    | ATTESTATION_CONNECTS_WITH_DEBUGGER             | Contact PayNL support |
| PAY-700227    | ATTESTATION_MALICIOUS_NFC_APP                  | Contact PayNL support |
| PAY-700228    | ATTESTATION_DEVICE_ROOT                        | Contact PayNL support |
| PAY-700229    | ATTESTATION_SYSTEM_DEBUGGABLE_FLAG             | Contact PayNL support |
| PAY-700230    | ATTESTATION_TIMESTAMP_EXPIRED                  | Contact PayNL support |
| PAY-700231    | ATTESTATION_TRACING_DETECTED                   | Contact PayNL support |
| PAY-700232    | ATTESTATION_SDK_BLOCKED                        | Contact PayNL support |
| PAY-700233    | ATTESTATION_SDK_REVOKED                        | Contact PayNL support |
| PAY-700234    | ATTESTATION_DEVICE_EMULATOR                    | Contact PayNL support |
| PAY-700235    | ATTESTATION_OS_VERSION_ROLLBACK                | Contact PayNL support |
| PAY-700236    | ATTESTATION_APP_VERSION_ROLLBACK               | Contact PayNL support |
| PAY-700237    | ATTESTATION_SDK_VERSION_ROLLBACK               | Contact PayNL support |
| PAY-700238    | ATTESTATION_KEY_INVALID_KEY_EXCEPTION          | Contact PayNL support |
| PAY-700239    | ATTESTATION_KEY_PARSE_CERT_ERROR               | Contact PayNL support |
| PAY-700240    | ATTESTATION_KEY_CERT_REVOKED                   | Contact PayNL support |
| PAY-700241    | ATTESTATION_KEY_ROOT_CERT_NOT_VALIDATED        | Contact PayNL support |
| PAY-700242    | ATTESTATION_KEY_CERT_INVALID                   | Contact PayNL support |
| PAY-700243    | ATTESTATION_KEY_CERT_CHAIN_NOT_VERIFIED        | Contact PayNL support |
| PAY-700244    | ATTESTATION_KEY_BOOTLOADER_UNLOCKED            | Contact PayNL support |
| PAY-700245    | ATTESTATION_KEY_BOOTLOADER_UNVERIFIED          | Contact PayNL support |
| PAY-700246    | ATTESTATION_KEY_SECURITY_LEVEL_SOFTWARE        | Contact PayNL support |
| PAY-700247    | ATTESTATION_ANDROID_SECURITY_PATCH_TOO_LOW     | Contact PayNL support |
| PAY-700248    | ATTESTATION_KEY_INVALID_APP_PACKAGE_NAME       | Contact PayNL support |
| PAY-700249    | ATTESTATION_KEY_INVALID_SIGNATURE_DIGEST       | Contact PayNL support |
| PAY-700250    | ATTESTATION_KEY_APP_VERSION_TOO_LOW            | Contact PayNL support |
| PAY-700251    | ATTESTATION_KEY_CHALLENGE_NOT_MATCH            | Contact PayNL support |
| PAY-700252    | INTEGRITY_TOKEN_CHALLENGE_NOT_MATCH            | Contact PayNL support |
| PAY-700253    | INTEGRITY_TOKEN_APK_PACKAGE_NAME_NOT_MATCH     | Contact PayNL support |
| PAY-700254    | INTEGRITY_TOKEN_SIGNATURE_NOT_MATCH            | Contact PayNL support |
| PAY-700255    | INTEGRITY_TOKEN_NOT_MEETS_BASIC                | Contact PayNL support |
| PAY-700256    | INTEGRITY_TOKEN_NOT_MEETS_DEVICE               | Contact PayNL support |
| PAY-700257    | INTEGRITY_TOKEN_FAILED_PARSE_VERIFY            | Contact PayNL support |
| PAY-700258    | ATTESTATION_NO_LIGHT_SENSOR                    | Contact PayNL support |
| PAY-700259    | ATTESTATION_SIDE_LOADED_DETECTED               | Contact PayNL support |
| PAY-700260    | ATTESTATION_SECURITY_PATCH_TOO_LOW             | Contact PayNL support |
| PAY-700261    | ATTESTATION_GOOGLE_PLAY_SERVICES_NOT_AVAILABLE | Contact PayNL support |
| PAY-700262    | ATTESTATION_API_LEVEL_TOO_LOW                  | Contact PayNL support |
| PAY-700263    | ATTESTATION_NO_HARDWARE_KEYSTORE               | Contact PayNL support |
| PAY-700264    | ATTESTATION_GOOGLE_PLAY_VERSION_TOO_LOW        | Contact PayNL support |
| PAY-700265    | BASELINE_INFO_NO_NFC                           | Contact PayNL support |
| PAY-700266    | BASELINE_INFO_NFC_OFF                          | Contact PayNL support |
| PAY-700267    | ATTESTATION_DATA_CHECK_FAILED_WITH_DETAILS     | Contact PayNL support |
| PAY-700268    | ATTESTATION_CHECK_FAILED                       | Contact PayNL support |

### Sunmi Hardpos errors

| **PAY-error** | **Title**                                               | **Description**                                        |
|---------------|---------------------------------------------------------|--------------------------------------------------------|
| PAY-710006    | FAILED_KEYLOADING                                       | Try again later or send logs and contact PayNL support |
| PAY-710100    | TRANSACTION_REFUSE                                      | Transaction was refused, please try another card       |
| PAY-710101    | PLEASE_USE_ANOTHER_INTERFACE                            | Please use another payment interface                   |
| PAY-710102    | TRANSACTION_TERMINATION                                 | Transaction has been terminated                        |
| PAY-710103    | SEE_PHONE                                               | Please check your mobile device                        |
| PAY-710104    | FINAL_SELECT_DATA_ERROR                                 | Card selection data error                              |
| PAY-710105    | USE_ANOTHER_CARD                                        | Please use a different card                            |
| PAY-710106    | TRANSACTION_TERMINATION_COMMAND_SENDING_ERROR           | Transaction terminated due to communication error      |
| PAY-710107    | TRANSACTION_TERMINATION_COMMAND_RECEIVING_TIMEOUT_1     | Transaction timeout occurred                           |
| PAY-710108    | TRANSACTION_TERMINATION_COMMAND_RECEIVING_TIMEOUT_2     | Transaction timeout occurred                           |
| PAY-710109    | TRANSACTION_TERMINATED_STATUS_CODE_ERROR                | Invalid card response received                         |
| PAY-710110    | TRANSACTION_TERMINATION_CARD_LOCKED                     | Card is locked. Try other card                         |
| PAY-710111    | TRANSACTION_TERMINATED_APPLICATION_LOCKED               | Payment application is locked                          |
| PAY-710112    | TRANSACTION_TERMINATION_NO_APPLICATION_ON_TERMINAL      | No payment application found                           |
| PAY-710113    | TRANSACTION_TERMINATION_NO_COMMON_APPLICATION           | No compatible payment method found                     |
| PAY-710114    | TRANSACTION_TERMINATED_CARD_RETURN_DATA_ERROR           | Card data validation failed                            |
| PAY-710115    | TRANSACTION_TERMINATION_DUPLICATE_DATA_ELEMENT          | Duplicate card data detected                           |
| PAY-710116    | TRANSACTION_TERMINATION_TRANSACTION_NOT_ACCEPTED        | Transaction not accepted                               |
| PAY-710117    | TRANSACTION_TERMINATION_CARD_EXPIRED                    | Card has expired                                       |
| PAY-710118    | PREPROCESSING_PARAMETERS_LIST_EMPTY                     | Payment parameters missing                             |
| PAY-710119    | COMMUNICATION_TIMEOUT_APDU_COMMAND                      | Card communication timeout                             |
| PAY-710120    | TRANSACTION_TERMINATION_L1_TRANSMISSION_ERROR           | Card transmission error                                |
| PAY-710121    | ERROR_IN_APDU_COMMAND                                   | Invalid payment command                                |
| PAY-710122    | TRANSACTION_TERMINATION_L2_MANDATORY_DATA_ERROR         | Missing required payment data                          |
| PAY-710123    | TRANSACTION_TERMINATION_L2_OFFLINE_AUTH_FAILED          | Card authentication failed                             |
| PAY-710124    | TRANSACTION_TERMINATION_L2_STATUS_WORD_ERROR            | Invalid card response                                  |
| PAY-710125    | TRANSACTION_TERMINATION_L2_DATA_PARSING_FAILED          | Payment data parsing failed                            |
| PAY-710126    | TRANSACTION_TERMINATION_L2_AMOUNT_EXCEEDS_LIMIT         | Transaction amount too high                            |
| PAY-710127    | TRANSACTION_TERMINATION_L2_CARD_DATA_ERROR              | Invalid card data                                      |
| PAY-710128    | MAG_NOT_SUPPORT_PAYPASS                                 | Magnetic stripe not supported                          |
| PAY-710129    | TRANSACTION_TERMINATION_L2_CARD_WITHOUT_PPSE            | Card configuration error                               |
| PAY-710130    | TRANSACTION_TERMINATION_L2_PPSE_PROCESSING_ERROR        | Card configuration error                               |
| PAY-710131    | CANDIDATE_LIST_IS_EMPTY                                 | No payment methods available                           |
| PAY-710132    | TRANSACTION_TERMINATION_L2_IDS_READ_ERROR               | Payment configuration read error                       |
| PAY-710133    | TRANSACTION_TERMINATION_L2_IDS_WRITE_ERROR              | Payment configuration write error                      |
| PAY-710134    | TRANSACTION_TERMINATION_L2_IDS_DATA_ERROR               | Payment configuration data error                       |
| PAY-710135    | TRANSACTION_TERMINATION_L2_IDS_NO_MATCHING_AC           | Payment authorization failed                           |
| PAY-710136    | TRANSACTION_TERMINATION_L2_TERMINAL_DATA_ERROR          | Terminal configuration error                           |
| PAY-710137    | L3_TIMEOUT                                              | Transaction timeout                                    |
| PAY-710138    | TRANSACTION_TERMINATION_L3_CANCEL                       | Transaction cancelled                                  |
| PAY-710139    | TRANSACTION_TERMINATION_L3_AMOUNT_DOES_NOT_EXIST        | Missing transaction amount                             |
| PAY-710140    | TRANSACTION_TERMINATION_REPRESENTATION_OF_CARD          | Card re-presentation required                          |
| PAY-710141    | TRANSACTION_TERMINATION_USING_OTHER_CARDS_WITH_RECORD   | Alternative card required                              |
| PAY-710142    | TRANSACTION_TERMINATION_USING_OTHER_CARDS               | Alternative card required                              |
| PAY-710143    | TRANSACTION_TERMINATION_GPO_RESPONSE_ERROR              | Card initialization error                              |
| PAY-710144    | TRANSACTION_TERMINATION_FINAL_SELECTION_CARD_DATA_ERROR | Card selection failed                                  |
| PAY-710145    | TRANSACTION_TERMINATION_L3_NO_DET_DATA                  | Missing transaction data                               |
| PAY-710146    | KERNEL_NOT_SUPPORT                                      | Unsupported payment method                             |
| PAY-710147    | AMOUNT_EXCEEDS_READER_CONTACTLESS_LIMIT                 | Transaction amount too high                            |
| PAY-710148    | AMOUNT_IS_ZERO                                          | Invalid transaction amount                             |
| PAY-710149    | INSERT_SWIPE_OR_TRY_ANOTHER_CARD                        | Alternative payment method required                    |
| PAY-710150    | INVALID_PARAMETER                                       | Invalid payment parameters                             |
| PAY-710151    | CHECK_CODE_ERROR_DOWNLOAD_PUBLIC_KEY                    | Security verification failed                           |
| PAY-710152    | TERMINAL_PARAMETERS_DATA_DOES_NOT_EXIST                 | Missing terminal configuration                         |
| PAY-710153    | TERMINAL_PARAMETERS_DATA_ERROR                          | Invalid terminal configuration                         |
| PAY-710154    | TRANSACTION_LOG_DOES_NOT_EXIST                          | Missing transaction records                            |
| PAY-710155    | TRANSACTION_LOG_DATA_ERROR                              | Invalid transaction records                            |
| PAY-710156    | EMV_DATA_DOES_NOT_EXIST                                 | Missing EMV payment data                               |
| PAY-710157    | KEY_LENGTH_ERROR                                        | Invalid encryption key length                          |
| PAY-710158    | THE_CHECK_VALUE_ERROR                                   | Security validation failed                             |
| PAY-710159    | STORE_KEY_FAIL                                          | Key storage failed                                     |
| PAY-710160    | CALCULATE_MAC_ERROR                                     | Security calculation failed                            |
| PAY-710161    | ENCRYPTION_ERROR                                        | Payment encryption failed                              |
| PAY-710162    | RETURN_ARRAY_DATA_LENGTH_ERROR                          | Invalid response data length                           |
| PAY-710163    | THE_MAC_ALGORITHM_TYPE_NOT_SUPPORT                      | Unsupported security algorithm                         |
| PAY-710164    | LENGTH_OF_CHECK_VALUE_ERROR                             | Invalid security check length                          |
| PAY-710165    | KEY_INDEX_ERROR                                         | Invalid key reference                                  |
| PAY-710166    | DECRYPT_ERROR                                           | Payment decryption failed                              |
| PAY-710167    | LENGTH_OF_KEY_ERROR                                     | Invalid key length                                     |
| PAY-710168    | GET_RANDOM_ERROR                                        | Random number generation failed                        |
| PAY-710169    | KEY_DOES_NOT_EXIST                                      | Missing encryption key                                 |
| PAY-710170    | VERIFY_SIGNATURE_FAIL                                   | Digital signature invalid                              |
| PAY-710171    | FAILED_TO_GET_ALARM_INFORMATION_CODE                    | System alert retrieval failed                          |
| PAY-710172    | KEY_PARTITION_HAS_RUN_OUT                               | Key storage full                                       |
| PAY-710173    | INJECT_BDK_ERROR                                        | Key injection failed                                   |
| PAY-710174    | TRANSFORMATION_NOT_SUPPORTED                            | Unsupported data transformation                        |
| PAY-710175    | KEY_NOT_SAVED                                           | Key persistence failed                                 |
| PAY-710176    | TRANSACTION_PREPROCESS_FAIL                             | Payment preparation failed                             |
| PAY-710177    | TRANSACTION_PROCESS_FAIL                                | Payment processing failed                              |
| PAY-710178    | EMV_KERNEL_PROCESS_FAIL                                 | EMV payment processing failed                          |
| PAY-710179    | PAN_FORMAT_ERROR                                        | Invalid card number format                             |
| PAY-710180    | CALL_PINPAD_FAIL                                        | PIN entry device failed                                |
| PAY-710181    | NONE_KERNEL_DATA                                        | Missing payment kernel data                            |
| PAY-710182    | PINPAD_PARAMETER_ERROR                                  | Invalid PIN device parameters                          |
| PAY-710183    | EMV_PROCESS_NOT_FINISH                                  | Incomplete payment process                             |
| PAY-710184    | THE_TRANSACTION_TYPE_NOT_SUPPORT                        | Unsupported transaction type                           |
| PAY-710185    | CHECKING_CARD_INFORMATION_FAIL_OR_TIMEOUT               | Card validation timeout                                |
| PAY-710186    | CVM_ERROR                                               | Card verification failed                               |
| PAY-710187    | DATABASE_OPERATION_FAIL                                 | Payment database error                                 |
| PAY-710188    | NO_MATCHING_CAPK                                        | Missing certificate authority                          |
| PAY-710189    | SAVE_TERMINAL_PARAMETER_ERROR                           | Terminal configuration save failed                     |
| PAY-710190    | NO_MATCHING_AID                                         | No compatible payment application                      |
| PAY-710191    | CHECK_CARD_FAIL                                         | Card validation failed                                 |
| PAY-710192    | CALL_INTERFACE_ORDER_ERROR                              | Invalid payment sequence                               |
| PAY-710193    | TRANSACTION_DATA_INVALID                                | Invalid payment data                                   |
| PAY-710194    | PIN_ENTRY_CANCEL                                        | PIN entry cancelled                                    |
| PAY-710195    | PIN_ENTRY_ERROR                                         | PIN entry failed                                       |
| PAY-710196    | THE_INDEX_OF_APP_SELECT_ERROR                           | Invalid application selection                          |
| PAY-710197    | CERT_VERIFY_ERROR                                       | Certificate verification failed                        |
| PAY-710198    | ONLINE_PROCESS_ERROR                                    | Online processing failed                               |
| PAY-710199    | APP_FINAL_SELECT_TIMEOUT                                | Application selection timeout                          |
| PAY-710200    | APP_FINAL_SELECT_ERROR                                  | Application selection failed                           |
| PAY-710201    | SIGNATURE_ERROR                                         | Signature verification failed                          |
| PAY-710202    | UNKNOWN_CVM_TYPE                                        | Unsupported verification method                        |
| PAY-710203    | DATA_EXCHANGE_ERROR                                     | Payment data transfer failed                           |
| PAY-710204    | DATA_EXCHANGE_TIMEOUT                                   | Payment data transfer timeout                          |
| PAY-710205    | TERMINAL_RISK_MANAGEMENT_TIMEOUT                        | Risk assessment timeout                                |
| PAY-710206    | TERMINAL_RISK_MANAGEMENT_ERROR                          | Risk assessment failed                                 |
| PAY-710207    | PRE_FIRST_GAC_CALLED_TIMEOUT                            | Payment authentication timeout                         |
| PAY-710208    | PRE_FIRST_GAC_CALLED_ERROR                              | Payment authentication failed                          |
| PAY-710209    | INPUT_PIN_TIMEOUT                                       | PIN entry timeout                                      |
| PAY-710210    | KEYBOARD_FAILED_TO_ACTIVATE_PASSWORD                    | PIN pad activation failed                              |
| PAY-710211    | PINPADTYPE_TYPE_ERROR                                   | Invalid PIN device type                                |
| PAY-710212    | GETTING_PINBLOCK_FAILED                                 | PIN encryption failed                                  |
| PAY-710213    | PIN_STATUS_QUERY_THREAD_IS_INTERRUPTED                  | PIN process interrupted                                |
| PAY-710214    | MISS_PERMISSION_MSR                                     | Missing magnetic stripe permission                     |
| PAY-710215    | MISS_PERMISSION_ICC                                     | Missing chip card permission                           |
| PAY-710216    | MISS_PERMISSION_CONTACTLESS_CARD                        | Missing contactless permission                         |
| PAY-710217    | MISS_PERMISSION_PINPAD                                  | Missing PIN pad permission                             |
| PAY-710218    | MISS_PERMISSION_SECURITY                                | Missing security permission                            |
| PAY-710219    | MISS_PERMISSION_LED                                     | Missing LED indicator permission                       |
| PAY-710220    | UNKNOWN_KERNEL_ERROR                                    | Unknown payment processing error                       |
| PAY-710500    | NETWORK_ERROR                                           | Check your internet settings or try again later        |
| PAY-710501    | DEVICE_CERT_INVALID_BIND                                | Contact PayNL support                                  |
| PAY-710502    | SERVER_CERT_INVALID_BIND                                | Contact PayNL support                                  |
| PAY-710503    | SERVER_CERT_SIGN_VERIFY_FAILED_BIND                     | Contact PayNL support                                  |
| PAY-710504    | CRL_INVALID_BIND                                        | Contact PayNL support                                  |
| PAY-710505    | CRL_SIGN_VERIFY_FAILED_BIND                             | Contact PayNL support                                  |
| PAY-710506    | SERVER_CERT_CRL_BIND                                    | Contact PayNL support                                  |
| PAY-710507    | SERVER_CERT_INVALID_SYNC                                | Contact PayNL support                                  |
| PAY-710508    | SERVER_SIGN_VERIFY_FAILED_SYNC                          | Contact PayNL support                                  |
| PAY-710509    | DELETE_KEY_FAILED                                       | Retry keyloading or contact PayNL support              |
| PAY-710510    | SAVE_KEY_FAILED                                         | Contact PayNL support                                  |
| PAY-710511    | DOWNLOAD_CANCEL                                         | Key inject has been cancelled, try again later         |
| PAY-710512    | DOWNLOAD_CANCEL_AUTO                                    | Key inject has been cancelled, try again later         |
| PAY-710513    | SAVE_KEY_FAILED_SITUATION                               | Contact PayNL support                                  |
| PAY-710514    | KEY_NEGOTIATE_FAILED                                    | Contact PayNL support                                  |
| PAY-710515    | SERVER_SIGN_FAILED_DOWN                                 | Contact PayNL support                                  |
| PAY-710516    | REBIND_CHECK_FAILED                                     | Contact PayNL support                                  |
| PAY-710517    | DEVICE_CERT_INVALID_UNBIND                              | Contact PayNL support                                  |
| PAY-710518    | SERVER_CERT_INVALID_UNBIND                              | Contact PayNL support                                  |
| PAY-710519    | DEVICE_TOKEN_ERROR_UNBIND                               | Contact PayNL support                                  |
| PAY-710520    | SERVER_CERT_SIGN_VERIFY_FAILED_UNBIND                   | Contact PayNL support                                  |
| PAY-710521    | SERVER_SIGN_VERIFY_FAILED_UNBIND                        | Contact PayNL support                                  |
| PAY-710522    | CRL_INVALID_UNBIND                                      | Contact PayNL support                                  |
| PAY-710523    | CRL_SIGN_VERIFY_FAILED_UNBIND                           | Contact PayNL support                                  |
| PAY-710524    | DEVICE_TOKEN_ERROR_VALID                                | Contact PayNL support                                  |
| PAY-710525    | SERVER_SIGN_FAILED_VALID                                | Contact PayNL support                                  |
| PAY-710526    | SERVER_CERT_INVALID_VALID                               | Contact PayNL support                                  |
| PAY-710527    | SERVER_CERT_SIGN_FAILED_VALID                           | Contact PayNL support                                  |
| PAY-710528    | CRL_INVALID_VALID                                       | Contact PayNL support                                  |
| PAY-710529    | CRL_SIGN_VERIFY_FAILED_VALID                            | Contact PayNL support                                  |
| PAY-710530    | DOWNLOAD_KEY_FAILED                                     | Try again later or contact PayNL support               |
| PAY-710531    | KEY_NOT_EXIST                                           | Restart PAY.POS app or contact PayNL support           |
| PAY-710532    | KEY_INDEX_INCORRECT                                     | Contact PayNL support                                  |
| PAY-710533    | KEY_NOT_ASSIGNED                                        | Contact PayNL support                                  |
| PAY-710534    | DEV_CERT_ERROR                                          | Contact PayNL support                                  |
| PAY-710535    | CONNECT_L1_FAILED                                       | Check your internet settings or try again later        |
| PAY-710536    | DEVICE_CERT_VERIFY_INVALID                              | Contact PayNL support                                  |
| PAY-710537    | CONNECT_TMC_FAILED                                      | Check your internet settings or try again later        |
| PAY-710538    | SDK_VERSION_LOW                                         | Contact PayNL support                                  |
| PAY-710539    | DEVICE_LOCKED                                           | Contact PayNL support                                  |
| PAY-710540    | DEVICE_NOT_FOUND                                        | Contact PayNL support                                  |
| PAY-710541    | RKI_VERSION_TOO_LOW                                     | Contact PayNL support                                  |

### iOS errors

| **PAY-error** | **Title**                      | **Description**                                                            |
|---------------|--------------------------------|----------------------------------------------------------------------------|
| PAY-720000    | UNEXPECTED_ERROR               | Send logs and contact PayNL support                                        |
| PAY-720001    | NO_ACTIVATION_DATE_AVAILABLE   | Try again later or send logs and contact PayNL support                     |
| PAY-720002    | KEY_LOADING_FAILED             | Try again later or send logs and contact PayNL support                     |
| PAY-720003    | PAYMENT_FAILED                 | Try again later or send logs and contact PayNL support                     |
| PAY-720004    | PAYMENT_ERROR                  | Try again later or send logs and contact PayNL support                     |
| PAY-720005    | PAYMENT_CANCELLED              | Transaction has been cancelled by customer. Start new transaction          |
| PAY-720006    | GETTING_PAYMENT_BLOB_FAILED    | Try again later or send logs and contact PayNL support                     |
| PAY-720007    | PASSCODE_REQUIRED              | iPhone needs to have a passcode before it can support Tap to Pay on iPhone |
| PAY-722003    | READER_NOT_INITIALIZED         | Try again later or send logs and contact PayNL support                     |
| PAY-722002    | TOKEN_NOT_LOADED               | Try again later or send logs and contact PayNL support                     |
| PAY-722101    | ACCOUNT_NOT_LINKED             | Try again later or send logs and contact PayNL support                     |
| PAY-722102    | PREPARE_FAILED                 | Try again later or send logs and contact PayNL support                     |
| PAY-722103    | INVALID_READER_TOKEN           | Try again later or send logs and contact PayNL support                     |
| PAY-722104    | OS_VERSION_NOT_SUPPORTED       | Update your iOS version                                                    |
| PAY-722105    | DEVICE_BANNED                  | Your device has been banned from using Tap to Pay on iPhone by Apple       |
| PAY-722106    | NOT_READY                      | Try again later or send logs and contact PayNL support                     |
| PAY-722107    | READER_BUSY                    | Try again later or send logs and contact PayNL support                     |
| PAY-722108    | PREPARE_EXPIRED                | Try again later or send logs and contact PayNL support                     |
| PAY-722109    | ACCOUNT_ALREADY_LINKED         | Try again later or send logs and contact PayNL support                     |
| PAY-722110    | ACCOUNT_LINKING_FAILED         | Try again later or send logs and contact PayNL support                     |
| PAY-722111    | ACCOUNT_LINKING_CANCELLED      | Try again later or send logs and contact PayNL support                     |
| PAY-722112    | TOKEN_EXPIRED                  | Try again later or send logs and contact PayNL support                     |
| PAY-722113    | UNSUPPORTED                    | Try again later or send logs and contact PayNL support                     |
| PAY-722114    | NOT_ALLOWED                    | Try again later or send logs and contact PayNL support                     |
| PAY-722115    | BACKGROUND_REQUEST_NOT_ALLOWED | Try again later or send logs and contact PayNL support                     |
| PAY-722116    | PREPARE_IN_PROGRESS            | Try again later or send logs and contact PayNL support                     |
| PAY-722117    | SESSION_PREPARATION_FAILED     | Try again later or send logs and contact PayNL support                     |
| PAY-722118    | PIN_NOT_ALLOWED                | Try again later or send logs and contact PayNL support                     |
| PAY-722119    | PIN_INPUT_CANCELLED            | Pin input has been cancelled by customer. Start new transaction            |

# PAX hardpos errors

| **PAY-error** | **Title**                     | **Description**       |
|---------------|-------------------------------|-----------------------|
| PAY-730001    | FAILED_TO_LOAD_PUBLIC_KEY     | Contact PayNL support |
| PAY-730002    | FAILED_TO_LOAD_EMV_PARAM      | Contact PayNL support |
| PAY-730003    | FAILED_TO_START_PAYMENT       | Contact PayNL support |
| PAY-730004    | FAILED_TO_ENCRYPT_PAYLOAD     | Contact PayNL support |
| PAY-730005    | FAILED_TO_PARSE_PAYMENT_START | Contact PayNL support |
| PAY-730006    | FAILED_KEYLOADING             | Contact PayNL support |
| PAY-730007    | FAILED_TO_START_KERNEL        | Contact PayNL support |
| PAY-730008    | INVALID_PAYMENT_STATE         | Contact PayNL support |
| PAY-730010    | NO_ONGOING_PAYMENTS           | Contact PayNL support |
| PAY-730011    | DUPLICATED_PAYMENTS           | Contact PayNL support |

# PAY.POS SDK - Error codes OLD

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
| SV-1004  | Failed to derive keys          | Contact PayNL support and share your logs -> Most likely issue: initSDK has not been called after login                                      |
| SV-1005  | Failed to encrypt data         | Contact PayNL support and share your logs                                                                                                    |
| SV-1006  | Failed to scan card            | The provided card is not supported. Please ask the consumer to pay using a different card                                                    |
| SV-1007  | Failed to capture PIN          | The user cancelled the PIN prompt or something went wrong during PIN capture. If the later, please contact PayNL Support and share your logs |
| SV-1008  | Configuration is missing       | The SDK did not find a valid configuration. Consider using `setConfiguration` or the full constructor                                        |

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
