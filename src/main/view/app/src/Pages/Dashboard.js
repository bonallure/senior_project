import React from "react";
import NavBarAuth from "../Componets/NavBar/NavBarAuth";
import Card from '@mui/material/Card';
import { CardHeader, CardContent, Grid, Paper } from "@mui/material";
import Container from '@mui/material/Container';

class Dashboard extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            userId: "",
            appointment: []
        }
        this.state.userId = props.userId;
    }
    render(){
        return(
            <div>
                <NavBarAuth/>
                <Container maxWidth="xl">
                    <p style = {{textAlign: "left"}}> Schedule </p>
                    <Card sx={{size: "small"}}>
                        
                    </Card>
                    <p style = {{textAlign: "left"}}> Referrals </p>
                    <Card>

                    </Card>
                    
                    <p style = {{textAlign: "left"}}> Appointments </p>
                    <Paper elevation={3} style={{padding:'50px 20px',width:'auto', margin:"20px auto"}}>
                        {this.state.appointment.map(appointment =>(
                            <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={appointment.userId}>
                                {appointment.date}, {appointment.type}, with Doctor whose Id is {appointment.doctorId}
                            </Paper>
                        ))}
                    </Paper>
                </Container>
            </div>
        )
    }
}

export default Dashboard;