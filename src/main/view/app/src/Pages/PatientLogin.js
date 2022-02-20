import axios from "axios"
import React from "react";
import NavBar from "../Components/NavBar/NavBar";
import "../CSS/patientLogin.css"
import Footer from "../Components/Footer";
import {Button, TextField} from "@mui/material";

class PatientLogin extends React.Component{
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: "",
            loginErrors: ""
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange = (e) => {
        const {id, value} = e.target
        this.state[id] = value
        console.log("hi")
    }

    handleSubmit(event) {
        event.preventDefault();
        const { email, password } = this.state;
        console.log(this.state)
        axios.post(
                "http://localhost:8080/patient/login", {"email": email, "password":password}
            )
            .then(response => {
                if (response.data.email === email) {
                    this.props.handleSuccessfulAuth(response.data);
                }
            })
            .catch(error => {
                console.log("login error", error);
            });
    }

    render(){
        return(
            <div>
                <NavBar/>
                <section>
                    <div className="float-container" style={{textAlign:"center"}}>
                        <h2> Login </h2>
                        <br />
                        <form onSubmit={this.handleSubmit}>
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
                            <Button type="submit" variant="contained"> Login </Button>
                        </form>
                    </div>
                </section>
                <Footer/>
            </div>
        )
    }
}

export default PatientLogin;