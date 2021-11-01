import React from "react";
import NavBarAuth from "../Componets/NavBarAuth";
import axios from "axios";

class AppointmentForm extends React.Component{
    constructor(props) {
        super(props);

        this.state = {
            patientId: props.userId,
            doctorId: props.doctorId,
            link: "",
            isConfirmed: false,
            note: ""
        }
    }

    handleChange = (e) => {
        const {id, value} = e.target
        this.state[id] = value
        this.setState(state => {
            return{date: state.date, doctorId: state.doctorId}
        });
        console.log(this.state)
    }

    // submit event handler
    handleAppointmentSubmit = (event) => {
        event.preventDefault();
        console.log(this.state)
        this.setState(state => {
            return{date: state.date, doctorId: state.doctorId}
        });
        axios.post("http://localhost:8080/patient/appointment", {
            date: this.state.date,
            doctorId: this.state.doctorId,
            isConfirmed: false,
            link: "",
            note: "",
            patientId: this.state.patientId}
        )
            .then(response => {
                if (response.data.id !== null) {
                    console.log(response.data.id)
                    this.props.history.push("/appointment");
                }
            })
            .catch(error => {
                console.log("login error", error);
                this.props.history.push("/appointment");
            });
    }

    render() {
        return(
            <div>
                <NavBarAuth/>
                <p> New Appointment </p>
                <br />
                <form>
                    <input type="text" id="doctorId" placeholder= {this.state.doctorId} onChange={this.handleChange}/>
                    <br />
                    <br />
                    <input type="date" id="date" placeholder= "Enter a date" onChange={this.handleChange}/>
                    <br />
                    <br />
                    <br />
                    <button type="submit" onClick={this.handleAppointmentSubmit}> Submit </button>
                </form>
            </div>
        )
    }
}
export default AppointmentForm;