import React from "react";  
import NavBarAuthDoc from "../../Components/NavBar/NavBarAuthDoc";
import axios from "axios";
import {Paper} from "@mui/material";

class DoctorMessages extends React.Component{
    constructor(props) {
        super(props)
        const {cookies} = props;
        // this.state.csrfToken = cookies.get('XSRF-TOKEN');
        // console.log(props.user);
        this.state = {
        //     hasError: false,
            user: props.user,
            outbox: props.user.outbox,
            inbox: props.user.inbox
        };
    }

    /* TODO: Change url to messages*/
    async getInbox() {
        console.log(this.state)
        const url = "http://localhost:8080/doctor/messages/inbox/" + this.state.user.id;
        await axios.get(url)
            .then(function (response) {
                console.log("this is the response", response.data);
                this.state.inbox = response.data
            })
            .catch(function (error) {
                console.log("this is the error", error);
            });
    }

    async getOutbox() {
        console.log(this.state)
        const url = "http://localhost:8080/doctor/messages/outbox/" + this.state.user.id;
        await axios.get(url)
            .then(function (response) {
                console.log("this is the response", response.data);
                this.state.outbox = response.data
            })
            .catch(function (error) {
                console.log("this is the error", error);
            });
    }

    componentDidMount() {
        this.getInbox();
        this.getOutbox();
    }

    render(){
        return(
            <div>
                <NavBarAuthDoc props={this.props}/>
                <div className = "messages">
                    <p style = {{textAlign: "left"}}> Messages </p>
                    <div className = "CurrentMessages">
                        <Paper elevation={3} style={{padding:'50px 20px',width:600, margin:"20px auto"}}>
                                {/* {this.state.messages.map(message =>(
                                    <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={message.userId}>
                                        Recipient:{}
                                        Date:{}
                                        Time:{}
                                        Message:{}
                                    </Paper>
                                ))} */}
                        </Paper>
                    </div>
                </div>
            </div>
        )
    }

}

export default DoctorMessages;