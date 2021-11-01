import axios from "axios"
import React from "react";
import NavBarAuth from "../Componets/NavBarAuth";
import { Paper } from "@mui/material";


class Appointment extends React.Component{
    constructor(props) {
        super(props);
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
                    <button onClick={this.onClick}>New Appointment</button>
                    <p style = {{textAlign: "left"}}> Appointment </p>
                    <div className = "CurrentAppt">
                        <Paper elevation={3} style={{padding:'50px 20px',width:600, margin:"20px auto"}}>
                            {this.state.appointments.map(appointment =>(
                                <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={appointment.userId}>
                                    {appointment.date}, {appointment.type}, with Doctor whose Id is {appointment.doctorId}
                                </Paper>
                            ))}
                        </Paper>
                    </div>
                    <p style = {{textAlign: "left"}}> Upcoming </p>
                </div>
            </div>
        )
    }
}

export default Appointment;