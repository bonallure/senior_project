import React from "react";  
import NavBarAuthDoc from "../../Components/NavBar/NavBarAuthDoc";
import axios from "axios";
import {Paper} from "@mui/material";
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';

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
            inbox: props.user.inbox,
            currentBox: "inbox",
            messages: []
        };
        this.getOutbox = this.getOutbox.bind(this);
        this.getInbox = this.getInbox.bind(this);
    }

    async getInbox() {
        console.log(this.state)
        const url = "http://localhost:8080/doctor/messages/inbox/" + this.state.user.id;
        const response = await axios.get(url);
        try {
            console.log("this is the response", response.data);
            this.state.inbox = response.data
        } catch (error) {
            console.log("this is the error", error);
        }
    }

    async getOutbox() {
        console.log(this.state)
        const url = "http://localhost:8080/doctor/messages/outbox/" + this.state.user.id;
        const response = await axios.get(url);
        try {
            console.log("this is the response", response.data);
            this.state.outbox = response.data
        } catch (error) {
            console.log("this is the error", error);
        }
    }

    componentDidMount() {
        this.getInbox();
        this.getOutbox();
    }

    onClick = (e) => {
        e.preventDefault();
        this.props.history.push("/doctor/message/new")
    }

    handleChange = (event, newBox) => {
        if (newBox !== null) {
            this.setState({currentBox: newBox});
        }
      };

    render(){
        const messages = (this.state.currentBox == "inbox") ? this.state.inbox : this.state.outbox;

        return(
            <div>
                <NavBarAuthDoc props={this.props}/>
                <div className = "messages">
                    <Box sx={{pt: 2, pr: 2, textAlign: 'right'}}>
                        <Button variant="contained"  onClick={this.onClick}>New Message</Button>
                    </Box>
                    <p style = {{textAlign: "left"}}> Messages </p>
                    <ToggleButtonGroup
                        color="primary"
                        value={this.state.currentBox}
                        exclusive
                        onChange={this.handleChange}
                    >
                        <ToggleButton value="inbox">Inbox</ToggleButton>
                        <ToggleButton value="outbox">Outbox</ToggleButton>
                    </ToggleButtonGroup>
                    <div className = "CurrentMessages">
                        <Paper elevation={3} style={{padding:'50px 20px',width:600, margin:"20px auto"}}>
                                {messages.map(message =>(
                                    <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={message.userId}>
                                        Recipient:{}
                                        Date:{message.date}
                                        Time:{message.time}
                                        Message:{message.message}
                                    </Paper>
                                ))}
                        </Paper>
                    </div>
                </div>
            </div>
        )
    }

}

export default DoctorMessages;