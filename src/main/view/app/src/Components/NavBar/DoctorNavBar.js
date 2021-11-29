import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import HomeIcon from '@mui/icons-material/Home';
import "./navBar.css"

export default function DoctorNavBar() {


  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar style={{background: "rgba(105,152,199,0.87)"}}>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            <HomeIcon onClick={(e) => {
              e.preventDefault();
              window.location.href='/';}}/>
          </IconButton>
          <Typography variant="h4" component="div" align="justify" sx={{ flexGrow: 1 }}>
            Clinic Online
          </Typography>
          <Button color="inherit" sx={{px:6}} onClick={(e) => {
              e.preventDefault();
              window.location.href='/patient/aboutUs';}} >About Us</Button><br/>
          <Button color="inherit" sx={{px:6}} onClick={(e) => {
              e.preventDefault();
              window.location.href='/patient/login';}} >Login</Button><br/><br/>
          <Button color="inherit" sx={{px:6}} onClick={(e) => {
              e.preventDefault();
              window.location.href='/registration';}}>Register</Button><br/>
        </Toolbar>
      </AppBar>
    </Box>
  );
}