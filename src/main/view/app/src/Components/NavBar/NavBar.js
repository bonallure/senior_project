import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import HomeIcon from '@mui/icons-material/Home';
import "../../CSS/navBar.css"

export default function NavBar() {

    const toolbarStyle = {
        alignItems: 'flex-start',
        background: "rgba(82,107,190,0.87)",
        paddingTop: 10,
        paddingBottom: 3,
        // Override media queries injected by theme.mixins.toolbar
        '@media all': {
        minHeight: 128,
        }
    }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="fixed">
        <Toolbar style={toolbarStyle}>
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