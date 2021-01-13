import config from 'config';
import { authHeader, restAuthHeader, handleResponse } from '@/_helpers';

export const gameService = {
    getGameById, getLastGame, addNewGame, updateGameState, requestPair, registerInGamePool
};

function getLastGame(userId) {
    const requestOptions = { method: 'GET', headers: authHeader() };
    return fetch(`${config.apiUrl}/rest/user/game/${userId}/last`, requestOptions)
        .then(handleResponse);
}

function getGameById(gameId) {
    const requestOptions = { method: 'GET', headers: authHeader() };
    return fetch(`${config.apiUrl}/rest/games/${gameId}`, requestOptions)
        .then(handleResponse);
}

function requestPair(userId) {
    const requestOptions = { method: 'GET', headers: authHeader() };
    return fetch(`${config.apiUrl}/rest/user/${userId}/pair`, requestOptions)
        .then(handleResponse);
}

function registerInGamePool(userId) {
    const requestOptions = { method: 'GET', headers: authHeader() };
    return fetch(`${config.apiUrl}/rest/user/${userId}/register`, requestOptions)
        .then(handleResponse);
}

function addNewGame(userId) {
    let data = {
        'userId': userId,
        'gameState' : JSON.stringify({
                        history: [{squares: Array(9).fill(null)}],
                        stepNumber: 0,
                        xIsNext: true
                      }),
        'completed': false
    };

    const requestOptions = {
        method: 'POST',
        headers: restAuthHeader(),
        body: JSON.stringify(data),
    };

    return fetch(`${config.apiUrl}/rest/games`, requestOptions)
        .then(handleResponse);
}

function updateGameState(game, gameState) {
    let data = {
        'userId' : game.userId,
        'createdOn': game.createdOn,
        'completed': game.completed,
        'gameState' : JSON.stringify(gameState),
    };

    const requestOptions = {
        method: 'PUT',
        headers: restAuthHeader(),
        body: JSON.stringify(data),
    };

    return fetch(`${config.apiUrl}/rest/games/${game.gameId}`, requestOptions)
        .then(handleResponse);
}