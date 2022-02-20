import React from "react";
import NavBar from "../Components/NavBar/NavBar";
import "../CSS/homepage.css"
import Button from '@mui/material/Button';
import BottomBar from "../Components/BottomBar";
import { withCookies } from 'react-cookie';

class Homepage extends React.Component{
    // declaring state
    state = {
        isLoggedIn: false,
        password: '',
        user: undefined
    }

    loginPatient = (e) => {
        e.preventDefault();
        this.props.history.push("/patient/login")
    }

    loginDoctor = (e) => {
        e.preventDefault();
        this.props.history.push("/doctor/login")
    }

    registerPatient = (e) => {
        e.preventDefault();
        this.props.history.push("/patient/register")
    }
    
    registerDoctor = (e) => {
        e.preventDefault();
        this.props.history.push("/doctor/register")
    }

    render(){
        return(
            <div>
                <NavBar/>
                <section>
                    <div className="float-container1">
                        <div className="float-child1">
                            <h2>Welcome to Clinic Online</h2>
                            <br/>
                            <p>We offers a complete work environment for the doctors,
                                and the portal for patients to access their medical information and interact with their doctors.
                            </p>
                        </div>
                        <div className="float-child2">
                            <br/>
                            <br/> <br/> <br/> <br/> <br/> <br/>
                            <br/>
                            <h2>Login or register</h2>
                            <br/>
                            <Button variant="contained" onClick={this.loginDoctor}>Login as a doctor</Button>
                            <div className="divider"/>
                            <Button variant="contained" onClick={this.loginPatient}>Login as a patient</Button>
                            <br/>
                            <br/>
                            <br/>
                            <Button variant="contained" color="secondary" onClick={this.registerDoctor}>Register as a doctor</Button>
                            <div className="divider"/>
                            <Button variant="contained" color="secondary" onClick={this.registerPatient}>Register as a patient</Button>
                        </div>
                    </div>
                </section>
                <BottomBar/>
            </div>
        );
    };
}

export default Homepage;