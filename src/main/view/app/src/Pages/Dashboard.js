import React from "react";
import axios from "axios";
import NavBarAuth from "../Componets/NavBar/NavBarAuth";
import Card from '@mui/material/Card';
import { CardHeader, CardContent, Grid, Paper } from "@mui/material";
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import { Box } from "@mui/system";

class Dashboard extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            userId: "",
            appointment: [],
            prescription: [],
            messages: []
        }
        this.state.userId = props.userId;
    }

    updateState = (data) => {
        this.setState(state => {
            return{appointments: data}
        })
    }

    updatePrescriptionState = (data) => {
        this.setState(state => {
            return{prescription: data}
        })
    }

    getAllPatientAppointments() {
        console.log(this.state)
        const url = "http://localhost:8080/patient/appointments/" + this.state.userId
        console.log(url)
        axios.get(url)
            .then(response =>{
                console.log("this is the response", response.data);
                this.updateState(response.data)
            })
            .catch(function (error) {
                console.log("this is the error", error);
            });
    }

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
                <Container maxWidth="xl" sx={{pt: 2}}>
                    <Card sx={{ minWidth: 160, maxWidth:250 }}>
                        <CardHeader title="Prescriptions"/>
                        <Typography sx={{ fontSize: 14 }} color="text.secondary">
                         get prescriptions method
                        </Typography>     
                    </Card>
                    <Box sx={{pt: 2, position: "relative", left: 400, bottom: 150}}>
                        <Card sx={{ minWidth: 160, maxWidth:250 }}>
                            <CardHeader title="Messages"/>
                            {this.state.messages.map(message =>(
                                    <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={message.userId}>
                                        Recipient:{}
                                        Date:{}
                                        Time:{}
                                        Message:{}
                                    </Paper>
                                ))}
                        </Card>
                    </Box>
                    
                    <p style = {{textAlign: "left"}}> Appointments </p>
                    <Paper elevation={3} style={{padding:'50px 20px',width:'auto', margin:"20px auto"}}>
                        {this.state.appointment.map(appointment =>(
                            <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={appointment.userId}>
                                {appointment.date}, {appointment.type}, with Doctor whose Id is {appointment.doctorId}
                            </Paper>
                        ))}
                    </Paper>
                </Container>
            </div>
        )
    }
}

export default Dashboard;