import React from "react";
import NavBarAuth from "../Components/NavBar/NavBarAuth";
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import isWeekend from 'date-fns/isWeekend';
import TextField from '@mui/material/TextField';
import StaticDatePicker from '@mui/lab/StaticDatePicker';
import { Container } from "@mui/material";

class Calendar extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            userId: "",
            date: new Date()
        }
        this.state.userId = props.userId;
    }

    //onClick open appointment request
    handleClick(){
        alert('Appointment')
    }

    render(){
        return(
            <div>
                <NavBarAuth/>
                <Container>
                    <LocalizationProvider dateAdapter={AdapterDateFns}>
                        <StaticDatePicker
                            orientation="landscape"
                            openTo="day"
                            value={this.state.date}
                            shouldDisableDate={isWeekend}
                            // onChange={(newValue) => {
                            //     setValue(newValue);
                            // }}
                            onclick={this.handleClick}
                            renderInput={(params) => <TextField {...params} />}
                        />
                    </LocalizationProvider>
                </Container>
                <Container id="appointment">

                </Container>
            </div>
        )
    }
}

export default Calendar;