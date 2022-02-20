import axios from "axios"
import React from "react";
import NavBarAuth from "../Components/NavBar/NavBarAuth";
import { Grid, Paper } from "@mui/material";
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import {withCookies} from "react-cookie";


class Appointment extends React.Component{
    state = {
        user: undefined
    }

    constructor(props) {
        super(props);
        const {cookies} = props;
        this.state.csrfToken = cookies.get('XSRF-TOKEN');
        this.state = {
            userId: props.userId,
            appointments: [],
            doctorName: ""
        }
    }

    onClick = (e) => {
        e.preventDefault();
        this.props.history.push("/patient/appointment/new")
    }

    updateState = (data) => {
        this.setState(state => {
            return{appointments: data}
        })
    }

    updateDoctorState = (data) => {
        this.setState(state => {
            return{doctorName: data.lastName}
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

    getDoctor() {
        const url = "http://localhost:8080/doctor/13"
        axios.get(url)
            .then(response =>{
                console.log("this is the response", response.data);
                this.updateDoctorState(response.data)
            })
            .catch(function (error) {
                console.log("this is the error", error);
            });
    }

    componentDidMount() {
        this.getAllPatientAppointments()
        // this.getDoctor()
    }

    render(){
        return(
            <div>
                <NavBarAuth/>
                <div className = "Appointment">
                    <Box sx={{pt: 2, textAlign: 'right'}}> 
                        <Button variant="contained"  onClick={this.onClick}>New Appointment</Button>
                    </Box>
                    <Container maxWidth="xl">
                        <p style = {{textAlign: "left"}}> Upcoming Appointments </p>
                        <div className = "CurrentAppt">
                            <Paper elevation={3} style={{padding:'50px 20px',width:'auto', margin:"20px auto"}}>
                                {this.state.appointments.map(appointment =>(
                                    <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={appointment.userId}>
                                        {appointment.date}, {appointment.type}, with Doctor whose Id is {appointment.doctorId}
                                    </Paper>
                                ))}
                            </Paper>
                        </div>
                        <p style = {{textAlign: "left"}}> Past Appointments </p>
                        <div className = "CurrentAppt">
                            <Paper elevation={3} style={{padding:'50px 20px',width:'auto', margin:"20px auto"}}>
                                {this.state.appointments.map(appointment =>(
                                    <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={appointment.userId}>
                                        {appointment.date}, {appointment.type}, with Doctor whose Id is {appointment.doctorId}
                                    </Paper>
                                ))}
                            </Paper>
                        </div>
                    </Container>
                </div>
            </div>
        )
    }
}

export default withCookies(Appointment);