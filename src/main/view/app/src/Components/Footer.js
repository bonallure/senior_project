import * as React from 'react';

import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

export default function Footer() {

    const footerStyle = {
        background: "lightgray",
        position: "fixed",
        left: 0,
        bottom: 0,
        width: "100%"
    }

    return (

        <Toolbar sx={footerStyle}>
            <Typography variant="h8" component="div" align="justify" sx={{ flexGrow: 1 }}>
                Clinic Online, 2021
            </Typography>

            <Button color="inherit" sx={{px:6}} onClick={(e) => {
                e.preventDefault();
                window.location.href='/aboutUs';}} >More About Us</Button><br/>
            <Button color="inherit" sx={{px:6}} onClick={(e) => {
                e.preventDefault();
                window.location.href='/careers';}}>Careers</Button><br/>
            <Button color="inherit" sx={{px:6}} onClick={(e) => {
                e.preventDefault();
                window.location.href='/partners';}}>Our Partners</Button><br/>
            <Button color="inherit" sx={{px:6}} onClick={(e) => {
                e.preventDefault();
                window.location.href='/contactUs';}}>Contact Us</Button><br/>

        </Toolbar>

    );
}