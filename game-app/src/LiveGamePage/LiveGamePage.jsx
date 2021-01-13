import React from 'react';

import { gameService, authenticationService } from '@/_services';
import LiveGame from './LiveGame';
import Spinner from 'react-bootstrap/Spinner'

class LiveGamePage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: authenticationService.currentUserValue,
            loading: true,
            refreshing: false,
            game: null,
            retries: 0,
            gameStateRetries: 0,
            errorMessage: "",
        };

        this.getGame = this.getGame.bind(this);
        this.getGameState = this.getGameState.bind(this);
    }

    getGame(){
        if( !this.state.game ) {
            gameService.requestPair(this.state.currentUser.user_id).then(
                game => {
                    if (game) {
                        this.setState({ game });
                        this.setState({ loading: false });
                        clearInterval(this.pairInterval);
                    }
                },
                error => {
                    this.setState({ retries: retries + 1 });
                    console.log(error);
                    if ( retries > 10 ) {
                        this.setState({ loading: false });
                        this.setState({ errorMessage: "Maximum retries exceeded during waiting for a pair player." });
                        clearInterval(this.pairInterval);
                    }
                }
            );
        }
    }

    getGameState(){
        if( this.state.game ) {
            gameService.getGameById(this.state.game.gameId).then(
                game => {
                    this.setState({ game });
                    this.setState({ refreshing: false });
                    clearInterval(this.gameInterval);
                },
                error => {
                    this.setState({ gameStateRetries: gameStateRetries + 1 });
                    console.log(error);
                    if ( gameStateRetries > 200 ) {
                        this.setState({ refreshing: false });
                        this.setState({ errorMessage: "Maximum retries exceeded refreshing game state." });
                        clearInterval(this.gameInterval);
                    }
                }
            );
        }
    }

    componentDidMount() {
        // set scheduled intervals
        this.pairInterval = setInterval(this.getGame, 5000);
        this.gameInterval = setInterval(this.getGameState, 1000);
    }

    componentWillUnmount() {
        // Clear the scheduled intervals right before component unmount
        clearInterval(this.pairInterval);
        clearInterval(this.gameInterval);
    }

    render() {
        const { currentUser, game, loading, errorMessage } = this.state;
        return (
        <div className="container">
            { errorMessage &&
                <div className="row justify-content-md-center">
                     <span className="text-danger">{errorMessage}</span>
                </div>
            }
            <LiveGame game={game} live={true}/>
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