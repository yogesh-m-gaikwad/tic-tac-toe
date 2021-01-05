import React from 'react';

import { gameService, authenticationService } from '../_services';
import Game from './Game';
import Spinner from 'react-bootstrap/Spinner'

class LiveGamePage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: authenticationService.currentUserValue,
            loading: true,
            game: null
        };

        this.getGame = this.getGame.bind(this);
    }

    getGame(){
        if( !this.state.game ) {
            gameService.requestPair(this.state.currentUser.user_id).then(
                game => {
                    this.setState({ game });
                    this.setState({ loading: false });
                    clearInterval(this.interval);
                },
                error => {
                    this.setState({ loading: false });
                    clearInterval(this.interval);
                }
            );
        }
    }

    componentDidMount() {
        // set Interval
        this.interval = setInterval(this.getGame, 5000);
    }

    componentWillUnmount() {
        // Clear the interval right before component unmount
        clearInterval(this.interval);
    }

    render() {
        const { currentUser, game, loading } = this.state;
        return (
        <div className="container">
            <Game game={game} />
            { loading &&
                <div className="row justify-content-md-center">
                    <Spinner animation="border" role="status">
                      <span className="sr-only">Loading...</span>
                    </Spinner>
                </div>
            }
        </div>
        );
    }
}

export { LiveGamePage };