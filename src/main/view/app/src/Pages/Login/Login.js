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
        const {id, value} = e.target
        this.state[id] = value
    }

    handleSubmit = async (e) => {
        e.preventDefault()
        console.log(this.state)
        axios.post("http://localhost:8080/patient/login/", this.state)
            .then(function (response) {
                console.log(this)
                console.log("this is the response", response.data);
            })
            .catch(function (error) {
                console.log("this is the error", error);
            });
        this.props.history.push('/appointment')
    }

    render(){
        return(
            <div className={Login}>
                <p> Login </p>
                <br />
                <form onSubmit = {this.handleSubmit}>
                    <input type = "text" id = "email" placeholder= "Email" onChange = {this.handleChange}/>
                    <br />
                    <br />
                    <input type = "password" id = "password" placeholder= "Password" onChange = {this.handleChange}/>
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