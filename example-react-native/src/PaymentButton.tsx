import {Platform, StyleSheet, Text, TouchableOpacity} from "react-native";
import Back from './assets/back.svg';
import Start from './assets/start.svg';

interface PaymentButtonProps {
    value: number;
    disabled: boolean;
    onPress?: (value: number) => void;
}

const PaymentButton = (props: PaymentButtonProps) => {
    return <TouchableOpacity style={[styles.button, props.disabled && styles.buttonDisabled]} disabled={props.disabled}
                             onPress={() => props.onPress?.(props.value)}>
        {getContent(props.value)}
    </TouchableOpacity>
}

const getContent = (value: number) => {
    switch (value) {
        case 10:
            return <Back fill="white" width={26} height={26}/>
        case 11:
            return <Text style={styles.text}>0</Text>
        case 12:
            return Platform.select({
                ios: <Text style={styles.text}>00</Text>,
                android: <Start fill="white" width={26} height={26}/>
            })
        default:
            return <Text style={styles.text}>{value}</Text>
    }
}

const styles = StyleSheet.create({
    button: {backgroundColor: '#585FFF', flex: 1, borderRadius: 16, justifyContent: 'center', alignItems: 'center'},
    buttonDisabled: {backgroundColor: '#999'},
    text: {fontSize: 16, fontWeight: 'bold', color: 'white'}
})

export default PaymentButton;
