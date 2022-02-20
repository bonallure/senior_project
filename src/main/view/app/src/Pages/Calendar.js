import React, { useState } from "react";
import NavBarAuth from "../Components/NavBar/NavBarAuth";
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import isWeekend from 'date-fns/isWeekend';
import TextField from '@mui/material/TextField';
import StaticDatePicker from '@mui/lab/StaticDatePicker';
import { Container, Paper } from "@mui/material";

class Calendar extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            userId: "",
            date: null
        }
        this.state.userId = props.userId;
    }

    //onClick open appointment request
    handleClick() {
        alert('Appointment')
    }

    render() {
        function changeDate(newValue) {
            this.date = newValue;
        }

        const today = new Date();

        return (
            <div>
                <NavBarAuth />
                <Container>
                    <LocalizationProvider dateAdapter={AdapterDateFns}>
                        <StaticDatePicker
                            orientation="landscape"
                            openTo="day"
                            value={today}
                            shouldDisableDate={isWeekend}
                            onChange={(newValue) => {
                                changeDate(newValue);
                            }}
                            renderInput={(params) => (
                                <TextField {...params} helperText={params?.inputProps?.placeholder} />
                            )}
                        />
                    </LocalizationProvider>
                </Container>
            </div>
        )
    }
}

export default Calendar;