import React, { useState } from 'react';
import { AlertDismissible, history } from '@/_helpers';
import { gameService, userService, authenticationService, requestPair } from '@/_services';

class HomePage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: authenticationService.currentUserValue,
            userDetails: null,
            game: null,
            errorMessage: ''
        };
    }

    componentDidMount() {
        userService.getUser(this.state.currentUser.user_id).then(
            userDetails => this.setState({ userDetails })
        );
    }

    startGame() {
        gameService.addNewGame(this.state.currentUser.user_id).then(
            game => {
                if ( game ){
                    this.setState({ game });
                    history.push({pathname: `/game/${game.gameId}`, state: this.state});
                }
            },
            error => {
                this.setState({errorMessage: 'Error starting new game.'});
            }
        );
    }

    resumeLastGame() {
        gameService.getLastGame(this.state.currentUser.user_id).then(
            game => {
                this.setState({ game });
                history.push({ pathname: `/game/${game.gameId}`, state: this.state });
            },
            error => {
                this.setState({errorMessage: 'Error resuming last game.'});
            }
        );
    }

    startOnlineGame() {
        gameService.registerInGamePool(this.state.currentUser.user_id).then(
            gamePool => {
                history.push({pathname: `/live`, state: this.state});
            },
            error => {
                this.setState({errorMessage: 'Error starting online game.'});
            }
        );

    }

    render() {
        const { currentUser, game } = this.state;

        return (
            <div>
                <div className="container">
                    <div className="game-board row justify-content-md-center">
                        {this.state.errorMessage && <AlertDismissible errorMessage={this.state.errorMessage}/>}
                    </div>
                    <div className="game-board row justify-content-md-center">
                        <button className="move-button" onClick={() => this.startGame()}>New Game</button>
                    </div>
                    <div className="game-board row justify-content-md-center">
                        <button className="move-button" onClick={() => this.resumeLastGame()}>Resume Last Game</button>
                    </div>
                    <div className="game-board row justify-content-md-center">
                        <button className="move-button" onClick={() => this.startOnlineGame()}>Start Online Game</button>
                    </div>
                </div>
            </div>
        );
    }
}

export { HomePage };