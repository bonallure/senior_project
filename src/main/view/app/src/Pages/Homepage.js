import React from "react";
import NavBar from "../Components/NavBar/NavBar";
import "../CSS/homepage.css"
import Button from '@mui/material/Button';
import Footer from "../Components/Footer";
import Background from "../Components/Images/telepsychology-therapy-zoom.jpg";
import { withCookies } from 'react-cookie';
import {Container, Paper} from "@mui/material";
import {Box} from "@mui/system";
import { styled } from '@mui/system';

class Homepage extends React.Component{
    // declaring state
    state = {
        isLoggedIn: false,
        password: '',
        user: undefined
    };

    loginPatient = (e) => {
        e.preventDefault();
        this.props.history.push("/patient/login")
    };

    loginDoctor = (e) => {
        e.preventDefault();
        this.props.history.push("/doctor/login")
    };

    registerPatient = (e) => {
        e.preventDefault();
        this.props.history.push("/patient/register")
    };
    
    registerDoctor = (e) => {
        e.preventDefault();
        this.props.history.push("/doctor/register")
    };

    render(){

        const leftPaperStyle={
            backgroundColor: "transparent",
            width: 650,
            // maxWidth: '45%',
            height: 500,
            alignSelf: "left",
            position: "relative",
            right: 300,
            left: 10
        };

        const rightPaperStyle={
            backgroundColor: "transparent",
            width: 400,
            // maxWidth: '45%',
            height: 500,
            textAlign: 'center',
            alignContent: 'center',
            alignItems: 'center',
            marginRight: 5,
            marginLeft: 5,
            position: "relative",
            right: 10,
            left: 100
        };

        const containerStyle = {
            width: '100%',
            textAlign: 'center',
            alignContent: 'center',
            alignItems: 'center',
            display: 'flex',
            flexWrap: 'wrap',
            flexGrow: 1,
            flexDirection: 'row',
            // justifyContent: 'flex-end',
            '& > :not(style)': {m: 1},
            marginTop: 10,
            marginRight: 5,
            marginBottom:5
        };

        const loginBoxStyle = {
            alignSelf: 'center',
            textAlign: 'center',
            alignContent: 'center',
            alignItems: 'center',
            display: 'flex',
            flexWrap: 'wrap',
            '& > :not(style)': {
                m: 1}};

        const loginPaperStyle = {
            textAlign: 'center',
            alignContent: 'center',
            alignItems: 'center',
            alignSelf: 'center',
            backgroundColor: "rgba(255, 255, 255, 0.3)",
            lineHeight: '45px',
            width: "60%",
        }
        return(

            <div>
                <NavBar/>
                <br/>
                <Container sx={containerStyle}>
                    <br/>
                    <Paper square={true} sx={leftPaperStyle}>
                        <div className="float-child1">
                            <h2>Welcome to Clinic Online</h2>
                            <br/>
                            <p>Clinic Online allows for communication between the two parties while providing the patient
                                with access to their records and the doctors with a complete virtual office
                                to conduct their daily work.
                            </p>
                        </div>
                    </Paper>

                    <Paper sx={rightPaperStyle}>
                        <div className="float-child2">
                        <br/>
                        <Box sx={loginBoxStyle}>
                            <Paper elevation={3} sx={loginPaperStyle}>
                                <h2>Doctors</h2>
                                <Button variant="contained" onClick={this.loginDoctor}>Login as a doctor</Button>
                                <br/>
                                <Button variant="contained" onClick={this.registerDoctor}>Register as a doctor</Button>
                            </Paper>
                            <Paper elevation={3} sx={loginPaperStyle}>
                                <h2>Patients</h2>
                                <Button variant="contained" color="secondary" onClick={this.loginPatient}>Login as a patient</Button>
                                <br/>
                                <Button variant="contained" color="secondary" onClick={this.registerPatient}>Register as a patient</Button>
                            </Paper>
                        </Box>
                        </div>
                    </Paper>
                </Container>
                <Footer/>
            </div>
        );
    };
}

export default Homepage;