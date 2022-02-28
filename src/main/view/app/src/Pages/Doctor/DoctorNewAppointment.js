import React from "react";
import NavBarAuth from "../../Components/NavBar/NavBarAuth";
import axios from "axios";
import {withCookies} from "react-cookie";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';


class DoctorNewAppointment extends React.Component{
    constructor(props) {
        super(props);

        this.state = {
            credentials: props.credentials,
            patients: props.patients,
            doctorId: props.doctorId,
            link: "",
            isConfirmed: false,
            note: "",
        };
        this.patientId = "";
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
    async handleAppointmentSubmit() {
        const body = {date: this.state.date,
                     doctorId: this.state.doctorId,
                     isConfirmed: false,
                     link: "",
                     note: "",
                     patientId: this.patientId
                     };
        console.log(body);
        const url = "http://localhost:8080/doctor/addAppointment";
        const res = await axios.post(url, body);
//         const res = await this.props.postData(url, body);
        console.log("response below");
        console.log(res);
        console.log("cookie below");
        console.log(document.cookie);
        if (res.status === 200) {
            this.setState({newAppointment: res.data});
        }
    }

    handleNewAppointment = async (e) => {
        e.preventDefault();
        await this.handleAppointmentSubmit();
        if (this.state.newAppointment){
            this.props.history.push("/doctor/appointments");
        }

    }

    render() {
        const names = ["laurent", "abby", "johnny"];
        return(
            <div>
                <NavBarAuth/>
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
                    <input type="date" id="date" placeholder= "Enter a date" onChange={this.handleChange}/>
                    <br />
                    <br />
                    <br />
                    <button type="submit" onClick={this.handleNewAppointment}> Submit </button>
                </form>
            </div>
        )
    }
}
export default withCookies(DoctorNewAppointment);