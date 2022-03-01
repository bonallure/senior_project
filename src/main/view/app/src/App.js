import React, {Component} from "react";
import { withCookies } from "react-cookie";
import { Base64 } from 'js-base64';
import Homepage from "./Pages/Homepage";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import axios from "axios";
import AdminLogin from "./Pages/AdminLogin";
import DoctorLogin from "./Pages/Doctor/DoctorLogin";
import DoctorAppointments from "./Pages/Doctor/DoctorAppointments";
import DoctorNewAppointment from "./Pages/Doctor/DoctorNewAppointment";
import DoctorMessages from "./Pages/Doctor/DoctorMessages"
import DoctorRegistration from "./Pages/Doctor/DoctorRegistration";
import DoctorDashboard from "./Pages/Doctor/DoctorDashboard";
import DoctorCalendar from "./Pages/Doctor/DoctorCalendar";
import PatientLogin from "./Pages/Patient/PatientLogin";
import PatientAppointments from "./Pages/Patient/PatientAppointments";
import PatientNewAppointment from "./Pages/Patient/PatientNewAppointment";
import PatientMessages from "./Pages/Patient/PatientMessages"
import PatientRegistration from "./Pages/Patient/PatientRegistration";
import PatientDashboard from "./Pages/Patient/PatientDashboard";
import PatientCalendar from "./Pages/Patient/PatientCalendar";


class App extends Component{
    constructor(props) {
        super(props);
        const {cookies} = props;
        this.state = {
            csrfToken: cookies.get('XSRF-TOKEN'),
            isLoggedIn: false,
            credentials:{},
            user: null,
        };
        this.handleDoctorLogin = this.handleDoctorLogin.bind(this);
        this.postData = this.postData.bind(this);
    }

    componentDidMount(){
        const CSRF_TOKEN = document.cookie.match(new RegExp(`XSRF-TOKEN=([^;]+)`))[1];
        const instance = axios.create({
          headers: { "X-XSRF-TOKEN": CSRF_TOKEN }
        });
        this.setState({csrfToken: CSRF_TOKEN});
        this.setState({instance: instance});
    }

    handleSuccessfulDoctorAuth() {
        console.log("doctor view model: "+ this.state.user);
        this.history.push("/doctor/appointments");
    }

    handleSuccessfulRetrieval(data) {
        console.log(data)
        this.handleAppointments(data);
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

    handleChange = (e) => {
        const {id, value} = e.target;
        this.state.credentials[id] = value;
    }

    //  POST method implementation:
    async postData(url, data) {
      console.log(data);
      // Default options are marked with *
      const response = await fetch(url, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
//         mode: 'cors', // no-cors, *cors, same-origin
//         credentials: 'include', // include, *same-origin, omit
        headers: {
          "Authorization": `Basic ${Base64.encode(`${this.state.credentials.email}:${this.state.credentials.password}`)}`,
          'Content-Type': 'application/json'
        },
//         redirect: 'follow', // manual, *follow, error
//         referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        body: JSON.stringify(data) // body data type must match "Content-Type" header
      });
      return response; // parses JSON response into native JavaScript objects
    }

    async getFetch(theUrl, creds){
        const response = await fetch(theUrl, {
            headers: new Headers({
                "Authorization": `Basic ${Base64.encode(`${creds.email}:${creds.password}`)}`
//                 "content-type": "API-Key"
            }),
//             credentials: 'include'
        });
        return response;
    }

    async handleDoctorLogin() {
        const theUrl = "http://localhost:8080/doctor/login/" + this.state.credentials.email;
        let value = null;
//         const res = await axios.get("http://localhost:8080/doctor/login/" + email, {
//             auth: {username: email, password: password},
//             withCredentials: true
//         });
        const res = await this.getFetch(theUrl, this.state.credentials);
        if (res.status === 200) {
            let body = await res.text();
            body = await JSON.parse(body);
            console.log(body);
            this.setState({user: body});
            this.setState({isLoggedIn: true});
        } else {
            if (res.status === 404) {
                value = 400
                console.log("user not found");
                this.message = <h2> User not found</h2>;
            }
            if (res.status === 401) {
                value = 401
                console.log("bad password");
                this.message = <h2> Bad credentials</h2>;
            }
        }
    }

    handleAppointments(data) {
        this.state.setState({
           appointments: data
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
    /************************* DOCTOR ROUTES ********************************/
                        <Route
                            exact
                            path={"/doctor/login"}
                            render={props => (
                                <DoctorLogin
                                    {...props}
                                    handleChange={this.handleChange}
                                    handleLogin={this.handleDoctorLogin}
                                    handleLogout={this.handleLogout}
                                    handleSuccessfulAuth={this.handleSuccessfulAuth}
                                    isLoggedIn={this.state.isLoggedIn}
                                    user={this.state.user}
                                    checkLoginStatus={this.checkLoginStatus}
//                                     instance={this.state.instance}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/doctor/appointments"}
                            render={props => (
                                <DoctorAppointments
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    handleAppointments={this.handleAppointments}
                                    user={this.state.user}
                                    credentials={this.state.credentials}
                                    getFetch={this.getFetch}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/doctor/appointment/new"}
                            render={props => (
                                <DoctorNewAppointment
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    patients= {this.state.user.patients}
                                    doctorId = {this.state.user.id}
                                    credentials={this.state.credentials}
                                    postData={this.postData}
                                    user={this.state.user}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/doctor/messages"}
                            render={props => (
                                <DoctorMessages
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    user={this.state.user}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/doctor/calendar"}
                            render={props => (
                                <DoctorCalendar
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    user={this.state.user}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/doctor/dashboard"}
                            render={props => (
                                <DoctorDashboard
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    user={this.state.user}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/registration"}
                            render={props => (
                                <DoctorRegistration
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    userId = {this.state.user.id}
                                />
                            )}
                        />
/************************* ADMIN ROUTES ********************************/
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
/************************* PATIENT ROUTES ********************************/
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
                            path={"/patient/appointments"}
                            render={props => (
                                <PatientAppointments
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    userId = {this.state.user.id}
                                    user={this.state.user}
                                    handleAppointments={this.handleAppointments}
                                    appointments = {this.state.appointments}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/patient/appointment/new"}
                            render={props => (
                                <PatientNewAppointment
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    user={this.state.user}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/patient/messages"}
                            render={props => (
                                <PatientMessages
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    user={this.state.user}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/patient/calendar"}
                            render={props => (
                                <PatientCalendar
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    user={this.state.user}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/patient/dashboard"}
                            render={props => (
                                <PatientDashboard
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    user={this.state.user}
                                />
                            )}
                        />
                        <Route
                            exact
                            path={"/registration"}
                            render={props => (
                                <PatientRegistration
                                    {...props}
                                    handleLogin={this.handleLogin}
                                    handleLogout={this.handleLogout}
                                    isLoggedIn={this.state.isLoggedIn}
                                    checkLoginStatus={this.checkLoginStatus}
                                    user = {this.state.user}
                                />
                            )}
                        />
/************************* OTHER ROUTES ********************************/
                    </Switch>
                </Router>
            </div>
        )
    }
}

export default withCookies(App);