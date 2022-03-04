import React from "react";  
import NavBarAuthDoc from "../../Components/NavBar/NavBarAuthDoc";
import axios from "axios";
import {Paper} from "@mui/material";
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';


class DoctorPrescriptions extends React.Component{
    constructor(props) {
        super(props)
        const {cookies} = props;
        // this.state.csrfToken = cookies.get('XSRF-TOKEN');
        // console.log(props.user);
        this.state = {
        //     hasError: false,
            user: props.user,
            prescriptions: props.user.prescriptions
        };
        this.patientId = null;
        this.getPrescriptions = this.getPrescriptions.bind(this);
        this.getPatientPrescriptions = this.getPatientPrescriptions.bind(this);
    }

    async getPrescriptions() {
        console.log(this.state)
        const url = "http://localhost:8080/patient/prescriptions/" + this.state.user.id;
        const response = await axios.get(url);
        try {
            console.log("this is the response", response.data);
            this.state.prescriptions = response.data
        } catch (error) {
            console.log("this is the error", error);
        }
    }

    async getPatientPrescriptions() {
        console.log(this.state)
        const url = "http://localhost:8080/patient/prescriptions/patient/" + this.patientId;
        const response = await axios.get(url);
        try {
            console.log("this is the response", response.data);
            this.setState({prescriptions: response.data});
        } catch (error) {
            console.log("this is the error", error);
        }
    }

    componentDidMount() {
        this.getPrescriptions();
    }

    onClick = (e) => {
        e.preventDefault();
        this.props.history.push("/patient/prescription/new")
    }

    handlePatientChange = (e) => {
        console.log(e.target);
        const value = e.target.value;
        if (Number.isInteger(value)){
            this.patientId = e.target.value;
            console.log(this.patientId);
            this.getPatientPrescriptions();
        }
        else
            this.getPrescriptions();
    }

    render(){
        return(
            <div>
                <NavBarAuthDoc props={this.props}/>
                <div className = "prescriptions">
                    <Box sx={{pt: 2, pr: 2, textAlign: 'right'}}>
                        <Button variant="contained"  onClick={this.onClick}>New Prescription</Button>
                    </Box>
                    <p style = {{textAlign: "left"}}> Prescriptions </p>
                    <Select
                        value={this.patientId}
                        placeholder="Select a patient"
                        onChange={this.handlePatientChange}
                      >
                        {this.state.user.patients.map(patient =>(
                            <MenuItem value={patient.id} >{patient.firstName} {patient.lastName}</MenuItem>
                        ))}
                    </Select>
                    <div className = "allPrescriptions">
                        <Paper elevation={3} style={{padding:'50px 20px',width:600, margin:"20px auto"}}>
                                {this.state.prescriptions.map(prescription =>(
                                    <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={prescription.id}>
                                        Medicine:{prescription.medicine}
                                        Date:{prescription.date_prescribed}
                                        Dosage:{prescription.dosage}
                                    </Paper>
                                ))}
                        </Paper>
                    </div>
                </div>
            </div>
        )
    }
}

export default DoctorPrescriptions;