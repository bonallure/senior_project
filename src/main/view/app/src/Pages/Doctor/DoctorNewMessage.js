import React from "react";
import NavBarAuthDoc from "../../Components/NavBar/NavBarAuthDoc";
import axios from "axios";
import {withCookies} from "react-cookie";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';

class DoctorNewMessage extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            credentials: props.credentials,
            patients: props.patients,
            doctorId: props.doctorId,
            user: props.user
        };
        this.patientId = "";
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handlePatientChange = (e) => {
        console.log(e.target);
        this.patientId = e.target.value;
        console.log(this.patientId);
    }

    handleChange = (e) => {
        const {id, value} = e.target;
        console.log(e.target);
        this.state[id] = value;
        console.log(id, value);
        console.log(this.state);
    }

    // submit event handler
    async handleSubmit(e) {
        e.preventDefault();
        console.log(this.state);
        const date = new Date();
        const body = {date: date.toLocaleDateString('en-GB').split('/').reverse().join('-'),
            time: new Date().getTime(),
            senderRole: "DOCTOR",
            recipientRole: "PATIENT",
            doctorId: this.state.doctorId,
            patientId: this.patientId,
            message: this.state.message,
            parentMessageId: null
        };
        console.log("body", body);
        const url = "http://localhost:8080/doctor/message";
        const res = await this.props.postData(url, body);
        console.log("response below");
        console.log(res);
        if (res.status === 200) {
            let data = await res.text();
            data = await JSON.parse(data);
            console.log(data);
            this.setState({newMessage: data});
            console.log(this.state.newMessage);
            this.props.history.push("/doctor/messages");
        }
    }

    render() {
        return(
            <div>
                <NavBarAuthDoc props={this.props}/>
                <p> New Appointment </p>
                <br />
                <form>
                    <InputLabel id="patient"> Choose Patient </InputLabel>
                    <Select
                        value={this.patientId}
                        label="Patients"
                        onChange={this.handlePatientChange}
                      >
                        {this.state.patients.map(patient =>(
                            <MenuItem value={patient.id} >{patient.firstName} {patient.lastName}</MenuItem>
                        ))}
                      </Select>
                    <br />
                    <br />
                    <input type="text" id="message" placeholder= "Compose your message" onChange={this.handleChange}/>
                    <br />
                    <br />
                    <br />
                    <button type="submit" onClick={this.handleSubmit}> Submit </button>
                </form>
            </div>
        )
    }
}
export default withCookies(DoctorNewMessage);