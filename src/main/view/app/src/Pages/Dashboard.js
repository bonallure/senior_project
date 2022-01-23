import React from "react";
import axios from "axios";
import NavBarAuth from "../Components/NavBar/NavBarAuth";
import Card from '@mui/material/Card';
import { CardHeader, CardContent, Grid, Paper, Button } from "@mui/material";
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Box from "@mui/system";
import BottomBar from "../Components/BottomBar";

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
                    <Typography sx={{ fontSize: 14 }} color="text.secondary">Hello Dr. </Typography>
                    <Typography sx={{ fontSize: 14 }} color="text.secondary">Today is {new Date().toLocaleTimeString()}.</Typography>
                    <Button>Clock-In</Button>

                    <div id="fistRow" maxWidth='70%' height>
                        <table id='shifts_table' width={'100%'}>
                            <tr> 
                                <td>
                                    <Card sx={{maxWidth:"90%"}}>
                                        <CardHeader title="My Shits"/>
                                        <Typography sx={{ fontSize: 14 }} color="text.secondary"></Typography>
                                    </Card>
                                    <br></br>
                                </td>
                                <td>
                                    <Card sx={{maxWidth:"90%"}}>
                                        <CardHeader title="Appointment Requests"/>
                                        <Typography sx={{ fontSize: 14 }} color="text.secondary"></Typography>
                                    </Card>
                                    <br></br>
                                </td>
                                <td>
                                    <Card sx={{maxWidth:"90%"}}>
                                        <CardHeader title="New Message"/>
                                        <Typography sx={{ fontSize: 14 }} color="text.secondary"></Typography>
                                    </Card>
                                    <br></br>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <Card sx={{maxWidth:"90%"}}>
                                        <CardHeader title="My Patients"/>
                                        <Typography sx={{ fontSize: 14 }} color="text.secondary"></Typography>
                                    </Card>
                                    <br></br>
                                </td>
                                <td>
                                    <Card sx={{maxWidth:"90%"}}>
                                        <CardHeader title="My Prescriptions"/>
                                        <Typography sx={{ fontSize: 14 }} color="text.secondary"></Typography>
                                    </Card>
                                    <br></br>
                                </td>
                                <td>
                                    <Card sx={{maxWidth:"90%"}}>
                                        <CardHeader title="My Appointments"/>
                                        <Typography sx={{ fontSize: 14 }} color="text.secondary"></Typography>
                                    </Card>
                                    <br></br>
                                </td>
                            </tr>
                        </table>
                    </div>
                </Container>
                <BottomBar/>
            </div>
        )
    }
}

export default Dashboard;