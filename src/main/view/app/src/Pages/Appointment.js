import axios from "axios"
import React from "react";
import NavBar from "../Componets/NavBar/NavBar";



class Appointment extends React.Component{

    getAllPatientAppointments() {
        console.log(this.state)
        axios.post("http://localhost:8080/patient/login/", this.state)
            .then(function (response) {
                console.log(this)
                console.log("this is the response", response.data);
            })
            .catch(function (error) {
                console.log("this is the error", error);
            });
    }

    render(){
        return(
            <div className = "Appointment">
                <NavBar/>
                <p style = {{textAlign: "left"}}> Appointment </p>
                <div className = "CurrentAppt">
                </div>
                <p style = {{textAlign: "left"}}> Upcoming </p>
            </div>
        );
    }
}

export default Appointment;