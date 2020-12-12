import React from 'react';
import { history } from '../_helpers';
import { gameService, userService, authenticationService } from '../_services';

class HomePage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: authenticationService.currentUserValue,
            userDetails: null,
            lastGame: null
        };
    }

    componentDidMount() {
        userService.getUser(this.state.currentUser.user_id).then(
            userDetails => this.setState({ userDetails })
        );
    }

    startGame() {
        gameService.addNewGame(this.state.currentUser.user_id).then(
            lastGame => {
                this.setState({ lastGame });
                history.push({pathname: '/game', state: this.state});
            }
        );
    }

    resumeLastGame() {
        gameService.getLastGame(this.state.currentUser.user_id).then(
            lastGame => {
                this.setState({ lastGame });
                history.push({pathname: '/game', state: this.state});
            }
        );
    }

    startOnlineGame() {
        history.push({pathname: '/game/live', state: this.state});
    }

    render() {
        const { currentUser, game } = this.state;
        return (
            <div>
                <div className="container">
                    <div className="game-board row justify-content-md-center">
                        <button className="move-button" onClick={() => this.startGame()}>New Offline Game</button>
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