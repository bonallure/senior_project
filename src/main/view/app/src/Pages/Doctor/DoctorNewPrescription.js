import React from "react";
import NavBarAuthDoc from "../../Components/NavBar/NavBarAuthDoc";
import {withCookies} from "react-cookie";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';


class DoctorNewPrescription extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            credentials: props.credentials,
            patients: props.patients,
            doctorId: props.doctorId,
            user: props.user,
            refillable: true
        };
        this.patientId = "";
        if (props.patientId){
            this.patientId = props.patientId;
        }
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

    handleChangeRefillable = (e) => {
        this.setState({refillable: e.target.checked});
        console.log(e.target.checked);
    }

    // submit event handler
    async handleSubmit(e) {
        e.preventDefault();
        console.log(this.state);
        const date = new Date();
        const body = {date_prescribed: date.toLocaleDateString('en-GB').split('/').reverse().join('-'),
            doctorId: this.state.doctorId,
            patientId: this.patientId,
            medicine: this.state.medicine,
            dosage: this.state.dosage,
            quantity: this.state.quantity,
            refillable: this.state.refillable
        };
        console.log("body", body);
        const url = "http://localhost:8080/doctor/prescription";
        const res = await this.props.postData(url, body);
        console.log("response below");
        console.log(res);
        if (res.status === 200) {
            let data = await res.text();
            data = await JSON.parse(data);
            console.log(data);
            this.setState({newPrescription: data});
            console.log(this.state.newMessage);
            this.props.history.push("/doctor/prescription");
        }
    }

    render() {
        return(
            <div>
                <NavBarAuthDoc props={this.props}/>
                <p> New Prescription </p>
                <br />
                <form>
                    <InputLabel id="patient"> Choose Patient </InputLabel>
                    <Select
                        value={this.patientId}
                        label="Patients"
                        onChange={this.handlePatientChange}
                      >
                        {this.state.patients.map(patient =>(
                            <MenuItem exclusive value={patient.id} >{patient.firstName} {patient.lastName}</MenuItem>
                        ))}
                    </Select>
                    <br />
                    <br />
                    <TextField required id="medicine" label="Required" placeholder="Medicine Name" onChange={this.handleChange}/>
                    <TextField required id="dosage" label="Required" placeholder="Dosage" onChange={this.handleChange}/>
                    <TextField required id="quantity" type="number" label="Required" defaultValue="Quantity" onChange={this.handleChange}/>
                    <FormControlLabel id="refillable" control={<Checkbox defaultChecked />} label="Refillable" onChange={this.handleChangeRefillable}/>
                    <br />
                    <br />
                    <br />
                    <button type="submit" onClick={this.handleSubmit}> Submit </button>
                </form>
            </div>
        )
    }
}
export default withCookies(DoctorNewPrescription);