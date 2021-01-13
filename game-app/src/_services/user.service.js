import config from 'config';
import { authHeader, handleResponse } from '@/_helpers';

export const userService = {
    getUser
};

function getUser(user_id) {
    const requestOptions = { method: 'GET', headers: authHeader() };
    return fetch(`${config.apiUrl}/rest/user/${user_id}`, requestOptions)
        .then(handleResponse);
}