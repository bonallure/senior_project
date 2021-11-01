import React from "react";  
import NavBarAuth from "../Componets/NavBarAuth";
import axios from "axios";

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
                    <div className = "CurrentAppt">

                    </div>
                </div>
            </div>
        )
    }

}

export default Message;