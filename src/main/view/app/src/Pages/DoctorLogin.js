import axios from "axios"
import React from "react";
import NavBar from "../Components/NavBar/NavBar";
import "../CSS/doctorLogin.css"
import Footer from "../Components/Footer";
import {Button, TextField} from "@mui/material";
import DoctorNavBar from "../Components/NavBar/DoctorNavBar";
import {Link} from "@material-ui/core";
import {withCookies} from "react-cookie";
import Container from "@mui/material/Container";

class DoctorLogin extends React.Component{

    state = {
        isAuthenticated: false,
        user: undefined
    }

    constructor(props) {
        super(props);
        const {cookies} = props;
        this.state.csrfToken = cookies.get('XSRF-TOKEN');
        this.login = this.login.bind(this);
        //this.logout = this.logout.bind(this);
    }

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

    async login() {
        const {email, password} = this.state;
        const res = await axios.get("http://localhost:8080/doctor/login/" + email, {
            auth: {username: email, password: password}
        });
        if (res.status === 200) {
            console.log(res.data);
            this.state.user = res.data;
            this.state.isAuthenticated = true;
            this.props.handleSuccessfulAuth(res.data);
        } else {
            if (res.status === 404) {
                console.log("user not found");
                this.message = <h2> User not found</h2>;
            }
            if (res.status === 401) {
                console.log("bad password");
                this.message = <h2> Bad credentials</h2>;
            }
        }
        console.log(this.state)
    }
    handleChange = (e) => {
        const {id, value} = e.target
        this.state[id] = value
        console.log("hi")
    }

    render(){

        const errorMessage = this.message

        return(
            <div>
                <DoctorNavBar/>
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
                                onChange={this.handleChange}/>
                            <br />
                            <br />
                            <TextField
                                required
                                label="Required"
                                type="password"
                                variant="filled"
                                id="password"
                                placeholder="Password"
                                onChange={this.handleChange}/>
                            <br />
                            <br />
                            <br />
                            <Button onClick={this.login} variant="contained"> Login </Button>
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