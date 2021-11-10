import React from "react";  
import NavBarAuth from "../Componets/NavBar/NavBarAuth";
import axios from "axios";
import {Paper} from "@mui/material";

class Message extends React.Component{
    constructor(props) {
        super(props)
        this.state = {
            userId: "",
            messages: []
        }
        this.state.userId = props.userId;
    }

    /* TODO: Change url to messages*/
    getAllMessages() {
        console.log(this.state)
        const url = "http://localhost:8080/patient/messages/inbox/" + this.state.userId
        axios.get(url)
            .then(function (response) {
                console.log("this is the response", response.data);
                this.state.messages = response.data
            })
            .catch(function (error) {
                console.log("this is the error", error);
            });
    }

    componentDidMount() {
        this.getAllMessages();
    }

    render(){
        return(
            <div>
                <NavBarAuth/>
                <div className = "messages">
                    <p style = {{textAlign: "left"}}> Messages </p>
                    <div className = "CurrentMessages">
                        <Paper elevation={3} style={{padding:'50px 20px',width:600, margin:"20px auto"}}>
                                {this.state.messages.map(message =>(
                                    <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={message.userId}>
                                        Recipient:{}
                                        Date:{}
                                        Time:{}
                                        Message:{}
                                    </Paper>
                                ))}
                        </Paper>
                    </div>
                </div>
            </div>
        )
    }

}

export default Message;