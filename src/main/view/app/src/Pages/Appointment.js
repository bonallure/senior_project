import axios from "axios"
import React from "react";
import NavBarAuth from "../Componets/NavBarAuth";
import {alignPropType} from "react-bootstrap/types";



class Appointment extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            userId: "",
            appointments: [],
        }
        console.log(this.props.state)
        this.state.userId = props.userId
    }

    onSubmit = (e) => {
        e.preventDefault();
        this.props.history.push("/patient/appointment/new")
    }

    getAllPatientAppointments() {
        console.log(this.state)
        const url = "http://localhost:8080/patient/appointments/" + this.state.userId
        console.log(url)
        axios.get(url)
            .then(function (response) {
                console.log("this is the response", response.data);
                this.state.appointments = response.data
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
                    <button onClick={this.onSubmit}>New Appointment</button>
                    <p style = {{textAlign: "left"}}> Appointment </p>
                    <div className = "CurrentAppt">

                    </div>
                    <p style = {{textAlign: "left"}}> Upcoming </p>
                </div>
            </div>
        )
    }
}

export default Appointment;