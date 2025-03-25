/**
 * Converts ISO4217 value to currency symbol
 * @param value - ISO4217 value
 */
export const currencyResolver = (value: string) => {
    switch (value) {
        case 'EUR':
            return '€';
        case 'USD':
            return '$';
        case 'GBP':
            return '£';
        default:
            return value;
    }
}
