import axios from "axios"
import React from "react";
import NavBarAuth from "../Componets/NavBarAuth";
import {alignPropType} from "react-bootstrap/types";
import { Paper } from "@mui/material";
import { padding } from "@mui/system";



class Appointment extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            userId: props.userId,
            appointments: []
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

    getAllPatientAppointments() {
        console.log(this.state)
        const url = "http://localhost:8080/patient/appointments/" + this.state.userId
        console.log(url)
        axios.get(url)
            .then(response =>{
                console.log("this is the response", response.data);
                this.props.handleAppointments(response.data)
            })
            .catch(function (error) {
                console.log("this is the error", error);
            });
    }

    componentDidMount() {
        this.getAllPatientAppointments()
    }

    render(){
        return(
            <div>
                <NavBarAuth/>
                <div className = "Appointment">
                    <button onClick={this.onClick}>New Appointment</button>
                    <p style = {{textAlign: "left"}}> Appointment </p>
                    <div className = "CurrentAppt">
                        /*TODO: Change map function to print out appointments */
                        <Paper elevation={3} style={{padding:'50px 20px',width:600, margin:"20px auto"}}>
                            {appointment.map(appointment =>(
                                <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={appointment.userId}>
                                    Date:{}
                                    Doctor:{}
                                    Link:{}
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