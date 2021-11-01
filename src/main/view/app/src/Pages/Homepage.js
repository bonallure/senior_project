import React from "react";
import NavBar from "../Componets/NavBar/NavBar";
import axios from "axios";
import {Redirect} from "react-router";
import Login from "./Login/Login";

class Homepage extends React.Component{
    // declaring state
    state = {
        isLoggedIn: "",
        password: ''
    }

    onSubmit = (e) => {
        e.preventDefault();
        this.props.history.push("/login")
    }

    render(){
        return(
            <div>
                <NavBar/>
                <div className="mainPaper">
                    <p> Welcome to Clinic Online </p>
                    <button onClick={this.onSubmit}>Login</button>
                </div>
            </div>
        )
    }
}

export default Homepage;