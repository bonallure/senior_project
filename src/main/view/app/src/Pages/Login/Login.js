import axios from "axios"
import React from "react";
import Button from "../../Componets/Button";
import Input from "../../Componets/Input";
import NavBar from "../../Componets/NavBar/NavBar";
import "./Login.css"

class Login extends React.Component{
    state = {
        email: '',
        password: ''
    }

    handleChange = (e) => {
        const {name, value} = e.target
        this.setState({[name]: value})
    }

    handleSubmit = (e) => {
        e.preventDefault()
    }
    render(){
        return(
            <div>
                <NavBar/>
                <p> Login </p>
                <br />
                <form onSubmit = {this.handleSubmit}>
                    <input type = "text" id = "email" placeHolder = "Email" onChange = {this.handleChange}/>
                    <br />
                    <br />
                    <input type = "text" id = "pwd" placeHolder = "Password"onChange = {this.handleChange}/>
                    <br />
                    <br />
                    <br />
                    <button onSubmit = {this.handleSubmit}> Login </button>
                </form>
            </div>
        )
    }
}

export default Login;