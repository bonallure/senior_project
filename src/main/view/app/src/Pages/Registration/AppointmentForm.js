import React from "react";
import NavBarAuth from "../Componets/NavBarAuth";
import AdminLogin from "../Login/AdminLogin";
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
    }

    // submit event handler
    handleSubmit(event) {
        axios
            .post(
                "http://localhost:8080/patient/appointment", this.state
            )
            .then(response => {
                if (response.data.id !== nulll) {
                    console.log(response.data.id)
                    this.props.history.push("/patient/appointment");
                }
            })
            .catch(error => {
                console.log("login error", error);
            });
        event.preventDefault();
    }

    render() {
        return(
            <div>
                <NavBarAuth/>
                <p> New Appointment </p>
                <br />
                <form onSubmit={this.handleSubmit}>
                    <input type = "text" id = "doctor" placeholder= {this.state.doctorId} onChange = {this.handleChange}/>
                    <br />
                    <br />
                    <input type = "date" id = "date" placeholder= "Enter a date" onChange = {this.handleChange}/>
                    <br />
                    <br />
                    <br />
                    <button type="submit"> Submit </button>
                </form>
            </div>
        )
    }
}
export default AppointmentForm;