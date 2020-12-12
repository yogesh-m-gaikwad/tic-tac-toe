import config from 'config';
import { authHeader, restAuthHeader, handleResponse } from '../_helpers';

export const gameService = {
    getLastGame, addNewGame, updateGameState
};

function getLastGame(userId) {
    const requestOptions = { method: 'GET', headers: authHeader() };
    return fetch(`${config.apiUrl}/rest/user/${userId}/lastgame`, requestOptions)
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