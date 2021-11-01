import axios from "axios"
import React from "react";
import Button from "../../Componets/Button";
import Input from "../../Componets/Input";
import NavBar from "../../Componets/NavBar/NavBar";
import "./Login.css"

class AdminLogin extends React.Component{
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
        const { email, password } = this.state;
        console.log(this.state)
        axios
            .post(
                "http://localhost:8080/systemAdmin/login", {"email": email, "password":password}
            )
            .then(response => {
                if (response.data.email === email) {
                    this.props.handleSuccessfulAuth(response.data);
                }
            })
            .catch(error => {
                console.log("login error", error);
            });
        event.preventDefault();
    }

    render(){
        return(
            <div className={AdminLogin}>
                <NavBar/>
                <p> Login </p>
                <br />
                <form onSubmit={this.handleSubmit}>
                    <input type = "text" id = "email" placeholder= "Email" onChange = {this.handleChange}/>
                    <br />
                    <br />
                    <input type = "password" id = "password" placeholder= "Password" onChange = {this.handleChange}/>
                    <br />
                    <br />
                    <br />
                    <button type="submit"> Login </button>
                </form>
            </div>
        )
    }
}

export default AdminLogin;