import React from 'react';
import Board from './Board';
import { authenticationService, gameService } from '@/_services';

class LiveGame extends React.Component {
    constructor(props) {
        super(props);
        let gameState = {
            history: [{ squares: Array(9).fill(null)}],
            xIsNext:  true,
            stepNumber: 0,
        };

        if (this.props.game && this.props.game.gameState) {
            gameState = JSON.parse(this.props.game.gameState);
        }

        let gameId = null;
        if (this.props.game && this.props.game.gameId) {
            gameId = this.props.game.gameId;
        }

        this.state = {
            currentUser: authenticationService.currentUserValue,
            game: this.props.game,
            gameId: gameId,
            history: gameState.history,
            stepNumber: gameState.history ? gameState.history.length - 1 : 0,
            xIsNext: gameState.history ? !gameState.history[gameState.history.length - 1].xIsNext : true,
        };
    }

    componentDidUpdate() {
        if (!this.state.game) {
            if (this.props.game && this.props.game.gameId) {
                let gameState = {
                    history: [{ squares: Array(9).fill(null)}],
                    xIsNext:  true,
                    stepNumber: 0,
                };

                if (this.props.game.gameState) {
                    gameState = JSON.parse(this.props.game.gameState);
                }

                this.setState( {
                    game: this.props.game,
                    gameId: this.props.game.gameId,
                    history: gameState.history,
                    stepNumber: gameState.stepNumber
                });
            }
        }
        else {
            const history = this.state.history.slice(0, this.state.stepNumber + 1);
            const current = history[this.state.stepNumber];
            const squares = current.squares.slice();

            if (!this.state.game.completed) {
                if (calculateWinner(squares)) {
                    const gameUpdated = {...this.state.game, completed: true};
                    this.setState( {
                        game: gameUpdated
                    });
                }
            }
        }
    }

    handleClick(i) {
        const history = this.state.history.slice(0, this.state.stepNumber + 1);
        const current = history[this.state.stepNumber];
        const squares = current.squares.slice();
        if (calculateWinner(squares) || squares[i]) {
            return;
        }

        squares[i] = this.state.xIsNext ? 'X' : 'O';
        this.setState({
            history: history.concat([{
                squares: squares,
            }]),
            stepNumber: history.length,
            xIsNext: !this.state.xIsNext,
        }, () => {
            // Update game state to db

            const gameStateUpdate = {
                history: this.state.history,
                stepNumber: this.state.stepNumber,
                xIsNext: this.state.xIsNext
            };

            gameService.updateGameState(this.state.game, gameStateUpdate).then(
                console.log('Game state update posted!')
            );
        });
    }

    jumpTo(step) {
        this.setState({
            stepNumber: step,
            xIsNext: (step % 2) === 0,
        });
    }

    render() {
        const history = this.state.history;
        const current = history[this.state.stepNumber];
        const winner = calculateWinner(current.squares);

        const moves = history.map((step, move) => {
            const desc = move ?
                'Go to move #' + move :
                'Go to game start';
            return (
                <li key={move}>
                    <button className="move-button" onClick={() => this.jumpTo(move)}>{desc}</button>
                </li>
            );
        });

        if (winner) {
            status = 'Winner: ' + winner;
        } else {
            status = 'Next player: ' + (this.state.xIsNext ? 'X' : 'O');
        }

        return (
            <div>
                <div className="container">
                    <div className="game-board row justify-content-md-center">
                        <Board
                            squares={current.squares}
                            onClick={(i) => this.handleClick(i)}
                        />
                    </div>
                </div>
                <div className="container game-info">
                    <div className="row justify-content-md-center">
                        <div>{status}</div>
                    </div>
                </div>
                <div className="container game-info">
                    <div className="game-moves row justify-content-md-center">
                        <ol>{moves}</ol>
                    </div>
                </div>
            </div>
        );
    }
}

function calculateWinner(squares) {
    const lines = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6],
    ];

    for (let i = 0; i < lines.length; i++) {
        const [a, b, c] = lines[i];
        if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
            return squares[a];
        }
    }

    return null;
}

export default LiveGame;
