import React from 'react';

import { authenticationService, gameService } from '../_services';
import Game from './Game';

class GamePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: authenticationService.currentUserValue,
            ...props.location.state
        }
    }

    render() {
        return (
            <Game lastGame={this.state.lastGame}/>
        );
    }
}

export { GamePage };