import axios from "axios"
import React from "react";
import NavBar from "../Componets/NavBar/NavBar";


class Appointment extends React.Component{
    
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