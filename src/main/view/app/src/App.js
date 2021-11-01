import React, {Component} from "react";
import Homepage from "./Pages/Homepage";
import NavBar from "./Componets/NavBar/NavBar";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Appointment from "./Pages/Appointment";
import Registration from "./Pages/Registration/Registration";
import Login from "./Pages/Login/PatientLogin";
import axios from "axios";
import Message from "./Pages/Message";
import Calendar from "./Pages/Calendar";
import PatientLogin from "./Pages/Login/PatientLogin";
import AdminLogin from "./Pages/Login/AdminLogin";


export default class App extends Component{
    constructor() {
        super();

        this.state = {
            isLoggedIn: false,
            credentials: {
                email: "",
                password:""
            },
            user: {}
        }
        this.handleLogin = this.handleLogin.bind(this)
        this.handleLogout = this.handleLogout.bind(this)
    }

    handleSuccessfulAuth(user) {
        console.log(user)
        this.handleLogin(user);
        this.history.push("/appointment");
    }

    handleLogoutClick() {
        axios
            .delete("http://localhost:3001/logout", { withCredentials: true })
            .then(response => {
                this.props.handleLogout();
            })
            .catch(error => {
                console.log("logout error", error);
            });
    }

    checkLoginStatus() {
        axios
            .post("http://localhost:8080/patient/login", this.state.credentials)
            .then(response => {
                if (
                    response.data.email === this.state.credentials.email &&
                    this.state.isLoggedIn === false
                ) {
                    this.setState({
                        isLoggedIn: true,
                        user: response.data
                    });
                    this.handleSuccessfulAuth(response.data)

                } else if (
                    response.data.email === "" &&
                    (this.state.isLoggedIn === true)
                ) {
                    this.setState({
                        isLoggedIn: false,
                        user: {}
                    });
                }
            })
            .catch(error => {
                console.log("check login error", error);
            });

    }

    handleLogin(data) {
        this.setState({
            isLoggedIn: true,
            user: data
        })
    }

    handleLogout() {
        this.setState({
            isLoggedIn: false,
            user: {}
        })
    }

    render() {
        return (
            <div>
                <Router>
                    <Switch>
                        <Route
                            exact
                            path={"/"}
                            render={props => (
                                <Homepage
                                    {...props}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/systemAdmin/login"}
                            render={props => (
                                <AdminLogin
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    handleSuccessfulAuth={this.handleSuccessfulAuth}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/patient/login"}
                            render={props => (
                                <PatientLogin
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    handleSuccessfulAuth={this.handleSuccessfulAuth}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/registration"}
                            render={props => (
                                <Registration
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    userId = {this.state.user.id}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/message"}
                            render={props => (
                                <Message
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    userId = {this.state.user.id}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/calendar"}
                            render={props => (
                                <Calendar
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    userId = {this.state.user.id}
                                />
                            )}
                        />
                    </Switch>
                </Router>
            </div>
        )
    }
}
