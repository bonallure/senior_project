import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import HomeIcon from '@mui/icons-material/Home';

export default function BottomBar() {

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar style={{background: "lightgray"}}>
                    <Typography variant="h8" component="div" align="justify" sx={{ flexGrow: 1 }}>
                        Clinic Online, 2021
                    </Typography>

                    <Button color="inherit" sx={{px:6}} onClick={(e) => {
                        e.preventDefault();
                        window.location.href='/aboutUs';}} >More About Us</Button><br/>
                    <Button color="inherit" sx={{px:6}} onClick={(e) => {
                        e.preventDefault();
                        window.location.href='/careers';}}>Careers</Button><br/>
                </Toolbar>
            </AppBar>
        </Box>
    );
}