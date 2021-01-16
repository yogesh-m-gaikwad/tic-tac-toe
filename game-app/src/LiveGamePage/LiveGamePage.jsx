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
        this.updateGameState = this.updateGameState.bind(this);
        this.clearTimers = this.clearTimers.bind(this);
    }

    getGame(){
        if ( !this.state.game ) {
            gameService.requestPair(this.state.currentUser.user_id).then(
                game => {
                    if (game) {
                        if (game.gameState && typeof game.gameState == "string") {
                            game.gameState = JSON.parse(game.gameState);
                        }
                        this.setState({ game, loading: false });                                                
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

    isMyTurn (game, userId) {
        if ( game.xIsNext) {
            if ( game.userX == userId ){
                return true;    
            }
        }
        else {
            if ( game.userX != userId ){
                return true;    
            }
        }


        return false;
    }

    getGameState() {
        if ( this.state.game ) {
            gameService.getGameById(this.state.game.gameId).then(
                game => {                        
                    if (game.gameState && typeof game.gameState == "string") {
                        game.gameState = JSON.parse(game.gameState);
                    }

                    if ( this.state.game.gameState.stepNumber != game.gameState.stepNumber ) {
                        this.setState({ game, refreshing: false });
                    }

                    //clearInterval(this.gameInterval);
                },
                error => {
                    this.setState({ gameStateRetries: gameStateRetries + 1 });
                    console.log(error);
                    if ( gameStateRetries > 200 ) {
                        this.setState({ refreshing: false });
                        this.setState({ errorMessage: "Maximum retries exceeded refreshing game state." });
                        //clearInterval(this.gameInterval);
                    }
                }
            );
        }
    }

    updateGameState(game, gameStateUpdate){
        this.setState({ game: game }, () => {            
            // Save game state to db after state updates
            gameService.updateGameState(game, gameStateUpdate).then(
                console.log('Game state update posted!')
            );
        });
    }

    componentDidMount() {
        // set scheduled intervals
        this.pairInterval = setInterval(this.getGame, 5000);
        this.gameInterval = setInterval(this.getGameState, 1200);
    }

    componentWillUnmount() {
        // Clear the scheduled intervals right before component unmount
        this.clearTimers();
    }

    clearTimers() {
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
            { game &&
                <LiveGame game={game} live={true} updateGameState={this.updateGameState} clearTimers={this.clearTimers} />
            }
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