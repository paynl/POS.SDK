import {StyleSheet, TouchableOpacity, useWindowDimensions} from 'react-native';
import Animated, {clamp, interpolate, SharedValue, useAnimatedStyle} from 'react-native-reanimated';

export const Backdrop = (props: { onTouch?: () => void; animationProgress: SharedValue<number> }) => {
    const {height} = useWindowDimensions();

    const backdropStyle = useAnimatedStyle(() => {
        const cValue = clamp(props.animationProgress.value, -1, 0);

        const v = interpolate(cValue, [-1, 0], [0, 0.5]);
        const offset = clamp(interpolate(cValue, [-1, 0], [height, 0]), 0, height);

        return {
            opacity: v,
            top: offset > height - 5 ? height : 0,
        };
    }, []);

    const pressBackdrop = () => {
        props?.onTouch?.();
    };

    return (
        <Animated.View style={[styles.backdrop, backdropStyle]}>
            <TouchableOpacity activeOpacity={1} onPress={() => pressBackdrop()} style={styles.sizeFull}/>
        </Animated.View>
    );
};

const styles = StyleSheet.create({
    backdrop: {backgroundColor: '#000', position: 'absolute', left: 0, right: 0, bottom: 0},
    sizeFull: {height: '100%', width: '100%'},
});
