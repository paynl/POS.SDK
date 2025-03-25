import React, {useEffect, useRef, useState} from 'react';
import {Platform, StyleSheet, Text, TouchableOpacity, View,} from 'react-native';
import {BottomSheetModal, BottomSheetModalProvider, BottomSheetView} from "@gorhom/bottom-sheet";
import {GestureHandlerRootView} from "react-native-gesture-handler";
import {PayNlSdk} from '@paynl/pos-sdk-react-native'
import {A_CODE, SL_CODE, SL_SECRET} from "@env";
import PaymentButton from "./PaymentButton";
import TapToPayOnIphone from './assets/ttpoi.svg';
import CheckCircle from './assets/check-circle.svg';
import XCircle from './assets/x-circle.svg';
import {currencyResolver} from "./currencyResolver";
import {Backdrop} from "./components/Backdrop";
import {useSharedValue} from "react-native-reanimated";

function App(): React.JSX.Element {
    const transactionResultBottomSheet = useRef<BottomSheetModal | null>(null)

    const [isReady, setIsReady] = useState(false);
    const [amount, setAmount] = useState(0);
    const [currency, setCurrency] = useState('EUR');
    const [transactionResult, setTransactionResult] = useState({approved: false, ticket: '', payerMessage: ''});

    const sharedAnimation = useSharedValue(-1);

    useEffect(() => {
        initPaySDK().then(x => {
            setIsReady(x);
        });
    }, []);

    const addValue = (value: number) => {
        let amountStr = amount.toString(10);

        switch (value) {
            case 10:
                amountStr = amountStr.substring(0, amountStr.length - 2);
                if (amountStr === '') {
                    amountStr = '0';
                }
                break;
            case 11:
                amountStr += '0';
                break;
            case 12:
                amountStr += Platform.select({ios: '00', default: '0'});
                break;
            default:
                amountStr += value.toString(10);
                break;
        }

        setAmount(parseInt(amountStr, 10));
    }

    const startPayment = async () => {
        try {
            const result = await PayNlSdk.startTransaction({
                transaction: {amount: {value: amount, currency}},
                forService: undefined
            });

            let ticket = result.ticket;
            if (ticket != '') {
                const buff = new Buffer(ticket, 'base64');
                ticket = buff.toString('ascii');
            }

            setTransactionResult({
                approved: result.statusAction === 'PAID',
                payerMessage: result.payerMessage,
                ticket: ticket
            });
            setAmount(0);
            transactionResultBottomSheet.current?.present();
        } catch (e) {
            setTransactionResult({
                approved: false,
                payerMessage: `${e}`,
                ticket: ''
            });
            setAmount(0);
            transactionResultBottomSheet.current?.present();
        }
    }

    return (
        <GestureHandlerRootView style={{flex: 1}}>
            <BottomSheetModalProvider>
                <View style={{flex: 1}}>
                    <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
                        <Text style={styles.amountText}>{currencyResolver(currency)}{(amount / 100).toFixed(2)}</Text>
                    </View>
                    <View style={styles.bottomHalf}>
                        <View style={styles.row}>
                            <PaymentButton disabled={amount >= 999999} value={1} onPress={(x) => addValue(x)}/>
                            <PaymentButton disabled={amount >= 999999} value={2} onPress={(x) => addValue(x)}/>
                            <PaymentButton disabled={amount >= 999999} value={3} onPress={(x) => addValue(x)}/>
                        </View>
                        <View style={styles.row}>
                            <PaymentButton disabled={amount >= 999999} value={4} onPress={(x) => addValue(x)}/>
                            <PaymentButton disabled={amount >= 999999} value={5} onPress={(x) => addValue(x)}/>
                            <PaymentButton disabled={amount >= 999999} value={6} onPress={(x) => addValue(x)}/>
                        </View>
                        <View style={styles.row}>
                            <PaymentButton disabled={amount >= 999999} value={7} onPress={(x) => addValue(x)}/>
                            <PaymentButton disabled={amount >= 999999} value={8} onPress={(x) => addValue(x)}/>
                            <PaymentButton disabled={amount >= 999999} value={9} onPress={(x) => addValue(x)}/>
                        </View>
                        <View style={styles.row}>
                            <PaymentButton disabled={amount === 0} value={10} onPress={(x) => addValue(x)}/>
                            <PaymentButton disabled={amount >= 999999} value={11} onPress={(x) => addValue(x)}/>
                            {Platform.OS === 'ios' ? (<PaymentButton disabled={amount >= 999999} value={12}
                                                                     onPress={(x) => addValue(x)}/>) : (
                                <PaymentButton disabled={amount === 0} value={12} onPress={() => startPayment()}/>)}
                        </View>
                        {Platform.OS === 'ios' && (
                            <TouchableOpacity style={[styles.ttpoi, (!isReady || amount === 0) && styles.ttpoiDisabled]}
                                              disabled={!isReady} onPress={() => startPayment()}>
                                <TapToPayOnIphone width={26} height={26}/>
                                <Text style={styles.ttpoiText}>Tap to Pay on iPhone</Text>
                            </TouchableOpacity>)}
                    </View>
                </View>
                <BottomSheetModal
                    ref={transactionResultBottomSheet}
                    enableDynamicSizing
                    enablePanDownToClose
                    backdropComponent={() => Backdrop({
                        onTouch: () => transactionResultBottomSheet.current?.close(),
                        animationProgress: sharedAnimation,
                    })}
                    animatedIndex={sharedAnimation}
                >
                    <BottomSheetView style={{
                        paddingBottom: 30,
                        paddingTop: 10,
                        paddingHorizontal: 25,
                        alignItems: 'center',
                        gap: 20
                    }}>
                        {transactionResult.approved ? <SuccessIcon/> : <ErrorIcon/>}
                        <Text>{transactionResult.payerMessage}</Text>
                        {transactionResult.ticket && <Text style={styles.ticketText}>{transactionResult.ticket}</Text>}
                    </BottomSheetView>
                </BottomSheetModal>
            </BottomSheetModalProvider>
        </GestureHandlerRootView>
    );
}

const SuccessIcon = () => {
    return (
        <View style={[styles.iconBase, {backgroundColor: '#00D38833'}]}>
            <CheckCircle width={64} height={64} color="#00D388"/>
        </View>
    );
};

const ErrorIcon = () => {
    return (
        <View style={[styles.iconBase, {backgroundColor: 'FF000033'}]}>
            <XCircle width={64} height={64} color="#FF0000"/>
        </View>
    );
};

async function initPaySDK(): Promise<boolean> {
    console.log('⌛ Initializing SDK')
    try {
        const result = await PayNlSdk.initSdk();
        if (result === 'needs_login') {
            return await loginSDK();
        }

        console.log('✅ Ready for payments!')
        return true;
    } catch (e) {
        console.warn("Failed to init PaySDK: " + e);
        return false;
    }
}

async function loginSDK() {
    console.log('⌛ Logging in...')
    try {
        await PayNlSdk.loginViaCredentials(A_CODE, SL_CODE, SL_SECRET);

        console.log('✅ Logged in -> Reinitializing')
        return await initPaySDK();
    } catch (e) {
        console.warn("Failed to login PaySDK: " + e);
        return false;
    }
}

const styles = StyleSheet.create({
    amountText: {fontWeight: 'bold', fontSize: 32},
    bottomHalf: {flex: 1, gap: 10, paddingBottom: 30, paddingHorizontal: 25},
    row: {flex: 1, flexDirection: 'row', gap: 10},
    ttpoi: {
        backgroundColor: '#585FFF',
        flex: 1,
        position: 'relative',
        justifyContent: 'center',
        paddingHorizontal: 20,
        borderRadius: 16
    },
    ttpoiDisabled: {backgroundColor: '#999'},
    ttpoiText: {position: 'absolute', left: 0, right: 0, textAlign: 'center', color: 'white', fontWeight: 'bold'},
    iconBase: {borderRadius: 999, width: 50, height: 50, alignItems: 'center', justifyContent: 'center'},
    ticketText: {
        color: '#1D1D1D',
        textAlign: 'justify',
        fontFamily: Platform.select({ios: 'Courier', android: 'monospace'}),
        fontSize: 11,
        lineHeight: 13,
    }
});

export default App;
