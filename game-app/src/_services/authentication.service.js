import { BehaviorSubject } from 'rxjs';
import qs from "qs";
import config from 'config';
import { handleResponse } from '@/_helpers';

const currentUserSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('currentUser')));

export const authenticationService = {
    login,
    logout,
    currentUser: currentUserSubject.asObservable(),
    get currentUserValue () { return currentUserSubject.value }
};

function login(username, password) {
    let data ={
        'scope': 'role_webclient',
        'grant_type': 'password',
        'username': username,
        'password': password
    };
    const requestOptions = {
        method: 'POST',
        headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic YWRtaW5hcHA6cGFzc3dvcmQ='
        },
        body: qs.stringify(data)
    };

    return fetch(`${config.apiUrl}/oauth/token`, requestOptions)
        .then(handleResponse)
        .then(user => {
            // store user details and jwt token in local storage to keep user logged in between page refreshes
            localStorage.setItem('currentUser', JSON.stringify(user));
            currentUserSubject.next(user);
            return user;
        });
}

function logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    currentUserSubject.next(null);
}