import React from "react";
import "../../CSS/doctorLogin.css"
import Footer from "../../Components/Footer";
import {Button, TextField} from "@mui/material";
import NavBar from "../../Components/NavBar/NavBar";
import {withCookies} from "react-cookie";
import Container from "@mui/material/Container";

class DoctorLogin extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            csrfToken: props.csrfToken,
            isLoggedIn: props.isLoggedIn,
            user: props.user,
            credentials: {}
        }
    }

    loginDoctor = async (e) => {
        e.preventDefault();
        await this.props.handleLogin();
        if (this.props.isLoggedIn) {
            this.props.history.push("/doctor/appointments");
        }
    };

    // async componentDidMount() {
    //     const response = await fetch('/api/user', {credentials: 'include'});
    //     const body = await response.text();
    //     if (body === '') {
    //         this.setState(({isAuthenticated: false}))
    //     } else {
    //         this.setState({isAuthenticated: true, user: JSON.parse(body)})
    //     }
    // }

    // login() {
    //     let port = (window.location.port ? ':' + window.location.port : '');
    //     if (port === ':3000') {
    //         port = ':8080';
    //     }
    //     window.location.href = '//' + window.location.hostname + port + '/private';
    // }
    //
    // logout() {
    //     fetch('/api/logout', {method: 'POST', credentials: 'include',
    //         headers: {'X-XSRF-TOKEN': this.state.csrfToken}}).then(res => res.json())
    //         .then(response => {
    //             window.location.href = response.logoutUrl + "?id_token_hint=" +
    //                 response.idToken + "&post_logout_redirect_uri=" + window.location.origin;
    //         });
    // }

    message = "";

    render(){

        const errorMessage = this.message;

        return(
            <div>
                <NavBar/>
                <section className="doctorSection">
                    <div className="float-container" style={{textAlign:"center"}}>
                        <h2> Login </h2>
                        <br />
                        <form>
                            <TextField
                                required
                                label="Required"
                                variant="outlined"
                                id="email"
                                placeholder="Email"
                                onChange={this.props.handleChange}/>
                            <br />
                            <br />
                            <TextField
                                required
                                label="Required"
                                type="password"
                                variant="filled"
                                id="password"
                                placeholder="Password"
                                onChange={this.props.handleChange}/>
                            <br />
                            <br />
                            <br />
                            <Button onClick={this.loginDoctor}
                                    variant="contained">
                                Login
                            </Button>
                            <Container fluid>
                                {errorMessage}
                            </Container>
                        </form>
                    </div>
                </section>
                <Footer/>
            </div>
        )
    }
}

export default withCookies(DoctorLogin);