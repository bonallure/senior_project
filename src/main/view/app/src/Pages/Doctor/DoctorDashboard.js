import React from "react";
import axios from "axios";
import NavBarAuthDoc from "../../Components/NavBar/NavBarAuthDoc";
import {Box, Paper, Button, Card, CardContent, CardActions, CardHeader}  from '@mui/material';
import Typography from '@mui/material/Typography';
import Footer from "../../Components/Footer";
import AddIcon from '@mui/icons-material/Add';
import { margin } from "@mui/system";
import PatientGrid from "../../Components/PatientGrid";


class Dashboard extends React.Component{
    constructor(props){
        super(props)
        const {cookies} = props;
        // this.state.csrfToken = cookies.get('XSRF-TOKEN');
        // console.log(props.user);
        this.state = {
            hasError: false,
            user: props.user,
            userId: props.user.id,
            inbox: props.user.inbox,
            prescriptions: props.user.prescriptions,
            appointments: props.user.appointments,
            patients: props.patients
        };
    }

    updateState = (data) => {
        this.setState(state => {
            return{appointments: data}
        })
    }

    updatePrescriptionState = (data) => {
        this.setState(state => {
            return{prescription: data}
        })
    }

    async getAllAppointments() {
        console.log(this.state)
        const url = "http://localhost:8080/doctor/appointments/" + this.state.user.id
        const response = await axios.get(url);
        try {
            console.log("appointments: ", response.data);
            this.state.appointments = response.data
        } catch (error) {
            console.log("appointments get error", error);
        }
    }

    async getInbox() {
        console.log(this.state)
        const url = "http://localhost:8080/doctor/messages/inbox/" + this.state.user.id;
        const response = await axios.get(url);
        try {
            console.log("inbox :", response.data);
            this.state.inbox = response.data
        } catch (error) {
            console.log("inbox get error", error);
        }
    }

    componentDidMount() {
        this.getInbox();
    }

    /**
     * New Messages / View Messages / Send a Message
    Appointment Requests / Upcoming Appointments / New appointment
    My Patients https://mui.com/components/data-grid/ */

    onClick = (e) => {
        e.preventDefault();
        this.props.history.push(e.target.id);
    }

    render(){

        const today = new Date();

        let newApptReq = 0;
        for (let appointment of this.state.appointments){
            if (appointment.requester !== "DOCTOR" && (! appointment.isConfirmed)){
                console.log(appointment.requester);
                newApptReq += 1;
            }
        }
        const newApptReqMessage = (newApptReq == 1) ? "appointment request" : "appointment requests";

        let upcomingAppt = 0;
        for (let appointment of this.state.appointments){
            if (new Date(appointment.date) > today){
                console.log(appointment.date);
                upcomingAppt ++;
            }
        }
        const upcomingApptMessage = (newApptReq == 1) ? "upcoming appointment" : "upcoming appointments";

        let newMessages = 0;
        for (let message of this.state.inbox){
            if (! message.isRead){
                console.log(message.isRead);
                newMessages += 1;
            }
        }
        const newMessagesText = (newMessages == 1) ? "new message" : "new messages";

        let inboxCount = 0;
        for (let mail of this.state.inbox){
            inboxCount ++;
        }
        const inboxText = (inboxCount == 1) ? "message in your inbox" : "messages in your inbox";

        return(
            <div>
                <NavBarAuthDoc props={this.props}/>
                <div className="dashboard" sx={{justifyContent: "center"}}>
                    <Typography sx={{ fontSize: 14 }} color="text.secondary">Hello Dr. {this.state.user.doctor.lastName} </Typography>
                    <Typography sx={{ fontSize: 14 }} color="text.secondary">Today is {new Date().toLocaleTimeString()}.</Typography>
                    <Button>Clock-In</Button>
                    <Box id="appointmentBox"
                        sx={{
                            display: 'flex',
                            flexWrap: 'wrap',
                            '& > :not(style)': {
                                m: 3
                            },
                            justifyContent: 'center'
                        }}>
                        <Card sx={{ minWidth: 275}}>
                            <CardContent sx={{textAlign: "center"}}>
                                <Typography sx={{ fontSize: 14, mb: 1.5}} color="text.secondary" gutterBottom>
                                    You have have
                                </Typography>
                                <Typography variant="h3" component="span">
                                    {newApptReq}
                                </Typography>
                                <Typography sx={{ mt: 0}} color="text.secondary">
                                    {newApptReqMessage}
                                </Typography>
                            </CardContent>
                            <CardActions>
                                {/* <Button size="small">Learn More</Button> */}
                            </CardActions>
                        </Card>
                        <Card sx={{ minWidth: 275}}>
                            <CardContent sx={{textAlign: "center"}}>
                                <Typography sx={{ fontSize: 14, mb: 1.5 }} color="text.secondary" gutterBottom>
                                    You have have
                                </Typography>
                                <Typography variant="h3" component="span">
                                    {upcomingAppt}
                                </Typography>
                                <Typography sx={{ mb: 0}} color="text.secondary">
                                    {upcomingApptMessage}
                                </Typography>
                            </CardContent>
                            <CardActions>
                                {/* <Button size="small">Learn More</Button> */}
                            </CardActions>
                        </Card>
                        <Card sx={{ minWidth: 275 }}>
                            <CardContent>
                                <Typography sx={{ fontSize: 14 }} color="text.primary" gutterBottom>
                                    <AddIcon sx={{ fontSize: 90 }} color="primary"/>   
                                </Typography>
                            </CardContent>
                            <CardActions sx={{justifyContent: "center"}}>
                                <Button size="small" onClick={this.onClick} id="/doctor/appointment/new">New Appointment Request</Button>
                            </CardActions>
                        </Card>
                    </Box>
                    <Box id="messageBox"
                        sx={{
                            display: 'flex',
                            flexWrap: 'wrap',
                            '& > :not(style)': {
                                m: 3
                            },
                            justifyContent: 'center'
                        }}>
                        <Card sx={{ minWidth: 275}}>
                            <CardContent sx={{textAlign: "center"}}>
                                <Typography sx={{ fontSize: 14, mb: 1.5}} color="text.secondary" gutterBottom>
                                    You have have
                                </Typography>
                                <Typography variant="h3" component="span">
                                    {newMessages}
                                </Typography>
                                <Typography sx={{ mt: 0}} color="text.secondary">
                                    {newMessagesText}
                                </Typography>
                            </CardContent>
                            <CardActions>
                                {/* <Button size="small">Learn More</Button> */}
                            </CardActions>
                        </Card>
                        <Card sx={{ minWidth: 275}}>
                            <CardContent sx={{textAlign: "center"}}>
                                <Typography sx={{ fontSize: 14, mb: 1.5 }} color="text.secondary" gutterBottom>
                                    You have have
                                </Typography>
                                <Typography variant="h3" component="span">
                                    {inboxCount}
                                </Typography>
                                <Typography sx={{ mb: 0}} color="text.secondary">
                                    {inboxText}
                                </Typography>
                            </CardContent>
                            <CardActions>
                                {/* <Button size="small">Learn More</Button> */}
                            </CardActions>
                        </Card>
                        <Card sx={{ minWidth: 275 }}>
                            <CardContent>
                                <Typography sx={{ fontSize: 14 }} color="text.primary" gutterBottom>
                                    <AddIcon sx={{ fontSize: 90 }} color="primary"/>   
                                </Typography>
                            </CardContent>
                            <CardActions sx={{justifyContent: "center"}}>
                                <Button size="small" onClick={this.onClick} id="/doctor/message/new">Send a message</Button>
                            </CardActions>
                        </Card>
                        <PatientGrid patients={this.state.patients}/>
                        <br/>
                        <br/>
                    </Box>
                    <br/>
                    <br/>
                </div>
                <Footer/>
            </div>
        )
    }
}

export default Dashboard;