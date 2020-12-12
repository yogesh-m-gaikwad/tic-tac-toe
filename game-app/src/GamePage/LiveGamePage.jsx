import React from 'react';

import { userService, authenticationService } from '../_services';
import Game from './Game';

class LiveGamePage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: authenticationService.currentUserValue,
            game: null
        };
    }

    componentDidMount() {
        userService.getUserGame(this.state.currentUser.user_id).then(
            game => this.setState({ game })
        );
    }

    render() {
        const { currentUser, game } = this.state;
        return (
            <Game />
        );
    }
}

export { LiveGamePage };