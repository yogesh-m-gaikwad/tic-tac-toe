import React from 'react';
import { Router, Route, Link } from 'react-router-dom';

import { history } from '@/_helpers';
import { authenticationService } from '@/_services';
import { PrivateRoute } from '@/_components';
import { HomePage } from '@/HomePage';
import { GamePage } from '@/GamePage';
import { LiveGamePage } from '@/LiveGamePage';
import { LoginPage } from '@/LoginPage';

class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: null
        };
    }

    componentDidMount() {
        authenticationService.currentUser.subscribe(x => this.setState({ currentUser: x }));
    }

    logout() {
        authenticationService.logout();
        history.push('/signin');
    }

    render() {
        const { currentUser } = this.state;
        return (
            <Router history={history}>
                <div>
                    {currentUser &&
                        <nav className="navbar navbar-expand navbar-dark bg-dark">
                            <div className="navbar-nav">
                                <Link to="/" className="nav-item nav-link">Home</Link>
                                <a onClick={this.logout} className="nav-item nav-link">Logout</a>
                                <span className="nav-item nav-link">{this.state.currentUser.email}</span>
                            </div>
                        </nav>
                    }
                    <div className="jumbotron page">
                        <div className="container">
                            <div className="row">
                                <div className="col-md-6 offset-md-3">
                                    <PrivateRoute exact path="/" component={HomePage} />
                                    <PrivateRoute exact path="/game/:gameId" component={GamePage} />
                                    <PrivateRoute exact path="/live" component={LiveGamePage} />
                                    <PrivateRoute exact path="/live/:gameId" component={LiveGamePage} />
                                    <Route path="/signin" component={LoginPage} />

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </Router>
        );
    }
}

export { App };