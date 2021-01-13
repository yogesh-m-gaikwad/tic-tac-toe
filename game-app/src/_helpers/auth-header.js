import { authenticationService } from '@/_services';

export function authHeader() {
    // return authorization header with jwt token
    const currentUser = authenticationService.currentUserValue;
    if (currentUser && currentUser.access_token) {
        return { Authorization: `Bearer ${currentUser.access_token}` };
    } else {
        return {};
    }
}

export function restAuthHeader() {
    // return authorization header with jwt token
    const currentUser = authenticationService.currentUserValue;
    if (currentUser && currentUser.access_token) {
        return {
            Authorization: `Bearer ${currentUser.access_token}`,
            Accept: 'application/json',
            'Content-Type': 'application/json'
        };
    } else {
        return {};
    }
}